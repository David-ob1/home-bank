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
    this.clientLogOut()

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

            .catch(error => 
              Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "error when entering",
                // footer: '<a href="#">Why do I have this issue?</a>'
              })

            )
    },


    clientLogOut(){
        axios.post("/api/logout")
        .then(response =>  {
            console.log("sign out!")
           
        })
    }
  }

  
}).mount('#app')