package models;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class EntrylistComparator implements Comparator<Map.Entry>{
	@Override
	public int compare(Entry o1, Entry o2) {
		int a=-((String)((Map)((List)o1.getValue()).get(0)).get("date")).compareTo((String)((Map)((List)o2.getValue()).get(0)).get("date"));
		return a;
	}
}
