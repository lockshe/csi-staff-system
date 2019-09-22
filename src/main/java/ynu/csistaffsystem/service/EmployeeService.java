package ynu.csistaffsystem.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import ynu.csistaffsystem.dto.EmployeeDTO;
import ynu.csistaffsystem.dto.PageRequest;
import ynu.csistaffsystem.dto.vo.EmployeeVO;

import java.util.Map;

public interface EmployeeService {

    PageInfo<EmployeeVO> queryEmploy(Map<String,Object> searchParam, PageRequest pageRequest);

    void deleteEmployById(Integer employId);

    void addEmploy(EmployeeDTO employeeDTO);

    void updateEmployById(EmployeeDTO employeeDTO);
}
