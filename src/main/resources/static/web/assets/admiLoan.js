const { createApp } = Vue

createApp({
  data() {
    return {
      paymets:null,
      interest:null,
      amountMax:null,
      NameLoan:null,

    }

  },

  methods:{


    clientLogOut(){
        axios.post("/api/logout")
        .then(response =>  {
            console.log("sign out!")
           
        })
    },

    createLoan(){
        const loan = {
              nameLoan:this.NameLoan,
              amountMax:this.amountMax,
              interest:this.interest ,      
              paymets:[this.paymets]
        
        }

        axios.post("/api/newLoan", loan)
        .then(

            Swal.fire({
                position: "top-end",
                icon: "success",
                title: "Your loan has been saved",
                showConfirmButton: false,
                timer: 1300 
              })

              
        )
        .catch(error => console.log(error))

    }

  }

  
}).mount('#app')