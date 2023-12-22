const app = Vue.createApp({
    data() {
        return {
            data: [],
            name: "",
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
        signin(event){
            event.preventDefault()
            axios.post("/api/login?email=" + this.email + "&password=" + this.password)
            .then(response => {
                console.log("response", response)
                if(response.status == 200){
                    window.location.href="./pages/accounts.html"
                }
            })
            .catch(error => console.log(error))
        },
        signup(){
            axios.post("/api/clients?firstName=" + this.firstName + "&lastName=" + this.lastName + "&email=" + this.email + "&password=" + this.password)
                .then(response => {
                    console.log("registered" + this.email);
                    console.log(response.data);
                    this.login();   
                })
                .catch(error => console.log(error))
        },

        logout(event){
            event.preventDefault()
            axios.get("/api/logout")
            .then(response => {
                console.log("logout", response)
                window.location.href="src/main/resources/static/index.html"
            })
            .catch(error => console.log(error))
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
