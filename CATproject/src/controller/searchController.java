package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import models.searchModel;

@Controller
@RequestMapping("/search")
public class searchController {
	
	 
	@Autowired
	searchModel smod;
	
	@RequestMapping("/01")
	public ModelAndView firstHandle(HttpServletRequest req) throws IOException{
		
		String title = (String)req.getParameter("title");
		if(title==""){		//title=""로 들어온다.
			ModelAndView mva1 = new ModelAndView();
			mva1.setViewName("redirect:/");
			return mva1;
		}
			
		String text = URLEncoder.encode(title, "UTF-8");
		Map<String,Object> map =smod.data1(text);
		
		
		//page수 처리
		int total = (int)map.get("total");				// 전체개수
		int size = total%5 ==0? total/5 : total/5+1;	// 한페이지에 올릴 게시글 
		String pStr = req.getParameter("page")== null ? "1":req.getParameter("page");		//페이지위치 결정
		req.setAttribute("total", total);		
		req.setAttribute("size", size);
		req.setAttribute("page", pStr);
		
		int start =(Integer.parseInt(pStr)-1) *5;	//시작 ==> 0  , 5 , 10 
		int end = start+4;							//     ==> 4  , 9 , 14
		
		req.setAttribute("start", start);
		req.setAttribute("end", end);
		
		ModelAndView mva = new ModelAndView("s_t_01");
		mva.addObject("map",map);
		System.out.println(map.toString());
		return mva;
	}
	
	
	@RequestMapping("/searchAjax")
	public ModelAndView AutoserchHandle(@RequestParam(name="title") String title) throws IOException{
		
		String text = URLEncoder.encode(title, "UTF-8");
		ArrayList<Map> listmap = smod.data2(text);

		/*for(Map<String,Object> map : listmap){
			System.out.println(map.toString());
		}*/
		ModelAndView mav = new ModelAndView();
		mav.addObject("listmap",listmap);
		mav.setViewName("/search_view/searchAjax");
		return mav;
		
	}
	
	
}
