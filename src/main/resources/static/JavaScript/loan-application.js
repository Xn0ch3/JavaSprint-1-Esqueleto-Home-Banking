const { createApp } = Vue
const app = createApp({
    data() {
        return {
            data: [],
            clients: [],
            selectLoan: "0",
            amount: "",
            accountDest: "",
            payments: "0",
            paymentsFilter: "0",
            name:"",    
            statusLoans: false,
            Selectedpayments:0,
        }
    },
    created() {
        this.loadData()
        this.loadloans()
    },
    methods: {
        loadloans() {
            axios.get("/api/loans")
                .then(response => {
                    this.payments = response.data
                    console.log("Payments", this.payments)
                })
                .catch(error => {
                    console.log(error)
                })
        },
        loadData() {
            axios.get("/api/clients/current")
                .then(response => {
                    this.clients = response.data
                    console.log("Clients", this.clients)
                    this.data = response.data.accounts

                    console.log(this.data)
                })
                .catch(error => {
                    console.log(error)
                })
        },
        Payments() {
            const filter = this.payments.filter(loan => { return this.selectLoan == loan.id })[0]
            
            this.paymentsFilter = filter.payments
            console.log(this.paymentsFilter)
        },
        createLoans() {
            const body ={
                "id": this.selectLoan,
                "amount": this.amount,
                "payments": this.Selectedpayments,
                "destinyAccount":this.accountDest,

            }
            console.log(this.selectLoan)
            console.log(this.amount)
            console.log(this.Selectedpayments)
            console.log(this.accountDest)

            axios.post("/api/loans", body)
                .then(response => {
                    this.data = response.data
                    console.log(this.data)
                    if(response.status.toString().startsWith('2')) {
                        this.successMsg();
                        this.statusTransaction = true
                    } else {
                        this.errorMsg();
                        this.statusTransaction = false
                    }
                    window.location.href = "/pages/accounts.html"
                })
                .catch(error => console.log(error))
                
        },
        errorMsg(){
            Swal.fire({
                background: "linear-gradient(to right, #191970, #00BFFF) no-repeat 0 0 / cover",
                color: "white",
                icon: "error",
                title: "Oops...",
                text: "Something went wrong!",
            })
        },
        successMsg(){
            Swal.fire({
                background: "linear-gradient(to right, #191970, #00BFFF) no-repeat 0 0 / cover",
                color: "white",
                icon: "success",
                title: "Success!",
                text: "Transaction created successfully!",
            })
        },
    }
}).mount('#app')