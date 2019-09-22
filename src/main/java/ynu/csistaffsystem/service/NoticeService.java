package ynu.csistaffsystem.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import ynu.csistaffsystem.dto.NoticeDTO;
import ynu.csistaffsystem.dto.PageRequest;
import ynu.csistaffsystem.dto.vo.NoticeVO;

import java.util.Map;

public interface NoticeService {

    PageInfo<NoticeVO> queryNotice(Map<String, Object> searchParam , PageRequest pageRequest);

    void deleteNoticeById(Integer noticeId);

    void addNotice(NoticeDTO noticeDTO);

    void updateNoticeById(NoticeDTO noticeDTO);
}
