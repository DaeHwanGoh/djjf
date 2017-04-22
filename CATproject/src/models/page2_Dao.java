package models;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class page2_Dao {
	@Autowired
	UrlList3 urlList;
	public List getRankList(String sel,String date, String tg) throws IOException{
		return urlList.ranking(sel, date, tg);
	}
}
