const { createApp } = Vue

  createApp({
    data() {
      return {
        cards:{},
        debitCards:[],
        creditCards:[],
        accounts:{},
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
      
     axios.get("/api/clients/1")
     
      .then(response => {
     
        this.account = response.data;
        console.log(this.account)

        this.cards = this.account.cards
        console.log(this.cards)

        this.debitCards = this.cards.filter(card => card.type == "DEBIT")
        this.creditCards =  this.cards.filter(card => card.type == "CREDIT")

        console.log(this.debitCards)
     })
    }




  }).mount('#app')