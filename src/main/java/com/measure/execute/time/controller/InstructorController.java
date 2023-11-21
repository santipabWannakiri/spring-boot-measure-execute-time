package com.measure.execute.time.controller;


import com.measure.execute.time.model.Instructor;
import com.measure.execute.time.service.InstructorService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
public class InstructorController {
    private InstructorService instructorService;
    private final Timer timer;

    @Autowired
    public InstructorController(InstructorService instructorService, MeterRegistry meterRegistry) {
        this.instructorService = instructorService;
        this.timer = Timer.builder("transaction.execution.time").register(meterRegistry);
    }

    @GetMapping("/instructor")
    public ResponseEntity<?> getInstructorById(@RequestParam("id") int id) {
        Timer.Sample sample = Timer.start();
        Optional<Instructor> instructorOptional = instructorService.findById(id);
        double responseTimeInMilliSeconds = timer.record(() -> sample.stop(timer) / 1_000_000_000.0);
        System.out.println(" API response time: " + responseTimeInMilliSeconds + " Sec");

        if (instructorOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(instructorOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("[]");
        }

    }

    @GetMapping("/instructors")
    public ResponseEntity<?> getAllInstructor() {
        List<Instructor> listResult = instructorService.findAll();
        if (listResult.size() != 0) {
            return ResponseEntity.status(HttpStatus.OK).body(listResult);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("[]");
        }
    }
}
