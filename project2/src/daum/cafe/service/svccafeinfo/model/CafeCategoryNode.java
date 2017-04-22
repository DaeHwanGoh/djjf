/**
 * 
 */
package daum.cafe.service.svccafeinfo.model;

import daum.cafe.service.svccafeinfo.entity.Tcategory;

import java.util.ArrayList;

/**
 * 카테고리 하나의 node
 */
public class CafeCategoryNode {
	private CafeCategoryNode _parent;
	private final Tcategory _cate;
	private ArrayList<CafeCategoryNode> _childrens;

	public CafeCategoryNode(Tcategory cate) {
		_cate = cate;
	}

	public void setParent(CafeCategoryNode node) {
		_parent = node;
	}

	public CafeCategoryNode getParent() {
		return _parent;
	}

	public void addChildren(CafeCategoryNode node) {
		if (_childrens == null) {
			_childrens = new ArrayList<CafeCategoryNode>();
		}
		_childrens.add(node);
	}

	public ArrayList<CafeCategoryNode> getChildrens() {
		return _childrens;
	}

	public Tcategory getCategory() {
		return _cate;
	}

	public String getId() {
		return _cate.cateid;
	}

	public boolean hasParent() {
		return (_cate.parcateid != null && !"".equals(_cate.parcateid) && !" ".equals(_cate.parcateid));
	}

	public String getName() {
		return _cate.catename;
	}
}
