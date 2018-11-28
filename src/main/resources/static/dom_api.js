/*var req = new XMLHttpRequest();

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
req.send(null);*/

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

ajaxGet('https://faircorp-aubin-rabouan.cleverapps.io/api/building', function (reponse){
    console.log("unicornbuilding");
    var buildings = JSON.parse(reponse);
    buildings.forEach(function(building){
        console.log(building.id);
        addbuilding(building.id, building.name);
    });

});



function roomselection(id){
    console.log(id);
    ajaxGet('https://faircorp-aubin-rabouan.cleverapps.io/api/rooms', function (reponse){
        console.log("unicorn");
        var rooms = JSON.parse(reponse);
        document.getElementById('rooms').innerHTML = '<OPTION>Choose your Room</OPTION>';
        rooms.forEach(function(room){
            console.log(room.buildingId);
            if (id == room.buildingId) {
                addroom(room.id);
            }
        });

    });
}

function lights(id){
    ajaxGet('https://faircorp-aubin-rabouan.cleverapps.io/api/lights', function (reponse){
        console.log("unicorn");
        var lights = JSON.parse(reponse);
        document.getElementById('lights').innerHTML = "<tr><th>ID</th><th>Status</th><th>Level</th><th>Switch</th><th>Delete</th></tr>";
        lights.forEach(function(light) {
            if (id == light.roomId) {
                addlight(light.id, light.status, light.level, light);
            }
        });

    });
}

function addbuilding(id, name) {
    document.getElementById('buildings').innerHTML += '<OPTION>'+id+'</OPTION>' ;
}

function addroom(id) {

    document.getElementById('rooms').innerHTML += '<OPTION>'+id+'</OPTION>' ;
}

function addlight(id, status, level){

    document.getElementById('lights').innerHTML += "<tr><th>"+id+"</th><th>"+status+"</th><th><input type='number' id='level"+id+"' value='"+level+"' min='0' max='255' onchange='changelevel("+id+")'></th><th><input type = 'button' value = 'switch' onclick='switchlight("+id+")'></th><th><input type = 'button' value = 'delete' onclick='deletelight("+id+")'></th></tr>";
}

function switchlight(id){
    $.ajax({
        url: 'https://faircorp-aubin-rabouan.cleverapps.io/api/lights/'+id+'/switch',
        type: 'PUT',
        success: function(response) {
            console.log("UNINICORN");
        }
    });
}

function switchlight(id){
    $.ajax({
        url: 'https://faircorp-aubin-rabouan.cleverapps.io/api/lights/'+id+'/switch',
        type: 'PUT',
        success: function(reponse) {
            console.log("UNINICORN");
            lights(document.getElementById('rooms').value);
        }
    });


}


function deletelight(id){
    $.ajax({
        url: 'https://faircorp-aubin-rabouan.cleverapps.io/api/lights/'+id,
        type: 'DELETE',
        success: function(response) {
            console.log("DELETE UNICORN");
            lights(document.getElementById('rooms').value);
        }
    });
}

function changelevel(ide){

    var levele = document.getElementById('level'+ide).value;;
    var statuse =  null;
    var roome = document.getElementById('rooms').value;
    ajaxGet('https://faircorp-aubin-rabouan.cleverapps.io/api/lights/'+ide, function(reponse){
        var light = JSON.parse(reponse)
                statuse = light.status;



    ajaxPost('https://faircorp-aubin-rabouan.cleverapps.io/api/lights/',{
        "id" : ide,
        "level": levele,
        "status": statuse.toString(),
        "roomId": roome
    }, function(response) {
        console.log("create UNICORN");
        lights(document.getElementById('rooms').value);
    }, true);
    });
};

function createlight(){
     var levele = document.getElementById('levelnew').value;
    var statuse = document.getElementById('newlight').value;
    var roome =document.getElementById('rooms').value;
    ajaxPost('https://faircorp-aubin-rabouan.cleverapps.io/api/lights/',{
        "level": levele,
        "status": statuse,
        "roomId": roome
      }, function(response) {
            console.log("create UNICORN");
            lights(document.getElementById('rooms').value);
        }, true);
}

function teste(){
    console.log(document.getElementById('rooms').value);
}

function ajaxPost(url, data, callback, isJson) {
    var req = new XMLHttpRequest();
    req.open("POST", url);
    req.addEventListener("load", function () {
        if (req.status >= 200 && req.status < 400) {
            // Appelle la fonction callback en lui passant la réponse de la requête
            callback(req.responseText);
        } else {
            console.error(req.status + " " + req.statusText + " " + url);
        }
    });
    req.addEventListener("error", function () {
        console.error("Erreur réseau avec l'URL " + url);
    });
    if (isJson) {
        // Définit le contenu de la requête comme étant du JSON
        req.setRequestHeader("Content-Type", "application/json");
        // Transforme la donnée du format JSON vers le format texte avant l'envoi
        data = JSON.stringify(data);
    }
    req.send(data);
}