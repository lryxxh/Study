/**********************����ֱ����*********************/
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
	"main title" : "JavaScript", // �����а����ո����ַ�����ʾ""
	"sub-title" : "The Definitive Guide",// �����а���-�����ַ���
	"for" : "all audiences", // forΪ�����ؼ��֣����ַ���
	author : {
		firstname : "David",
		surname : "Flanagan"
	}
}

/**********************new ���캯��**********************/
var obj = new Object();
var array = new Array();
var date = new Date();
var regex = new RegExp("js");

function Range(from, to) {
	this.from = from;
	this.to = to;
}
var range = new Range(1,3);


/********************ͨ��Object.create(prototype)��������***********************/
var o1 = Object.create({x:1, y:2});//o1�̳�x��y����
var o2 = Object.create(null);//o2���̳��κ����Ժͷ���
var o3 = Object.create(Object.prototype);//o3Ϊһ����ͨ�Ŀն���,����new Object();

function inherit(p) {
	if(p == null) {
		throw TypeError();//p�Ƕ���,����Ϊnull
	}
	if(Object.create) {//ֱ��
		return Object.create(p);
	}
	var t = typeof p;
	if(t !== "object" && t !== "function") {
		throw TypeError();
	}
	function f() {};//����һ���չ��캯��
	f.prototype = p;//����ԭ����������Ϊp
	return new f();//ʹ��f()����p�ļ̳ж���
	
}

