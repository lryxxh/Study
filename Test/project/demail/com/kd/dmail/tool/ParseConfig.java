package demail.com.kd.dmail.tool;

import java.util.ArrayList;

/**
 * 
 * @author xuzhiqi
 */
public class ParseConfig {
	// #[ DMailGlobalSet =全局配置]
	// #local=本地邮局
	private String local;
	// #garbagePath=回收文件路径
	private String garbagePath;
	// #replyEhrPath=反向HER路径
	private String replyEhrPath;
	// #peerEhrPath=对端HER路径
	private String peerEhrPath;
	// #sentPath=已发送路径
	private String sentPath;
	// #backupPath=备份路径
	private String backupPath;
	// #ogPath=日志路径
	private String logPath;
	// #transferPath=转发路径
	private String transferPath;
	// #AgentPort=代理端口
	private String AgentPort;
	// #AgentPath=代理路径
	private String AgentPath;
	// #AgentBuffSize=代理报文长度
	private String AgentBuffSize;
	// #affixsize=附件大小
	private String affixsize;
	// #srvbusrequestPath=服务请求路径
	private String srvbusrequestPath;
	// #proxyIP=代理IP
	private String proxyIP;
	// #proxyPort=代理端口
	private String proxyPort;

	// #[ DMailSendSelfSet =发送端配置]

	// #SndBufSize=报文长度
	private String SndBufSize;
	// #AffirmWaitTmOut=确认等待超时时间
	private String AffirmWaitTmOut;
	// #WaitTimesMax=最大等待时间
	private String WaitTimesMax;
	// #EhAndDataPath=文件路径
	private String EhAndDataPath;
	// #UDPEnabale=UDP设置
	private String UDPEnabale;

	// #DIRLoopTime=路径循环时间
	private String DIRLoopTime;

	// #dmail-recv
	// #[DMailRecvSelfSet=接收端配置]
	// #Ip=接收端IP｛可修改｝
	private String ip;
	// #UDPPort=UDP接收端口
	private String udpPort;
	// #TCPPort=TCP/IP接收端口
	private String tcpPort;
	// #RcvBufSize=报文长度
	private String rcvBufSize;

	// #[DMailRecvPeerSet=备用接收端配置]
	// #Ip=IP地址
	private String peerIp;
	// #Port=端口
	private String peerPort;

	// #[DMailUserIP =接收用户IP配置]
	// #UDPPort= UDP接收端口
	private String userIpUDPPort;
	// #TCPPort= TCP/IP接收端口
	private String userIpTcpPort;
	private ArrayList<String> dMailUserIp;
	// #华中2=华中2IP地址
	// #国调2=国调2IP地址
	// #华北2=华北2IP地址
	// #华东2=华东2IP 地址

	// #[ DMailUser =用户信息]
	private ArrayList<String> dMailUser;

	// #[ DMailUserPath =用户及路径]
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
