package ynu.csistaffsystem.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import ynu.csistaffsystem.dto.DepartmentDTO;
import ynu.csistaffsystem.dto.PageRequest;

import java.util.Map;

public interface DepartmentService {

    PageInfo<DepartmentDTO> queryDepartment(PageRequest pageRequest, Map<String, Object> searchParam);

    void deleteDepartmentById(Integer departmentId);

    void addDepartment(DepartmentDTO departmentDTO);

    void updateDepartmentById(DepartmentDTO departmentDTO);
}
