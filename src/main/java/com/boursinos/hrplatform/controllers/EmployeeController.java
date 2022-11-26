package com.boursinos.hrplatform.controllers;

import com.boursinos.hrplatform.model.Employee;
import com.boursinos.hrplatform.service.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class EmployeeController {

    static final Logger logger = Logger.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;

    /**
     * This endpoint save employee data to the db.
     *
     * @param employee request class for saving data
     * @return id (str)
     * @throws IOException in case of input/output exception
     */
    @PostMapping(value = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> saveEmployee(
            @RequestBody Employee employee) throws Exception {
        logger.info(String.format("Save Employee request : %s" , employee.toString()));
        employeeService.saveEmployee(employee);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
