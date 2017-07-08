package runtime;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Runtime_Ttest {

	public static void main(String[] args) {
		try {
			
//			String a = "netstat -an|find \\"80\\"";
//			System.out.println(a);
			
			Process process = Runtime.getRuntime().exec("netstat -an | find /\"127.0.0.1/\"");
			final InputStream is = process.getInputStream();
			final InputStream es = process.getErrorStream();
			final OutputStream os = process.getOutputStream();
			new Thread() {
				public void run() {
					try {
						BufferedReader reader = new BufferedReader(new InputStreamReader(is));
						String line = "";
						while ((line = reader.readLine()) != null) {
							System.out.println("print:"+line);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				};
			}.start();
			new Thread() {
				public void run() {
					try {
						BufferedReader reader = new BufferedReader(new InputStreamReader(es));
						String line = "";
						while ((line = reader.readLine()) != null) {
							System.out.println("error:"+line);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				};
			}.start();
			
			
			
			
			new Thread() {
				public void run() {
					try {
						BufferedWriter reader = new BufferedWriter(new OutputStreamWriter(os));
						String line = "";
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				};
			}.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
