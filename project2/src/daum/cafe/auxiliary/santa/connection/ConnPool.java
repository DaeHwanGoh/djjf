/**
 * 
 */
package daum.cafe.auxiliary.santa.connection;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 산타 커넥션을 JDBC 커넥션으로 구현한 구현체를 리턴하는 클래스.
 * SantaConnectionAdapter는 예제의 범위를 넘어서므로 프로젝트의 의존성 확산을 막기위해 원소스는 주석처리.
 * 
 */
public class ConnPool {

	private static final Logger logger = Logger.getLogger(ConnPool.class);

	public static Connection getUConnection() throws SQLException {
		Connection conn = null;
		//		try {
		//			conn = new SantaConnectionAdapter("udb");
		//		} catch (SQLException e) {
		//			logger.error(e);
		//			closeConnection(conn);
		//			throw e;
		//		}
		
		return conn;
	}

	public static Connection getDConnection() throws SQLException {
		return getDConnection(true);
	}

	public static Connection getDConnection(boolean isWritable) throws SQLException {
		Connection conn = null;
		//		try {
		//			conn = new SantaConnectionAdapter("ddb");
		//		} catch (SQLException e) {
		//			logger.error(e);
		//			closeConnection(conn);
		//			throw e;
		//		}

		return conn;
	}

	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
	}

	public static void rollback(Connection conn) {
		if (conn != null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
	}

}
