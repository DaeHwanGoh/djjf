package clients;

import com.google.common.collect.Maps;
import daum.cafe.service.svccafeinfo.manager.CafeCategoryMgr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class PieFrameServlet {

	public void execute(HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> geps = Maps.newHashMap();
		geps.put("midcate", CafeCategoryMgr.getInstance().get("parentCategoryId"));
	}

}
