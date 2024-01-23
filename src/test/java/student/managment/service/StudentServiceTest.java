package student.managment.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import student.managment.entity.ClassRange;
import student.managment.entity.Student;
import student.managment.repo.StudentRepository;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

	@Mock
	private StudentRepository studentRepository;
	
	@InjectMocks
	private StudentService studentService;
	
	public Student dummyData() {
		Student student = new Student();
		student.setId(1L);
		student.setName("Harsh");
		student.setStudentClass(ClassRange.Class_10);
		student.setAddress("Nagpur");
		return student;
	}

	
	@Test
	public void getStudentTest() throws StudentNotFoundException {
		List<Student> result = new ArrayList<>();
		result.add(this.dummyData());
		Mockito.when(studentRepository.findAll()).thenReturn(result);
		result = studentService.getStudent(null);
		assertThat(result).isNotNull();
		
		Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(this.dummyData()));
		result = studentService.getStudent(1l);
		assertThat(result).isNotNull();
		
		Student student = new Student();
		Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
		result = studentService.getStudent(1l);
		assertThat(result).isNotNull();
	}
	
	@Test
	public void createStudentTest() throws StudentNotFoundException, StudentNotCreatedException {
		Student student = new Student();
		student.setName("mohit");
		student.setStudentClass(ClassRange.Class_2);
		student.setAddress("Kolkata");
		
		Mockito.when(studentRepository.save(student)).thenReturn(this.dummyData());
		Student result = studentService.createStudent(student);
		assertThat(result).isNotNull();
	}
	
	@Test
	public void updateStudentTest() throws StudentNotFoundException, StudentNotCreatedException, StudentNotUpdateException {
		Student student = new Student();
		student.setName("mohit");
		student.setStudentClass(ClassRange.Class_2);
		student.setAddress("Kolkata");
		
		Mockito.when(studentRepository.save(student)).thenReturn(this.dummyData());
		Student result = studentService.updateStudent(student);
		assertThat(result).isNotNull();
		
		Mockito.when(studentRepository.save(student)).thenThrow(new IllegalArgumentException("error"));
		Throwable exception = Assertions.assertThrows(StudentNotUpdateException.class, () -> studentService.updateStudent(student));
		Assertions.assertEquals("Error Occured", exception.getMessage());
	}
	
	@Test
	public void deleteStudentTest() throws StudentNotFoundException, StudentNotCreatedException {
		Long id = 1l;
		
		Mockito.when(studentRepository.existsById(id)).thenReturn(true);
		boolean result_first = studentService.delteStudent(id);
		assertThat(result_first).isTrue();
		
		
		Mockito.when(studentRepository.existsById(id)).thenReturn(false);
		boolean result_second = studentService.delteStudent(id);
		assertThat(result_second).isFalse();
		
		Mockito.when(studentRepository.existsById(id)).thenReturn(true);
		doNothing().when(studentRepository).deleteById(id);
		boolean result_third = studentService.delteStudent(id);
		assertThat(result_third).isTrue();
		
		Mockito.when(studentRepository.existsById(id)).thenThrow(new IllegalArgumentException("error"));
		Throwable exception = Assertions.assertThrows(StudentNotFoundException.class, () -> studentService.delteStudent(id));
		Assertions.assertEquals("Student id not found", exception.getMessage());
	}
	
	
	
}
