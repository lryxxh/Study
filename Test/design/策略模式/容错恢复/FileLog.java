package 策略模式.容错恢复;

/**
 * 把日志记录到文件
 */
public class FileLog implements LogStrategy {
	public void log(String msg) {
		System.out.println("现在把 '" + msg + "' 记录到文件中");
	}
}
