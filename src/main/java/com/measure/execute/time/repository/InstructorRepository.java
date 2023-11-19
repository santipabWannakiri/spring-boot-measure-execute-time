package com.measure.execute.time.repository;


import com.measure.execute.time.model.Instructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("InstructorRepository")
public interface InstructorRepository extends CrudRepository<Instructor,Integer> {
}
