const { createApp } = Vue

createApp({
  data() {
    return {
        client:{},
      userName:"",
      loans:{},

      accounts:{},
      loanSelected:0,

      idAccount:null,
      idLoan:null,
   
      payments:[],
      typeLoan:"",
      // paymnet:0,
      amount:null,
      accountDestiny:'',
      //cuotas seleccionas por el cliente
    
     
    }
  },

  
    created() {
        axios.get("/api/clients/current")
            .then(response => {
              console.log(response)
                this.client = response.data;
                this.loans = this.client.loans;
                console.log( this.loans )
                this.userName = response.data.email;
                this.accounts = response.data.accounts;
                console.log(this.accounts)

                
            })
            .catch(error => {
              
                console.log(error);
            });


  },

  methods:{

    formatNumber(number) {
        console.log(number)
        return number
            .toFixed(2)
            .replace(/./g, ",")
            .replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    },



  payLoan(){
    Swal.fire({
        title: "Are you sure?",
        text: "You won't be able to revert this!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes ",
        cancelButtonText: "No "
      }).then((result) => {
        if (result.isConfirmed) {


    const param = `idLoan=${this.idLoan}&idAccount=${this.idAccount}&amount=${this.amount}` ;
    console.log(this.idLoan)
    console.log(this.idAccount)
    console.log(this.amount)
    
  axios.post(`/api/loans/payments`, param)
            .then(() => {

                
                location.href = "pay-loan.html";})
            .catch(error => Swal.fire({
              icon: "error",
              title: "Oops...",
              text: error.response.data,
            }));

        }})
    }
}





}).mount('#app')