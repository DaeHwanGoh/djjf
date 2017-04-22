package models;

import java.util.Comparator;
import java.util.Map;

public class RankComparator implements Comparator<Map>{

	@Override
	public int compare(Map one, Map two) {
		return -((String)one.get("userRating")).compareTo((String)two.get("userRating"));
	}
//	//숫자가 작은놈이 작은놈, 같은 숫자면 Spade Ace,Heart Ace, Diamond Ace, Clover Ace
//	public int compare(Card o1, Card o2) {
//		// c1과 c2를 비교해서 c1을 작다고 인식시켜야 되는 상황을 -1
//		// c1 이 더 크다고 인식시켜야 되는 상황 1
//		// 같으면 0
//		
//		return (o1.rank-o2.rank)*4+(o1.type-o2.type);
//	}
}