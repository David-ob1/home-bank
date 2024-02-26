const { createApp } = Vue

  createApp({
    data() {
      return {
        accounts:[],
        client:{},
        loans:[],
        accountType:"",
      
      }
    },
    created(){
     axios.get("/api/clients/current")
      .then(response => 
        this.getData()
        )


        axios.get('http://localhost:8080/api/clients/current',{headers:{'accept':'application/xml'}}).then(response =>

 console.log(response.data))
    },

    methods:{
        formatNumber(number) {
            return number
                .toFixed(2)
                .replace(/./g, ",")
                .replace(/\B(?=(\d{3})+(?!\d))/g, ".");
        },

        clientLogOut(){
          axios.post("/api/logout")
          .then(response =>  {
              console.log("sign out!")
              location.href ="login.html"
          }).catch(error => console.log(error))
      },

      createAccount(account){
        axios.post("/api/clients/current/accounts",`accountType=${account}`)
        .then(response =>{
          Swal.fire({
            position: "top-end",
            icon: "success",
            title: "the account has been created",
            showConfirmButton: false,
            timer: 1500
          });

          this.getData()
       }
          
          
          
          )
        .catch(error => console.log(error))
      },

      getData(){

        axios.get("/api/clients/current")
        .then(response => {
          apiRestP = response
          console.log(apiRestP)
          
          this.client = response.data;
          console.log(this.client)
  
  
          this.accounts = this.client.accounts
          console.log(this.accounts)
  
          this.loans = this.client.loans
          console.log(this.loans)
       }).catch(error => console.log(error))


      },

      deleteAccount(id) {
        console.log(id)
        Swal.fire({
            title: 'Do you want to delete account?',
            text: 'This action cannot be reversed',
            showCancelButton: true,
            cancelButtonText: 'Cancell',
            confirmButtonText: 'Yes',
            confirmButtonColor: '#28a745',
            cancelButtonColor: '#dc3545',
            showClass: {
              popup: 'swal2-noanimation',
              backdrop: 'swal2-noanimation'
            },
            hideClass: {
              popup: '',
              backdrop: ''
        }, preConfirm: () => {
          console.log(id)
          axios.patch(`/api/clients/current/accounts`, `accountId=${id}`)
            .then(() => {
                Swal.fire({
                    title: "Successfully delete account",
                    icon: "success",
                    confirmButtonColor: "#3085d6",
                  }).then((result) => {
                    if (result.isConfirmed) {

                      Swal.fire({
                        title: "the card has been deleted",
                        showClass: {
                          popup: `
                            animate__animated
                            animate__fadeInUp
                            animate__faster
                          `
                        },
                        hideClass: {
                          popup: `
                            animate__animated
                            animate__fadeOutDown
                            animate__faster
                          `
                        }
                      }),
                      
                        location.href  = `accounts.html`;
                    }
                  });    
            })
            .catch(error => {
              console.log(error)
                Swal.fire({
                  icon: 'error',
                  text: error.response.data,
                  confirmButtonColor: "#7c601893",
                });
        });
        },
    })
}


    }


  }).mount('#app')