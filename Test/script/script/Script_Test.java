/**
 * 
 */
package script;

import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.UIManager;

/**
 * @author HMI-Lenovo
 *
 */
public class Script_Test {
	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			ScriptEngineManager factory = new ScriptEngineManager();
			// 指定脚本语言为javascript，engine支持很多种脚本语言
			ScriptEngine engine = factory.getEngineByName("JavaScript");
			// 告诉脚本引擎需要加载的脚本代码
			InputStreamReader in = new InputStreamReader(new FileInputStream(
					"./bin/script/alert.js"));
			engine.eval(in);
			Invocable inv = (Invocable) engine;
			inv.invokeFunction("test");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
