package com.chococo.mypage;

import java.util.Locale;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chococo.mypage.Common.VO.MailVO;

//home
@Controller
public class HomeController {
	
	//contactUs 메일 보내기
	@Inject
	private JavaMailSender mailSender;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		return "home";
	}
	
	
	//Contact Us
	@RequestMapping(value="/contactUs", method=RequestMethod.POST)
	public String contactUs(Model model, MailVO mail) throws Exception {
		logger.info("Contact Us... 건의메일 :)");
		logger.info(mail.toString());
		
		try {
			final MimeMessagePreparator preparetor = new MimeMessagePreparator() {
				@Override
				public void prepare(MimeMessage mimeMessage) throws Exception {
					final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
					helper.setFrom(mail.getMailEmail());
					helper.setTo("ekai3214@naver.com");
					helper.setSubject(mail.getMailName() + "님으로 부터 온 Contact Us...");
					helper.setText(contextSetting(mail), true);
				}
			};
			mailSender.send(preparetor);
			model.addAttribute("msg", "메일을 전송하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "오류가 발생했습니다.");
		}
		model.addAttribute("url", "/chococo/");
		return "include/Result";
	}
	
	private String contextSetting(MailVO mail) {
		String context = "";
		
		context += "<html><body>";
		context += "<h1>Chococo - Contact Us... </h1>";
		context += "<br><br>"
				+ mail.getMailMessage() +"<br><br></body></html>";
		
		return context;
	}
	
}
