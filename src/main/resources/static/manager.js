const { createApp } = Vue

const app = createApp({
    data() {
        return {
            clients: [],
            data: [],
            name: "",
            lastname: "",
            email: "",

        }
    },
    created() {
        this.loadData()
    },
    methods: {
        loadData() {
            axios.get("/clients")
                .then(response => {
                    this.data = response
                    this.clients = response.data._embedded.clients
                })
                .catch(error => console.log(error))
        },

        createClient(event) {
            event.preventDefault()
            axios.post("/clients", {
                "name": this.name,
                "lastname": this.lastname,
                "email": this.email
            })
                .then(response => {
                    this.data = response
                    this.loadData()
                    this.cleanInputs()
                })
                .catch(error => console.log(error))
        }
    }// Aca termina Methods
}).mount('#app')