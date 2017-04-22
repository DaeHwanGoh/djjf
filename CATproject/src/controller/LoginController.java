package controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import models.LoginRecordDao;
import models.MemberDao;

@Controller
@RequestMapping("/log")
public class LoginController {
	@Autowired
	MemberDao mdao;
	@Autowired
	LoginRecordDao logdao;
	
	@RequestMapping("/logout")
	public ModelAndView logoutHandler(HttpSession session, HttpServletResponse response) {
		
		logdao.logoutLog((String)session.getAttribute("auth_id")); //추가
		
		session.removeAttribute("auth");
		session.removeAttribute("auth_id");
		session.removeAttribute("auth_pass");
		session.removeAttribute("auth_age");
		session.removeAttribute("auth_gender");


		// 쿠키 삭제는..? 같은 설정을 잡아서 0초로 만들어서 send .. (유효치 않은 쿠키로 변경)
		Cookie c = new Cookie("save","bomb");
			c.setPath("/");
			c.setMaxAge(0);
		response.addCookie(c);
		
		ModelAndView mav = new ModelAndView("redirect:/");
		
		return mav;
	}
	
	@RequestMapping("/login")
	public ModelAndView lloginHandler() {
		ModelAndView mav = new ModelAndView("t_login");

		return mav;
	}

	@RequestMapping("/login/result")
	public ModelAndView lresultHandler(@RequestParam Map map1, HttpSession session,
			@RequestParam(name = "keep", defaultValue = "off") String val, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		boolean b = mdao.checkMember(map1);
		if (b) {
			Map map = new HashMap<>();
			map = mdao.getMemberInfo((String)map1.get("id"));
			int age = ((BigDecimal)map.get("AGE")).intValue();
			
			System.out.println("로그인에서 age = "+ age);
			
			logdao.loginLog((String)map.get("ID")); //추가==========
			
			session.setAttribute("auth_id", map.get("ID"));
			session.setAttribute("auth_pass",map.get("PASS")); //회원 탈퇴시 진행하기 위해서 세션에 갖고 있기 
			session.setAttribute("auth_age", age);  // 추천게시글에 정보 저장하기 위해서 세션에 나이 정보 갖고 있기
			session.setAttribute("auth_gender", map.get("GENDER")); // 추천게시글에 정보 저장하기 위해서 세션에 성별 정보 갖고 있기
			
			int sessionage = (Integer)session.getAttribute("auth_age");
			System.out.println("auth_age"+ sessionage);
			
			if (val.equals("on")) {
				Cookie c = new Cookie("save", (String)map1.get("id")); // 밸류에는 계정정보가 있어야 함. 쿠키는 한쌍씩만 됨
				c.setMaxAge(60 * 60 * 24 * 7);
				c.setPath("/");
				response.addCookie(c);
				session.setAttribute("auth", "yes"); //yes or no yes면 쿠키 갖고 있음
			}else{
				session.setAttribute("auth", "no"); //yes or no yes면 쿠키 갖고 있음
			}
			mav.setViewName("redirect:/");
		}else {
			
			mav.setViewName("redirect:/log/login");
		}
		return mav;
		
	}
}
