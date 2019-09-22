package ynu.csistaffsystem.service;

import com.github.pagehelper.PageInfo;
import ynu.csistaffsystem.dto.PageRequest;
import ynu.csistaffsystem.dto.PwdDTO;
import ynu.csistaffsystem.dto.UserInfoDTO;
import ynu.csistaffsystem.dto.UserLoginDTO;
import ynu.csistaffsystem.dto.vo.UserLoginVO;

import java.util.List;
import java.util.Map;

public interface UserInfoService {

    UserLoginVO login(UserLoginDTO userLoginDTO);

    void register(UserLoginDTO userLoginDTO);

    void reset(PwdDTO pwdDTO);

    void adminSetPwd(UserLoginDTO userLoginDTO);

    PageInfo<UserInfoDTO> queryUser(PageRequest pageRequest,Map<String, Object> searchParam);

    void deleteUserById(Integer userId);

    void deleteUserByIdList(List<Integer> userIds);

    void addUser(UserInfoDTO userInfoDTO);

    void updateUserById(UserInfoDTO userInfoDTO);
}
