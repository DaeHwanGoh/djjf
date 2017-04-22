package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import models.AllDao;
import models.AuthorityDao;
import models.PictureDao;

@Controller
@RequestMapping("/auth")
public class AuthorityController {
	@Autowired
	AllDao alldao;
	@Autowired
	AuthorityDao authDao;
	@Autowired
	PictureDao pdao;
	@Autowired
	ModelAndView mav;
	
	
	@RequestMapping("/show")
	public ModelAndView showlist(HttpSession session){
		String id = (String)session.getAttribute("auth_id");
		if(authDao.maxAuthority(authDao.userAuthority(id)).equals("user") || 
				authDao.userAuthority(id).equals("vip")){
			ModelAndView mav=new ModelAndView("redirect:/authority.jsp");
			return mav;
		}
		List userList=authDao.authorityList(id);
		mav.setViewName("t_authview");
		mav.addObject("userList", userList);
		return mav;
	}
	@RequestMapping("/authority")
	@ResponseBody
	public String authority(HttpSession session){
		String id = (String)session.getAttribute("auth_id");
		return authDao.maxAuthority(authDao.userAuthority(id));
	}
	@RequestMapping("/authchange")
	@ResponseBody
	public String authchange(HttpServletRequest req){
		String userid = req.getParameter("userid");
		String authority = req.getParameter("authority");
		String result=authDao.authChange(userid, authority);
		System.out.println("++++++++++++++++++++++++");
		System.out.println(userid);
		System.out.println(authority);
		System.out.println(result);
		System.out.println("++++++++++++++++++++++++");
		return result;
	}
}
