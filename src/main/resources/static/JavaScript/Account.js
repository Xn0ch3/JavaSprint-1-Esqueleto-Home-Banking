const { createApp } = Vue

const app = createApp({
    data() {
        return {
            clients: [],
            listAccounts: [],

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
                console.log(this.clients)
                this.listAccounts = response.data.listAccount
                console.log(this.listAccounts)
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
        }
    }// Aca termina Methods
}).mount('#app')