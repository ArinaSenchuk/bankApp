package com.senchuk.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "program_interest")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProgramInterest {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;


    private long program_id;

    private long term_id;

    private String interest;
}
