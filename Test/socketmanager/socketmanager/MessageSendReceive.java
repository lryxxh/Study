package socketmanager;

import agency.message.base.MessageToReceive;
import agency.message.base.MessageToSend;

/**
 * 
 * @author LRY
 *
 */
public class MessageSendReceive {

	/** ������Ϣ��*/
	private MessageToSend messageToSend;
	
	/** ������Ϣ��*/
	private MessageToReceive messageToReceive;
	
	public void setMessageToReceive(MessageToReceive messageToReceive) {
		this.messageToReceive = messageToReceive;
	}
	
	public void setMessageToSend(MessageToSend messageToSend) {
		this.messageToSend = messageToSend;
	}
	
	public MessageToReceive getMessageToReceive() {
		return messageToReceive;
	}
	
	public MessageToSend getMessageToSend() {
		return messageToSend;
	}
}
