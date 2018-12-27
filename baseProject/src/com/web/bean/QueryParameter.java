package com.web.bean;

import java.util.List;
import java.util.Map;

public class QueryParameter {
	private Class<?> clazz;
	private Map<String, Object> parameters;
	private List<Integer> list;
	private int from = 0;
	private int rows = 10;
	private int page = 1;

	public List<Integer> getList() {
		return list;
	}

	public void setList(List<Integer> list) {
		this.list = list;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public int getFrom() {
		from = (page - 1) * rows;
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
