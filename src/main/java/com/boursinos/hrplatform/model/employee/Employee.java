package com.boursinos.hrplatform.model.employee;

import com.boursinos.hrplatform.model.branch.Branch;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
    @Column(name = "post_code")
    private String postCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "telNumber")
    private String telNumber;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "contract_type")
    private ContractType contractType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "total_holidays")
    private int totalHolidays;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "remaining_holidays")
    private int remainingHolidays;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "salary")
    private int salary;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "contract_start")
    private Date contractStart;


    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Branch branch;

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

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public int getRemainingHolidays() {
        return remainingHolidays;
    }

    public void setRemainingHolidays(int remainingHolidays) {
        this.remainingHolidays = remainingHolidays;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", gender=" + gender +
                ", yearOfBirth=" + yearOfBirth +
                ", address='" + address + '\'' +
                ", postCode='" + postCode + '\'' +
                ", telNumber='" + telNumber + '\'' +
                ", contractType=" + contractType +
                ", totalHolidays=" + totalHolidays +
                ", remainingHolidays=" + remainingHolidays +
                ", salary=" + salary +
                ", contractStart=" + contractStart +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", branch=" + branch +
                '}';
    }
}
