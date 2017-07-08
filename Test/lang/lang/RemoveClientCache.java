package lang;

import java.io.File;

import javax.swing.JApplet;

public class RemoveClientCache extends JApplet{

	public void removeClient() {
		try {
			String cacheDir = System.getenv("deployment.user.cachedir");
			System.out.println("env cachedir : " + cacheDir);
			if(cacheDir == null || "".equals(cacheDir)) {
				cacheDir= System.getProperty("deployment.user.cachedir");
			}
			System.out.println("prop cachedir : " + cacheDir);

			if(cacheDir != null && !cacheDir.equals("")) {
				File file = new File(cacheDir);
				file.setWritable(true);
				System.out.println(file);
				deleteFile(file);
				System.out.println(file.exists());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteFile(File file) {
		if(file.isDirectory()) {
			for(File subFile : file.listFiles()) {
				deleteFile(subFile);
			}
		} else {
			file.delete();
		}
	}
	
}
