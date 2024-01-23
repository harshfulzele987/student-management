package student.managment;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import student.managment.controller.StudentControllerTest;
import student.managment.service.StudentServiceTest;

@SpringBootTest(classes = {StudentControllerTest.class,
		StudentServiceTest.class})
class ManagmentApplicationTests {

}
