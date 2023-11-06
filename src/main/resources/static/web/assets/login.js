const { createApp } = Vue

createApp({
  data() {
    return {
      email:"",
      password:""
    }

  },

  methods:{

    clientLog(){

console.log("datos")

      let check = true
      let msn = ""

      if(this.email == ""|| this.email == " " ){
          msn += `el campo email esta vacio <br> `
          check = false
      }

      if(this.password == ""|| this.password == " "){
        msn += `contraseña no valida <br>`
        check = false
      }

    
      if(check != true ){
        alert(msn)
        return false
        
      }

        let clientLogin = `email=${this.email}&password=${this.password}`
        axios.post("/api/login", clientLogin)
            .then(response =>{
                console.log("sign in!")
                location.href = "/web/accounts.html"
                console.log("paso")
            })

            .catch(error => alert("User not found"))
    },


    clientLogOut(){
        axios.post("/api/logout")
        .then(response =>  {
            console.log("sign out!")
            location.href ="/web/login.html"
        })
    }
  }

  
}).mount('#app')