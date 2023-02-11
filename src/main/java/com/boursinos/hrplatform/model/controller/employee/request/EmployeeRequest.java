package com.boursinos.hrplatform.model.controller.employee.request;

import com.boursinos.hrplatform.model.entity.branch.Branch;
import com.boursinos.hrplatform.model.entity.employee.ContractType;
import com.boursinos.hrplatform.model.entity.employee.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class EmployeeRequest {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String firstname;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastname;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Gender gender;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int yearOfBirth;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String address;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String postCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String telNumber;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ContractType contractType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int totalHolidays;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int remainingHolidays;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int salary;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date contractStart;

}
