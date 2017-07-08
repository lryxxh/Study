package demail.com.kd.dmail.config.self;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import demail.com.kd.dmail.consts.DMailConstants;

/**
 * 
 * @author xuzhiqi
 */
public class Configuration {
	public static Properties config = new Properties();
	static {
		InputStream input = null;
		try {
			input = new FileInputStream(DMailConstants.confFilePath
					+ "config.properties");
			config.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
