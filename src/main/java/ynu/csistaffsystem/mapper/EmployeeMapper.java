package ynu.csistaffsystem.mapper;


import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import ynu.csistaffsystem.dto.EmployeeDTO;
import ynu.csistaffsystem.dto.vo.EmployeeVO;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper extends Mapper<EmployeeDTO> {

    List<EmployeeVO> queryEmployee(@Param("searchParam") Map<String,Object> searchParam);

}
