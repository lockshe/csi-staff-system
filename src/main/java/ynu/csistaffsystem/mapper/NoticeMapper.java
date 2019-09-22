package ynu.csistaffsystem.mapper;


import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import ynu.csistaffsystem.dto.NoticeDTO;
import ynu.csistaffsystem.dto.vo.NoticeVO;

import java.util.List;
import java.util.Map;

public interface NoticeMapper extends Mapper<NoticeDTO> {

    List<NoticeVO> queryNotice(@Param("searchParam")Map<String,Object> searchParam);
}
