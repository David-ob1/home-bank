const { createApp } = Vue

createApp({
  data() {
    return {
      mail:"",
      password:""
    }

  },

  methods:{

    clientLog(){
        const clientLogin = `mail=${this.mail}&password=${this.password}`
        axios.post("/api/login", clientLogin)
            .then(response =>{
                console.log("sign in!")
                location.pathName ="http://localhost:8080/web/accounts.html"
            })

            .catch(error => alert("User not found"))
    },

    clientLogOut(){
        axios.post("/api/logout")
        .then(response =>  {
            console.log("sign out!")
            location.href ="http://localhost:8080"
        })
    }
  }

  
}).mount('#app')