package student.managment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student.managment.entity.Student;
import student.managment.repo.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	public Student createStudent(Student student) throws StudentNotCreatedException {
		Student result = null;
		try {
			result = studentRepository.save(student);
			if(result == null) {
				throw new StudentNotCreatedException("Student object is empty");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Student> getStudent(Long id) throws StudentNotFoundException {
		List<Student> response = new ArrayList<>();
		try {
			if(id == null) {
					return response = (List<Student>) studentRepository.findAll();			
			}
			  response.add(studentRepository.findById(id).get());
		}catch(Exception e) {
			 throw new StudentNotFoundException("Student with id not found");
		}
		return response;

	}

	public Student updateStudent(Student student) throws StudentNotUpdateException {
		Student result = null;
		try {
			result=	studentRepository.save(student);
		}catch(Exception e) {
			throw new StudentNotUpdateException("student not updated");
		}
		return result;
	}

	public boolean delteStudent(Long id) throws StudentNotFoundException {
		try {
		if(studentRepository.existsById(id)) {
			studentRepository.deleteById(id);
			return true;
		}
		return false;
		}catch(Exception e) {
			throw new StudentNotFoundException("Student id not found");
		}
		
	}
	
}
