package org.gr.wocandroid.po;

public class Chat {

	private int chatId,senderId,receiverId;
	private String senderName,receiverName;
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	private String ChatContent;
	@Override
	public String toString() {
		return "Chat [ChatId=" + chatId + ", SenderId=" + senderId
				+ ", ReceiverId=" + receiverId + ", SenderName=" + senderName
				+ ", ReceiverName=" + receiverName + ", ChatContent="
				+ ChatContent + "]";
	}
	public int getChatId() {
		return chatId;
	}
	public void setChatId(int chatId) {
		this.chatId = chatId;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public int getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}
	public String getChatContent() {
		return ChatContent;
	}
	public void setChatContent(String chatContent) {
		ChatContent = chatContent;
	}
	
	
}
