package clients;

import com.google.common.collect.Maps;
import daum.cafe.service.svccafeinfo.manager.CafeCategoryMgr;

import java.util.Map;

public class CafeMetaDataBatchServlet {
	public void serviceImpl() {
		Map<String, Object> modelRoot = Maps.newHashMap();
		modelRoot.put("CAFECATEGORYNAME", CafeCategoryMgr.getInstance().getFullName("categoryId"));
	}
}
