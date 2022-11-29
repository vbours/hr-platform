package com.boursinos.hrplatform.service;

import com.boursinos.hrplatform.model.branch.Branch;
import com.boursinos.hrplatform.model.employee.Employee;

import java.util.List;
import java.util.Optional;

public interface BranchService {

    List<Branch> getAllBranches();

    public Optional<Branch> getBranch(String id);

    String saveBranch(Branch branch);

    void deleteBranch(String id);

    Branch updateBranch(String id, Branch branch);

}
