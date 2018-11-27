var req = new XMLHttpRequest();

req.open("GET","https://faircorp-aubin-rabouan.cleverapps.io/api/rooms");
req.addEventListener("load",function(){
    if (req.status >=200 && req.status<400){
        console.log(req.responseText);
    } else{
        console.error(req.status+" "+req.statusText);
    }
});
req.addEventListener("error",function(){
    console.error("Erreur réseau");
});
req.send(null);

function afficher(reponse){
    console.log(reponse);
}

function ajaxGet(url, callback) {
    var req = new XMLHttpRequest();
    req.open("GET", url);
    req.addEventListener("load", function () {
        if (req.status >= 200 && req.status < 400) {
            callback(req.responseText);
        } else {
            console.error(req.status + " " + req.statusText);
        }
    });
    req.addEventListener("error", function () {
        console.error("Erreur réseau avec L'URL" + url);
    });
    req.send(null);
}
