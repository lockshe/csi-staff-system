package ynu.csistaffsystem.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import ynu.csistaffsystem.dto.UserInfoDTO;
import ynu.csistaffsystem.dto.UserLoginDTO;

public class ShiroMd5Util {

    /**
     * 散列次数
     */
    private static int hashIteration = 1024;

    /**
     * 加密算法
     */
    private static String  hashAlgorithmName = "MD5";


    public static String encrypt(UserLoginDTO userLoginDTO){
        String credential = userLoginDTO.getPassword();
        ByteSource salt = ByteSource.Util.bytes(userLoginDTO.getUserName());
        SimpleHash simpleHash = new SimpleHash(hashAlgorithmName, credential, salt ,hashIteration);
        return simpleHash.toString();
    }

    public static String encryptPwd(UserInfoDTO userInfoDTO){
        String credential = userInfoDTO.getPassword();
        ByteSource salt = ByteSource.Util.bytes(userInfoDTO.getLoginName());
        SimpleHash simpleHash = new SimpleHash(hashAlgorithmName, credential, salt ,hashIteration);
        return simpleHash.toString();
    }
}

