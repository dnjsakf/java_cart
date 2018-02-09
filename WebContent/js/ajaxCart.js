/**
 * 
 */
const AJAX = new XMLHttpRequest();

function ajaxSetItemCount(itemID, count){
	AJAX.open('get', "http://localhost:8080/Carts/cart/ajax/item?itemID="+itemID+"&count="+count, true);

	AJAX.send();

	console.log( AJAX );
	AJAX.onreadystatechange = function() {
		if( AJAX.readyState == 4 && AJAX.status == 200 ){
			var data = AJAX.responseText;
			console.log( "[ajax-data]" );
		}
	}
}

function ajaxGetCartTotal(){
	AJAX.open("GET", "http://localhost:8080/Carts/cart/ajax/total", true);
	AJAX.send();
	AJAX.onreadystatechange = function(){
		if( AJAX.readyState == 4 && AJAX.status == 200 ){
			var data = AJAX.responseText;
			
			data = setPatternPrice( data );
			
			document.querySelector('.item-total').innerText = data;
		}
	}
}