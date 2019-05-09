package com.senchuk.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "interest_program")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InterestProgram {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;


    @ManyToOne
    private ReferenceData program;
    @ManyToOne
    private ReferenceData term;

    private String interest;
}
