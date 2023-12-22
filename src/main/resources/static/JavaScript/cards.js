const { createApp } = Vue

const app = createApp({
    data() {
        return {
            cards: [],
            id: null,
        }
    },
    created() {
        const search = location.search
        const params = new URLSearchParams(search)
        this.id = params.get('id')
        this.loadData()
        this.formatBudget()
    },
    methods: {
        loadData() {
            axios.get("/api/clients/1")
                .then(response => {
                    this.cards = response.data.cardDTOS
                    console.log("cards", this.cards)
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