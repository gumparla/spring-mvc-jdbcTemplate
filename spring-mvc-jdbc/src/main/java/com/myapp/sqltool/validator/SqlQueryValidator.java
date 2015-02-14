package com.myapp.sqltool.validator;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.myapp.sqltool.model.SqlQuerySearch;

public class SqlQueryValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return SqlQuerySearch.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sqlQuery",
				"valid.sqlQuery");
		SqlQuerySearch querySearch = (SqlQuerySearch) arg0;
		if (querySearch != null) {
			String queryStr = querySearch.getSqlQuery();
			if (!queryStr.isEmpty()) {
				if (queryStr.length() < 10) {
					errors.rejectValue("sqlQuery", "valid.sqlQuery.length");
				}
				if (!StringUtils.startsWithIgnoreCase(queryStr, "SELECT")) {
					errors.rejectValue("sqlQuery",
							"valid.sqlQuery.Invalid.Operation");
				}
			}
		}
	}
}
