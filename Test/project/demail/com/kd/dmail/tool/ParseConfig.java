package demail.com.kd.dmail.tool;

import java.util.ArrayList;

/**
 * 
 * @author xuzhiqi
 */
public class ParseConfig {
	// #[ DMailGlobalSet =ȫ������]
	// #local=�����ʾ�
	private String local;
	// #garbagePath=�����ļ�·��
	private String garbagePath;
	// #replyEhrPath=����HER·��
	private String replyEhrPath;
	// #peerEhrPath=�Զ�HER·��
	private String peerEhrPath;
	// #sentPath=�ѷ���·��
	private String sentPath;
	// #backupPath=����·��
	private String backupPath;
	// #ogPath=��־·��
	private String logPath;
	// #transferPath=ת��·��
	private String transferPath;
	// #AgentPort=����˿�
	private String AgentPort;
	// #AgentPath=����·��
	private String AgentPath;
	// #AgentBuffSize=�����ĳ���
	private String AgentBuffSize;
	// #affixsize=������С
	private String affixsize;
	// #srvbusrequestPath=��������·��
	private String srvbusrequestPath;
	// #proxyIP=����IP
	private String proxyIP;
	// #proxyPort=����˿�
	private String proxyPort;

	// #[ DMailSendSelfSet =���Ͷ�����]

	// #SndBufSize=���ĳ���
	private String SndBufSize;
	// #AffirmWaitTmOut=ȷ�ϵȴ���ʱʱ��
	private String AffirmWaitTmOut;
	// #WaitTimesMax=���ȴ�ʱ��
	private String WaitTimesMax;
	// #EhAndDataPath=�ļ�·��
	private String EhAndDataPath;
	// #UDPEnabale=UDP����
	private String UDPEnabale;

	// #DIRLoopTime=·��ѭ��ʱ��
	private String DIRLoopTime;

	// #dmail-recv
	// #[DMailRecvSelfSet=���ն�����]
	// #Ip=���ն�IP�����޸ģ�
	private String ip;
	// #UDPPort=UDP���ն˿�
	private String udpPort;
	// #TCPPort=TCP/IP���ն˿�
	private String tcpPort;
	// #RcvBufSize=���ĳ���
	private String rcvBufSize;

	// #[DMailRecvPeerSet=���ý��ն�����]
	// #Ip=IP��ַ
	private String peerIp;
	// #Port=�˿�
	private String peerPort;

	// #[DMailUserIP =�����û�IP����]
	// #UDPPort= UDP���ն˿�
	private String userIpUDPPort;
	// #TCPPort= TCP/IP���ն˿�
	private String userIpTcpPort;
	private ArrayList<String> dMailUserIp;
	// #����2=����2IP��ַ
	// #����2=����2IP��ַ
	// #����2=����2IP��ַ
	// #����2=����2IP ��ַ

	// #[ DMailUser =�û���Ϣ]
	private ArrayList<String> dMailUser;

	// #[ DMailUserPath =�û���·��]
	private ArrayList<String> dMailUserPath;

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getGarbagePath() {
		return garbagePath;
	}

	public void setGarbagePath(String garbagePath) {
		this.garbagePath = garbagePath;
	}

	public String getReplyEhrPath() {
		return replyEhrPath;
	}

	public void setReplyEhrPath(String replyEhrPath) {
		this.replyEhrPath = replyEhrPath;
	}

	public String getPeerEhrPath() {
		return peerEhrPath;
	}

	public void setPeerEhrPath(String peerEhrPath) {
		this.peerEhrPath = peerEhrPath;
	}

	public String getSentPath() {
		return sentPath;
	}

	public void setSentPath(String sentPath) {
		this.sentPath = sentPath;
	}

	public String getBackupPath() {
		return backupPath;
	}

	public void setBackupPath(String backupPath) {
		this.backupPath = backupPath;
	}

	public String getLogPath() {
		return logPath;
	}

	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}

	public String getTransferPath() {
		return transferPath;
	}

	public void setTransferPath(String transferPath) {
		this.transferPath = transferPath;
	}

	public String getAgentPort() {
		return AgentPort;
	}

	public void setAgentPort(String agentPort) {
		AgentPort = agentPort;
	}

	public String getAgentPath() {
		return AgentPath;
	}

	public void setAgentPath(String agentPath) {
		AgentPath = agentPath;
	}

	public String getAgentBuffSize() {
		return AgentBuffSize;
	}

	public void setAgentBuffSize(String agentBuffSize) {
		AgentBuffSize = agentBuffSize;
	}

	public String getAffixsize() {
		return affixsize;
	}

	public void setAffixsize(String affixsize) {
		this.affixsize = affixsize;
	}

	public String getSrvbusrequestPath() {
		return srvbusrequestPath;
	}

	public void setSrvbusrequestPath(String srvbusrequestPath) {
		this.srvbusrequestPath = srvbusrequestPath;
	}

	public String getProxyIP() {
		return proxyIP;
	}

	public void setProxyIP(String proxyIP) {
		this.proxyIP = proxyIP;
	}

	public String getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(String proxyPort) {
		this.proxyPort = proxyPort;
	}

	public String getSndBufSize() {
		return SndBufSize;
	}

	public void setSndBufSize(String sndBufSize) {
		SndBufSize = sndBufSize;
	}

	public String getAffirmWaitTmOut() {
		return AffirmWaitTmOut;
	}

	public void setAffirmWaitTmOut(String affirmWaitTmOut) {
		AffirmWaitTmOut = affirmWaitTmOut;
	}

	public String getWaitTimesMax() {
		return WaitTimesMax;
	}

	public void setWaitTimesMax(String waitTimesMax) {
		WaitTimesMax = waitTimesMax;
	}

	public String getEhAndDataPath() {
		return EhAndDataPath;
	}

	public void setEhAndDataPath(String ehAndDataPath) {
		EhAndDataPath = ehAndDataPath;
	}

	public String getUDPEnabale() {
		return UDPEnabale;
	}

	public void setUDPEnabale(String uDPEnabale) {
		UDPEnabale = uDPEnabale;
	}

	public String getDIRLoopTime() {
		return DIRLoopTime;
	}

	public void setDIRLoopTime(String dIRLoopTime) {
		DIRLoopTime = dIRLoopTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUdpPort() {
		return udpPort;
	}

	public void setUdpPort(String udpPort) {
		this.udpPort = udpPort;
	}

	public String getTcpPort() {
		return tcpPort;
	}

	public void setTcpPort(String tcpPort) {
		this.tcpPort = tcpPort;
	}

	public String getRcvBufSize() {
		return rcvBufSize;
	}

	public void setRcvBufSize(String rcvBufSize) {
		this.rcvBufSize = rcvBufSize;
	}

	public String getPeerIp() {
		return peerIp;
	}

	public void setPeerIp(String peerIp) {
		this.peerIp = peerIp;
	}

	public String getPeerPort() {
		return peerPort;
	}

	public void setPeerPort(String peerPort) {
		this.peerPort = peerPort;
	}

	public String getUserIpUDPPort() {
		return userIpUDPPort;
	}

	public void setUserIpUDPPort(String userIpUDPPort) {
		this.userIpUDPPort = userIpUDPPort;
	}

	public String getUserIpTcpPort() {
		return userIpTcpPort;
	}

	public void setUserIpTcpPort(String userIpTcpPort) {
		this.userIpTcpPort = userIpTcpPort;
	}

	public ArrayList<String> getdMailUserIp() {
		return dMailUserIp;
	}

	public void setdMailUserIp(ArrayList<String> dMailUserIp) {
		this.dMailUserIp = dMailUserIp;
	}

	public ArrayList<String> getdMailUser() {
		return dMailUser;
	}

	public void setdMailUser(ArrayList<String> dMailUser) {
		this.dMailUser = dMailUser;
	}

	public ArrayList<String> getdMailUserPath() {
		return dMailUserPath;
	}

	public void setdMailUserPath(ArrayList<String> dMailUserPath) {
		this.dMailUserPath = dMailUserPath;
	}

	public void parseFile(String file) {

	}
   	public void saveFile() {

	}

}
