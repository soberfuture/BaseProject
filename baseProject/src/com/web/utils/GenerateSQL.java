package com.web.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.web.bean.QueryParameter;
import com.web.bean.User;

public class GenerateSQL {

	public static String queryByConditions(QueryParameter param) {
		StringBuilder sb = new StringBuilder("select * from ");
		String table = new StringBuilder()
				.append(String.valueOf(param.getClazz().getSimpleName().charAt(0)).toLowerCase())
				.append(param.getClazz().getSimpleName().substring(1)).toString();
		String where = getWhere(param.getParameters());
		sb.append(table).append(where);
		return sb.toString();
	}

	public static String pagingQuery(QueryParameter param) {
		StringBuilder sb = new StringBuilder("select * from ");
		String table = new StringBuilder()
				.append(String.valueOf(param.getClazz().getSimpleName().charAt(0)).toLowerCase())
				.append(param.getClazz().getSimpleName().substring(1)).toString();
		String where = getWhere(param.getParameters());
		sb.append(table).append(where).append("limit ").append(param.getFrom()).append(",").append(param.getRows());
		return sb.toString();
	}

	private static String getWhere(Map<String, Object> paramaters) {
		if (paramaters.isEmpty()) {
			return " ";
		} else {
			StringBuilder sb = new StringBuilder(" where ");
			Set<String> set = paramaters.keySet();
			for (String string : set) {
				sb.append(string).append("=? and ");
			}
			sb.delete(sb.length() - 5, sb.length() - 1);
			return sb.toString();
		}
	}

	public static String queryCount(QueryParameter param) {
		StringBuilder sb = new StringBuilder("select count(*) from ");
		String table = new StringBuilder()
				.append(String.valueOf(param.getClazz().getSimpleName().charAt(0)).toLowerCase())
				.append(param.getClazz().getSimpleName().substring(1)).toString();
		String where = getWhere(param.getParameters());
		sb.append(table).append(where);
		return sb.toString();
	}

	public static String insert(QueryParameter param) {
		StringBuilder sb = new StringBuilder();
		String table = new StringBuilder()
				.append(String.valueOf(param.getClazz().getSimpleName().charAt(0)).toLowerCase())
				.append(param.getClazz().getSimpleName().substring(1)).toString();
		sb.append("insert into ").append(table).append(getColumns(param.getParameters()));
		return sb.toString();
	}

	private static String getColumns(Map<String, Object> map) {
		StringBuilder sb = new StringBuilder("(");
		StringBuilder values = new StringBuilder("values(");
		Set<String> set = map.keySet();
		for (String string : set) {
			sb.append(string).append(",");
			values.append("?,");
		}
		sb.replace(sb.length() - 1, sb.length(), ") ")
				.append(values.replace(values.length() - 1, values.length(), ")"));
		return sb.toString();
	}

	public static String delete(QueryParameter param) {
		String table = new StringBuilder()
				.append(String.valueOf(param.getClazz().getSimpleName().charAt(0)).toLowerCase())
				.append(param.getClazz().getSimpleName().substring(1)).toString();
		StringBuilder sb = new StringBuilder("delete from ").append(table).append(" where id in (");
		for (int i = 0; i < param.getList().size(); i++) {
			sb.append(param.getList().get(i)).append(",");
		}
		sb.replace(sb.length() - 1, sb.length(), ") ");
		return sb.toString();
	}

	public static String update(QueryParameter param) {
		StringBuilder sb = new StringBuilder();
		String table = new StringBuilder()
				.append(String.valueOf(param.getClazz().getSimpleName().charAt(0)).toLowerCase())
				.append(param.getClazz().getSimpleName().substring(1)).toString();
		sb.append("update ").append(table).append(getSet(param.getParameters()));
		sb.deleteCharAt(sb.length() - 1).append(" where id in (");
		for (int i = 0; i < param.getList().size(); i++) {
			sb.append(param.getList().get(i)).append(",");
		}
		sb.replace(sb.length() - 1, sb.length(), ")");
		return sb.toString();
	}

	private static String getSet(Map<String, Object> map) {
		StringBuilder sb = new StringBuilder(" set ");
		Set<String> set = map.keySet();
		for (String string : set) {
			sb.append(string).append("=?, ");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	public static void main(String[] args) {
		long starttime = System.currentTimeMillis();
		QueryParameter parameter = new QueryParameter();
		Map<String, Object> map = new HashMap<>();
		map.put("username", "333");
		map.put("password", "haha");
		List<Integer> list = new ArrayList<>();
		list.add(1);
		parameter.setClazz(User.class);
		parameter.setParameters(map);
		parameter.setList(list);
		String sql = update(parameter);
		System.out.println(sql);
		DBPoolConnection poolConnection = DBPoolConnection.getInstance();
		try {
			DruidPooledConnection con = poolConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setObject(1, "username");
			ps.setObject(2, "123334");
			int result = ps.executeUpdate();
			System.out.println(result);
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("花费时间：" + (System.currentTimeMillis() - starttime));
	}
}
