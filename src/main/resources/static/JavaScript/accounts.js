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

    }// Aca termina Methods
}).mount('#app')