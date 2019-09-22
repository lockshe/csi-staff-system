package ynu.csistaffsystem.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ynu.csistaffsystem.dto.EmployeeDTO;
import ynu.csistaffsystem.dto.PageRequest;
import ynu.csistaffsystem.dto.vo.EmployeeVO;
import ynu.csistaffsystem.exception.MyException;
import ynu.csistaffsystem.mapper.EmployeeMapper;
import ynu.csistaffsystem.service.EmployeeService;

import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public PageInfo<EmployeeVO> queryEmploy(Map<String, Object> searchParam, PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPage(),pageRequest.getPageSize());
        return new PageInfo<>(employeeMapper.queryEmployee(searchParam));
    }

    @Override
    public void deleteEmployById(Integer employId) {
        try {
            employeeMapper.deleteByPrimaryKey(employId);
        }catch (Exception e){
            throw new MyException("删除职员异常，请联系维护人员");
        }
    }

    @Override
    public void addEmploy(EmployeeDTO employeeDTO) {
        try {
            employeeMapper.insert(employeeDTO);
        }catch (Exception e){
            throw new MyException("新增职员失败");
        }
    }

    @Override
    public void updateEmployById(EmployeeDTO employeeDTO) {
        try {
            employeeMapper.updateByPrimaryKey(employeeDTO);
        }catch (Exception e){
            throw  new MyException("更新信息失败");
        }
    }
}
