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




            Swal.fire({
              title: "Are you sure?",
              text: "You won't be able to revert this!",
              icon: "warning",
              showCancelButton: true,
              confirmButtonColor: "#3085d6",
              cancelButtonColor: "#d33",
              confirmButtonText: "Yes, delete it!",
              cancelButtonText: "No, cancel!"
            }).then((result) => {
              if (result.isConfirmed) {


                const card = `cardType=${this.typeCard}&cardColor=${this.colorCard}`
                axios.post("/api/clients/current/cards", card)
                .then(response =>{ 
                  Swal.fire({
                    title: "Created!",
                    text: "Your card has been created.",
                    icon: "success"
                     });
                     setTimeout(() => {
                       location.href ="/web/cards.html";
                    }, 1800);
                  
                 
                 
                  
                })
                
                .catch(error => 
                  Swal.fire({
                    icon: "error",
                    title: "Oops...",
                    text: `${error.response.data}`,
                  
                  })
  
                  
                  )
               
              }
            });

         


        }
     

  }





}).mount('#app')