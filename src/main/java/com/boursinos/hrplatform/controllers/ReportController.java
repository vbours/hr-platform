package com.boursinos.hrplatform.controllers;

import com.boursinos.hrplatform.model.employee.Employee;
import com.boursinos.hrplatform.service.ReportService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class ReportController {

    static final Logger logger = Logger.getLogger(ReportController.class);

    @Autowired
    ReportService reportService;

    /**
     * This endpoint return the employees per branch.
     *
     * @return
     */
    @GetMapping(value = "/report/man-power", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Map<String,List<Employee>>> getManPowerPerBranch() {
        logger.info("Get employees per branch");
        Map<String,List<Employee>> getManPower = reportService.getEmployeesPerBranchMap();
        return new ResponseEntity<>(getManPower, HttpStatus.OK);
    }

    /**
     * This endpoint return the total number of employees per branch.
     *
     * @return
     */
    @GetMapping(value = "/report/total-number", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Map<String, Integer>> getTotalEmployeesPerBranch() {
        logger.info("Get total number of employees per branch");
        Map<String,Integer> getTotalManPower = reportService.getTotalEmployeesPerBranchMap();
        return new ResponseEntity<>(getTotalManPower, HttpStatus.OK);
    }
}
