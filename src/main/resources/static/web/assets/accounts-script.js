const { createApp } = Vue

  createApp({
    data() {
      return {
        accounts:[],
        client:{},
        loans:[]
      
      }
    },
    created(){
     axios.get("/api/clients/current")
      .then(response => 
        this.getData()
        )
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

      createAccount(){
        axios.post("/api/clients/current/accounts")
        .then(response =>{
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
       })


      }


    }


  }).mount('#app')