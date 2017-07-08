package 策略模式.容错恢复;

public class Client {
	public static void main(String[] args) {
		LogContext log = new LogContext();
		log.log("记录日志");
		log.log("再次记录日志");
	}
}
