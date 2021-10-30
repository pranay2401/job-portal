package com.jobPortal.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    Long id;
    @Column
    String company;
    @Column
    String designation;
    @Column
    Double pay;
    @Column
    String location;
    @Column
    Double minExperience;
    @Column
    Double maxExperience;
}
