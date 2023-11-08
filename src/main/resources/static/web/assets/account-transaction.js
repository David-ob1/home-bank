const { createApp } = Vue

  createApp({
    data() {
      return {
        account:{},
        transactions:{},
        id:null,

      
      }
    },
    created(){

       let parametro = location.search
        let params = new URLSearchParams(parametro)
        console.log(params)

        // let idAccount = params.get("id")
        this.id = params.get("id")
        // console.log(idAccount)

      // let urlParams = new URLSearchParams(location.search);
      //   let id = urlParams.get('id')
      
     axios.get(`/api/clients/current/accounts`)
      .then(response => {
     
        this.account = response.data.find(account => account.id == this.id);
        console.log(this.account)

        this.transactions = this.account.transactions
        this.transactions.sort((a,b) => b.id - a.id);

        console.log(this.transactions)


     })
    },

    methods:{
      clientLogOut(){
               axios.post("/api/logout")
               .then(response =>  {
                   console.log("sign out!")
                   location.href ="login.html"
               })
           }
     
     
         }




  }).mount('#app')