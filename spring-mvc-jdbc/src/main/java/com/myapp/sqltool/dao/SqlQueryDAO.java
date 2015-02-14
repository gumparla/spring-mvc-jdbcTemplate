package com.myapp.sqltool.dao;

import com.myapp.sqltool.model.SqlQuerySearch;

public interface SqlQueryDAO 
{
	public SqlQuerySearch executeSqlQuery(final String sqlQuery);

}
