package clients;


import com.google.common.collect.Maps;
import daum.cafe.service.svccafeinfo.manager.CafeCategoryMgr;

import java.util.Map;

/**
 * 카페 만들기.
 */
public class CafeMakeServlet {

	private void fillTemplate() {

		Map<String, Object> modelRoot = Maps.newHashMap();
		modelRoot.put("CATEGORY", CafeCategoryMgr.getInstance().getFullName("categoryId"));
	}

}
