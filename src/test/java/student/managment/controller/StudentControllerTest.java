package student.managment.controller;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import student.managment.entity.ClassRange;
import student.managment.entity.Student;
import student.managment.service.StudentService;


@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {
	private MockMvc mockMvc;
	
	
	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectIdWriter = objectMapper.writer();
	
	
	@Mock
	private StudentService studentService;
	
	@InjectMocks 
	private StudentController studentController;
	
	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
	}
	
	public Student dummyData() {
		Student student = new Student();
		student.setId(1L);
		student.setName("Harsh");
		student.setStudentClass(ClassRange.Class_10);
		student.setAddress("Nagpur");
		return student;
	}
	
	@Test
	@DisplayName("Test Case for Get Student Details")
	public void getStudentTest() throws UnsupportedEncodingException, Exception {
		List<Student> result  = new ArrayList<>();
		result.add(this.dummyData());
		Mockito.when(studentService.getStudent(1L)).thenReturn(result);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/student/?id=1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$" , hasSize(1)))
				.andReturn().getResponse().getContentAsString();
		
		Mockito.when(studentService.getStudent(null)).thenReturn(result);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/student")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$" , hasSize(1)))
				.andReturn().getResponse().getContentAsString();
		
		List<Student> dummyResult = new ArrayList<>();
		Mockito.when(studentService.getStudent(null)).thenReturn(dummyResult);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/student")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andReturn().getResponse().getContentAsString();
	}
	
	@Test
	@DisplayName("Test Case for Create Student")
	public void CreateStudentTest() throws UnsupportedEncodingException, Exception {
		
		Student student1 = new Student();
		student1.setName("nitin");
		student1.setStudentClass(ClassRange.Class_1);
		student1.setAddress("mumbai");
		
		Student student2 = new Student();
		student2 = null;
		
		Mockito.when(studentService.createStudent(Mockito.any())).thenReturn(this.dummyData());
		
		String content = objectIdWriter.writeValueAsString(student1);
		String content_empty = objectIdWriter.writeValueAsString(student2);
		
		MockHttpServletRequestBuilder mockRequestBuilder = MockMvcRequestBuilders.post("/student")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(content);

				mockMvc.perform(mockRequestBuilder)
						.andExpect(status().isCreated())
						.andExpect(jsonPath("$.id",is(1)))
						.andReturn().getResponse().getContentAsString();
		
				
		MockHttpServletRequestBuilder mockRequestBuilder_empty = MockMvcRequestBuilders.post("/student")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.content(content_empty);
				
				mockMvc.perform(mockRequestBuilder_empty)
				.andExpect(status().isBadRequest())
				.andReturn().getResponse().getContentAsString();
				
	}
	
	@Test
	@DisplayName("Test Case for Update Student")
	public void updateStudentTest() throws UnsupportedEncodingException, Exception {
		
		
		Mockito.when(studentService.updateStudent(Mockito.any())).thenReturn(this.dummyData());
		
		String content = objectIdWriter.writeValueAsString(this.dummyData());
		
		MockHttpServletRequestBuilder mockRequestBuilder = MockMvcRequestBuilders.put("/student")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(content);
		
		mockMvc.perform(mockRequestBuilder)
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id",is(1)))
				.andReturn().getResponse().getContentAsString();
		
//		Mockito.when(studentService.updateStudent(null)).thenReturn(null);
		
		mockMvc.perform(MockMvcRequestBuilders
				.put("/student")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andReturn().getResponse().getContentAsString();
	}
	
	@Test
	@DisplayName("Test Case For delete Student")
	public void deleteStudentTest() throws UnsupportedEncodingException, Exception {
		
		
		Mockito.when(studentService.delteStudent(Mockito.anyLong())).thenReturn(true);
		
		
		  mockMvc.perform(MockMvcRequestBuilders
		            .delete("/student/1")
		            .contentType(MediaType.APPLICATION_JSON))
		            .andExpect(status().isOk());
		  
		  
		  Mockito.when(studentService.delteStudent(Mockito.anyLong())).thenReturn(false);
			
			
		  mockMvc.perform(MockMvcRequestBuilders
		            .delete("/student/1")
		            .contentType(MediaType.APPLICATION_JSON))
		            .andExpect(status().isBadRequest());
		  
	}
	
	
	
	
}
