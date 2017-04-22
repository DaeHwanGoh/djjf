package clients;


import daum.cafe.service.svccafeinfo.manager.CafeCategoryMgr;
import daum.cafe.service.svccafeinfo.model.CafeCategoryNode;

import java.util.List;

public class WIGameManagement {

	public List<CafeCategoryNode> getSubCategories(String cateid) {

		CafeCategoryNode categoryNode = CafeCategoryMgr.getInstance().getNode(cateid);
		if (categoryNode != null) {
			return categoryNode.getChildrens();
		}

		return null;
	}

}
