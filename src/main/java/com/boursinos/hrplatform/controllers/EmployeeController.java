package com.boursinos.hrplatform.controllers;

import com.boursinos.hrplatform.model.Employee;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

public class EmployeeController {

    static final Logger logger = Logger.getLogger(EmployeeController.class);

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

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
