package report_update_jdbchelper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

public class JDBCConnection {
	static Properties props = new Properties();
	static Connection connection = null;
	static{
		try {
			props.load(JDBCConnection.class.getResourceAsStream("jdbc.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		if (null == connection) {
			try {
				Class.forName(props.getProperty("driverName"));
				String url = props.getProperty("url");
				String userName = props.getProperty("userName");
				String passWord = props.getProperty("passWord");
				connection = DriverManager.getConnection(url, userName, passWord);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		return connection;
	}
	
	public static void main(String[] args) throws SQLException {
//		connection = JDBCConnection.getConnection();
//		PreparedStatement statement = null;
//		statement = connection
//				.prepareStatement("select top 1 * from \"HISDB\".\"HISDB\".\"MANALOG_DATA_SCADA_20130127\"");
//		statement.execute();
//		ResultSet set = statement.getResultSet();
//		int count = 0;
//		if (set.next()) {
//			for(int k=0;k<1443;k++) {
//				if (set.getObject(k+1) != null) {
//				}else {
//					String column = "DATA_" + k / 60 +"_" + k % 60;
//					String ss = "update \"HISDB\".\"HISDB\".\"MANALOG_DATA_SCADA_20130127\" set "+column+ "=100*rand();";
//					statement.execute(ss);
//					System.out.println(column);
//				}
//			}
//		}
		long longt= new Date().getTime();
		String time = String.valueOf(longt);
				System.out.println(time.length());

	}
	
//	public static void main(String[] args) throws SQLException {
//			String sql = "select st_name,data_id from SYS_INFO_SCADA_ANALOG where data_id in (select id from pdstatic where ddate >='2013-01-01' and ddate<='2013-01-31') and real_table_name='计算点表' and dev_name like '%有功总加' limit 360";
//				 connection = JDBCConnection.getConnection();
//			PreparedStatement statement = null;
//			statement = connection
//					.prepareStatement(sql);
//			statement.execute();
//			ResultSet set = statement.getResultSet();
//			int month = 1;
//			int day = 1;
//			String dayStr = "1";
//			String montString = "1";
//			String tempname = "";
//			while (set.next()) {
//				String name = set.getString(1);
//				long id = set.getLong(2);
//				if(day > 30 ||(day == 1 && month == 1)) {
//					if(day>30) {
//						month++;
//					}
//					day =1;
//					tempname = name;
//				}
//				if(day < 10) {
//					dayStr ="0" + day;
//				} else {
//					dayStr = String.valueOf(day);
//				}
//				if(month < 10) {
//					montString ="0" + month;
//				} else {
//					montString = String.valueOf(month);
//				}
//				String ss = "update \"HISDB\".\"HISDB\".\"SYS_INFO_SCADA_ANALOG\" set st_name ='" + tempname+"' where data_id ="+ id+";";
//				String string = "update \"HISDB\".\"HISDB\".\"PDSTATIC\" set line='"+(tempname+"有功总加") +"',sdate='2013-"+montString +"-"+dayStr+"' where id="+id+";";
//				System.out.println(ss);
//				System.out.println(string);
//				day++;
//			}
//	
//	}
	
//	public static void main(String[] args) {
//		String[] string ={ "北京.高丽营站/220kV.高孙二",
//		"北京.高丽营站/220kV.高孙一",
//		"北京.高丽营站/220kV.顺高二",
//		"北京.高丽营站/220kV.顺高一",
//		"北京.韩村河站/220kV.韩房二",
//		"北京.韩村河站/220kV.韩房一",
//		"北京.红军营站/220kV.城红二",
//		"北京.红军营站/220kV.城红一",
//		"北京.红军营站/220kV.红清二",
//		"北京.红军营站/220kV.红清一",
//		"北京.红军营站/220kV.红寺二",
//		"北京.红军营站/220kV.红寺一",
//		"北京.华能电厂/220kV.华营二",
//		"北京.华能电厂/220kV.华营一",
//		"北京.华能电厂/220kV.华垡二",
//		"北京.华能电厂/220kV.华垡一",
//		"北京.怀柔站/220kV.昌怀二",
//		"北京.怀柔站/220kV.昌怀一",
//		"北京.怀柔站/220kV.孙怀二",
//		"北京.怀柔站/220kV.孙怀一",
//		"北京.黄寺站/220kV.红寺二",
//		"北京.黄寺站/220kV.红寺一",
//		"北京.黄寺站/220kV.寺地二",
//		"北京.黄寺站/220kV.寺地一",
//		"北京.回龙观站/220kV.西观二",
//		"北京.回龙观站/220kV.西观一"
//			};
//		System.out.println(string.length);
//		long id = 116812287318163530L;
//		for(int i=0;i<string.length;i++) {
//			String ar[] = string[i].split("/");
//			String sql = "update pdstatic set ddate='2013-01-05',line='" + ar[1]+"', st='" + ar[0]+"' where id = "+(id + 3*i)+";";
//			String sql2 = "update pdstatic set ddate='2013-01-15',line='" + ar[1]+"', st='" + ar[0]+"' where id = "+(id + 3*i + 1)+";";
//			String sql3 = "update pdstatic set ddate='2013-01-25',line='" + ar[1]+"', st='" + ar[0]+"' where id = "+(id + 3*i + 2)+";";
//			System.out.println(sql);
//			System.out.println(sql2);
//			System.out.println(sql3);
//		}
//	}
	
//	public static void main(String[] args) throws SQLException {
//		String sql = "SELECT top 50 data_id,st_name,dev_name FROM HISDB.SYS_INFO_SCADA_ANALOG where dev_name like '%负载率%'";
//		connection = JDBCConnection.getConnection();
//		PreparedStatement statement = null;
//		statement = connection
//				.prepareStatement(sql);
//		statement.execute();
//		ResultSet set = statement.getResultSet();
//		while (set.next()) {
//			long id = set.getLong(1);
//			String st_name = set.getString(2);
//			String dev_name = set.getString(3);
//			String sql2 = "update pdstatic set st='" + st_name +"', line='" + dev_name+"' where id=" + id+";";
//			System.out.println(sql2);
//		}
//	}

}
