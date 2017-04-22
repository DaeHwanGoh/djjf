/**
 * 
 */
package daum.cafe.service.svccafeinfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import daum.cafe.service.svccafeinfo.entity.Tcategory;

public class IcategoryHandler {

	private static IcategoryHandler __instance;

	public static IcategoryHandler getInstance() {
		return __instance;
	}

	static {
		__instance = new IcategoryHandler();
	}

	/**
	 * ResultSet을 Tcategory로 복사 (oracle).
	 * 
	 * @param rs
	 *            복사할 ResultSet.
	 * @param entity
	 *            복사될 Tcategory.
	 * @exception SQLException.
	 */
	private void copyResultSetAll(ResultSet rs, Tcategory entity) throws SQLException {
		if (rs != null && entity != null) {
			entity.cateid = rs.getString(Tcategory.CATEID);
			entity.catename = rs.getString(Tcategory.CATENAME);
			entity.catedesc = rs.getString(Tcategory.CATEDESC);
			entity.subcateyn = rs.getString(Tcategory.SUBCATEYN);
			entity.parcateid = rs.getString(Tcategory.PARCATEID);
			entity.todaycafenum = rs.getInt(Tcategory.TODAYCAFENUM);
			entity.cafenum = rs.getInt(Tcategory.CAFENUM);
		}
	}

	/**
	 * ResultSet을 Tcategory로 복사 (oracle).
	 * 
	 * @param rs
	 *            복사할 ResultSet.
	 * @param entity
	 *            복사될 Tcategory.
	 * @exception SQLException.
	 */
	private void copyResultSet(ResultSet rs, Tcategory entity) throws SQLException {
		if (rs != null && entity != null) {
			entity.cateid = rs.getString(Tcategory.CATEID);
			entity.catename = rs.getString(Tcategory.CATENAME);
			entity.catedesc = rs.getString(Tcategory.CATEDESC);
			entity.subcateyn = rs.getString(Tcategory.SUBCATEYN);
			entity.parcateid = rs.getString(Tcategory.PARCATEID);
			entity.todaycafenum = rs.getInt(Tcategory.TODAYCAFENUM);
			entity.cafenum = rs.getInt(Tcategory.CAFENUM);
		}
	}

	/** 모든 카테고리 정보를 가져온다. */
	private static final String SELECT_SQL_BY_ALL = "SELECT /*+ index(category category_uk1) */"
			+ " cateid, catename, catedesc, subcateyn, parcateid, todaycafenum, cafenum"
			+ " FROM category CONNECT BY prior cateid = parcateid AND categbn='2'" + " START WITH parcateid = ' ' AND categbn='2'";

	public Tcategory[] selectAll(Connection conn, String cateid) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<Tcategory> result = new ArrayList<Tcategory>();
		Tcategory tmpEntity = null;

		try {
			pstmt = conn.prepareStatement(SELECT_SQL_BY_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				tmpEntity = new Tcategory();
				copyResultSetAll(rs, tmpEntity);
				result.add(tmpEntity);
			}
		} finally {
			closeStatement(pstmt, rs);
		}

		return (result.isEmpty() ? null : (Tcategory[]) result.toArray(new Tcategory[result.size()]));
	}

	/**
	 * @param pstmt
	 * @param rs
	 * @throws SQLException
	 */
	private void closeStatement(PreparedStatement pstmt, ResultSet rs) throws SQLException {
		if (pstmt != null) {
			pstmt.close();
		}
		if (rs != null) {
			rs.close();
		}
	}
}
