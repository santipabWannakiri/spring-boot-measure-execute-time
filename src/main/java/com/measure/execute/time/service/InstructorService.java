package com.measure.execute.time.service;


import com.measure.execute.time.model.Instructor;

import java.util.List;
import java.util.Optional;

public interface InstructorService {

    public void save(Instructor instructor);
    public Optional<Instructor> findById(int id);
    public List<Instructor> findAll();
    public void deleteById(int id);

}
