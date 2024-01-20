package student.managment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student.managment.entity.Student;
import student.managment.repo.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}
	
	public List<Student> getStudent(Long id) {
		List<Student> response = new ArrayList<>();
		if(id == null) {
			return response = (List<Student>) studentRepository.findAll();			
		}
		 response.add(studentRepository.findById(id).get());
		 return response;
	}

	public Student updateStudent(Student student) {
		return studentRepository.save(student);
	}

	public boolean delteStudent(Long id) {
		if(studentRepository.existsById(id)) {
			studentRepository.deleteById(id);
			return true;
		}
		return false;
		
	}
	
}
