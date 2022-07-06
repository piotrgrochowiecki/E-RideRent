
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%--<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>--%>

<!DOCTYPE html>
<html>
<head>
    <title>Simple Map</title>
    <meta name="viewport" content="initial-scale=1.0">
    <meta charset="utf-8">
    <style>
        /* Always set the map height explicitly to define the size of the div
         * element that contains the map. */
        #map {
            height: 100%;
        }
        /* Optional: Makes the sample page fill the window. */
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body>
<div id="map"></div>
<script>
    function initMap() {
        const initialPosition = {lat: 52.237049, lng: 21.017532};

        const map = new google.maps.Map(document.getElementById('map'), {
            center: initialPosition,
            zoom: 15
        });

        const marker = new google.maps.Marker({ map, position: initialPosition });

    }
</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC9g6JjDjteRGpcxLpguu425Df9ELt7r9E&callback=initMap"
        async defer></script>
</body>
</html>