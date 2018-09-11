package Delegation;

public class SendEmail {

	RealSender s = new RealSender();
	
	void sendEmail() {
		s.sendEmail();
	}
	
}
