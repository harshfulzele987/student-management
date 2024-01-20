package student.managment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import student.managment.entity.Student;
import student.managment.service.StudentService;

@RestController
@Validated
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/create")
	public Student createStudent( @Valid @RequestBody Student student) {
		return studentService.createStudent(student);
	}
	
	@GetMapping("/")
	public List<Student> getStudent(){
		return studentService.getStudent();
	}
	
	@PutMapping("/update")
	public  Student updateStudent(@RequestBody Student student){
			return studentService.updateStudent(student);
	}
	
	@DeleteMapping("/{id}")
	public boolean deleteStudent(@PathVariable Long id) {
		return studentService.delteStudent(id);
	}
	
}
