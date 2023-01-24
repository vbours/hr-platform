package com.boursinos.hrplatform.service.report;

import com.boursinos.hrplatform.clients.MinioClients;
import com.boursinos.hrplatform.model.employee.Employee;
import com.boursinos.hrplatform.model.report.File;
import com.boursinos.hrplatform.repositories.employee.EmployeeRepository;
import com.boursinos.hrplatform.repositories.file.FileRepository;
import com.boursinos.hrplatform.utils.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class ReportServiceImpl implements ReportService{
    static final Logger logger = Logger.getLogger(ReportServiceImpl.class);

    @Value("${BUCKET_NAME}")
    private String bucket;

    @Value("${STORAGE_FOLDER}")
    private String storageFolder;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MinioClients minioClients;

    @Autowired
    private FileRepository fileRepository;

    @Override
    public Map<String, List<Employee>> getEmployeesPerBranchMap() {
        List<Employee> resultList = employeeRepository.getEmployeesPerBranch();
        Map<String, List<Employee>> branchMap = new HashMap<>();

        for (Employee employee : resultList) {
            if (!branchMap.containsKey(employee.getBranch().getBranchId())) {
                branchMap.put(employee.getBranch().getBranchId(), new ArrayList<>());
            }
            branchMap.get(employee.getBranch().getBranchId()).add(employee);
        }

        String filename = String.format("%s.txt",UUID.randomUUID());
        FileUtils.createFile(branchMap,storageFolder,filename);
        try {
            minioClients.uploader(bucket,filename, storageFolder);
        } catch (NoSuchAlgorithmException | IOException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        fileRepository.saveFile(new File(filename,null,bucket));
        return branchMap;
    }

    @Override
    public Map<String, Integer> getTotalEmployeesPerBranchMap() {
        List<Employee> resultList = employeeRepository.getEmployeesPerBranch();
        Map<String, Integer> employeeCounterMap = new HashMap<>();

        for (Employee employee : resultList) {
            employeeCounterMap.put(employee.getBranch().getBranchId(), employeeCounterMap.getOrDefault(employee.getBranch().getBranchId(), 0) + 1);

        }
        logger.info(employeeCounterMap.toString());
        return employeeCounterMap;
    }

    @Override
    public Map<String, Integer> getEmployeesTotalSalaryPerBranchMap() {
        List<Employee> resultList = employeeRepository.getEmployeesPerBranch();
        Map<String, Integer> totalSalaryMap = new HashMap<>();

        for (Employee employee : resultList) {
            totalSalaryMap.put(employee.getBranch().getBranchId(), totalSalaryMap.getOrDefault(employee.getBranch().getBranchId(), 0) + employee.getSalary());

        }
        logger.info(totalSalaryMap.toString());
        return totalSalaryMap;
    }

}
