package ynu.csistaffsystem.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ynu.csistaffsystem.dto.NoticeDTO;
import ynu.csistaffsystem.dto.PageRequest;
import ynu.csistaffsystem.dto.vo.NoticeVO;
import ynu.csistaffsystem.dto.vo.UserLoginVO;
import ynu.csistaffsystem.exception.MyException;
import ynu.csistaffsystem.mapper.NoticeMapper;
import ynu.csistaffsystem.service.NoticeService;

import java.util.Map;

@Service
public class NoticeServiceImpl implements NoticeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeServiceImpl.class);

    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public PageInfo<NoticeVO> queryNotice(Map<String, Object> searchParam, PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPage(), pageRequest.getPageSize());
        LOGGER.info("queryResult------"+searchParam.toString()+pageRequest.toString());
        return new PageInfo<>(noticeMapper.queryNotice(searchParam));
    }

    @Override
    public void deleteNoticeById(Integer noticeId) {
        try {
            noticeMapper.deleteByPrimaryKey(noticeId);
        }catch (Exception e){
            throw new MyException("删除公告失败");
        }

    }

    @Override
    public void addNotice(NoticeDTO noticeDTO) {
        try {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();

            UserLoginVO userLoginVO = (UserLoginVO) session.getAttribute("user");
            noticeDTO.setUserId(userLoginVO.getId());
            noticeMapper.insertSelective(noticeDTO);
        }catch (Exception e){
            LOGGER.info(e.getMessage());
            throw new MyException("新增公告失败");
        }
    }

    @Override
    public void updateNoticeById(NoticeDTO noticeDTO) {
        try {
            noticeMapper.updateByPrimaryKeySelective(noticeDTO);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            throw new MyException("更新公告内容失败");
        }

    }
}
