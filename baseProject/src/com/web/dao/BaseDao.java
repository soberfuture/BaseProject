package com.web.dao;

import java.util.List;

import com.web.bean.QueryParameter;

public interface BaseDao {

	List<?> queryByConditions(QueryParameter parameters);

	List<?> query(QueryParameter parameters);

	int insert(QueryParameter parameters);

	int update(QueryParameter parameters);

	int delete(QueryParameter parameters);

	int count(QueryParameter parameters);
}
