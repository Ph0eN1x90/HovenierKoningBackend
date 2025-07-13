package com.hovenierkoning.backend.model;


import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false)
    private Long id;
    @Column(name = "streetname",nullable = false)
    private String streetname;  
    @Column(name = "city",nullable = false)
    private String city;
    @Column(name = "zipcode",nullable = false)
    private String zipcode;
    @Column(name = "housenumber",nullable = false)
    private int housenumber;
    @Column(name = "finished",nullable = false)
    private Boolean finished;
    @Column(name = "date_finished")
    private LocalDate date_finished;
}