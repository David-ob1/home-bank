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
            .then(response =>{ location.pathName ="/web/cards.html"
                alert("datos enviados")})
            
            .catch(error => console.log(error))


        }
     

  }





}).mount('#app')