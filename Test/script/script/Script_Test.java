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
			// ָ���ű�����Ϊjavascript��engine֧�ֺܶ��ֽű�����
			ScriptEngine engine = factory.getEngineByName("JavaScript");
			// ���߽ű�������Ҫ���صĽű�����
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
