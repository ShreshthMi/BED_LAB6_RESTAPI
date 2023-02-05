package com.gl.studentmanagement.controller;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.studentmanagement.entity.Role;
import com.gl.studentmanagement.entity.Student;
import com.gl.studentmanagement.repository.RoleRepository;
import com.gl.studentmanagement.serviceImpl.StudentServiceImpl;
import com.gl.studentmanagement.serviceImpl.UserServiceImpl;

@Controller
@RequestMapping("/")
public class CustomStudentController {

	@Autowired
	StudentServiceImpl studentServiceImpl;

	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	RoleRepository roleRepository;

	@RequestMapping("/")
	public String helloController() {
		return "redirect:/listStudents";
	}

	@RequestMapping("/listStudents")
	public String getAllStudents(Model theModel, Principal user) {
		List<Student> students = studentServiceImpl.getAllStudents();
		theModel.addAttribute("students", students);
		String username = user.getName();
		Set<Role> roles = userServiceImpl.getRoles(username);
		Role adminRole = roleRepository.findRoleByName("ROLE_ADMIN");
		if (roles.contains(adminRole)) {
			return "listStudents";
		} else {
			return "listStudentsUser";
		}

	}

	@RequestMapping("/addStudent")
	public String addStudent(Model theModel) {
		Student student = new Student();
		theModel.addAttribute("student", student);
		theModel.addAttribute("formType", "Add Student");
		return "formStudent";
	}

	@RequestMapping("/deleteStudent")
	public String deleteStudent(@RequestParam("id") Long id) {
		studentServiceImpl.deleteStudentById(id);
		return "redirect:/listStudents";
	}

	@RequestMapping("/editStudent")
	public String editStudent(@RequestParam("id") Long id, Model theModel) {
		Student student = studentServiceImpl.getStudentById(id);
		theModel.addAttribute("student", student);
		theModel.addAttribute("formType", "Edit Student");
		return "formStudent";
	}

	@PostMapping("saveStudent")
	public String saveTicket(@RequestParam("id") Long id, @RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname, @RequestParam("course") String course,
			@RequestParam("country") String country) {

		Student student;
		if (id != 0) {
			student = studentServiceImpl.getStudentById(id);
			student.setFirstname(firstname);
			student.setLastname(lastname);
			student.setCourse(course);
			student.setCountry(country);
		} else {
			student = Student.builder().firstname(firstname).lastname(lastname).course(course).country(country).build();
		}
		studentServiceImpl.saveStudent(student);
		return "redirect:/listStudents";
	}

	@RequestMapping("/403")
	public String accessDenied(Principal user, Model theModel) {
		theModel.addAttribute("msg", "You " + user.getName() + " do not have permission to access this page!");
		return "403";
	}
}
