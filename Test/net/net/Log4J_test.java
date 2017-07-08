/**
 * Log4J_test.java
 * Created by liurenyong at 2013-8-7
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package net;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 
 * @author liurenyong 2013-8-7
 */
public class Log4J_test {
    
    private static Logger log = Logger.getLogger(Log4J_test.class);
    
    public static void main(String[] args) {
        PropertyConfigurator.configure(Log4J_test.class.getResource("/resources/log4j.properties"));
        log.info(";;;;;;;;;;;;;;;;");
        log.error(";;;;;;;;;;;;;;;;");
    }

}
