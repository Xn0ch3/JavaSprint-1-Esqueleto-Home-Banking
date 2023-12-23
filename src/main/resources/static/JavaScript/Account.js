const { createApp } = Vue

const app = createApp({
    data() {
        return {
            transactions: [],
            id: null,
            clients: [],
            account: []

        }
    },
    created() {
        const search = location.search
        const params = new URLSearchParams(search)
        this.id = params.get('id')
        this.loadData()
        this.loadTrans()
        this.formatBudget()
    },
    methods: {
        loadTrans() {
            axios.get("/api/accounts/" + this.id + "/transactions")
                .then(response => {
                    this.transactions = response.data;
                    console.log("Transactions" , this.transactions);
                })
                .catch(error => {
                    console.error("Error fetching data:", error);
                });
        },
        loadData() {
            axios.get("/api/clients/current")
                .then(response => {
                    this.clients = response.data
                    console.log("Clientes", this.clients)
                    this.account = response.data.accounts.find(account=> account.id == this.id)
                    console.log("Cuentas" , this.account)
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