
function reqIsServerUp(event) {
    event.preventDefault();

    const serverUpResultContainer = document.getElementById("server-status-container");

    fetch("http://localhost:8080/isServerUp")
    .then(req => req.text())
    .then(result => {
        serverUpResultContainer.innerHTML = "The server is running!"
    })
    .catch(err => {
        console.log(err);
        serverUpResultContainer.innerHTML = "The server is down!"
    })

}

function reqNapelemekList(event) {
    event.preventDefault();

    const napelemekListContainer = document.getElementById("napelemek-list-container");

    fetch("http://localhost:8080/getNapelemekList")
    .then(res => res.json())
    .then(result => {
        
        const napelemekTable = document.createElement("table");
        napelemekTable.classList = "table table-striped";

        const tableHeader = napelemekTable.createTHead();
        const row = tableHeader.insertRow();

        row.insertCell().innerHTML = "Megnevezes";
        row.insertCell().innerHTML = "Kod";
        row.insertCell().innerHTML = "Tipus";
        row.insertCell().innerHTML = "Teljesitmeny";
        row.insertCell().innerHTML = "Netto_ar";
        row.insertCell().innerHTML = "Brutto_ar";

        const tableBody = napelemekTable.createTBody();

        for( const item in result ) {
            const row = tableBody.insertRow();

            row.insertCell().innerHTML = result[item].Megnevezes;
            row.insertCell().innerHTML = result[item].Kod;
            row.insertCell().innerHTML = result[item].Tipus;
            row.insertCell().innerHTML = result[item].Teljesitmeny;
            row.insertCell().innerHTML = result[item].Netto_ar;
            row.insertCell().innerHTML = result[item].Brutto_ar;
        }

        napelemekListContainer.appendChild(napelemekTable);

    })
    .catch(err => {
        console.log(err);
    })
}

