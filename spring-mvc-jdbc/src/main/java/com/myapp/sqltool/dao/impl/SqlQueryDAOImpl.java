package com.myapp.sqltool.dao.impl;

import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.myapp.sqltool.dao.SqlQueryDAO;
import com.myapp.sqltool.model.SqlQuerySearch;

@Service
public class SqlQueryDAOImpl implements SqlQueryDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public SqlQuerySearch executeSqlQuery(String sqlQuery) {
		final SqlQuerySearch sqlQuerySearch = new SqlQuerySearch();
		jdbcTemplate.query(sqlQuery, new RowCallbackHandler() {
			public void processRow(java.sql.ResultSet rs)
					throws java.sql.SQLException {
				int columnCount = sqlQuerySearch.getHeaderNames().size();
				List<String> cols = null;
				if (CollectionUtils.isEmpty(sqlQuerySearch.getHeaderNames())) {
					ResultSetMetaData resultSetMetaData = rs.getMetaData();
					columnCount = resultSetMetaData.getColumnCount();
					for (int i = 1; i <= columnCount; i++) {
						System.out.println("Header Name	-" + resultSetMetaData.getColumnName(i));
						sqlQuerySearch.addHeaderName(resultSetMetaData
								.getColumnName(i).toUpperCase());
					}
				}
				cols = new ArrayList<String>(columnCount);
				for (int i = 1; i <= columnCount; i++) {
					cols.add(rs.getString(i));
				}
				sqlQuerySearch.addResults(rs.getRow(), cols);
			};
		});
		return sqlQuerySearch;
	}
}
