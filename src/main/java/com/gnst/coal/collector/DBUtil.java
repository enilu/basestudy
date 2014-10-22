package com.gnst.coal.collector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库操作类
 * <p>
 * 2014年9月23日
 * 
 * @author enilu(82552623@qq.com)
 * 
 */
public class DBUtil {
	Connection conn = null;
	PreparedStatement st = null;
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/test";
	private String user = "root";
	private String pass = "root";

	public DBUtil() throws Exception {
		Class.forName(driver);
		conn = DriverManager.getConnection(url, user, pass);
	}

	public void execute(String sql) {

		try {
			st = conn.prepareStatement(sql);
			st.execute();
			st.close();
		} catch (SQLException e) {
			System.out.println(sql);
			System.out.println(e.getLocalizedMessage());

		}

	}

	public List<String> queryIds() {
		List<String> idList = new ArrayList<String>();
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT id FROM mk where fuzerenl=''");
			rs = st.executeQuery();

			while (rs.next()) {
				idList.add(rs.getString("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return idList;
	}

	public void deleteById(String id) {
		try {
			st = conn.prepareStatement("delete from  mk where id=?");
			st.setString(1, id);
			st.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void close() throws Exception {
		if (conn != null) {
			conn.close();
		}

	}
}
