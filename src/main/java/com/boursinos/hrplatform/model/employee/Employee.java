package com.boursinos.hrplatform.model.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
@Table(name = "Employee")
public class Employee {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "employee_id")
    private String employeeId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "firstname")
    private String firstname;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "lastname")
    private String lastname;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "gender")
    private Gender gender;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "address")
    private String address;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "postCode")
    private String postCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "telNumber")
    private String telNumber;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "contractType")
    private ContractType contractType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "totalHolidays")
    private int totalHolidays;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "salary")
    private int salary;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "contractStart")
    private Date contractStart;


    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public int getTotalHolidays() {
        return totalHolidays;
    }

    public void setTotalHolidays(int totalHolidays) {
        this.totalHolidays = totalHolidays;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getContractStart() {
        return contractStart;
    }

    public void setContractStart(Date contractStart) {
        this.contractStart = contractStart;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + employeeId + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", gender=" + gender +
                ", Address='" + address + '\'' +
                ", postCode='" + postCode + '\'' +
                ", telNumber='" + telNumber + '\'' +
                ", contractType=" + contractType +
                ", totalHolidays=" + totalHolidays +
                ", salary=" + salary +
                ", contractStart=" + contractStart +
                '}';
    }
}
