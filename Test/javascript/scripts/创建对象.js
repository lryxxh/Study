/**********************对象直接量*********************/
var empty = {};
var point = {
	x : 0,
	y : 0
};
var point2 = {
	x : poing.x,
	y : point.y
};
var book = {
	"main title" : "JavaScript", // 属性中包含空格，用字符串表示""
	"sub-title" : "The Definitive Guide",// 属性中包含-，用字符串
	"for" : "all audiences", // for为保留关键字，用字符串
	author : {
		firstname : "David",
		surname : "Flanagan"
	}
}

/**********************new 构造函数**********************/
var obj = new Object();
var array = new Array();
var date = new Date();
var regex = new RegExp("js");

function Range(from, to) {
	this.from = from;
	this.to = to;
}
var range = new Range(1,3);


/********************通过Object.create(prototype)创建对象***********************/
var o1 = Object.create({x:1, y:2});//o1继承x和y属性
var o2 = Object.create(null);//o2不继承任何属性和方法
var o3 = Object.create(Object.prototype);//o3为一个普通的空对象,类似new Object();

function inherit(p) {
	if(p == null) {
		throw TypeError();//p是对象,不能为null
	}
	if(Object.create) {//直接
		return Object.create(p);
	}
	var t = typeof p;
	if(t !== "object" && t !== "function") {
		throw TypeError();
	}
	function f() {};//定义一个空构造函数
	f.prototype = p;//将其原型属性设置为p
	return new f();//使用f()创建p的继承对象
	
}

