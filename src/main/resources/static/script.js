  const { createApp } = Vue

  createApp({
    data() {
      return {
       name:"",
       lastName:"",
       mail: "",
       userData:[],
       json:"",
      }
    },
    created(){
     axios.get("/rest/clients")
     .then(response => {
        apiRestP = response.data._embedded.clients
        console.log(apiRestP)
        this.json = response.data
        this.userData = apiRestP

     })
     .catch(error => console.log(error))
        

    },
    methods:{
        addUser(){
            const clientData = {
                name:this.name,
                lastName:this.lastName,
                mail:this.mail
            }

            axios.post("/rest/clients",clientData)
            .then(response =>{
                console.log(response)



            })
            .catch(error => console.log(error))

        }
        
    }
  }).mount('#app')