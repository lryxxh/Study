/*-----------函数语句------------------*/
function printprops(o){
	for(var p in o)
		alert(p + ": " + o[p] + "\n");
}

function distance(x1, y1, x2, y2) {
	var dx = x2 - x1;
	var dy = y2 - y1;
	return Math.sqrt(dx * dx + dy * dy);
}

/*--------------函数表达式---------------*/
var square = function(x){return x * x;}

var fact = function fact(x) {
	if(x <= 1) {
		return 1;
	} else {
		return x * fact(x - 1);
	}
}

