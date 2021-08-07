function getType() {
    var x = document.getElementById("cantidad").value;
    var items;
    if (x == 1) {
        items = ["Single", "Doble", "Triple", "Multiple"];
    } else if (x == 2) {
        items = ["Doble", "Triple", "Multiple"];
    } else if (x == 3) {
        items = ["Triple", "Multiple"];
    } else if (x >= 4){
        items = ["Multiple"];
    }
    var str = "";
    for (var item of items) {
        str += "<option>" + item + "</option>";
    }
    document.getElementById("tipo").innerHTML = str;
}
document.getElementById("cantidad").addEventListener("input", getType);


