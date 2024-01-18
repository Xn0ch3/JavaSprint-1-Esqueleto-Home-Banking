const { createApp } = Vue

const app = createApp({
    data() {
        return {
            cards: [],
            clients: [],
            // id: null,
            CardType: "",
            CardColor: "",
            cardId:"",
            
        }
    },
    created() {
        // const search = location.search
        // const params = new URLSearchParams(search)
        // this.id = params.get('id')
        this.loadData()
        this.formatBudget()

    },
    methods: {
        loadData() {
            axios.get("/api/clients/current")
                .then(response => {
                    this.clients = response.data
                    this.cards = response.data.cardDTOS
                    console.log("cards", this.cards)
                    console.log("Response",response)

                })
                .catch(error => console.log(error))
        },
        selectAndCreateCard() {
            return Swal.fire({
                background: "linear-gradient(to right, #191970, #00BFFF) no-repeat 0 0 / cover",
                color: "black",
                title: "Select Card",
                html:
                    '<label for="cardType">Card Type:</label>' +
                    '<select id="cardType" class="swal2-input">' +
                    '   <option value="DEBIT">Debit</option>' +
                    '   <option value="CREDIT">Credit</option>' +
                    '</select>' +
                    '<label for="cardColor">Card Color:</label>' +
                    '<select id="cardColor" class="swal2-input">' +
                    '   <option value="SILVER">Silver</option>' +
                    '   <option value="GOLD">Gold</option>' +
                    '   <option value="TITANIUM">Titanium</option>' +
                    '</select>',
                showCancelButton: true,
                preConfirm: async () => {
                    background: "linear-gradient(to right, #191970, #00BFFF) no-repeat 0 0 / cover"
                    color: "white"
                    const cardType = document.getElementById('cardType').value;
                    const cardColor = document.getElementById('cardColor').value;

                    await this.createdCard(cardType, cardColor);
                    this.loadData()

                    return { cardType, cardColor };
                },
                inputValidator: (result) => {
                    background: "linear-gradient(to right, #191970, #00BFFF) no-repeat 0 0 / cover"
                    color: "white"
                    if (!result.cardType || !result.cardColor) {
                        return 'Please select both card type and color.';
                    }
                }
            }).then((result) => {
                if (result.isConfirmed) {
                    background: "linear-gradient(to right, #191970, #00BFFF) no-repeat 0 0 / cover"
                    color: "white"
                    const { cardType, cardColor } = result.value;
                    Swal.fire(`You selected: ${cardColor} ${cardType} card`);
                    this.loadData
                }
            });
        },

        createdCard(cardType, cardColor) {
            axios.post("/api/clients/current/cards?cardColor=" + cardColor + "&cardType=" + cardType)
                .then(response => {
                    console.log("Card Created", response);
                    this.cards.push(response.data);
                    console.log("Cards", this.cards);
                    // window.location.href="src/main/resources/static/pages/createCards.html?id=" + this.id

                })
                .catch(error => {

                    if (error.response) {

                        Swal.fire({
                            background: "linear-gradient(to right, #191970, #00BFFF) no-repeat 0 0 / cover",
                            color: "white",
                            icon: 'error',
                            title: 'Dear customer, we must inform you:',
                            text: `${JSON.stringify(error.response.data, null, 2)}`,
                            footer: `Response Error: ${error.response.status}`
                        });
                    }
                });
        },
        deleteCard() {
            axios.patch("/api/clients/current/cards/delete?id=" + this.cardId)
                .then(response => {
                    response
                    this.loadData()
                }).catch((error) => {console.log(error)})
        },

        cardExpiration(card) {
            const currentDate = new Date();
            const expirationDate = new Date(card.thrueDate);

            card.isExpired = expirationDate < currentDate;
        
            // Compara la fecha de vencimiento con la fecha actual
            if (expirationDate < currentDate) {
                // La tarjeta está vencida
                Swal.fire({
                    icon: 'error',
                    title: 'Tarjeta Vencida',
                    text: `La tarjeta con número ${card.number} está vencida.`,
                    confirmButtonColor: '#d33',
                    background: "linear-gradient(to right, #191970, #00BFFF) no-repeat 0 0 / cover",
                    color: "white",
                });
            } else {
                // La tarjeta no está vencida
                Swal.fire({
                    icon: 'success',
                    title: 'Tarjeta Válida',
                    text: `La tarjeta con número ${card.number} no está vencida.`,
                    confirmButtonColor: '#3085d6',
                    background: "linear-gradient(to right, #191970, #00BFFF) no-repeat 0 0 / cover",
                    color: "white",
                });
            }
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
                        timer: 3000,
                    });
                    setTimeout(() => {
                        this.logout();
                    }, 3000);
                }
            });
        }


    }// Aca termina Methods
}).mount('#app')