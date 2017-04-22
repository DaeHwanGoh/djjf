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
		
		
	}

	private InputStreamReader getJson() throws UnsupportedEncodingException {
		return new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("category.json"), "UTF-8");
	}
	
}
