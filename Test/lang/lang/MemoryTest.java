package lang;
import tool.PrintMemory;

/**
 * ssssss.java
 * Created by liurenyong at 2014-1-6
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */

/**
 * 
 * @author liurenyong 2014-1-6
 */
public class MemoryTest {

	public static void main(String[] args) {
		
		/*
											 * // int e = 1111111100; //
											 * NumberFormat format =
											 * NumberFormat.getNumberInstance();
											 * //
											 * format.setMaximumFractionDigits
											 * (4); //
											 * format.setMinimumFractionDigits
											 * (4); // String value =
											 * format.format(e); //
											 * System.out.println(value); long
											 * time =
											 * System.currentTimeMillis();
											 * Vector<Vector> data = new
											 * Vector<Vector>(); for (int i = 0;
											 * i < 250000; i++) { Vector temp =
											 * new Vector(); for (int j = 0; j <
											 * 50; j++) { temp.add((i + j)); }
											 * if (i % 100000 == 0) {
											 * System.out.println(i + " ");
											 * printMemory(); } data.add(temp);
											 * } printMemory();
											 * System.out.println
											 * ("...............time " +
											 * (System.currentTimeMillis() -
											 * time)); try { Thread.sleep(2000);
											 * } catch (InterruptedException e)
											 * { // TODO Auto-generated catch
											 * block e.printStackTrace(); } time
											 * = System.currentTimeMillis();
											 * Vector<Vector> fixdata = new
											 * Vector<Vector>(); Vector<Vector>
											 * mobiledata = new
											 * Vector<Vector>(); int fixNo = 10;
											 * int index = 0; for (Vector
											 * rowDataVector : data) {
											 * fixdata.add(new
											 * Vector(rowDataVector.subList(0,
											 * fixNo))); mobiledata.add(new
											 * Vector
											 * (rowDataVector.subList(fixNo,
											 * rowDataVector.size()))); if
											 * (index++ % 10000 == 0) {
											 * System.err.print(index + "   ");
											 * printMemory(); } } System.err
											 * .println(
											 * "========================================================="
											 * ); printMemory();
											 * System.out.println(data.size() +
											 * "  " + fixdata.size() + "  " +
											 * mobiledata.size());
											 * System.out.println
											 * ((System.currentTimeMillis() -
											 * time));
											 */
//		try {
//			FtpClient ftp = new FtpClient("192.168.200.211");
//			ftp.login("d5000", "ptscd1");
//			BufferedReader reader = new BufferedReader(new InputStreamReader(
//					ftp.nameList("data/graph")));
//			String line = "";
//			while ((line = reader.readLine()) != null) {
//				System.out.println(line);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(calendar.YEAR, 2004);
//		calendar.set(Calendar.MONTH, 1);
//		System.out.println(calendar);
//		int max_day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//		System.out.println(max_day);
		PrintMemory.printMemory();
//		int[][] a= new int[10][102400];
//		int a[] = new int[102400];
//		int a1[] = new int[102400];
//		int a2[] = new int[102400];
//		int a3[] = new int[102400];
//		int a4[] = new int[102400];
//		int a5[] = new int[102400];
//		int a6[] = new int[102400];
//		int a7[] = new int[102400];
//		int a8[] = new int[102400];
//		int a9[] = new int[102400];
//		PrintMemory.printMemory();
		System.out.println("1 "+Math.toRadians(90));
		System.out.println("2 "+Math.toRadians(-90));
		System.err.println("3 "+-Math.toRadians(-90));
	}

}
