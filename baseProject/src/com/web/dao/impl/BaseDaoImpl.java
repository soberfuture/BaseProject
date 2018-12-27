package com.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.web.bean.QueryParameter;
import com.web.dao.BaseDao;
import com.web.utils.DBPoolConnection;
import com.web.utils.GenerateSQL;

public class BaseDaoImpl implements BaseDao {

	private DBPoolConnection dbPoolConnection = DBPoolConnection.getInstance();
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	@Override
	public List<?> query(QueryParameter parameters) {
		try {
			con = dbPoolConnection.getConnection();
			String sql = GenerateSQL.pagingQuery(parameters);
			System.out.println(sql);
			ps = con.prepareStatement(sql);
			setParameters(parameters.getParameters());
			rs = ps.executeQuery();
			return getQueryResult();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	}

	private List<?> getQueryResult() {
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			List<Map<String, Object>> list = new ArrayList<>();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<>();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					map.put(rsmd.getColumnName(i), rs.getObject(i));
				}
				list.add(map);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void setParameters(Map<String, Object> map) {
		Collection<Object> values = map.values();
		Iterator<Object> iterator = values.iterator();
		try {
			int i = 0;
			while (iterator.hasNext()) {
				ps.setObject(++i, iterator.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public int insert(QueryParameter parameters) {
		try {
			con = dbPoolConnection.getConnection();
			String sql = GenerateSQL.insert(parameters);
			System.out.println(sql);
			ps = con.prepareStatement(sql);
			setParameters(parameters.getParameters());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return 0;
	}

	@Override
	public int update(QueryParameter parameters) {
		try {
			con = dbPoolConnection.getConnection();
			String sql = GenerateSQL.update(parameters);
			System.out.println(sql);
			ps = con.prepareStatement(sql);
			setParameters(parameters.getParameters());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return 0;
	}
	@Override
	public int delete(QueryParameter parameters) {
		try {
			con = dbPoolConnection.getConnection();
			String sql = GenerateSQL.delete(parameters);
			System.out.println(sql);
			ps = con.prepareStatement(sql);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return 0;
	}
	@Override
	public int count(QueryParameter parameters) {
		try {
			con = dbPoolConnection.getConnection();
			String sql = GenerateSQL.queryCount(parameters);
			System.out.println(sql);
			ps = con.prepareStatement(sql);
			setParameters(parameters.getParameters());
			rs = ps.executeQuery();
			int count = 0;
			while (rs.next()) {
				count = rs.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return 0;
	}

	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<?> queryByConditions(QueryParameter parameters) {
		try {
			con = dbPoolConnection.getConnection();
			String sql = GenerateSQL.queryByConditions(parameters);
			System.out.println(sql);
			ps = con.prepareStatement(sql);
			setParameters(parameters.getParameters());
			rs = ps.executeQuery();
			return getQueryResult();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	}
}
