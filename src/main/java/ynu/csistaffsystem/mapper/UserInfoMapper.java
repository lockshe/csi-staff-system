package ynu.csistaffsystem.mapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import ynu.csistaffsystem.dto.UserInfoDTO;
import ynu.csistaffsystem.dto.UserLoginDTO;

import java.util.List;
import java.util.Map;


public interface UserInfoMapper extends Mapper<UserInfoDTO> {
    void updatePwdByName(@Param("loginName") String loginName, @Param("password") String password);

    List<UserInfoDTO> queryUser(@Param("searchParam") Map<String,Object> searchParam);
}
