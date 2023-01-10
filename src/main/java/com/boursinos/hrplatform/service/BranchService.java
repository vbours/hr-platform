package com.boursinos.hrplatform.service;

import com.boursinos.hrplatform.model.branch.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchService {

    List<Branch> getAllBranches();

    List<Branch> getAllBranches(int count);

    public Optional<Branch> getBranch(String id);

    String saveBranch(Branch branch);

    void deleteBranch(String id);

    Branch updateBranch(String id, Branch branch);

    Branch createBranch(final String address, final String city, final String country);

}
