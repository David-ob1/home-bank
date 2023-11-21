const { createApp } = Vue

createApp({
  data() {
    return {
      userName:"",
      accounts:{},
      paymentMethods:{},
      loanSelected:0,
      loanId:0,
      payments:[],
      typeLoan:"",
      // paymnet:0,
      amount:0,
      accountDestiny:'',
      description:"",
      //cuotas seleccionas por el cliente
      dues:0
     
    }
  },

  
    created() {
        axios.get("/api/clients/current")
            .then(response => {
              console.log(response)
                this.userName = response.data.email;
                this.accounts = response.data.accounts;
                console.log(this.accounts)

                
            })
            .catch(error => {
              
                console.log(error);
            });




            axios.get("/api/loans")
            .then(responseLoans => {
                this.paymentMethods = responseLoans.data;
                console.log("metodos de pagos" + this.paymentMethods)

              this.payments = this.paymentMethods.payments


                
            })
            .catch(error => {
              
                console.log(error);
            });

            

  },

  methods:{
    applyLoan(){
        
        // console.log("amount " + this.amount)
      
        // console.log("accountOrigin " + this.accountOrigin)

        // console.log("accountDestiny " +this.accountDestiny)

        // console.log("description " +this.description)payments,destinationAccount

          const userData = {"idLoan":`${this.loanId}`,"amount":`${this.amount}`,"payments":`${this.dues}`,"destinationAccount":`${this.accountDestiny}`}
            axios.post(`/api/loans`, userData)
            .then(() => {location.href = "/web/loan-application.html";})
            .catch(error => Swal.fire({
              icon: "error",
              title: "Oops...",
              text: error.response.data,
            }));

  }

}





}).mount('#app')