package lang;

import java.awt.Dialog;
import java.awt.FileDialog;

import javax.swing.JFrame;

public class OutterTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		OutterClass clas1 = new OutterClass();
//		OutterClass.inner1 c1 = clas1.new inner1();
//		OutterClass.inner2 c2 = new OutterClass.inner2();
//		OutterClass.inner5 c5 = new OutterClass.inner5();
//		OutterClass.inner6 c6 = clas1.new inner6();;
//		OutterClass.inner7 c7 = clas1.new inner7();
//		OutterClass.inner8 c8 = new OutterClass.inner8();;
//		System.out.println(Test.test());
//		Test.test();
		
//		try {
//			System.out.println(new String("ÊÇ".getBytes(),"BIG5").getBytes().length);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		JFrame frame = new JFrame();
		Dialog dialog = new FileDialog(frame);
		dialog.setSize(800,600);
		dialog.setVisible(true);

	}
	
	public static void test() {
		int j=0;
		try
		{
		j++;
		return;
		}
		catch(Exception e){ }
		finally
		{
		System.out.println("Ö´ÐÐfinally");
		}    
		System.out.println(j);
	}

}
