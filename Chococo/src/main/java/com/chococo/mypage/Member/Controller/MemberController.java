package com.chococo.mypage.Member.Controller;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chococo.mypage.Member.Service.MemberService;
import com.chococo.mypage.Member.VO.MemberVO;

/*
 * MemberController
 * 1. 로그인, 로그아웃 처리 로직
 * 2. 로그인/로그아웃 처리할때 암호화 처리
 * 3. session 처리 - member
 */

@Controller
@RequestMapping("/member/*")
public class MemberController {

	@Inject
	private MemberService service;

	//패스워드 암호화 처리
	@Inject
	private BCryptPasswordEncoder pwdEncoder;

	//forget password?
	@Inject
	private JavaMailSender mailSender;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	//create_post
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(MemberVO member, Model model) throws Exception {
		logger.info("MemberController - 회원가입을 시도합니다. : " + member.toString());

		member.setUserPass(pwdEncoder.encode(member.getUserPass()));
		//1이 아닐 경우 DB연결이 안되거나 뭔가 오류가 있을것.
		int result = service.create(member);
		if(result == 1) {
			model.addAttribute("msg", "회원가입이 완료되었습니다.");
		} else {
			model.addAttribute("msg", "오류가 발생했습니다. 시스템 관리자에게 문의해주세요.");
		}

		//2. Main으로 redirect
		model.addAttribute("url", "/chococo/");

		return "include/Result";
	}

	//login_post
	//일반 user = member, 어드민 = adminCheck
	//isBan이 1일 경우 = 로그인 금지 계정
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(Model model, MemberVO login, HttpSession session, HttpServletRequest request) throws Exception {
		logger.info("MemberController - 로그인을 시도합니다. : " + login.toString());

		//1. 입력한 정보가 db에 실제로 존재하는지, 아닌지.
		//2. DB에 없을 경우 로그인 실패로 안내.
		//3. 입력한 정보가 등록되어 있을 경우 session에 isLogin이란 이름으로 저장. 저장한 후 main으로 redirect를 해버리게 되면 오른쪽 상단 버튼 바뀜.
		MemberVO search = service.searchMemberByLogin(login);

		if(search == null) {
			model.addAttribute("msg", "가입된 회원이 아닙니다.");
		} else {
			boolean pwdMatch = pwdEncoder.matches(login.getUserPass(), search.getUserPass());
			//search가 null일 경우 찾고자 하는 회원이 없는거고, pwdMatch가 1이 아닐경우 비밀번호가 틀림.
			if(search != null && pwdMatch == true) {
				//2020.04.02 ban 유무 판별
				if(search.getIsBan() == 0) {
					//성공했을 경우 session 설정까지
					//selectOne 유저 데이터 정보 와장창 가져올 것.
					session.setAttribute("isLogin", search);
					model.addAttribute("msg", "로그인이 완료되었습니다.");
				} else {
					model.addAttribute("msg", "로그인 할 수 없는 계정입니다. 운영자에게 문의하세요.");
				}

				//2020.04.02 adminCheck
				if(search.getIsAdmin() == 1) {
					logger.info("MemberController - 어드민 계정 로그인 : " + search.toString());
					session.setAttribute("adminCheck", search);
				}

			} else {
				model.addAttribute("msg", "비밀번호가 틀렸습니다.");
			}	
		}

		model.addAttribute("url", "/chococo/");

		return "include/Result";
	}

	//logout_get
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session, Model model) throws Exception {
		logger.info("MemberController - 로그아웃합니다. " + session.getAttribute("isLogin").toString());

		session.removeAttribute("isLogin");

		//2020.03.30 adminCheck 제거
		MemberVO admin = (MemberVO)session.getAttribute("adminCheck");
		if(admin != null) {
			session.removeAttribute("adminCheck");
		}

		model.addAttribute("msg", "정상적으로 로그아웃 되었습니다.");
		model.addAttribute("url", "/chococo/");

		return "include/Result";
	}

	//회원탈퇴 - mypage에서 넘어옴
	@RequestMapping(value="/deleteMember", method=RequestMethod.GET)
	public String deleteMember(HttpSession session, Model model) throws Exception {
		logger.info("deleteMember - 회원탈퇴");
		//1. 세션에서 로그인 이메일값 가져와서 이걸로 db삭제
		//2. 로그인 세션 식제
		//3. Chococo 메인으로 redirect - result창을 통해 탈퇴가 완료되었다고 유도해주기.
		MemberVO member = (MemberVO)session.getAttribute("isLogin");

		service.deleteMember(member);
		session.removeAttribute("isLogin");

		model.addAttribute("msg", "회원 탈퇴가 완료되었습니다.");
		model.addAttribute("url", "/chococo/");

		return "include/Result";
	}

	//회원정보 변경 from mypage
	@RequestMapping(value="/modifyMember", method=RequestMethod.POST)
	public String modifyMember(HttpSession session, MemberVO member, Model model) throws Exception {
		logger.info("modifyMember data check : " + member.toString());

		MemberVO isLogin = (MemberVO)session.getAttribute("isLogin");
		service.modifyMember(member);

		//1. userPass가 ""값이 아니면 일단 비밀번호가 들어온거임.
		//2. 값이 들어왔는데 기존 세션에 있는 비밀번호와 다르다 = 변경하는거임.
		if(member.getUserPass() != ""
				&& pwdEncoder.matches(member.getUserPass(), isLogin.getUserPass()) == false) {
			member.setUserPass(pwdEncoder.encode(member.getUserPass()));
			service.passwordSetting(member);

			//3. 패스워드 변경 했으면 다시 재로그인 하라고 하자. + 세션삭제
			session.removeAttribute("isLogin");

			model.addAttribute("msg", "패스워드 변경이 완료되었습니다. 재로그인 해주세요.");
			model.addAttribute("url", "/chococo/");

			return "include/Result";
		}

		//기존에 isLogin안에 등록된 정보를 한번 갈아끼워줄 필요가 있음!
		session.removeAttribute("isLogin");
		session.setAttribute("isLogin", service.searchMemberByLogin(member));

		model.addAttribute("msg", "수정이 완료되었습니다.");
		model.addAttribute("url", "/chococo/mypage/main");

		return "include/Result";
	}

	//2020.03.27 forget Password?
	@RequestMapping(value="/forgetPw", method=RequestMethod.POST)
	public String forgetPw(Model model, MemberVO member) throws Exception {
		logger.info("forget Password - " + member.toString());

		MemberVO forgetMember = service.searchMemberByLogin(member);
		//이메일이 맞으면 가입된 회원임
		if(forgetMember != null) {
			try {
				final MimeMessagePreparator preparetor = new MimeMessagePreparator() {
					@Override
					public void prepare(MimeMessage mimeMessage) throws Exception {
						//true = html 사용할꺼임.
						final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
						helper.setFrom("rebianne03@gmail.com");
						helper.setTo(member.getUserEmail());
						helper.setSubject(" * Chococo - 임시 비밀번호 안내 * ");
						helper.setText(passwordSetting(member), true);
					}
				};

				mailSender.send(preparetor);
				model.addAttribute("msg", "이메일로 임시 비밀번호가 전송되었습니다. 로그인 후 꼭 비밀번호를 변경해주세요.");
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", "오류가 발생했습니다.");
			}
		} else {
			model.addAttribute("msg", "등록된 회원이 아닙니다. 다시 확인해주세요.");
		}

		model.addAttribute("url", "/chococo/");

		return "include/Result";
	}

	//2020.03.27 이름 중복 가입 여부 체크
	@ResponseBody
	@RequestMapping(value="/nameChk", method=RequestMethod.POST)
	public int nameChk(MemberVO member) throws Exception {
		return service.nameChk(member);
	}

	@ResponseBody
	@RequestMapping(value="/emailChk", method=RequestMethod.POST)
	public int emailChk(MemberVO member) throws Exception {
		return service.emailChk(member);
	}

	/*
	 * 
	 * ----------------------------
	 */


	private String passwordSetting(MemberVO member) throws Exception {
		String tempPw = "";
		String text = "";

		//임시 비밀번호 생성
		for(int i = 0; i < 8; i++) {
			tempPw += (char)((Math.random() * 26) + 97);
		}

		//tempPw의 암호화 전 값을 먼저 메일로 보낸 후에
		//DB에는 암호화 한 값을 전송해줘야 할 듯.
		text += "<html><body>";
		text += "<h1>Chococo 임시 비밀번호 설정 안내 메일</h1>";
		text += "<h3>아래 비밀번호로 로그인 후 반드시 Mypage에서 비밀번호를 변경해주세요!</h3>";
		text += "<br><br>"
				+ tempPw +"<br><br></body></html>";

		//1. 암호 암호화 해서
		//2. db로 업데이트
		member.setUserPass(pwdEncoder.encode(tempPw));
		try {
			service.passwordSetting(member);
		} catch (Exception e) {
			System.out.println("passwordSetting : " + e.toString());
		}

		return text;
	}
}
