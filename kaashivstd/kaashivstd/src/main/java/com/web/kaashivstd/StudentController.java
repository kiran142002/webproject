package com.web.kaashivstd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class StudentController {
	@Autowired
	StudentRepository repo;
	
	@GetMapping("/")
	public String GetAll(Model m)
	{
		 List<StudentModel> list=repo.all_student();
		 m.addAttribute("stdlist", list);
		 return "index";
	}
	
	@GetMapping("/edit/{id}")
	public String GetOne(@PathVariable int id,Model m)
	{
		StudentModel std=(StudentModel)repo.one_student(id);
		 m.addAttribute("stdobj", std);
		return "edit";
	}

	@GetMapping("/signup")
	public String Insert_Students1()
	{
		 

		 return "signup";
	}
	
	@PostMapping("/sign-post")
	public String Insert_Students2(@ModelAttribute StudentModel std) {
		if(std!=null) {
			repo.sp_insert(std.getEmail(), std.getPassword(), std.getName(), std.getCourse(), std.getFees());
			return "redirect:/";
		}
		return "redirect:/signup";
	
	}
	@GetMapping("/login")
	public String Login_Students1()
	{
		 

		 return "login";
	}
	
	@PostMapping("/login-post")
	public String Login_Students2(@ModelAttribute StudentModel std) {
		if(std!=null) {
			repo.login_student(std.getEmail(), std.getPassword());
			return "redirect:/";
		}
		return "redirect:/login";
	
	}
	
	@PutMapping("/edit-put/{id}")
	public String Update_Students(@PathVariable int id,@ModelAttribute StudentModel std) {
		if(std!=null) {
			repo.sp_update(id, std.getPassword(), std.getName(), std.getCourse(), std.getFees());
			return "redirect:/";
		}
		return "redirect:/edit/"+id;
	
	}
	
	@DeleteMapping("/delete/{id}")
	public String Delete_Student(@PathVariable int id) {
		{
			repo.sp_delete(id);
			return "redirect:/";
		}
	
	}

}
