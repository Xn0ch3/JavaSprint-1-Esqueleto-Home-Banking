const app = Vue.createApp({
    data() {
        return {
            data: [],
            firstname: "",
            lastname: "",
            email: "",
            password: "",
            isSignUpActive: false,
            modalVisible: false
        };
    },
    created() {
        
    },
    methods: {
        signin(){
            axios.post("/api/login?email=" + this.email + "&password=" + this.password)
            .then(response => {
                console.log("response", response)
                if(response.status == 200){
                    window.location.href="./pages/accounts.html"
                }
            })
            .catch(error => {
                console.log(error);
                // Código para manejar el error
                if (error.response) {
                    // El servidor respondió con un código de estado fuera del rango 2xx
                    Swal.fire({
                        icon: 'error',
                        title: `Error de respuesta: ${error.response.data.message}`,
                        text: error.response.status || 'Error desconocido',
                        footer: `<pre>${JSON.stringify(error.response.data, null, 2)}</pre>`
                    });
                } else if (error.request) {
                    // La solicitud fue hecha, pero no se recibió respuesta
                    Swal.fire({
                        icon: 'error',
                        title: 'Error de solicitud',
                        text: 'No se recibió respuesta del servidor'
                    });
                } else {
                    // Ocurrió un error al configurar la solicitud
                    Swal.fire({
                        icon: 'error',
                        title: 'Error general',
                        text: error.message
                    });
                }
            });
        },
        signup(){
            const requestBody ={
                "firstname": this.firstname,
                "lastname": this.lastname,
                "email": this.email,
                "password": this.password
            }
            axios.post("/api/clients" , requestBody)
                .then(response => {
                    console.log("Register" , response.data);
                    this.signin();
                    this.createdAccount();
                })
                .catch(error => {
                    console.log(error);
                    // Código para manejar el error
                    if (error.response) {
                        // El servidor respondió con un código de estado fuera del rango 2xx
                        Swal.fire({
                            icon: 'error',
                            title: `Error de respuesta: ${error.response.status}`,
                            text: error.response.data.message || 'Error desconocido',
                            footer: `<pre>${JSON.stringify(error.response.data, null, 2)}</pre>`
                        });
                    } else if (error.request) {
                        // La solicitud fue hecha, pero no se recibió respuesta
                        Swal.fire({
                            icon: 'error',
                            title: 'Error de solicitud',
                            text: 'No se recibió respuesta del servidor'
                        });
                    } else {
                        // Ocurrió un error al configurar la solicitud
                        Swal.fire({
                            icon: 'error',
                            title: 'Error general',
                            text: error.message
                        });
                    }
                });
        },

        logout(event){
            event.preventDefault()
            axios.get("/api/logout")
            .then(response => {
                console.log("logout", response)
                window.location.href="src/main/resources/static/index.html"
            })
            .catch(error => {
                // Código para manejar el error
                if (error.response) {
                    // El servidor respondió con un código de estado fuera del rango 2xx
                    Swal.fire({
                        icon: 'error',
                        title: `Error de respuesta: ${error.response.status}`,
                        text: error.response.data.message || 'Error desconocido',
                        footer: `<pre>${JSON.stringify(error.response.data, null, 2)}</pre>`
                    });
                } else if (error.request) {
                    // La solicitud fue hecha, pero no se recibió respuesta
                    Swal.fire({
                        icon: 'error',
                        title: 'Error de solicitud',
                        text: 'No se recibió respuesta del servidor'
                    });
                } else {
                    // Ocurrió un error al configurar la solicitud
                    Swal.fire({
                        icon: 'error',
                        title: 'Error general',
                        text: error.message
                    });
                }
            });
        },

        createdAccount(){
            axios.post("/api/clients/current/accounts")
                .then(response => {
                    console.log("Cuenta Creada", response)
                    this.listAccounts.push(response.data);
                    console.log("listAccount", this.listAccounts)
                    // window.location.href="src/main/resources/static/pages/accounts.html"
                    
                })
        },

        getFirstname(event){
            this.firstname = event.target.value
            console.log("firstname", this.firstname)
        },
        getLastname(event){
            this.lastname = event.target.value
            console.log("lastname", this.lastname)
        },

        getEmail(event){
            this.email = event.target.value
            console.log("Email", this.email)
        },

        getPassword(event){
            this.password = event.target.value
            console.log("password" , this.password)
        },
    

        togglePassword(){
            const x = document.getElementById("passwordSignIn");
            const y =document.getElementById("passwordSignup");
            const eye = document.getElementById("togglePassword");
            eye.classList.toggle("fa-eye-slash");
            const currentInputType = x.getAttribute("type");
            if (currentInputType === "password") {
                x.setAttribute("type", "text");
                y.setAttribute("type", "text");
            } else {
                x.setAttribute("type", "password");
                y.setAttribute("type", "password");
            }
        },



        togglePanel() {
            this.isSignUpActive = !this.isSignUpActive;
        },
        showModal() {
            this.modalVisible = true;
        },
    
        closeModal() {
            this.modalVisible = false;
        },

        signUpButton(){
            container.classList.add("right-panel-active")
        },

        signInButton(){
            container.classList.remove("right-panel-active")
        }

        

    },
}).mount('#app');

// Event listeners movidos fuera del objeto Vue.js
// const signUpButton = document.getElementById('signUp');
// const signInButton = document.getElementById('signIn');
// const container = document.getElementById('container');

// signUpButton.addEventListener('click', () => {
//     container.classList.add("right-panel-active");
// });

// signInButton.addEventListener('click', () => {
//     container.classList.remove("right-panel-active");
// });
