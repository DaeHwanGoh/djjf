package daum.cafe.service.svccafeinfo.manager;

import daum.cafe.auxiliary.santa.connection.ConnPool;
import daum.cafe.service.svccafeinfo.dao.IcategoryHandler;
import daum.cafe.service.svccafeinfo.entity.Tcategory;
import daum.cafe.service.svccafeinfo.model.CafeCategoryNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 카페고리 관리 (전체 목록을 메모리에 로드)
 */
public class CafeCategoryMgr {
	private static CafeCategoryMgr __instance;

	private static Logger __logger = LoggerFactory.getLogger(CafeCategoryMgr.class);

	private Tcategory[] _categoryArray;

//	private Tcategory[] _midCategoryArray;
//
//	private Tcategory[] _subCategoryArray;

	private Map<String, CafeCategoryNode> _categoryMap;

//	static {
//		__instance = new CafeCategoryMgr();
//	}

	public static CafeCategoryMgr getInstance() {
		if(__instance==null){
			synchronized(CafeCategoryMgr.class) {
				__instance= new CafeCategoryMgr();
			}
		}
		return __instance;
	}
//	public  static MemberDAO getInstance(){
//		if(instance==null){
//			synchronized(MemberDAO.class) {
//				instance= new MemberDAO();
//			}
//		}
//		return instance;
//	}

	public CafeCategoryMgr() {
	}

	public void load() {
		__logger.debug("load()");

		Connection uconn = null;
		_categoryArray = null;
//		_midCategoryArray = null;
//		_subCategoryArray = null;
		_categoryMap = null;
		try {
			uconn = ConnPool.getUConnection();
			_categoryArray = IcategoryHandler.getInstance().selectAll(uconn, null);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnPool.closeConnection(uconn);
		}
		// mid와 sub로 분리
//		ArrayList<Tcategory> midList = new ArrayList<Tcategory>();
//		ArrayList<Tcategory> subList = new ArrayList<Tcategory>();
		// 해쉬에 추가
		
		_categoryMap = new HashMap<String, CafeCategoryNode>(_categoryArray.length);
		for (int i = 0; i < _categoryArray.length; i++) {
			Tcategory cate = _categoryArray[i];
			_categoryMap.put(cate.cateid, new CafeCategoryNode(cate));
//			if (cate.parcateid == null || "".equals(cate.parcateid) || " ".equals(cate.parcateid)) {
			if(getNode(cate.cateid).hasParent()){
//				midList.add(cate);
//			} else {
//				subList.add(cate);
//				hashLinkingNode(cate);
			}

		}
//		_midCategoryArray = midList.toArray(new Tcategory[midList.size()]);
//		_subCategoryArray = subList.toArray(new Tcategory[subList.size()]);

		// 해쉬에 각 링크 연결
//		for (int i = 0; i < _subCategoryArray.length; i++) {
//			Tcategory subcate = _subCategoryArray[i];
//			CafeCategoryNode midnode = getNode(subcate.parcateid);
//			CafeCategoryNode subnode = getNode(subcate.cateid);
//			if (midnode == null) {
//				__logger.error("부모 category node를 찾을 수 없습니다. : " + subcate.parcateid);
//			} else {
//				midnode.addChildren(subnode);
//				subnode.setParent(midnode);
//			}
//		}
	}
//	public void hashLinkingNode(Tcategory cate){
//		CafeCategoryNode midnode = getNode(cate.parcateid);
//		CafeCategoryNode subnode = getNode(cate.cateid);
//		if (midnode == null) {
//			__logger.error("부모 category node를 찾을 수 없습니다. : " + cate.parcateid);
//		} else {
//			midnode.addChildren(subnode);
//			subnode.setParent(midnode);
//		}
//	}

	public Tcategory[] getAll() {
		if (_categoryArray == null) {
			load();
		}
		return _categoryArray == null ? null : _categoryArray.clone();
	}

//	public Tcategory[] getAllMid() {
//		if (_categoryArray == null) {
//			load();
//		}
//		return _midCategoryArray == null ? null : _midCategoryArray.clone();
//	}
//
//	public Tcategory[] getAllSub() {
//		if (_categoryArray == null) {
//			load();
//		}
//		return _subCategoryArray == null ? null : _subCategoryArray.clone();
//	}

	public Tcategory get(String cateid) {
		CafeCategoryNode node = getNode(cateid);
		return node == null ? null : node.getCategory();
	}

	public CafeCategoryNode getNode(String cateid) {
		if (_categoryArray == null) {
			load();
		}
		return _categoryMap.get(cateid);
	}

	/**
	 * 전체 category 이름을 얻어온다.
	 * 
	 * @param cateid
	 * @return parentCategoryName > categoryName ...
	 */
	public String getFullName(String cateid) {
		StringBuilder buf = new StringBuilder();
		CafeCategoryNode node = getNode(cateid);
		CafeCategoryNode parent = node.getParent();

		if (parent != null) {
			buf.append(parent.getName()).append(" > ");
		}
		buf.append(node.getName());

		return buf.toString();
	}
}
