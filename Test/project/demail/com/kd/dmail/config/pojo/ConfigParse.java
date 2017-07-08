package demail.com.kd.dmail.config.pojo;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author xuzhiqi
 */
public class ConfigParse {

	public static HashMap<String, String> parseFile(BufferedReader reader,
			String type) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String line = null;
		boolean flag = false;
		while ((line = reader.readLine()) != null) {
			if (line.equals(type)) {
				flag = true;
			}
			if (line.startsWith("[") && !line.equals(type)) {
				if (!flag)
					continue;
				else
					break;
			}
			if (line.startsWith("#") || line.indexOf("=") == -1) {
				continue;
			}
			if (flag) {
				String temp[] = line.split("=");
				map.put(temp[0], temp[1]);
			}
		}
		return map;
	}

	public static ArrayList<String> parseFile_list(BufferedReader reader,
			String type) throws Exception {
		ArrayList<String> list = new ArrayList<String>();
		String line = null;
		boolean flag = false;
		while ((line = reader.readLine()) != null) {
			if (line.equals(type)) {
				System.out.println(line);
				flag = true;
			}
			if (line.startsWith("[") && !line.equals(type)) {
				if (!flag)
					continue;
				else
					break;
			}
			if (line.startsWith("#") || line.indexOf("=") == -1) {
				continue;
			}
			if (flag) {
				list.add(line);
			}
		}
		return list;
	}
}
