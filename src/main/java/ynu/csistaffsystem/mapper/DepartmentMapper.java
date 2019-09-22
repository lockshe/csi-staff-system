package ynu.csistaffsystem.mapper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import ynu.csistaffsystem.dto.DepartmentDTO;

import java.util.List;
import java.util.Map;

public interface DepartmentMapper extends Mapper<DepartmentDTO> {

    List<DepartmentDTO> queryDepartment(@Param("searchParam")Map<String,Object> searchParam);
}
