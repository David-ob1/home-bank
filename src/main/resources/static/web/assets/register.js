const { createApp } = Vue

createApp({
  data() {
    return {
      name:"",
      lastName:"",
      mail:"",
      password:""
    }

  },

  methods:{

    register(){
        const clientLogin = `name=${this.name}&lastName=${this.lastName}&mail=${this.mail}&password=${this.password}`
        axios.post("/api/clients", clientLogin)
            .then(response =>{
                console.log("register")
                location.pathName ="http://localhost:8080/web/accounts.html"
            })

            .catch(error => alert("error"))
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