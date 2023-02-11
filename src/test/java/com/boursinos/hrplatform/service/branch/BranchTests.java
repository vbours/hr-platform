package com.boursinos.hrplatform.service.branch;

import com.boursinos.hrplatform.BaseTests;
import com.boursinos.hrplatform.model.entity.branch.Branch;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BranchTests extends BaseTests {

    @Autowired
    private BranchService branchService;

    private int count = 1;

    private Branch branch;

    @Before
    public void init(){
        branch = new Branch("test","test2","test3");
    }

    @Test
    public void saveAndGetBranchTest(){
        String branchId = branchService.saveBranch(branch);
        Assert.assertNotEquals(branchId,null);

        Optional<Branch> branch = branchService.getBranch(branchId);
        Assert.assertNotEquals(branch,null);
    }

    @Test
    public void getAllBranchesTest(){
        branchService.saveBranch(branch);
        List<Branch> getAllBranches = branchService.getAllBranches();
        Assert.assertEquals(getAllBranches.size(),2);
    }

    @Test
    public void getAllBranchesCountTest(){
        List<Branch> getAllBranches = branchService.getAllBranches(count);
        Assert.assertEquals(getAllBranches.size(),1);
    }

    @Test
    public void saveAndUpdateBranchTest(){
        String branchId = branchService.saveBranch(branch);
        Assert.assertNotEquals(branchId,null);
        Branch newBranch = new Branch("newAddress","newCity","newCountry");
        Branch branch = branchService.updateBranch(branchId,newBranch);
        Assert.assertEquals(branch.getAddress(),"newAddress");
    }
    @Test
    public void saveAndDeleteBranchTest(){
        String branchId = branchService.saveBranch(branch);
        Assert.assertNotEquals(branchId,null);
        branchService.deleteBranch(branchId);
        Optional<Branch> branch = branchService.getBranch(branchId);
        Assert.assertEquals(branch,Optional.empty());
    }
}
