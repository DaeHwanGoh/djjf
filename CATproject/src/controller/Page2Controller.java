package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import models.DetailDao;
import models.Page2Dao;

@Controller
@RequestMapping("/")
public class Page2Controller {
	static String tg;
	
	@Autowired
	DetailDao detaildao;
	@Autowired
	Page2Dao P;
	@Autowired
	ModelAndView mav;
	
	@RequestMapping("/view22")
	public ModelAndView view222(HttpServletRequest req) throws IOException{
		mav.setViewName("t_view22");
		String tg=req.getParameter("tg");
		Map d=(Map) P.mongoMap(tg).get("data");
//		Map d=(Map)P.mongoMap(tg).get("data");
		List data=(List) d.get("data");
		List data1=(List) d.get("data1");
		mav.addObject("data",data );
		mav.addObject("data1", data1);
		return mav;
	}
	@RequestMapping("/view222")
	public ModelAndView view22(HttpServletRequest req) throws IOException{
		List rank;
		List rank1=null;
		String tg=req.getParameter("tg");
		if(tg.equals("0")){
			rank=P.ranking(req.getParameter("sel"), "0");
		} else {
			String []tmp=tg.split(",");
			rank=P.mixedranking("cnt", tmp);
			rank1=P.mixedranking("pnt", tmp);
			for(Map<String,String> map:(List<Map>) rank1){
				System.out.println(map.get("link"));
				String thumnailurl=detaildao.getThumnailURL(map.get("link"));
				map.put("image", thumnailurl);
//				image=http://imgmovie.naver.com/mdi/mit110/0100/10050_P25_174225.jpg
			}
			System.out.println(rank1);
		}
		Map map=new HashMap<>();
		map.put("data", rank);
		map.put("data1", rank1);
		P.mongomixedranking(map, tg);
		return mav;
	}
	@RequestMapping(path="/mainView/ajax",produces="application/json;charset=utf-8")
	@ResponseBody
	public List mainView(HttpServletRequest req) throws IOException{
//		List rank;   업데이트 해줄때 갱신.
//		rank=P.ranking(req.getParameter("sel"), "0");
//		P.mongomainranking(req.getParameter("sel"), rank);
//		===========================================================
		
		List rank=P.mongoMainList(req.getParameter("sel"));
		
		
//		System.out.println(rank);
		return rank;
	}
//	@RequestMapping(path="/mainView/ajax2",produces="application/json;charset=utf-8")
//	@ResponseBody
//	public List sortedView(HttpServletRequest req) throws IOException{
//		List rank=P.ranking(req.getParameter("sel"), "0");
//		System.out.println(rank);
//		return rank;
//	}
}
