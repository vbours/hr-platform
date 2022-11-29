package com.boursinos.hrplatform.repositories.branch;

import com.boursinos.hrplatform.model.branch.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, String>, BranchCustomRepository {

}
