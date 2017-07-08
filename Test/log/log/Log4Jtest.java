package log;
import org.apache.log4j.Logger;


public class Log4Jtest {

	static Logger log = Logger.getLogger(Log4Jtest.class);
	
	public static void main(String[] args) {
		log.debug(">>>>>>>>>>");
	}
}
