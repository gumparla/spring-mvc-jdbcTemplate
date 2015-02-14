package com.myapp.sqltool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myapp.sqltool.dao.impl.StudentDAOImpl;
import com.myapp.sqltool.model.Student;

@Controller
public class AccountController {

	@Autowired
	public StudentDAOImpl studentImpl;

	@RequestMapping(value = "/student.html", method = RequestMethod.GET)
	public ModelAndView show_users(Model model) {
		List<Student> students = studentImpl.getAllstudents();
		ModelAndView modelAndView = new ModelAndView("students");
		model.addAttribute("current", "students");
		modelAndView.addObject("students", students);
		return modelAndView;
	}

	@RequestMapping(value = "/account.html", method = RequestMethod.GET)
	public ModelAndView manage_account(Model model) {
		List<Student> students = studentImpl.getAllstudents();
		ModelAndView modelAndView = new ModelAndView("account");
		model.addAttribute("current", "shopping");
		modelAndView.addObject("students", students);
		return modelAndView;
	}

	@RequestMapping(value = "/remove/{id}.html", method = RequestMethod.GET)
	public String manage_account_remove(@PathVariable("id") Integer id,
			Model model) {
		model.addAttribute("current", "shopping");
		boolean success = studentImpl.deletestudents(id);
		return "redirect:/account.html?success=" + success;
	}

	@RequestMapping(value = "/update/{id}.html", method = RequestMethod.GET)
	public ModelAndView manage_account_update(@PathVariable("id") Integer id,
			Model model) {
		Student student = studentImpl.getOnestudents(id);
		ModelAndView modelAndView = new ModelAndView("account");
		model.addAttribute("current", "shopping");
		modelAndView.addObject("studentdata", student);
		return modelAndView;
	}

	@RequestMapping(value = "/submitupdateddata.html", method = RequestMethod.POST)
	public String submit_updateddata(
			@Validated @ModelAttribute("student1") Student student,
			BindingResult result) {
		boolean bool = studentImpl.Updatestudents(student);
		return "redirect:/account.html?success=" + bool;
	}

}
