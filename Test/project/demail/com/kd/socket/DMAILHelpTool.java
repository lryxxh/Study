/**
 * DMAILHelpTool.java
 * Created by liurenyong at 2013-7-29
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package demail.com.kd.socket;

/**
 * 
 * @author liurenyong 2013-7-29
 */
public interface DMAILHelpTool {
    
    /**
     * 发送邮件.
     * @param address 接收地址
     * @param userName  收件人
     * @param mailFilePath  邮件对应的文件路径
     * @param appendixFilePath 附件对应文件路径
     */
    public void sendDMAIL(String address, String userName, String mailFilePath, String appendixFilePath);
    
    /**
     * 接收邮件.
     * @param address 接收地址
     * @param mailFilePath  邮件对应的文件路径
     * @param appendixFilePath  附件对应的文件路径
     */
    public void receiveDMAIL(String address, String mailFilePath, String appendixFilePath);

}
