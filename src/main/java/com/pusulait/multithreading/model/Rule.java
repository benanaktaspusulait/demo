package com.pusulait.multithreading.model;

import com.pusulait.multithreading.model.base.BaseEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "RULES")
@ToString
public class Rule extends BaseEntity implements   Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "DEFINITION", length = 4096)
    private String definition;

    @Column(name = "NAME", length = 250)
    private String name;

    @Lob
    @Column(name = "FUNCTION")
    private String function;

    @Column(name = "MESSAGE", length = 250)
    private String message;

    @Column(name = "RAW_FUNCTION", length = 4096)
    private String rawFunction;

}