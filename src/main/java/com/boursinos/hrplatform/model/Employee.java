package com.boursinos.hrplatform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @JsonProperty("_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnore
    @Column(name = "firstname")
    private String firstname;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnore
    @Column(name = "lastname")
    private String lastname;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnore
    @Column(name = "gender")
    private Gender gender;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnore
    @Column(name = "Address")
    private String Address;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnore
    @Column(name = "postCode")
    private String postCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnore
    @Column(name = "telNumber")
    private String telNumber;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnore
    @Column(name = "contractType")
    private ContractType contractType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnore
    @Column(name = "totalHolidays")
    private int totalHolidays;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnore
    @Column(name = "salary")
    private int salary;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnore
    @Column(name = "contractStart")
    private Date contractStart;


}
