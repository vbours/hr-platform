package com.boursinos.hrplatform.graphql.mutation;

import com.boursinos.hrplatform.model.entity.branch.Branch;
import com.boursinos.hrplatform.service.branch.BranchService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BranchMutation implements GraphQLMutationResolver {

    @Autowired
    private BranchService branchService;

    public Branch createBranch(final String address, final String city, final String country) {
        return this.branchService.createBranch(address, city, country);
    }
}
