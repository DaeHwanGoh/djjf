package models;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

@Service
public class likeModel {

	@Autowired
	MongoTemplate template;

	// 데이터 삽입
	public void insertOne(Map map) {// controller에서 맵을 받아서..
		System.out.println(map.toString());
		template.insert(map, "test");// test컬렉션에 데이터 삽입

	}

	// like데이터 추출
	public List<Map> likeData(String id) {

		AggregationOperation a1 = Aggregation.match(Criteria.where("lid").is(id));
		Aggregation aggr = Aggregation.newAggregation(a1);
		System.out.println(aggr);
		AggregationResults<Map> result = template.aggregate(aggr, "recolikedata", Map.class);

		List<Map> listdata = result.getMappedResults();
		return listdata;
	}

	// review데이터 추출
	public List<Map> reviewData(String id) {

		AggregationOperation a1 = Aggregation.match(Criteria.where("id").is(id));
		Aggregation aggr = Aggregation.newAggregation(a1);
		System.out.println(aggr);
		AggregationResults<Map> result = template.aggregate(aggr, "review", Map.class);

		List<Map> listdata = result.getMappedResults();
		return listdata;
	}

}
