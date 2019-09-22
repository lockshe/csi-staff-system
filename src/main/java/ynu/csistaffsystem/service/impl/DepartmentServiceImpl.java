package ynu.csistaffsystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ynu.csistaffsystem.dto.DepartmentDTO;
import ynu.csistaffsystem.dto.PageRequest;
import ynu.csistaffsystem.exception.MyException;
import ynu.csistaffsystem.mapper.DepartmentMapper;
import ynu.csistaffsystem.service.DepartmentService;

import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public PageInfo<DepartmentDTO> queryDepartment(PageRequest pageRequest, Map<String, Object> searchParam) {
        LOGGER.info("----"+pageRequest.toString()+searchParam.toString());
        PageHelper.startPage(pageRequest.getPage(),pageRequest.getPageSize());
        return new PageInfo<>(departmentMapper.queryDepartment(searchParam));
    }

    @Override
    public void deleteDepartmentById(Integer departmentId) {
        try {
            departmentMapper.deleteByPrimaryKey(departmentId);
        }catch (Exception e){
            throw new MyException("删除部门失败");
        }

    }

    @Override
    public void addDepartment(DepartmentDTO departmentDTO) {
        try {
            departmentMapper.insertSelective(departmentDTO);
        }catch (Exception e){
            throw new MyException("新增部门失败");
        }
    }

    @Override
    public void updateDepartmentById(DepartmentDTO departmentDTO) {
        try {
            departmentMapper.updateByPrimaryKeySelective(departmentDTO);
        }catch (Exception e){
            throw new MyException("新增部门失败");
        }
    }
}
