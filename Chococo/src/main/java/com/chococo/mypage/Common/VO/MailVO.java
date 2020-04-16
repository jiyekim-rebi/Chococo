package com.chococo.mypage.Common.VO;

public class MailVO {
	
	private String mailName;
	private String mailEmail;
	private String mailMessage;
	
	@Override
	public String toString() {
		return "MailVO [mailName=" + mailName + ", mailEmail=" + mailEmail + ", mailMessage=" + mailMessage + "]";
	}

	public String getMailName() {
		return mailName;
	}

	public void setMailName(String mailName) {
		this.mailName = mailName;
	}

	public String getMailEmail() {
		return mailEmail;
	}

	public void setMailEmail(String mailEmail) {
		this.mailEmail = mailEmail;
	}

	public String getMailMessage() {
		return mailMessage;
	}

	public void setMailMessage(String mailMessage) {
		this.mailMessage = mailMessage;
	}
	
}
