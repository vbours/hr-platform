package com.boursinos.hrplatform.controllers;

import com.boursinos.hrplatform.model.employee.Employee;
import com.boursinos.hrplatform.service.employee.EmployeeService;
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
     * @param employeeId the id of the employee
     * @return employee
     */
    @GetMapping(value = "/employee/{employee_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Optional<Employee>> getEmployee(@RequestParam String employeeId) {
        logger.info(String.format("Get Employee request - id : ",employeeId));
        Optional<Employee> employee = employeeService.getEmployee(employeeId);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }


    /**
     * This endpoint saves employee data to the db.
     *
     * @param employee request class for saving data
     * @param branchId the specific id of the branch where the employee is working
     * @return id (str)
     * @throws IOException in case of input/output exception
     */
    @PostMapping(value = "branch/{branch_id}/employee", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> saveEmployee(
            @RequestBody Employee employee, @RequestParam String branchId) {
        logger.info(String.format("Save for branch %s - Employee request : %s" , branchId, employee.toString()));
        String id = employeeService.saveEmployee(employee, branchId);
        return new ResponseEntity<>(id,HttpStatus.CREATED);
    }

    /**
     * This endpoint updates employee data to the db.
     *
     * @param employee request class for saving data
     * @return id (str)
     * @throws IOException in case of input/output exception
     */
    @PutMapping(value = "branch/{branch_id}/employee/{employee_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Employee> updateEmployee(
            @RequestBody Employee employee, @RequestParam String branchId, @RequestParam String employeeId) {
        logger.info(String.format("Update Employee request employee_id : %s, branch_id : %s, employee_data : %s" , employeeId, branchId, employee.toString()));
        Employee updatedEmployee = employeeService.updateEmployee(branchId,employee);
        return new ResponseEntity<>(updatedEmployee,HttpStatus.CREATED);
    }

    /**
     * This endpoint deletes employee data from the db.
     *
     * @param employeeId id of the employee in the db that we want to delete
     */
    @DeleteMapping(value = "/employee/{employee_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> deleteEmployee(
            @RequestParam String employeeId) throws Exception {
        logger.info(String.format("Delete Employee request id : %s" , employeeId));
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(employeeId,HttpStatus.OK);
    }
}
