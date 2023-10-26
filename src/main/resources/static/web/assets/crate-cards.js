const { createApp } = Vue

createApp({
  data() {
    return {
      typeCard:'',
      colorCard:'',

    }
  },

  methods:{
      


          createCard(){

          const card = `cardType=${this.typeCard}&cardColor=${this.colorCard}`
            axios.post("/api/clients/current/cards", card)


        }
     

  }





}).mount('#app')