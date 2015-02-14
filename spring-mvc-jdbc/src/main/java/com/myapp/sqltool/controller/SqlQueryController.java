package com.myapp.sqltool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myapp.sqltool.dao.SqlQueryDAO;
import com.myapp.sqltool.model.SqlQuerySearch;

@Controller
public class SqlQueryController {
	@Autowired
	public SqlQueryDAO sqlQueryDAOImpl;

	@Autowired
	@Qualifier("sqlQueryValidator")
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/submitQuery.html", method = RequestMethod.POST)
	public ModelAndView sqlQuery(Model model,
			@Validated SqlQuerySearch sqlQuerySearch, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("queryUtility");
		if (!result.hasErrors()) {
			SqlQuerySearch results = sqlQueryDAOImpl
					.executeSqlQuery(sqlQuerySearch.getSqlQuery());
			results.setSqlQuery(sqlQuerySearch.getSqlQuery());
			modelAndView = new ModelAndView("queryUtility");
			model.addAttribute("current", "sqlQuery");
			modelAndView.addObject("sqlQuerySearch", results);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/sqlQuery.html", method = RequestMethod.GET)
	public ModelAndView manage_account(Model model) {
		SqlQuerySearch querySearch = new SqlQuerySearch();
		model.addAttribute("sqlQuerySearch", querySearch);
		model.addAttribute("current", "sqlQuery");
		ModelAndView modelAndView = new ModelAndView("queryUtility");
		return modelAndView;
	}
}
