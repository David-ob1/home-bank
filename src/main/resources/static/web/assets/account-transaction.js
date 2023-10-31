const { createApp } = Vue

  createApp({
    data() {
      return {
        accounts:{},
        transactions:{}
      
      }
    },
    created(){

       let parametro = location.search
        let params = new URLSearchParams(parametro)
        console.log(params)

        let idAccount = params.get("id")
        console.log(idAccount)

      // let urlParams = new URLSearchParams(location.search);
      //   let id = urlParams.get('id')
      
     axios.get(`/api/accounts/${idAccount}`)
      .then(response => {
     
        this.account = response.data;
        console.log(this.account)

        this.transactions = this.account.transactions

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