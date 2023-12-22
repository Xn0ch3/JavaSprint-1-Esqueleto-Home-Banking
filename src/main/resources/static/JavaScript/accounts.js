const { createApp } = Vue

const app = createApp({
    data() {
        return {
            clients: [],
            listAccounts: [],
            loans:[]

        }
    },
    created() {
        this.loadData()
        this.formatBudget()
    },
    methods: {
        loadData() {
            axios.get("/api/clients/1")
            .then(response => {
                this.clients = response.data
                console.log("Clients",this.clients)
                this.listAccounts = response.data.accounts
                console.log("listAccount",this.listAccounts)
                this.loans = response.data.clientLoans
                console.log("Loans",this.loans)
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
        logout(){
            axios.post("/api/logout")
                .then(response => {
                    console.log(response)
                    if (response.status == 200) {
                        window.location.href = "./login.html"
                    }
                })
        },
    }// Aca termina Methods
}).mount('#app')