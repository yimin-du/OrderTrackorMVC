

function initMap() {
	var url = "http://localhost:8088/OrderTrackorSpringMVC/ordersdata"

		$.getJSON(url, function(data) {
			var mapOptions = {
					zoom:14,
					mapTypeId: google.maps.MapTypeId.ROADMAP
			}
		    var bounds = new google.maps.LatLngBounds();

			var map = new google.maps.Map(document.getElementById("map"), mapOptions);
		    map.setTilt(45);

			for(var i in data) {
				
		        var place = new google.maps.LatLng(Number(data[i].lat),Number(data[i].lng));
		        bounds.extend(place);
				var marker = new google.maps.Marker({
					position: place,
					map: map
				});
			}
			
			map.fitBounds(bounds);

		});

}




