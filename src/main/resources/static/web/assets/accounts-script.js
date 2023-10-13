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
     axios.get("/api/clients/1")
      .then(response => {
        apiRestP = response
        console.log(apiRestP)
        
        this.client = response.data;
        console.log(this.client)


        this.accounts = this.client.accounts
        console.log(this.accounts)

        this.loans = this.client.loans
     })
    },

    methods:{
        formatNumber(number) {
            return number
                .toFixed(2)
                .replace(/./g, ",")
                .replace(/\B(?=(\d{3})+(?!\d))/g, ".");
        }


    }


  }).mount('#app')