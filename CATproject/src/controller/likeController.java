package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import models.likeModel;

@Controller
@RequestMapping("/like")
public class likeController {

	@Autowired
	likeModel ldao;
	
	@RequestMapping("/insert")
	public void insertHandle()throws IOException{	//@RequestParam Map map,@RequestParam("id") int id
		

		//review : id /profile picture/ writedate / 리뷰내용/ title / image /code
		//like : id / title/ 장르 /image /
		
		/*code 번호/영화정보(제목,서브타이틀,이미지,줄거리, 장르)/아이디 */
		// 테스트 데이터 삽입
		String[] title=new String[]{"미녀와 야수","미녀공심이","슈퍼걸:미녀파이터","저주받은 미녀","미녀와야수 시즌4"};
		String[] code=new String[]{"136872","149126","158471","152067","139923"};
		String[] subtitle=new String[]{"Beauty and the Beast","","SUPERGIRL XXX: an Axel Braun Parody","Doomed Beauty","Beauty &amp; the Beast"};
		String[] image=new String[]{"http://imgmovie.naver.com/mdi/mit110/1368/136872_P12_113639.jpg",
									"http://imgmovie.naver.com/mdi/mit110/1491/149126_P00_185644.jpg",
									"http://imgmovie.naver.com/mdi/mit110/1584/158471_P01_133620.jpg",
									"http://imgmovie.naver.com/mdi/mit110/1520/152067_P01_143732.jpg",
									"http://imgmovie.naver.com/mdi/mit110/1399/139923_P01_140310.JPEG"};
		String[] link=new String[]{"http://movie.naver.com/movie/bi/mi/basic.nhn?code=136872",
				"http://movie.naver.com/movie/bi/mi/basic.nhn?code=149126",
				"http://movie.naver.com/movie/bi/mi/basic.nhn?code=158471",
				"http://movie.naver.com/movie/bi/mi/basic.nhn?code=152067",
				"http://movie.naver.com/movie/bi/mi/basic.nhn?code=139923"};
		String[] story=new String[]{"스토리1","스토리2","스토리3","스토리4","스토리4"};
		String[] id = new String[]{"id_1","id_2","id_3","id_4","id_5"};
		
		Map map =  new HashMap();
		for(int i=0; i<5;i++){
			map.put("title",title[i]);
			map.put("code",code[i]);
			map.put("subtitle",subtitle[i]);
			map.put("image",image[i]);
			map.put("image",image[i]);
			map.put("link",link[i]);
			map.put("id",id[i]);
			ldao.insertOne(map);
		
		}
		
		//데이터 추출
		
	}
	
	@RequestMapping("/likedata")
	public ModelAndView dataHandle(HttpSession session,HttpServletRequest req){
		
		//찜한 데이터
		String id =(String)session.getAttribute("auth_id");
		Map<String,Object> map = new HashMap<>();
		
		List<Map> likedata= ldao.likeData(id);
			
		int like_total =0;
		for(Map e:likedata){
			//System.out.println(e.toString());
			like_total +=1;
		}
		//System.out.println("total=="+total);
		map.put("like_total", like_total);
		map.put("likedata", likedata);
		
		req.setAttribute("id", id);
		
		
		// 리뷰 단 데이터
		List<Map> reviewdata = ldao.reviewData(id);
		int review_total =0;
		for(Map e:reviewdata){
			//System.out.println(e.toString());
			review_total +=1;
		}
		
		map.put("review_total", review_total);
		map.put("reviewdata", reviewdata);
		
		ModelAndView mva = new ModelAndView();
		mva.addObject("map",map);
		mva.setViewName("like_t_01");
		return mva;
		
	}
}
