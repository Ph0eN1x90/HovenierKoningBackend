package com.hovenierkoning.backend.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tree")
public class Tree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "treenumber", nullable = false)
    private Long treenumber;  
    @Column(name = "treetype", nullable = false)
    private String treetype;
    @Column(name = "diameter", nullable = false)
    private double diameter;
    @Column(name = "height", nullable = false)
    private double height;
    @Column(name = "date_finished")
    private LocalDate date_finished;
    @Column(name = "finished", nullable = false)
    private Boolean finished;
    @Column(name = "created", nullable = false)
    private LocalDateTime created = LocalDateTime.now();
    @Column(name = "comment")
    private String comment;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany(mappedBy = "tree", cascade = CascadeType.ALL, orphanRemoval = true, fetch = jakarta.persistence.FetchType.EAGER)
    private List<TreeImage> treeimage;  
}