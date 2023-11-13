const { createApp } = Vue;

createApp({
  data() {
    return {
      accounts: {},
      type: "",
      accountOrigin: "",
      accountDestiny: "",
      amount: 0,
      description: "",
    };
  },

  created() {
    // axios
    //   .get("/api/clients/current/accounts")
    //   .then((response) => {
    //     this.accounts = response.data;
    //     console.log(this.accounts);
    //   })
    //   .catch((error) => {
    //     console.log(error);
    //   });
   
  },

  methods: {
   



    generateTransfer() {
      const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
          confirmButton: "btn btn-success",
          cancelButton: "btn btn-danger",
        },
        buttonsStyling: false,
      });
      swalWithBootstrapButtons
        .fire({
          title: "Are you sure?",
          text: "You won't be able to revert this!",
          icon: "warning",
          showCancelButton: true,
          confirmButtonText: "Yes,confirm",
          cancelButtonText: "No, cancel!",
          reverseButtons: true,
        })
        .then((result) => {
          if (result.isConfirmed) {
            axios
              .post(
                `/api/clients/current/transfer`,
                `amount=${this.amount}&description=${this.description}&accountOrigin=${this.accountOrigin}&accountDestiny=${this.accountDestiny}`
              )
              .then(() => {
                swalWithBootstrapButtons.fire({
                  title: "succesful transaction !",
                  text: "the transaction was completed.",
                  icon: "success",
                });
                setTimeout(() => {
                  location.href = "/web/transfers.html";
                }, 1900);
              })

              .catch((error) => {
                alert(error);
                console.log(error);
              });
          } else if (
            /* Read more about handling dismissals below */
            result.dismiss === Swal.DismissReason.cancel
          ) {
            swalWithBootstrapButtons.fire({
              title: "Cancelled",
              text: "the transaction was not carried out :)",
              icon: "error",
            });
          }
        });
    },

    destinationType(){
      if(this.type == "own"){
        console.log(this.type)
        return "d-block"

      }

      if(this.type == "other"){
        console.log(this.type)
        return "d-block"
      }else{
       alert ("hola")
      }


    }
  },
}).mount("#app");
