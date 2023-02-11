package com.boursinos.hrplatform.model.controller.employee.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SaveEmployeeResponse {

    private String employeeId;

    public SaveEmployeeResponse(String employeeId) {
        this.employeeId = employeeId;
    }
}
