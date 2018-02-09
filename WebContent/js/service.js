/**
 * 2017-11-24
 * 이 곳에는 프로젝트 내에서 포괄적으로 사용할 함수를 정의함.
 */
function setPatternPrice( price ){
	var result = "", reverse = "";
	var cnt = 0;
	if( typeof price !== "string" ){
		price = String(price);
	}
	// 수를 거꾸로 세면서  3번째 숫자를 찾은 후 콤마(,)를 찍어준다.
	for(var i = price.length; i > 0; i--){
	    cnt += 1;
	    reverse += price.charAt(i-1);
		if( cnt === 3 && i !== 1 ){
			reverse += ",";
			cnt = 0;
	    }	
	}
	for(var i = reverse.length-1; i >= 0; i--){
		result += reverse.charAt(i);
	}
	return result;
}