package ynu.csistaffsystem.mapper;

import tk.mybatis.mapper.common.Mapper;
import ynu.csistaffsystem.dto.DocumentDTO;
import ynu.csistaffsystem.dto.vo.DocumentVO;

import java.util.List;
import java.util.Map;

public interface DocumentMapper extends Mapper<DocumentDTO> {

    List<DocumentVO> queryDocument(Map<String, Object> map);
}
