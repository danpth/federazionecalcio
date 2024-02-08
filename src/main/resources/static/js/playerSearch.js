function filterPlayer() {
    const input = document.getElementById("playerSearchInput");
    const searchText = input.value.toUpperCase();
    const players = document.getElementById("listPlayers").children;
    for (let i = 0; i < players.length; i++) {
        if (players[i].id === "listTitle" || players[i].id === "noPlayers") continue; //skip all elements with id "listTitle" or "noPlayers"

        const name = players[i].getAttribute("data-name") + " " + players[i].getAttribute("data-surname");
        if (name.toUpperCase().includes(searchText)) {
            if (players[i].classList.contains("d-none")) {
                players[i].classList.remove("d-none");
            }
        } else {
            players[i].classList.add("d-none");
        }
    }
}
