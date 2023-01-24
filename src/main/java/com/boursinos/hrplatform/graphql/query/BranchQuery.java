package com.boursinos.hrplatform.graphql.query;

import com.boursinos.hrplatform.model.branch.Branch;
import com.boursinos.hrplatform.service.branch.BranchService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BranchQuery implements GraphQLQueryResolver {

    @Autowired
    private BranchService branchService;

    public List<Branch> getBranches(final int count) {
        return this.branchService.getAllBranches(count);
    }

    public Optional<Branch> getBranch(final String id) {
        return this.branchService.getBranch(id);
    }
}
