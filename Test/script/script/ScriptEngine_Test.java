/**
 * 
 */
package script;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptEngine_Test {
	public static void main(String[] args) {
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine se = sem.getEngineByName("javascript");
		try {
			// 调用直接JAVASCRIPT语句
			se.eval("println('111');");
			String tmpstr = "test string";
			se.eval(("println('" + tmpstr + "');"));
			// 调用无参数方法JAVASCRIPT函数
			se.eval("function sayHello() {"
					+ " print('Hello '+strname+'!');return 'my name is '+strname;"
					+ "}");
			Invocable invocableEngine = (Invocable) se;
			se.put("strname", "testname");
			String callbackvalue = (String) invocableEngine
					.invokeFunction("sayHello");
			System.out.println(callbackvalue);

			// 调用有参数JAVASCRIPT函数
			se.eval("function sayHello2(strname2) {"
					+ " print('Hello '+strname+'!');return 'my name is '+strname2;"
					+ "}");
			callbackvalue = (String) invocableEngine.invokeFunction(
					"sayHello2", "testname2");
			System.out.println(callbackvalue);

		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}