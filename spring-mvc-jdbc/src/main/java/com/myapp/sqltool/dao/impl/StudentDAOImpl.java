package com.myapp.sqltool.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.myapp.sqltool.dao.StudentDAO;
import com.myapp.sqltool.model.Student;

@Service
public class StudentDAOImpl implements StudentDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Student> getAllstudents() {
		String query = "select id,name,password,email,mobno,dob from student";
		List<Student> empList = new ArrayList<Student>();

		List<Map<String, Object>> empRows = jdbcTemplate.queryForList(query);

		for (Map<String, Object> empRow : empRows) {
			Student emp = new Student();
			emp.setId(Integer.parseInt(String.valueOf(empRow.get("id"))));
			emp.setName(String.valueOf(empRow.get("name")));
			emp.setPassword(String.valueOf(empRow.get("password")));
			emp.setEmail(String.valueOf(empRow.get("email")));
			emp.setMobno(Long.parseLong(empRow.get("mobno").toString()));
			emp.setDob((Date) (empRow.get("dob")));
			empList.add(emp);
		}
		return empList;
	}

	@Override
	public Student getOnestudents(int Studentid) {
		String query = "select id, name, password,email,dob,mobno from student where id = ?";
		// using RowMapper anonymous class, we can create a separate RowMapper
		// for reuse
		Student emp = jdbcTemplate.queryForObject(query,
				new Object[] { Studentid }, new RowMapper<Student>() {

					@Override
					public Student mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Student emp = new Student();
						emp.setId((rs.getInt("id")));
						emp.setName(rs.getString("name"));
						emp.setPassword(rs.getString("password"));
						emp.setEmail(rs.getString("email"));
						emp.setMobno(rs.getLong("mobno"));
						emp.setDob(rs.getDate("dob"));
						return emp;

					}
				});

		return emp;
	}

	@Override
	public boolean saveStudents(Student student) {

		String query = "insert into student (id, name, password ,email,mobno ,dob) values (?,?,?,?,?,?)";

		Object[] args = new Object[] { student.getId(), student.getName(),
				student.getPassword(), student.getEmail(), student.getMobno(),
				student.getDob() };

		int out = jdbcTemplate.update(query, args);

		if (out != 0) {
			System.out.println("Employee saved with id=" + student.getId());
			return true;
		} else
			System.out.println("Employee save failed with id="
					+ student.getId());
		return false;

	}

	@Override
	public boolean Updatestudents(Student student) {

		String query = "update student set name=?, password=? , email=? , mobno=?,dob=?  where id=?";
		Object[] args = new Object[] { student.getName(),
				student.getPassword(), student.getEmail(), student.getMobno(),
				student.getDob(), student.getId() };

		int out = jdbcTemplate.update(query, args);
		if (out != 0) {
			System.out.println("student updated with id=" + student.getId());
			return true;
		} else
			System.out.println("No Employee found with id=" + student.getId());
		return false;
	}

	@Override
	public boolean deletestudents(int studentid) {

		String query = "delete from student where id=?";
		int out = jdbcTemplate.update(query, studentid);
		if (out != 0) {

			System.out.println("Employee deleted with id=" + studentid);
			return true;
		}
		return false;

	}

}
