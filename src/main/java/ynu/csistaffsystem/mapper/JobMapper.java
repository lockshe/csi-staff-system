package ynu.csistaffsystem.mapper;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import ynu.csistaffsystem.dto.JobDTO;

import java.util.List;
import java.util.Map;

public interface JobMapper extends Mapper<JobDTO> {

    List<JobDTO> queryJob(@Param("searchParam") Map<String,Object> searchParam);

}
