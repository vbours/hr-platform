package com.boursinos.hrplatform.repositories.file;

import com.boursinos.hrplatform.model.employee.Employee;
import com.boursinos.hrplatform.model.report.File;

import java.util.List;

public interface FileCustomRepository {

    String saveFile(File file);

}
