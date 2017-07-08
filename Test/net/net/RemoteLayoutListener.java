package net;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;


public class RemoteLayoutListener extends Thread{

	private ServerSocket server;
	private Properties props;
	private String theme="center_right_new";
	
	public RemoteLayoutListener() {}
	
	public void run(){
//		Properties props = support.SystemConfig.getConfigFileProperties("remoteLayoutListen.properties");
//		
//		String enabled = props.getProperty("listenEnabled");
//		if (!"1".equals(enabled)) {
//			return;
//		}
//		
//		findBrowserName();
		
		try {
			server = new ServerSocket(4321);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("==========remote layout listen start============");
//		while (true) {
//			try {
//				Socket socket = server.accept();
//				InputStream is = socket.getInputStream();
//				BufferedReader br = new BufferedReader(new InputStreamReader(is));
//				String layout = br.readLine();
////				if(theme.equals(layout)){
////				}else{
////					BrowserBaseFrameManager.getInstance().getTopBrowserBaseFrame().getBrowser().switchWindows(layout);
////					theme = layout;
////				}
//				System.out.println("=== accept ===" + layout);
////				socket.close();
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//		}
		
		while (true) {
            try {
                Socket socket = server.accept();
                InputStream in = socket.getInputStream();
                byte[] b = new byte[1000];
                int len;
                    while ((len = in.read(b)) != -1) {
                        String name = new String(b, 0, len);
                        System.out.println("=== name ===" + name);
                    }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}

	private void findBrowserName() {
		while (true) {
			waiting();
//			Properties props = support.SystemConfig.getConfigFileProperties("remoteLayoutListen.properties");
//			String browserName = props.getProperty("browserName");
//			Browser browser = BrowserTool.findBrowserByName(browserName);
//			if (browser != null) {
//				break;
//			}
		}
	}
	
	private void waiting() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		new RemoteLayoutListener().start();
	}
}