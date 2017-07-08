package lang;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringSplitTest {

	public static void main(String[] args) {
	    String str = "platform,app,avc,poi-2.5.1-final-20040804,cfgfiles,jcommandbar,context,jcommon,jfreechart-1.0.14,bsh-2.0b4,support,modelmaintain,jbcl,j3dcore,j3dutils,vecmath,uiframework,jdom,curveviewer,menueditor,scadaoper,l2fprod-common-all,ccui,openswing,emsui_kd,kdui,agc,commons-net-1.4.0,mmiconsole,operationRec,permctrl,support,pdrrecord,jh,dbtable,alloy,iconset-1,jlfgr-1_0,openswing,jide-oss-2.7.1,dts,pinyin4j-2.5.0,dsa,wams,jaxen-core,jaxen-jdom,saxpath,pinyin4j-2.5.0,balloontip,swingx-1.0,log4j-1.2.16,sced,scadarelay,DmJdbcDriver,scadarelay,fes,permmanager,ruleOper,xercesImpl,commons-io-2.0.1,operateticket,jxl,version,commons-net-1.4.0,alarmquery,schedule_pdscs,schedule_oper,scs,dom4j-1.6.1,dsaoper,brainpoweroperator,idw-gpl,brainpoweroperator_sd,synalarm,scadacase,agc_kd,thpas,wf_proof,expr4j-0.0.1,DJNativeSwing,DJNativeSwing-SWT,swt-4.3-gtk-linux-x86";
	    String[] array = str.split(",");
	    for(String temp : array) {
	        System.out.println("<jar href=\"jars/"+ temp+".jar\" />");
	    }
//	    Socket socket = new Socket();
//	    try {
//            socket.connect(new InetSocketAddress("10.105.50.101",20014),5000);
//            System.err.println(socket);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//		Calendar[] calendars = Test.getDomainDate(true, 4, false,
//				Calendar.MINUTE, -4, 7);
//		System.out.println(calendars[0].getTime() + "   "
//				+ calendars[1].getTime());
//		System.out.println(new Date(1355278320000L));
		
//		
//			Properties prop = new Properties();
//			try {
//				prop.load(Test.class.getResourceAsStream("g_file_config.properties"));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			String dealPath = prop.getProperty("g_file_path");
//		
		
//		String string= "GraphApp -wnd -explorer -export_type 4 -pic:huangkun:g:The program for g.update by huangkun";
////		String string = "NARI_SVG_Viewer:huangkun:svg:The program for svg. update by huangkun.";
////		String content[] = string.split(":");
////		System.out.println(content[0].substring(0, content[0].indexOf(" ") + 1)+":"+content[2]);
//		String string2 = "ÈýµÄ·ëÉÜ·å";
//		String string3 = "sdfsdf";
//		System.out.println(FirstNByte(string2));
//		System.out.println(FirstNByte(string3));
//		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(1356278400000L)));
//		System.out.println("122222222".split(",").length);
//		System.out.println(Integer.toHexString(235) + Integer.toHexString(241) + Integer.toHexString(245));
		
	}
	
	static boolean FirstNByte(String s) {
		Pattern p = Pattern.compile("^[\\u4e00-\\u9fa5]$");
		int i = 0;
		for (char c : s.toCharArray()) {
			Matcher m = p.matcher(String.valueOf(c));
			i += m.find() ? 2 : 1;
		}
		return s.length() == i ? true : false;
	}

	public static Calendar[] getDomainDate(Boolean isInt, int intTime,
			Boolean isRun, int calendarField, int domainStart, int domainEnd) {
		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		System.out.println(startCalendar.getTime() +"   " + endCalendar.getTime());
		if (domainStart < 0 || domainEnd < 0) {
			startCalendar.add(calendarField, domainStart);
			endCalendar.add(calendarField, domainEnd);
		} else {
			startCalendar.set(calendarField, domainStart);
			endCalendar.set(calendarField, domainEnd);
		}
		if (isInt) {
			switch (calendarField) {
			case Calendar.HOUR_OF_DAY:
			{	
				if (calendarField == Calendar.HOUR_OF_DAY) {
					int starthour = startCalendar.get(calendarField);
					starthour = starthour / intTime * intTime;
					startCalendar.set(calendarField, starthour);
					int endthour = endCalendar.get(calendarField);
					endthour = endthour / intTime * intTime;
					endCalendar.set(calendarField, endthour);
					
				}
				startCalendar.set(Calendar.MINUTE, 0);
				endCalendar.set(Calendar.MINUTE, 0);
			}
			case Calendar.MINUTE:
			{
				if (calendarField == Calendar.MINUTE) {
					int startminute = startCalendar.get(calendarField);
					startminute = startminute / intTime * intTime;
					startCalendar.set(calendarField, startminute);
					int endtminute = endCalendar.get(calendarField);
					endtminute = endtminute / intTime * intTime;
					endCalendar.set(calendarField, endtminute);
				}
				startCalendar.set(Calendar.SECOND, 0);
				endCalendar.set(Calendar.SECOND, 0);
			}
			case Calendar.SECOND:
			{	
				if (calendarField == Calendar.SECOND) {
					int startsecond = startCalendar.get(calendarField);
					startsecond = startsecond / intTime * intTime;
					startCalendar.set(calendarField, startsecond);
					int endtminute = endCalendar.get(calendarField);
					endtminute = endtminute / intTime * intTime;
					endCalendar.set(calendarField, endtminute);
				}
				
				startCalendar.set(Calendar.MILLISECOND, 0);
				endCalendar.set(Calendar.MILLISECOND, 0);
				break;
			}
			default:
				break;
			}
		}

		Calendar[] calendars = new Calendar[2];
		calendars[0] = startCalendar;
		calendars[1] = endCalendar;
		return calendars;
	}
}
