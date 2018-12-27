package com.web.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.web.bean.QueryParameter;
import com.web.bean.User;
import com.web.dao.BaseDao;
import com.web.dao.impl.BaseDaoImpl;

public class Test {
	public static void main(String[] args) {
		long starttime = System.currentTimeMillis();
		QueryParameter parameter = new QueryParameter();
		Map<String, Object> map = new HashMap<>();
		map.put("id", "5");
		List<Integer> list = new ArrayList<>();
		list.add(2);
		parameter.setClazz(User.class);
		parameter.setParameters(map);
		parameter.setList(list);
		BaseDao baseDao = new BaseDaoImpl();
		List<?> list2 = baseDao.queryByConditions(parameter);
		System.out.println(list2.toString());
		System.out.println("花费时间：" + (System.currentTimeMillis() - starttime));
	}
}
