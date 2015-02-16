<!DOCTYPE html>
<html>
    <head>
        <script>
            function initialize() {
                var posicion = new google.maps.LatLng(${param.latitud}, ${param.longitud});
                var mapOptions = {
                    zoom: 12,
                    center: posicion
                };
                var map = new google.maps.Map(document.getElementById("map${param.idPost}"), mapOptions);
                var marker = new google.maps.Marker({
                    position: posicion,
                    map: map,
                    title: 'Posicion'
                });
            }
            function loadScript() {
                var script = document.createElement("script");
                script.type = "text/javascript";
                script.src = "http://maps.googleapis.com/maps/api/js?v3&key=AIzaSyAgG5LgI0PmcY0nFxyecNOJEWhVIrQjdWs&sensor=false&callback=initialize";
                document.body.appendChild(script);
            }
            window.onload = loadScript;
            google.maps.event.addDomListener(window, 'load', initialize());
        </script>
    </head>
    <body>
        <div id="map${param.idPost}" style="height:300px;width:1200px;"></div>
    </body>
</html>









