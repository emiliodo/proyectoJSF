function cargarMapa(idPost, gps) {
    
    var gpsSplit = gps.toString().split(",");
    var lat = gpsSplit[0];
    var long = gpsSplit[1];
    $("#mostrarMapa" + idPost).load('maps.jsp?latitud=' + lat + '&longitud=' + long+'&idPost='+idPost);
    var script = document.createElement('script');
    script.type = 'text/javascript';
    script.src = "http://maps.googleapis.com/maps/api/js?v3&key=AIzaSyAgG5LgI0PmcY0nFxyecNOJEWhVIrQjdWs&sensor=false&callback=initialize";
    document.body.appendChild(script);
    
}

