package com.measure.execute.time.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "instructor")
@Data
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String identificationNumber;
    private String firstName;
    private String lastName;
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;

    public Instructor() {
    }

    public Instructor(String identificationNumber, String firstName, String lastName, String email, InstructorDetail instructorDetail) {
        this.identificationNumber = identificationNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.instructorDetail = instructorDetail;
    }
}