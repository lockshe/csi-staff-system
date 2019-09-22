package ynu.csistaffsystem.service;

import com.github.pagehelper.PageInfo;
import ynu.csistaffsystem.dto.JobDTO;
import ynu.csistaffsystem.dto.PageRequest;

import java.util.Map;

public interface JobService {

    PageInfo<JobDTO> queryJob(Map<String, Object> searchParam , PageRequest pageRequest);

    void deleteJobById(Integer jobId);

    void addJob(JobDTO jobDTO);

    void updateJobById(JobDTO jobDTO);
}
