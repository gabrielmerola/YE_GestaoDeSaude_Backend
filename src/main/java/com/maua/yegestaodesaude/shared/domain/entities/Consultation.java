package com.maua.yegestaodesaude.shared.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    private String name;

    @Column(nullable = false)
    private String expertise;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String dateReturn;

    private String description;

}
