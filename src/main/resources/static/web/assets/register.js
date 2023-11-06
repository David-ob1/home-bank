const { createApp } = Vue

createApp({
  data() {
    return {
      name:"",
      lastName:"",
      email:"",
      password:""
    }

  },

  methods:{

    register(){
    // if(!validate(this.name,this.lastName,this.email,this.password)) {
    //   Swal.fire({
    //     icon: 'error',
    //     title: 'Oops...',
    //     text: msn,
    //     footer: '<a href="">Why do I have this issue?</a>'
    //   })
    //   return false

    // }
      
    // if(check != true ){
    //   Swal.fire({
    //     icon: 'error',
    //     title: 'Oops...',
    //     text: msn,
    //     footer: '<a href="">Why do I have this issue?</a>'
    //   })
    //   return false
      
    // }


      console.log(this.email)
        const clientRegister = `name=${this.name}&lastName=${this.lastName}&email=${this.email}&password=${this.password}`
        axios.post("/api/clients", clientRegister)
            .then(response =>{
                console.log("register")
                
                let clientLogin = `email=${this.email}&password=${this.password}`
                axios.post("/api/login", clientLogin)
                    .then(response =>{
                        console.log("sign in!")
                        location.href = "accounts.html"
                        console.log("paso")
                    }).catch(error => error)



                location.href ="/web/accounts.html"
                console.log(response)


            })
            // .then(

            //   let clientLogin = `email=${this.email}&password=${this.password}`

            // )

            .catch(error => console.log(error))



    },

    clientLogOut(){
        axios.post("/api/logout")
        .then(response =>  {
            console.log("sign out!")
            location.href ="http://localhost:8080"
        })
    },

    validate(name,lastName,email,password){
      let status = true


      return status
    }


  }

  
}).mount('#app')


    //   let check = true
    //   let msn = "";
    //   // let formatString = /\w/
    //   let format = /\w+@\w\.+[a-z]/
    //   // \w alfanimerico + @ y \. que es un punto (. es un caracter especial)
    //   //se declara que debe contener estrictamente

    //   if(this.name == ""|| this.name == " " ){
    //       msn .push('el campo email esta vacio') 
    //       check = false
    //   }else if(this.name.length > 30){
    //     msn += "el nombre es muy largo"
    //     check = false
    //   }
      
    //   if (!isNaN(this.name)){
    //     msn += "el nombre no debe ser un numero"
    //     check = false
    //   }


    //   if(this.lastName == ""|| this.lastName == " "){
    //     msn += `Nombre no valido`
    //     check = false
    //   }
    //   else if(this.name.length > 35){
    //     msn+="el apellido es muy largo"
    //     check = false

    //   }
    //    if (!isNaN(this.lastName)){
    //     msn+="el apellido no debe ser un numero"
    //     check = false

    //   }



    // //   if(this.email == ""|| this.email == " " ){
    // //     msn += `el campo email esta vacio`
    // //     check = false
    // // }else if (!format.test(this.email)){
    // //   msn += `el correo no es valido`
    // //   check = false
    // // }




    // if(this.password == ""|| this.password == " "){
    //   msn += `contraseña no valida`
    //   check = false
    // }

      