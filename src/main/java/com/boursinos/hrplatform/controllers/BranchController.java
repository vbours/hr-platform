package com.boursinos.hrplatform.controllers;

import com.boursinos.hrplatform.model.branch.Branch;
import com.boursinos.hrplatform.model.employee.Employee;
import com.boursinos.hrplatform.service.BranchService;
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
public class BranchController {

    static final Logger logger = Logger.getLogger(BranchController.class);

    @Autowired
    BranchService branchService;

    /**
     * This endpoint return all the branches from the db.
     *
     * @return employess (List<Employee></>)
     */
    @GetMapping(value = "/branch", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<Branch>> getAllBranches() {
        logger.info("Get all Branches request");
        List<Branch> branches = branchService.getAllBranches();
        return new ResponseEntity<>(branches,HttpStatus.OK);
    }

    /**
     * This endpoint return a specific branch given an id.
     *
     * @param branch_id the id of the employee
     * @return employee
     */
    @GetMapping(value = "/branch/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Optional<Branch>> getBranch(@RequestParam String branch_id) {
        logger.info(String.format("Get Branch request - id : ",branch_id));
        Optional<Branch> branch = branchService.getBranch(branch_id);
        return new ResponseEntity<>(branch,HttpStatus.OK);
    }


    /**
     * This endpoint saves employee data to the db.
     *
     * @param branch request class for saving data
     * @return id (str)
     * @throws IOException in case of input/output exception
     */
    @PostMapping(value = "/branch", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> saveBranch(
            @RequestBody Branch branch) throws Exception {
        logger.info(String.format("Save Branch request : %s" , branch.toString()));
        String id = branchService.saveBranch(branch);
        return new ResponseEntity<>(id,HttpStatus.CREATED);
    }

    /**
     * This endpoint updates branch data to the db.
     *
     * @param branch request class for saving data
     * @return id (str)
     * @throws IOException in case of input/output exception
     */
    @PutMapping(value = "/branch/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Branch> updateBranch(
            @RequestBody Branch branch, @RequestParam String id) {
        logger.info(String.format("Update Branch request : %s" , branch.toString()));
        Branch updatedBranch = branchService.updateBranch(id,branch);
        return new ResponseEntity<>(updatedBranch,HttpStatus.CREATED);
    }

    /**
     * This endpoint deletes branch data from the db.
     *
     * @param id id of the branch in the db that we want to delete
     */
    @DeleteMapping(value = "/branch/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> deleteBranch(
            @RequestParam String id) throws Exception {
        logger.info(String.format("Delete Branch request id : %s" , id));
        branchService.deleteBranch(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }
}
