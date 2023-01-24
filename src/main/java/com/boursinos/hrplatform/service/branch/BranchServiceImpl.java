package com.boursinos.hrplatform.service.branch;

import com.boursinos.hrplatform.model.branch.Branch;
import com.boursinos.hrplatform.repositories.branch.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public List<Branch> getAllBranches(){
       return branchRepository.findAll();
    }

    @Override
    public List<Branch> getAllBranches(int count) {
        return this.branchRepository.findAll().stream().limit(count).collect(Collectors.toList());
    }

    @Override
    public Optional<Branch> getBranch(String id){
        return branchRepository.findById(id);
    }

    @Transactional
    public String saveBranch(Branch branch){
        return branchRepository.saveBranch(branch);
    }

    @Override
    public void deleteBranch(String id){
        branchRepository.deleteById(id);
    }

    @Override
    public Branch updateBranch(String id, Branch branch){
        Branch oldBranch = branchRepository.getById(id);
        branch.setBranchId(id);
        branch.setUpdatedAt(new Date());
        branch.setCreatedAt(oldBranch.getCreatedAt());
        branchRepository.save(branch);
        return branch;
    }

    @Override
    public Branch createBranch(String address, String city, String country) {
        final Branch branch = new Branch();
        branch.setAddress(address);
        branch.setCity(city);
        branch.setCountry(country);
        branch.setCreatedAt(new Date());
        branch.setUpdatedAt(new Date());
        return this.branchRepository.save(branch);
    }


}
