/**
 * 2017-11-24
 * CartController는 장바구니의 수량을 조절하는 명령어들이 담겨있다.
 */
/**
 * Variable
 */
//import '/Carst/js/service.js'; // ESCMA6

var dec = document.querySelectorAll(".cnt-btn.decrease");	// 두 버튼의 개수(length)는 같다
var inc = document.querySelectorAll(".cnt-btn.increase");	//

for(var i = 0; i < dec.length; i++){
	dec[i].addEventListener('click', decrease);
	inc[i].addEventListener('click', increase);
}

/**
 * Functions
 */
function setPrice( itemID, count ){
	var className = ".item-price." + itemID;
	var element = document.querySelector( className );
	
	var price = parseInt(element.getAttribute("default-price")) * count;
	
	element.innerText = setPatternPrice( price );
	
	// AJAX
	ajaxGetCartTotal();
}

/**
 * AJAX로 session에 수량과 가격을 저장했기 때문에,
 * 같은 방식인 AJAX로 가격을 불러와서
 * 출력하는 방식으로 해보자
 * function setTotal(){
 * 
 * 
 * }
 */

function decrease( event ){
	var targetID = event.target.getAttribute("target");
	var prevElement = event.target.nextElementSibling;		// 수량이 감소할 요소
	var prevCount = parseInt(prevElement.innerText);		// 현재 수량 가져오기
	if( prevCount > 1 ){									// 0개 미만은 설정 불가
		prevCount -= 1;										// 감소
	}
	prevElement.innerText = prevCount;						// 대입

	// Set DOM
	// AJAX
	ajaxSetItemCount( targetID, prevCount );
	
	setPrice( targetID, prevCount );
}
function increase( event ){
	var targetID = event.target.getAttribute("target");
	var prevElement = event.target.previousElementSibling;	// 수량이 증가할 요소
	var prevCount = parseInt(prevElement.innerText);		// 현재 수량 가져오기
	prevCount += 1;											// 증가
	prevElement.innerText = prevCount;						// 대입

	// Set DOM
	// AJAX
	ajaxSetItemCount( targetID, prevCount );
	
	setPrice( targetID, prevCount );
}