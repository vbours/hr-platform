package com.boursinos.hrplatform.model.controller.employee.response;

import com.boursinos.hrplatform.model.entity.branch.Branch;
import com.boursinos.hrplatform.model.entity.employee.ContractType;
import com.boursinos.hrplatform.model.entity.employee.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String employeeId;

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
    private Date createdAt;

    private Date updatedAt;
}
