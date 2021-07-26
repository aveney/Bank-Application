function createNewCustomer(e){
    e.preventDefault();
    let customer = {
        name: document.getElementById("name").value,
        transaction-amount: +document.getElementById("transaction-amount").value,
        date: document.getElementById("date").value
    }
    fetch("http://localhost:8080/bank",
        {
            method: 'POST',
            headers: {'Content-Type': 'application/json',},
            body: JSON.stringify(customer),
        }
    ).then(()=>window.location.reload(true))
}
function createNewChecking(e){
    e.preventDefault();
    let expense = {
        description: document.getElementById("description").value,
        amount: +document.getElementById("amount").value,
        date: document.getElementById("date").value
    }
    fetch("http://localhost:8080/bank/checking",
        {
            method: 'POST',
            headers: {'Content-Type': 'application/json',},
            body: JSON.stringify(expense),
        }
    ).then(()=>window.location.reload(true))
}
function getChecking(e){
    e.preventDefault();
    let expense = {

        amount: +document.getElementById("amount").value,

    }
    fetch("http://localhost:8080/bank/checking",
        {
            method: 'GET',
            headers: {'Content-Type': 'application/json',},
            body: JSON.stringify(expense),
        }
    ).then(()=>window.location.reload(true))
}


async function getAllExpenses(){
    let response = await fetch("http://localhost:8080/expenses");
    let body = await response.json();
    let expenses = body.map(expense => {
        let date = new Date(expense.date)
        date = new Date(date.getTime() + date.getTimezoneOffset() * 60 * 1000)
        return (
            `<li class="list-group-item expense">
                <p>${date.getMonth()+1}/${date.getDate()}/${date.getFullYear()}</p>
                <p>${expense.description}</p>
                <p>$${expense.amount}</p>
            </li>`
        );
    }).join("");
    document.getElementById("expenses").innerHTML = expenses;
}
getAllExpenses();