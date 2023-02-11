package com.boursinos.hrplatform.model.controller.branch.response;

import com.boursinos.hrplatform.model.entity.branch.Branch;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BranchesResponse {

    private List<Branch> allBranches;

    public BranchesResponse(List<Branch> allBranches) {
        this.allBranches = allBranches;
    }
}
