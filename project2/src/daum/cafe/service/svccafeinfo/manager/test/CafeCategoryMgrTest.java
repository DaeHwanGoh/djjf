package daum.cafe.service.svccafeinfo.manager.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import daum.cafe.service.svccafeinfo.entity.Tcategory;
import daum.cafe.service.svccafeinfo.manager.CafeCategoryMgr;
import daum.cafe.service.svccafeinfo.model.CafeCategoryNode;

public class CafeCategoryMgrTest {

	@Test
	public void testLoad() throws Exception {
		CafeCategoryMgr mgr=CafeCategoryMgr.getInstance();
		mgr.load();
		Tcategory[] allCate=mgr.getAll();
		assertNotNull(allCate); //카테고리 매니저클래스에 로딩이 잘 됐는지 확인
		
		Type listType = new TypeToken<Tcategory[]>() {//assert 비교데이터 Json
		}.getType();						//GsonTest에서 에러없이 실행됐으므로 기준데이터로 정했습니다.
		Tcategory[] categorys = new Gson().fromJson(getJson(), listType);
		assertNotNull(categorys);
		
		assertThat(allCate.length, is(categorys.length));
		//주어진 Json데이터와 DB에서 불러오는 데이터가 같다고 가정하고 테스트
		
		List<String> mgrChildIdList=new ArrayList<>();
		for(int i=0; i<categorys.length; i++){
		//json데이터를 봤을 때 부모,서브,서브... 부모,서브,서브... 순으로 정렬돼있어서 Json데이터로 for문을 돌렸습니다.
			CafeCategoryNode jsonNode=new CafeCategoryNode(categorys[i]);
			if(jsonNode.hasParent()){
				assertThat(mgr.getNode(categorys[i].cateid).getParent().getId(), 
																is(categorys[i].parcateid));
				//만약 부모카테고리가 있다면 부모노드연결이 성공적으로 연결되었는지 비교

				assertTrue(mgrChildIdList.contains(categorys[i].cateid));
				//부모->서브 연결이 빠짐없이 잘 되어있는지 확인
			}else{
				mgrChildIdList=new ArrayList<>();
				//Json데이터 순서를 참고하여 새로운 부모가 나오면 리스트를 초기화하고 리스트에 자식노드의 ID를 담았습니다.
				for(CafeCategoryNode tmp : mgr.getNode(categorys[i].cateid).getChildrens()){
					mgrChildIdList.add(tmp.getId());
				}
				assertThat(mgr.get(categorys[i].cateid).cateid, is(categorys[i].cateid));
			}
		}
	}

	private InputStreamReader getJson() throws UnsupportedEncodingException {
		return new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("category.json"), "UTF-8");
	}
	
}
