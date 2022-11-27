package com.boursinos.hrplatform.controllers;

import com.boursinos.hrplatform.model.employee.Employee;
import com.boursinos.hrplatform.service.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class EmployeeController {

    static final Logger logger = Logger.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;

    /**
     * This endpoint return all the employees from the db.
     *
     * @return employess (List<Employee></>)
     */
    @GetMapping(value = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<Employee>> getAllEmployees() {
        logger.info("Get all Employees request");
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees,HttpStatus.OK);
    }

    /**
     * This endpoint return a specific employee given an id.
     *
     * @param employee_id the id of the employee
     * @return employee
     */
    @GetMapping(value = "/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Optional<Employee>> getEmployee(@RequestParam String employee_id) {
        logger.info(String.format("Get Employee request - id : ",employee_id));
        Optional<Employee> employee = employeeService.getEmployee(employee_id);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }


    /**
     * This endpoint saves employee data to the db.
     *
     * @param employee request class for saving data
     * @return id (str)
     * @throws IOException in case of input/output exception
     */
    @PostMapping(value = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> saveEmployee(
            @RequestBody Employee employee) throws Exception {
        logger.info(String.format("Save Employee request : %s" , employee.toString()));
        String id = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(id,HttpStatus.CREATED);
    }

    /**
     * This endpoint updates employee data to the db.
     *
     * @param employee request class for saving data
     * @return id (str)
     * @throws IOException in case of input/output exception
     */
    @PutMapping(value = "/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Employee> updateEmployee(
            @RequestBody Employee employee, @RequestParam String id) {
        logger.info(String.format("Update Employee request : %s" , employee.toString()));
        Employee updatedEmployee = employeeService.updateEmployee(id,employee);
        return new ResponseEntity<>(updatedEmployee,HttpStatus.CREATED);
    }

    /**
     * This endpoint deletes employee data from the db.
     *
     * @param id id of the employee in the db that we want to delete
     */
    @DeleteMapping(value = "/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> deleteEmployee(
            @RequestParam String id) throws Exception {
        logger.info(String.format("Delete Employee request id : %s" , id));
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }
}
