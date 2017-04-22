package models;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;


@Service
public class LoginRecordDao {
	@Autowired
	MongoTemplate template;
	
	public void loginLog(String userId){
		 Query query=new Query();
         Criteria criteria=Criteria.where("userid").is(userId);
         query.addCriteria(criteria);
         Update update=new Update();
         String date=System.currentTimeMillis()+"";
         update.set("loginLog", date);
         template.upsert(query, update, "personalLogRecord");
	}
	public void logoutLog(String userId){
		 Query query=new Query();
        Criteria criteria=Criteria.where("userid").is(userId);
        query.addCriteria(criteria);
        Update update=new Update();
        String date=System.currentTimeMillis()+"";
        update.set("logoutLog", date); 
        template.upsert(query, update, "personalLogRecord");
	}
	
	public void logInit(String userId){
		 Query query=new Query();
        Criteria criteria=Criteria.where("userid").is(userId);
        query.addCriteria(criteria);
        Update update=new Update();
        String date=System.currentTimeMillis()+"";
        update.set("loginLog", date);
        template.upsert(query, update, "personalLogRecord");
        update=new Update();
        update.set("logoutLog", date);
        template.upsert(query, update, "personalLogRecord");
	}

}
