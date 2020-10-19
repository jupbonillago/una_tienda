package com.SEII.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cartshop")
public class Cartshop {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "cartshop_id")
  private Integer id;

  @OneToOne
  @JoinColumn(name = "person_id")
  private Person personCartshop;

  @Column(name = "total")
  private Integer total;

  @Column(name = "state")
  private String state;

  @OneToMany(mappedBy = "cartshop")
  private List<Cartshop_item> items;


  public Cartshop() {
  }

  public Cartshop(Integer id, Person personCartshop, Integer total, String state, List<Cartshop_item> items) {
    this.id = id;
    this.personCartshop = personCartshop;
    this.total = total;
    this.state = state;
    this.items = items;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Person getPersonCartshop() {
    return this.personCartshop;
  }

  public void setPersonCartshop(Person personCartshop) {
    this.personCartshop = personCartshop;
  }

  public Integer getTotal() {
    return this.total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public String getState() {
    return this.state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public List<Cartshop_item> getItems() {
    return this.items;
  }

  public void setItems(List<Cartshop_item> items) {
    this.items = items;
  }

  public Cartshop id(Integer id) {
    this.id = id;
    return this;
  }

  public Cartshop personCartshop(Person personCartshop) {
    this.personCartshop = personCartshop;
    return this;
  }

  public Cartshop total(Integer total) {
    this.total = total;
    return this;
  }

  public Cartshop state(String state) {
    this.state = state;
    return this;
  }

  public Cartshop items(List<Cartshop_item> items) {
    this.items = items;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Cartshop)) {
            return false;
        }
        Cartshop cartshop = (Cartshop) o;
        return Objects.equals(id, cartshop.id) && Objects.equals(personCartshop, cartshop.personCartshop) && Objects.equals(total, cartshop.total) && Objects.equals(state, cartshop.state) && Objects.equals(items, cartshop.items);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, personCartshop, total, state, items);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", personCartshop='" + getPersonCartshop() + "'" +
      ", total='" + getTotal() + "'" +
      ", state='" + getState() + "'" +
      ", items='" + getItems() + "'" +
      "}";
  }

}
