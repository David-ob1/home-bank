const { createApp } = Vue

  createApp({
    data() {
      return {
        cards:{},
        debitCards:[],
        creditCards:[],
        client:{},
        transactions:{},
      
      }
    },
    created(){

    //    let parametro = location.search
    //     let params = new URLSearchParams(parametro)
    //     console.log(params)

    //     let idParametro = params.get("id")
    //     console.log(idParametro)

      // let urlParams = new URLSearchParams(location.search);
      //   let id = urlParams.get('id')
      
     axios.get("/api/clients/current")
     
      .then(response => {


        this.client = response.data;
        console.log(this.client)

        this.cards = this.client.cards
        console.log(this.cards)

        this.debitCards = this.cards.filter(card => card.type == "DEBIT")
        this.creditCards =  this.cards.filter(card => card.type == "CREDIT")

       
     })
    },
    methods:{
      clientLogOut(){
               axios.post("/api/logout")
               .then(response =>  {
                   console.log("sign out!")
                   location.href ="http://localhost:8080"
               })
           }
     
     
         }




  }).mount('#app')