const { createApp } = Vue

const app = createApp({
    data() {
        return {
            clients: [],
            listAccounts: [],
            loans: []

        }
    },
    created() {
        this.loadData()
        this.formatBudget()
    },
    methods: {
        loadData() {
            axios.get("/api/clients/current")
                .then(response => {
                    this.clients = response.data
                    console.log("Clients", this.clients)
                    this.listAccounts = response.data.accounts
                    console.log("listAccount", this.listAccounts)
                    this.loans = response.data.clientLoans
                    console.log("Loans", this.loans)
                })
                .catch(error => console.log(error))
        },

        createdAccount() {
            axios.post("/api/clients/current/accounts")
                .then(response => {
                    console.log("Cuenta Creada", response)
                    this.listAccounts.push(response.data);
                    console.log("listAccount", this.listAccounts)
                    this.loadData
                })
                .catch(error => {
                    // Código para manejar el error
                    if (error.response) {
                        // El servidor respondió con un código de estado fuera del rango 2xx
                        Swal.fire({
                            background: "linear-gradient(to right, #191970, #00BFFF) no-repeat 0 0 / cover",
                            color: "white",
                            icon: 'error',
                            title: 'Dear customer, we must inform you:',
                            text: `${JSON.stringify(error.response.data, null, 2)}`,
                            footer:  `Error de respuesta: ${error.response.status}`
                        });
                    }
                })
            },
                    formatBudget(balance) {
                        if (balance !== undefined && balance !== null) {
                            return balance.toLocaleString("en-US", {
                                style: "currency",
                                currency: "ARS",
                                minimumFractionDigits: 0,
                            });
                        }
                    },
                    logout() {
                        axios.post("/api/logout")
                            .then(response => {
                                console.log("LogOut", response)
                                window.location.href = "/index.html"
                            })
                    },
                    closeSeccion() {
                        Swal.fire({
                            background: "linear-gradient(to right, #191970, #00BFFF) no-repeat 0 0 / cover",
                            color: "white",
                            title: "Are you sure?",
                            text: "You won't be able to revert this!",
                            icon: "warning",
                            showCancelButton: true,
                            confirmButtonColor: "#3085d6",
                            cancelButtonColor: "#d33",
                            confirmButtonText: "Yes, close it!"
                        }).then((result) => {
                            if (result.isConfirmed) {
                                Swal.fire({
                                    background: "linear-gradient(to right, #191970, #00BFFF) no-repeat 0 0 / cover",
                                    color: "white",
                                    title: "Close Session!",
                                    text: "We are waiting for you soon in our homebanking.",
                                    icon: "success",
                                    // timer: 3000,
                                });
                                setTimeout(() => {
                                    this.logout();
                                }, 3000);
                            }
                        });
                    }


                }// Aca termina Methods
}).mount('#app')