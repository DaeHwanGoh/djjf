package clients;

import daum.cafe.service.svccafeinfo.manager.CafeCategoryMgr;

import java.util.HashMap;
import java.util.Map;

public class CafeMessageSender {

	private Map<String, String> makeMessageBody() {
		Map<String, String> body = new HashMap<String, String>();
		body.put("cateFullName", CafeCategoryMgr.getInstance().getFullName("categoryId"));
		return body;
	}

}
