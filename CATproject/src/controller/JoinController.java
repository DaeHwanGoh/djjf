package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import models.AuthorityDao;
import models.FriendDao;
import models.LoginRecordDao;
import models.MemberDao;

@Controller
@RequestMapping("/join")
public class JoinController {
	@Autowired
	MemberDao mdao;
	@Autowired
	FriendDao fdao;
	@Autowired
	LoginRecordDao logdao;
	@Autowired
	AuthorityDao authDao;
	
	@RequestMapping("/step01")
	public ModelAndView jstep01Handler() {
		ModelAndView mav = new ModelAndView("t_join01");
		
		return mav;
	}
	
	@RequestMapping("/step02")
	public ModelAndView jstep02Handler() {
		ModelAndView mav = new ModelAndView("t_join02");
		
		return mav;
	}
	
	@RequestMapping("/checkAjax")
	@ResponseBody  //리턴되는 그 값을 SpringDispatcher에서 뷰 이름으로 생각하지 않고
					//바로 응답으로 출력한다. String 객체만 출력이 가능하고, 한글 데이터 출력안됨.
					//responseBody는 한글은 깨진다. return "YYYYY"가 아니라 한글로 반환하면 안됨.
					//ResponseBoby는 무조건 String 출력~!
					//  return true 하면 오류나게됨
					//대신 Converter를 사용하면, 한글 출력도 가능하고 , 기타 등등의 효과를 볼 수 있다. 
	
	public String cAjaxHandler(@RequestParam(name="id") String id) {
		
		ModelAndView mav = new ModelAndView("t_join02");
		boolean rst = false;	// false;
		
		rst = mdao.checkID(id);
		
		
		// 'YYYYY' or 'NNNNN'  이런 문자만 출력해주면 된다.
		if(rst) 
			return "YYYYY";
		else
			return "NNNNN";
	}
	
	@RequestMapping("/result")
	public ModelAndView jresultHandler(HttpSession session, @RequestParam(name="name") String jname, @RequestParam(name="age") int jage,
			@RequestParam(name="gender") String jgender, @RequestParam(name="email") String jemail) {

		ModelAndView mav = new ModelAndView(); //t_joinresult
		
		String jid = (String)session.getAttribute("jid");
		String jpass = (String)session.getAttribute("jpass");		
		
		jname = jname.toUpperCase();
		jemail = jemail.toUpperCase();
		
		Map map = new HashMap();
		    map.put("id",jid);
		    map.put("pass",jpass);
		    map.put("name",jname);
		    map.put("age", jage);
		    map.put("gender", jgender);
		    map.put("email", jemail);
		    	
			
			int rst = mdao.addMember(map);
			session.removeAttribute("jid");
			session.removeAttribute("jpass");
			
		if(rst ==1){
			mav.setViewName("redirect:/");
			
			fdao.insertinit((String)map.get("id")); //추가
			logdao.logInit((String)map.get("id"));
			authDao.authSetting((String)map.get("id"));
			
		}else{
			mav.setViewName("redirect:/join/step01");
		}	

		return mav;
	}
	
}







