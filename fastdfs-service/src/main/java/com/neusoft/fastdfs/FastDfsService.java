package com.neusoft.fastdfs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.context.annotation.PropertySource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@PropertySource(value = {"classpath:fdfs_client.properties"})
@Component
public class FastDfsService {

   @Value("${racker_server}")
   private String serverFdfs;
   // private String serverFdfs="192.168.74.135:22122";

    public Map<String, String> uploadFile(Map<String, Object> param) {
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("code", "1");//1成功
        try {
            String url = "";
//            FastDFSFile fastDFSFile = new FastDFSFile();
            MultipartFile multipartFile[] = (MultipartFile[]) param.get("files");
            for (int i = 0; i < multipartFile.length; i++) {
                FastDFSFile file = new FastDFSFile();
                file.setAuthor("authorStt");
                String ext = multipartFile[i].getOriginalFilename().substring(multipartFile[i].getOriginalFilename().lastIndexOf(".") + 1);
                file.setContent(multipartFile[i].getBytes());
                file.setName(multipartFile[i].getOriginalFilename());
                file.setExt(ext);
                String filePath[] = FastDfsUtil.upload(file);
                resultMap.put("fileUrl", serverFdfs + filePath[0] + "/" + filePath[1]);
                resultMap.put("fileUrl_down", filePath[0] + "/" + filePath[1]);
                System.out.println(filePath);
            }
        } catch (Exception e) {
            resultMap.put("code", "0");//0失败
            e.printStackTrace();
        }
        return resultMap;
    }

    public void downloadFile(String fileUrl,HttpServletResponse response){
        String group = fileUrl.substring(0, fileUrl.indexOf("/"));
        String path = fileUrl.substring(fileUrl.indexOf("/") + 1);
        String ext = path.substring(path.lastIndexOf(".") );
        byte[] data = FastDfsUtil.downByte(group,path);
        download(response,data, UUID.randomUUID().toString().replaceAll("-", "")+ext);
    }

    public void download(HttpServletResponse response, byte[] data, String showFileName) {

        BufferedInputStream bis = null;
        OutputStream os = null;
        BufferedOutputStream bos = null;
        try {
            os = response.getOutputStream(); // 重点突出
            bos = new BufferedOutputStream(os);
            // 对文件名进行编码处理中文问题
            String fileName = new String( showFileName.getBytes("gb2312"), "ISO8859-1");
            response.reset(); // 重点突出
            response.setCharacterEncoding("UTF-8"); // 重点突出
            response.setContentType("application/x-msdownload");// 不同类型的文件对应不同的MIME类型 // 重点突出
            // inline在浏览器中直接显示，不提示用户下载
            // attachment弹出对话框，提示用户进行下载保存本地
            // 默认为inline方式
            response.setHeader("Content-Disposition", "attachment; filename="+fileName); // 重点突出
            bos.write(data, 0, data.length);// 将文件发送到客户端

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        } finally {
            // 特别重要
            // 1. 进行关闭是为了释放资源
            // 2. 进行关闭会自动执行flush方法清空缓冲区内容
            try {
                if (null != bis) {
                    bis.close();
                    bis = null;
                }
                if (null != bos) {
                    bos.close();
                    bos = null;
                }
                if (null != os) {
                    os.close();
                    os = null;
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex.getMessage());
            }
        }
    }
}
