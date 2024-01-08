const { createApp } = Vue

const app = createApp({
    data() {
        return {
            clients: [],
            account: [],
            listAccounts: [],
            loans: [],
            balance: "",
            amount: "",
            description: "",
            accountOrigen: "",
            accountDestiny: "",
            transactions:"",
            statusTransaction: false,
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
        selectAndTransfer() {
            axios.post("/api/transactions?amount="+ this.amount + "&description="+ this.description + "&accountOrigen=" + this.accountOrigen + "&accountDestiny="+ this.accountDestiny)
                .then(response => {
                    console.log("Transaction Created", response)
                    this.loadData()

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