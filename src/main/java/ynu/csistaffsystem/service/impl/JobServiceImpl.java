package ynu.csistaffsystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ynu.csistaffsystem.dto.JobDTO;
import ynu.csistaffsystem.dto.PageRequest;
import ynu.csistaffsystem.exception.MyException;
import ynu.csistaffsystem.mapper.JobMapper;
import ynu.csistaffsystem.service.JobService;

import java.util.Map;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobMapper jobMapper;

    @Override
    public PageInfo<JobDTO> queryJob(Map<String, Object> searchParam, PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPage(),pageRequest.getPageSize());
        return new PageInfo<>(jobMapper.queryJob(searchParam));
    }

    @Override
    public void deleteJobById(Integer jobId) {
        try {
            jobMapper.deleteByPrimaryKey(jobId);
        }catch (Exception e){
            throw new MyException("删除职位失败");
        }

    }

    @Override
    public void addJob(JobDTO jobDTO) {
        try {
            jobMapper.insertSelective(jobDTO);
        }catch (Exception e){
            throw new MyException("添加职位成功");
        }

    }

    @Override
    public void updateJobById(JobDTO jobDTO) {
        try {
            jobMapper.updateByPrimaryKeySelective(jobDTO);
        }catch (Exception e){
            throw new MyException("更新职位失败");
        }
    }
}
