/*package controller;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import models.reviewModel;

@Controller
@RequestMapping("/review")
public class reviewController {

	@Autowired
	reviewModel rmodel;
	
	@RequestMapping("/insert")
	public void inserHandle(){
		
		//별점,아이디,내용,작성일자(데이터등록시점),영화정보(제목,이미지,링크,)
		//review_t_01
		
		//review : id /profile picture/ writedate / 리뷰내용/ title / image /code
		//like : id / title/ subtitle /image /story /
		
		double[] userRating = new double[]{1.00,2.00,3.00,4.00,5.00};
		String id ="id_1";
		String[] review = new String[]{"사무엘잭슨과 콩을 번갈아 보여줄때 누가누군지 헷갈렸다",
				"상영전 오징어다리 꼭 사가라",
				"어쿠 깜짝이야 한세번 놀랬음 괴수들의 ufc 액션이 엄청남",
				"살다살다 킹콩한테 반할줄이야...♥",
				"일단 이런 영화에 흔히 등장하는, 뻔한 로맨스가 없어서 좋았다. 몰입감은 최고고, 교훈도 있다. 돈주고 볼 만한 영화."};
		String[] writedate=new String[10];
			for(int i=0; i<5;i++){
				DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.KOREA);
				Calendar cal = Calendar.getInstance(Locale.KOREA);
				writedate[i] =df.format(cal.getTime());
				System.out.println(writedate[i]);
			}
		String[] title=new String[]{"미녀와 야수","미녀공심이","슈퍼걸:미녀파이터","저주받은 미녀","미녀와야수 시즌4"};
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
		
		
		Map map =  new HashMap();
		for(int i=0; i<5;i++){
			map.put("userRating",userRating[i]);
			map.put("id",id);
			map.put("review",review[i]);
			map.put("writedate",writedate[i]);
			map.put("title",title[i]);
			map.put("image",image[i]);
			map.put("link",link[i]);
			rmodel.insertOne(map);
		
		}
		
	}
	
	
	@RequestMapping("/reviewdata")
	public ModelAndView dataHandle(HttpServletRequest req,@RequestParam(name="id")String id){
		
		Map<String,Object> map = new HashMap<>();
		List<Map> listdata = rmodel.reviewData(id);
			
		int total =0;
		for(Map e:listdata){
			System.out.println(e.toString());
			total +=1;
		}
		System.out.println("total=="+total);
		map.put("total", total);
		map.put("listdata", listdata);
		
		req.setAttribute("id", id);
		
		ModelAndView mva = new ModelAndView();
		mva.addObject("map",map);
		mva.setViewName("review_t_01");
		return mva;
		
	}
	
}
*/