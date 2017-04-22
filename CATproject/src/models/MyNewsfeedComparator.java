package models;

import java.util.Comparator;
import java.util.Map;

public class MyNewsfeedComparator implements Comparator<Map>{
	@Override
	public int compare(Map o1, Map o2) {
		int a=-((String)o1.get("date")).compareTo((String)o2.get("date"));
		return a;
	}

}
