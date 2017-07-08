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
     * �����ʼ�.
     * @param address ���յ�ַ
     * @param userName  �ռ���
     * @param mailFilePath  �ʼ���Ӧ���ļ�·��
     * @param appendixFilePath ������Ӧ�ļ�·��
     */
    public void sendDMAIL(String address, String userName, String mailFilePath, String appendixFilePath);
    
    /**
     * �����ʼ�.
     * @param address ���յ�ַ
     * @param mailFilePath  �ʼ���Ӧ���ļ�·��
     * @param appendixFilePath  ������Ӧ���ļ�·��
     */
    public void receiveDMAIL(String address, String mailFilePath, String appendixFilePath);

}
