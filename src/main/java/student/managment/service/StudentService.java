package student.managment.service;

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
	
	public List<Student> getStudent() {
		return (List<Student>) studentRepository.findAll();
	}

	public Student updateStudent(Student student) {
		return studentRepository.save(student);
	}

	public void delteStudent(Long id) {
		studentRepository.deleteById(id);
		
	}
	
}
