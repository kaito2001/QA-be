package com.nhom5.blogonline.apiblogonline.question.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "categories")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "name")
  private String name;


  @ManyToMany(fetch = FetchType.LAZY,
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      },
      mappedBy = "categories")
  @JsonIgnore
  private Set<Question> tutorials = new HashSet<>();

  public Category() {

  }
  
  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Question> getTutorials() {
    return tutorials;
  }

  public void setTutorials(Set<Question> tutorials) {
    this.tutorials = tutorials;
  }  
  
}