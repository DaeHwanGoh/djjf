package controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import models.MemberDao;

@Controller
public class mainController {
	@Autowired
	MemberDao mdao; //정보 가져오려고.. 
	
	@RequestMapping("/")
	public ModelAndView mainHandler(HttpSession session, HttpServletRequest request) {
		System.out.println("main컨트롤러 잘 들어옴(홈경로)");

		boolean r = session.isNew();	// 세션이 방금 만들어진건지 확인.
		String addr = request.getRemoteAddr();

		if(r) { 
			// save 쿠키가 있다면.. 그 값을 확인해서.. 로그인 진행을 시키자.
			Cookie[] cbx = request.getCookies();
			if(cbx!=null) {
				Cookie s = null;
				for(Cookie t : cbx) {
					if(t.getName().equals("save")) {
						s = t;
						break;
					}
				}
				//=================================================
				if(s != null) {
					String val = s.getValue();	// id가 담겨있을꺼다.
					// 로그인을 진행시키자.
					session.setAttribute("auth", "yes");
					session.setAttribute("auth_id", val);
					
					//dao 써서 그 아이디에 대한 정보를 갖고 와서 (pass, age, gender) session.setAttribute 시키기
					Map map = new HashMap<>();
					map = mdao.getMemberInfo(val);
					int age = ((BigDecimal)map.get("AGE")).intValue();
						
					session.setAttribute("auth_pass", map.get("PASS")); //회원 탈퇴시 진행하기 위해서 세션에 갖고 있기 
					session.setAttribute("auth_age", age); // 추천게시글에 정보 저장하기 위해서 세션에 나이 정보 갖고 있기
					session.setAttribute("auth_gender", map.get("GENDER")); // 추천게시글에 정보 저장하기 위해서 세션에 성별 정보 갖고 있기
									
					System.out.println("auth_age main = "+ age);
				}
			}
		}
		
		ModelAndView mav = new ModelAndView("t_view2");
		return mav;
	}	
}







