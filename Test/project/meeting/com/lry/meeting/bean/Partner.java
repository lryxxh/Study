/**
 * 
 */
package meeting.com.lry.meeting.bean;

import java.sql.Date;

/**
 * @author liurenyong
 *
 */
public class Partner {
	
	/**id*/
	private int id = 0;
	
	/**名字*/
	private String name = "";
	
	/**年龄*/
	private int age = 0;
	
	/**电话号*/
	private long phone = 12345678900l;
	
	/**城市名*/
	private String cityName = "";
	
	/**身份证*/
	private String identityNo = "";
	
	/**地址*/
	private String address = "";
	
	/**入会时间*/
	private Date joinDate = null;
	
	/**是否付费*/
	private boolean isPay = false;
	
	/**酒店名*/
	private String winehouseName = "";
	
	/**酒店房间号*/
	private String winehouseNo = "";
	
	/**职业*/
	private String job = "";
	
	/**职称*/
	private String professionalTitle = ""; 
	
	/**参与次数*/
	private int joinTimes = 0;
	
}
