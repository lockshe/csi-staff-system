package ynu.csistaffsystem.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import ynu.csistaffsystem.exception.MyException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class FileHandlerUtil {
    // test git commit
    private static final Logger LOGGER = LoggerFactory.getLogger(FileHandlerUtil.class);

    // bugfix
    private static String pathUrl;

    private static String uploadUrl;
    //fix...
    static{
        try {
            create();
        }catch (Exception e){
            throw new MyException("文件根目录创建错误");
        }
    }

    private static void create() throws FileNotFoundException{
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!path.exists()){
            path = new File("");
        }
        LOGGER.info("path--->"+path.getAbsolutePath());
        File upload = new File(path.getAbsolutePath(),File.separator+"static"+File.separator+"upload"+File.separator);
        if(!upload.exists()){
            upload.mkdirs();
        }
        pathUrl = path.getAbsolutePath();
        uploadUrl = upload.getAbsolutePath();
    }

    // .....................
    public static void upload(MultipartFile multipartFile, String filename){
        try {
            File file = new File(uploadUrl, filename);
            multipartFile.transferTo(file);
        }catch (IOException e){
            throw new MyException("文件上传错误");
        }
    }

    public static void download(HttpServletResponse response, String filename){
        try {
            File file = new File(uploadUrl, filename);
            if(!file.exists()){
                throw new MyException("不存在该文件");
            }
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"));// 设置response相应头进行下载
            FileInputStream in = new FileInputStream(file);
            ServletOutputStream os = response.getOutputStream();
            byte[] b;
            while (in.available() > 0) {
                b = in.available() > 1024 ? new byte[1024] : new byte[in.available()];
                in.read(b, 0, b.length);
                os.write(b, 0, b.length);
            }
            in.close();
            os.flush();
            os.close();
        }catch (IOException e){
            throw new MyException("文件下载错误");
        }
    }

    public static void delete(String filename){

        File file = new File(uploadUrl,filename);

        if(file.exists()){
            file.delete();
        }else {
            throw new MyException("不存在该文件");
        }
    }

    /**
     * 生成文件名随机后缀，防止文件重名
     */
    public static String randomPrefix(String filename){
        String[] arr = filename.split("\\.(?=[^\\.]+$)");
        arr[0]+= String.valueOf((int)(Math.random()*1000));
        return arr[0]+"."+arr[1];
    }

}
