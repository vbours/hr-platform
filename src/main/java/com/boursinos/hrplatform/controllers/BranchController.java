package com.boursinos.hrplatform.controllers;

import com.boursinos.hrplatform.model.controller.branch.request.BranchRequest;
import com.boursinos.hrplatform.model.controller.branch.response.BranchResponse;
import com.boursinos.hrplatform.model.controller.branch.response.BranchesResponse;
import com.boursinos.hrplatform.model.controller.branch.response.SaveBranchResponse;
import com.boursinos.hrplatform.model.entity.branch.Branch;
import com.boursinos.hrplatform.service.branch.BranchService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
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
     * @return employess (List<Branch>)
     */
    @GetMapping(value = "/branches", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BranchesResponse> getAllBranches() {
        logger.info("Get all Branches request");
        List<Branch> branches = branchService.getAllBranches();
        BranchesResponse branchesResponse = new BranchesResponse(branches);
        return new ResponseEntity<>(branchesResponse,HttpStatus.OK);
    }

    /**
     * This endpoint return a specific branch given an id.
     *
     * @param branchId the id of the employee
     * @return branchResponse
     */
    @GetMapping(value = "/branch/{branch_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BranchResponse> getBranch(@RequestParam String branchId) {
        logger.info(String.format("Get Branch request - id : %s ",branchId));
        Optional<Branch> branch = branchService.getBranch(branchId);
        ModelMapper modelMapper = new ModelMapper();
        BranchResponse branchResponse = modelMapper.map(branch.get(), BranchResponse.class);
        return new ResponseEntity<>(branchResponse,HttpStatus.OK);
    }


    /**
     * This endpoint saves employee data to the db.
     *
     * @param branchRequest request class for saving data
     * @return id (str)
     */
    @PostMapping(value = "/branch", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<SaveBranchResponse> saveBranch(
            @RequestBody BranchRequest branchRequest) {
        logger.info(String.format("Save Branch request : %s" , branchRequest.toString()));
        ModelMapper modelMapper = new ModelMapper();
        Branch branch = modelMapper.map(branchRequest, Branch.class);
        String id = branchService.saveBranch(branch);
        return new ResponseEntity<>(new SaveBranchResponse(id),HttpStatus.CREATED);
    }

    /**
     * This endpoint updates branch data to the db.
     *
     * @param branchRequest request class for saving data
     * @param branchId the id of the branch to update
     * @return id (str)
     * @throws IOException in case of input/output exception
     */
    @PutMapping(value = "/branch/{branch_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BranchResponse> updateBranch(
            @RequestBody BranchRequest branchRequest, @RequestParam String branchId) {
        logger.info(String.format("Update Branch id : %s -  request : %s" , branchId, branchRequest.toString()));
        ModelMapper modelMapper = new ModelMapper();
        Branch branch = modelMapper.map(branchRequest, Branch.class);
        Branch updatedBranch = branchService.updateBranch(branchId,branch);
        BranchResponse branchResponse = modelMapper.map(updatedBranch,BranchResponse.class);
        return new ResponseEntity<>(branchResponse,HttpStatus.CREATED);
    }

    /**
     * This endpoint deletes branch data from the db.
     *
     * @param branchId id of the branch in the db that we want to delete
     */
    @DeleteMapping(value = "/branch/{branch_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> deleteBranch(
            @RequestParam String branchId) throws Exception {
        logger.info(String.format("Delete Branch request id : %s" , branchId));
        branchService.deleteBranch(branchId);
        return new ResponseEntity<>(branchId,HttpStatus.OK);
    }
}
