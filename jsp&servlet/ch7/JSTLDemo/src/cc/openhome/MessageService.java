package cc.openhome;

public class MessageService {
	private Message[] fakeMessages;
	
	public MessageService() {
		//放些假数据，假装这些数据是来自数据库
		fakeMessages = new Message[3];
		fakeMessages[0] = new Message("mike","mike's message!");
		fakeMessages[1] = new Message("tom","tom's message!");
		fakeMessages[2] = new Message("jerry","jerry's message!");
	}
	
	public Message[] getMessages() {
		return fakeMessages;
	}
}
