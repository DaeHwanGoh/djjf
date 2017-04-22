package daum.cafe.service.svccafeinfo.entity;

public class Tcategory {

	// cache 하는 field
	/** 카테고리 아이디 : NOT NULL VARCHAR2(3). */
	public static final int CATEID = 1;
	/** 카테고리 이름 : NOT NULL VARCHAR2(30). */
	public static final int CATENAME = 2;
	/** 카테고리 설명 : VARCHAR2(50). */
	public static final int CATEDESC = 3;
	/** SUB 카테고리 유무 : VARCHAR2(1). */
	public static final int SUBCATEYN = 4;
	/** 상위 카테고리 아이디 : VARCHAR2(3). */
	public static final int PARCATEID = 5;
	/** 해당 카테고리에 오늘 생성된 카페수 : NUMBER(7). */
	public static final int TODAYCAFENUM = 6;
	/** 해당 카테고리에 총 생성된 카페수 : NUMBER(8). */
	public static final int CAFENUM = 7;

	// cache 하지않는 field
	/** 카테고리 유효종료일 : NOT NULL VARCHAR2(8). */
	public static final int VLDENDDT = 8;
	/** 카테고리 등록일자 : NOT NULL VARCHAR2(8). */
	public static final int REGDT = 9;
	/** 카테고리 구분 : NOT NULL VARCHAR2(1). */
	public static final int CATEGBN = 10;

	// cache 하는 field
	/** 카테고리 아이디 : NOT NULL VARCHAR2(3). */
 	public String cateid;
	/** 카테고리 이름 : NOT NULL VARCHAR2(30). */
 	public String catename;
	/** 카테고리 설명 : VARCHAR2(50). */
 	public String catedesc;
	/** SUB 카테고리 유무 : VARCHAR2(1). */
 	public String subcateyn;
	/** 상위 카테고리 아이디 : VARCHAR2(3). */
 	public String parcateid;
	/** 해당 카테고리에 오늘 생성된 카페수 : NUMBER(7). */
	public int todaycafenum;
	/** 해당 카테고리에 총 생성된 카페수 : NUMBER(8). */
 	public int cafenum;

	// cache 하지않는 field
	/** 카테고리 유효종료일 : NOT NULL VARCHAR2(8). */
 	public String vldenddt;
	/** 카테고리 등록일자 : NOT NULL VARCHAR2(8). */
 	public String regdt;
	/** 카테고리 구분 : NOT NULL VARCHAR2(1). */
 	public String categbn;
}
