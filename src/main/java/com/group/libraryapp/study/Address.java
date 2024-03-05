package com.group.libraryapp.study;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id = null;

  private String city;
  private String street;

  @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
  private Person person;

  public void setPerson(Person person) {
    this.person = person;
  }

  public Person getPerson() {
    return this.person;
  }
}
