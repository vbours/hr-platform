package com.boursinos.hrplatform.service.absence;

import com.boursinos.hrplatform.BaseTests;
import com.boursinos.hrplatform.model.absence.Absence;
import com.boursinos.hrplatform.model.absence.AbsenceStatus;
import com.boursinos.hrplatform.model.absence.AbsenceType;
import com.boursinos.hrplatform.model.branch.Branch;
import com.boursinos.hrplatform.model.employee.ContractType;
import com.boursinos.hrplatform.model.employee.Employee;
import com.boursinos.hrplatform.model.employee.Gender;
import com.boursinos.hrplatform.service.branch.BranchService;
import com.boursinos.hrplatform.service.employee.EmployeeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class AbsenceTests extends BaseTests {

    @Autowired
    private AbsenceService absenceService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private BranchService branchService;

    private int count = 1;

    private Absence absence;
    private Employee employee;

    private Branch branch;

    @Before
    public void init(){
        absence = new Absence(new Date(),new Date(),3, AbsenceType.NORMAL, AbsenceStatus.REJECTED);
        employee = new Employee("firstname1","lastname2", Gender.MALE, 1987,
                "address1", "EEE", "76543", ContractType.FULL_TIME, 28,
                18, 100000);
        branch = new Branch("city","city","country");
    }

    @Test
    public void saveAndGetAbsenceTest(){
        String branchId = branchService.saveBranch(this.branch);
        String employeeId = employeeService.saveEmployee(this.employee,branchId);
        String absenceId = absenceService.saveAbsence(this.absence,employeeId);
        Assert.assertNotEquals(absenceId,null);

        Optional<Absence> absence = absenceService.getAbsence(absenceId);
        Assert.assertNotEquals(absence,null);
    }

    @Test
    public void getAllAbsencesTest(){
        String branchId = branchService.saveBranch(this.branch);
        String employeeId = employeeService.saveEmployee(this.employee,branchId);
        absenceService.saveAbsence(this.absence,employeeId);
        List<Absence> getAllAbsences = absenceService.getAllAbsences();
        Assert.assertEquals(getAllAbsences.size(),1);
    }

    @Test
    public void getAllAbsencesCountTest(){
        List<Absence> getAllAbsences = absenceService.getAllAbsences(count);
        Assert.assertEquals(getAllAbsences.size(),1);
    }
    @Test
    public void saveAndUpdateAbsenceTest(){
        String branchId = branchService.saveBranch(branch);
        String employeeId = employeeService.saveEmployee(employee,branchId);
        String absenceId = absenceService.saveAbsence(this.absence,employeeId);

        Assert.assertNotEquals(absenceId,null);
        Absence updatedAbsence = new Absence(new Date(),new Date(),4, AbsenceType.NORMAL, AbsenceStatus.REJECTED);
        Absence absence = absenceService.updateAbsence(employeeId,absenceId,updatedAbsence);
        Assert.assertEquals(absence.getRequestedDays(),4);
    }
    @Test
    public void saveAndDeleteAbsenceTest(){
        String branchId = branchService.saveBranch(branch);
        String employeeId = employeeService.saveEmployee(employee,branchId);
        String absenceId = absenceService.saveAbsence(this.absence,employeeId);

        Assert.assertNotEquals(absenceId,null);
        absenceService.deleteAbsence(absenceId);
        Optional<Absence> absence = absenceService.getAbsence(absenceId);
        Assert.assertEquals(absence,Optional.empty());
    }

}
