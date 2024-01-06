package student.managment.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import student.managment.entity.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long>{

}
