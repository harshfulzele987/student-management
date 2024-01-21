package student.managment.controller;

import java.util.List;

import org.apache.coyote.http11.Http11InputBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import student.managment.entity.Student;
import student.managment.service.StudentNotCreatedException;
import student.managment.service.StudentNotFoundException;
import student.managment.service.StudentNotUpdateException;
import student.managment.service.StudentService;

@RestController
@Validated
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createStudent( @RequestBody Student student) throws StudentNotCreatedException {
		Student result =  studentService.createStudent(student);
		if(result!=null) return new ResponseEntity<Student>(result , HttpStatus.CREATED);
		return new ResponseEntity<>("Not created" , HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getStudent(@RequestParam(value = "id" ,required = false) Long id) throws StudentNotFoundException{
		List<Student> result = studentService.getStudent(id);
		if(!result.isEmpty()) return  new ResponseEntity<List<Student>>(result , HttpStatus.OK);
		return new ResponseEntity<>("error occured",HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/update")
	public  ResponseEntity<?> updateStudent(@RequestBody Student student) throws StudentNotUpdateException{
		Student result = studentService.updateStudent(student);
		if(result !=null) return  new ResponseEntity<Student>(result , HttpStatus.CREATED);
		return new ResponseEntity<>("error occured to update",HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable Long id) throws StudentNotFoundException {
		boolean result = studentService.delteStudent(id);
		if(result) return new ResponseEntity<Boolean>(result , HttpStatus.OK);
		return new ResponseEntity<>("Not able to delete" , HttpStatus.NO_CONTENT);
		
	}
	
}
