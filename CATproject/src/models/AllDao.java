package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.jboss.C3P0PooledDataSource;

public class AllDao {
	@Autowired
	ComboPooledDataSource dd;
	private Map<String, String> sqlMap;
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private DataSource dataSource;

	public AllDao() {
		sqlMap = new HashMap<>();
	}

	public AllDao(Map sqlMap) {
		this.sqlMap = sqlMap;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String sqlConverter(String sql1, Map<String, Object> map) {
		String[] tmp = sql1.split("#");
		String resultSql = "";
		for (String s : tmp) {
			boolean check = false;
			String t = "";
			if (s.startsWith("{")) {
				check = true;
				int b = s.indexOf("{", 0);
				int e = s.indexOf("}", b + 1);
				String t1 = s.substring(b + 1, e);
				String t2 = t1.trim();
				if (map.containsKey(t2)) {
					t = s.replaceFirst("\\{[a-zA-Z\\s]+\\}", "" + map.get(t2));
				}
			}
			if (check) {
				resultSql += t;
			} else {
				resultSql += s;
			}
		}
//		System.out.println(resultSql);

		return resultSql;
	}

	public List<Map> selectList(String sql1, Object... objects) {
		
		List<Map> list = new ArrayList<>();
		int cnt = 0;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			if(objects.length==0){
				sql=sql1;
			} else {
				sql = String.format(sql1,objects);
			}
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();
			while (rs.next()) {
				cnt++;
				Map<String, Object> map = new HashMap<>(); 
				for (int i = 1; i <= colCount; i++) {
					map.put(rsmd.getColumnName(i), rs.getObject(rsmd.getColumnName(i)));
				}
				list.add(map);
			}
			if (cnt == 0) {
				list = null;
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public Map selectOne(String sql1, Object... objects) {
		Map<String, Object> map = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			if(objects.length==0){
				sql=sql1;
			} else {
				sql = String.format(sql1,objects);
			}
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();
			if (rs.next()) {
				map = new HashMap<>();
				for (int i = 1; i <= colCount; i++) {
					map.put(rsmd.getColumnName(i), rs.getObject(rsmd.getColumnName(i)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	public int selectCount(String sql1, Object... objects) {
		int cnt = 0;
		int tmp = -1;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			if(objects.length==0){
				sql=sql1;
			} else {
				sql = String.format(sql1,objects);
			}
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();
			while (rs.next()) {
				cnt++;
				if (rs.getObject(1) instanceof java.math.BigDecimal) {
					tmp = rs.getInt(1);
				}
			}
			if (colCount == 1 && cnt == 1 && tmp != -1) {
				cnt = tmp;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}

	public int update(String sql1, Object... objects) {
		int rst = 0; // ����Ʈ : 0
		try {
			conn = dataSource.getConnection();
			String sql = "";
			if(objects.length==0){
				sql=sql1;
			} else {
				sql = String.format(sql1,objects);
			}
			PreparedStatement ps = conn.prepareStatement(sql);

			rst = ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rst;
	}

	public List<Map> selectList(String sql1, Map<String, Object> map) {
		String sql = sqlConverter(sql1, map);
		return selectList(sql);
	}

	public Map selectOne(String sql1, Map<String, Object> map) {
		String sql = sqlConverter(sql1, map);
		return selectOne(sql);
	}

	public int selectCount(String sql1, Map<String, Object> map) {
		String sql = sqlConverter(sql1, map);
		return selectCount(sql);
	}

	public int update(String sql1, Map<String, Object> map) {
		String sql = sqlConverter(sql1, map);
		return update(sql);
	}
}
// public Object select(String type, String sql, Object... objects) {
// String type1 = type.toUpperCase();
// if (type1.equals("LIST") || type1.equals("JAVA.UTIL.LIST")) {
// return selectList(sql,objects);
// } else if (type1.equals("MAP") || type1.equals("JAVA.UTIL.HASHMAP") ||
// type1.equals("HASHMAP")) {
// return selectOne(sql,objects);
// } else if (type1.equals("INT") || type1.equals("INTEGER") ||
// type1.equals("JAVA.LANG.INTEGER")) {
// return selectCount(sql,objects);
// } else {
// return null;
// }
// }
