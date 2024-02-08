function filterPlayer() {
    const input = document.getElementById("playerSearchInput");
    const searchText = input.value.toUpperCase();
    const players = document.getElementById("listPlayers").querySelectorAll("li");
    for (let i = 0; i < players.length; i++) {
        const name = (players[i].getAttribute("data-name")).toUpperCase();
        if (name.includes(searchText)) {
            players[i].classList.remove("d-none");
        } else {
            players[i].classList.add("d-none");
        }
    }
}
