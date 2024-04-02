package com.maua.yegestaodesaude.shared.domain.entities;

import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "consultation")
@Data
public class Consultation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Client client;

    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z]*$", message = "O campo [name] deve conter apenas letras")
    private String name;

    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-z]*$", message = "O campo [especialidade] deve conter apenas letras")
    private String expertise;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Date dateReturn;

    private String description;

}