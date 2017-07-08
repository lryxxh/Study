package net;

/**
 * middledata人机代理接口常量
 * 主操作码
 * 附操作码
 * 外部错误码
 * 内部错误码
 * 数据类型长度
 * 数据类型
 * 整实型标志
 * @author 边晓宇
 *
 */
public class StaticFinals {
	
	//数据类型长度
	public static final int CHAR_LENGTH=1;
//	public static final int STRING_LENGTH=-1;//接收时变长
	public static final int BYTE_LENGTH=1;
	public static final int SHORT_LENGTH=2;
	public static final int INT_LENGTH=4;
	public static final int FLOAT_LENGTH=4;
	public static final int DOUBLE_LENGTH=8;
	public static final int TIME_LENGTH=8;
	public static final int LONG_LENGTH=8;
//	public static final int BYTEARRAY_LENGTH=-1;//接收时变长
	

	//数据类型
	public static final byte STRING=1;
	public static final byte UCHAR=2;
	public static final byte SHORT=3;
	public static final byte INT=4;
	public static final byte TIME=5;
	public static final byte FLOAT=6;
	public static final byte DOUBLE=7;
	public static final byte KEYID = 8;
	public static final byte BYTEARRAY = 9;
	public static final byte TEXT = 10;
	public static final byte IMAGE = 11;
	public static final byte APPKEY = 12;
	public static final byte APPID = 13;
	public static final byte UINT = 14;
	public static final byte LONG=15;
	
	//显示类型
	public static final int DISPLAY_CHAR=1;
    public static final int DISPLAY_UCHAR=2;
    public static final int DISPLAY_SHORT=3;
    public static final int DISPLAY_INT=4;        
    public static final int DISPLAY_LONG=5;
    public static final int DISPLAY_FLOAT=6;
    public static final int DISPLAY_DOUBLE=7;
    public static final int DISPLAY_SINGLE_MENU=8;              
    public static final int DISPLAY_MULTI_MENU=9;
    public static final int DISPLAY_STRING=10;
    public static final int DISPLAY_DATE=11;
    public static final int DISPLAY_TIME=12;        
    public static final int DISPLAY_DATETIME=13;
    public static final int DISPLAY_MULTISTRING=14;
    public static final int DISPLAY_KEYID=15;
    public static final int DISPLAY_BYTE=16;    
    public static final int DISPLAY_PASSWORD=17;
    public static final int DISPLAY_TEXT=18;
    public static final int DISPLAY_IMAGE=19;   

	
	//整实型标志
	public static final byte TYPE_INTEGER=1;
	public static final byte TYPE_FLOAT=2;
	public static final byte TYPE_STRING=3;
	public static final byte TYPE_BYTEARRAY=4;
	
	public static String dataTypeToString(int dataType){
		String string=null;
		if(dataType==STRING){
			string="STRING";
		}
		else if(dataType==TIME){
			string="TIME";
		}
		else if(dataType==FLOAT){
			string="FLOAT";
		}
		else if(dataType==DOUBLE){
			string="DOUBLE";
		}
		else if(dataType==INT){
			string="INT";
		}
		else if(dataType==SHORT){
			string="SHORT";
		}
		else if(dataType==UCHAR){
			string="UCHAR";
		}
		else if(dataType==LONG){
			string="LONG";
		}
		return string;
	}
	
	public static String displayTypeToString(int displayType){
		String string=null;
		if(displayType==DISPLAY_CHAR){
			string="CHAR";
		}
		else if(displayType==DISPLAY_UCHAR){
			string="UCHAR";
		}
		else if(displayType==DISPLAY_SHORT){
			string="SHORT";
		}
		else if(displayType==DISPLAY_INT){
			string="INT";
		}
		else if(displayType==DISPLAY_LONG){
			string="LONG";
		}
		else if(displayType==DISPLAY_FLOAT){
			string="FLOAT";
		}
		else if(displayType==DISPLAY_DOUBLE){
			string="DOUBLE";
		}
		else if(displayType==DISPLAY_SINGLE_MENU){
			string="单选菜单";
		}
		else if(displayType==DISPLAY_MULTI_MENU){
			string="多选菜单";
		}
		else if(displayType==DISPLAY_STRING){
			string="STRING";
		}
		else if(displayType==DISPLAY_DATE){
			string="DATE";
		}
		else if(displayType==DISPLAY_TIME){
			string="TIME";
		}
		else if(displayType==DISPLAY_DATETIME){
			string="DATETIME";
		}
		else if(displayType==DISPLAY_MULTISTRING){
			string="MULTISTRING";
		}
		else if(displayType==DISPLAY_KEYID){
			string="KEYID";
		}
		else if(displayType==DISPLAY_BYTE){
			string="BYTE";
		}
		else if(displayType==DISPLAY_PASSWORD){
			string="PASSWORD";
		}
		else if(displayType==DISPLAY_TEXT){
			string="TEXT";
		}
		else if(displayType==DISPLAY_IMAGE){
			string="IMAGE";
		}
		return string;
	}
	
	
	/**
	 * 域信息表中定义的数据类型代号和当前系统统一的代号不相符
	 * 此方法用来转换
	 * 只有从域信息表中查询各个域的数据类型时需要转换
	 * 一般情况下不要调
	 * @return
	 */
	public static int dataTypeConvert(int dataTypeInSys_Column_Info){
		int dataType=-1;
		switch (dataTypeInSys_Column_Info) {
		case 1:
			dataType=INT;
			break;
		case 2:
			dataType=STRING;
			break;
		case 3:
			dataType=UCHAR;
			break;
		case 4:
			dataType=SHORT;
			break;
		case 5:
			dataType=INT;
			break;
		case 6:
			dataType=FLOAT;
			break;
		case 7:
			dataType=DOUBLE;
			break;
		case 8:
			dataType=TIME;
			break;
		case 9:
			dataType=KEYID;
			break;
		case 10:
			dataType=BYTEARRAY;
			break;
		case 11:
			dataType=TEXT;
			break;
		case 12:
			dataType=IMAGE;
			break;
		case 13:
			dataType=APPKEY;
			break;
		case 14:
			dataType=APPID;
			break;
		case 15:
			dataType=LONG;
			break;
		default:
			break;
		}
		return dataType;
	}
}
