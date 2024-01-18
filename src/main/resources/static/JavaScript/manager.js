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
            

            axios.post("/api/loans/admin", body)
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

        updateTotalAmount() {
            const selectedLoan = this.payments.find(loan => loan.id === this.selectLoan);
            console.log("payment2", this.payments)
            if (selectedLoan) {
                this.totalAmount = selectedLoan.interestRate * this.amount;
            }
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
    }// aca termina Methods
}).mount('#app')