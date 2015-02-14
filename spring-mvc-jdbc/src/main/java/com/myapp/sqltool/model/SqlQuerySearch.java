package com.myapp.sqltool.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlQuerySearch {

	public String sqlQuery;
	public final List<String> headerNames;
	public final Map<Integer, List<String>> results;

	public SqlQuerySearch() {
		headerNames = new ArrayList<String>();
		results = new HashMap<Integer, List<String>>();
	}

	public boolean addHeaderName(String headerName) {
		return headerNames.add(headerName);
	}

	public void addResults(Integer recordNbr, List<String> colValues) {
		if (results.get(recordNbr) == null) {
			results.put(recordNbr, new ArrayList<String>());
		}
		results.get(recordNbr).addAll(colValues);
	}

	public List<String> getHeaderNames() {
		return headerNames;
	}

	public Map<Integer, List<String>> getResults() {
		return results;
	}

	public String getSqlQuery() {
		return sqlQuery;
	}

	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}
}