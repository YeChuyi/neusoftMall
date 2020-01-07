package com.neusoft.demo;

import com.neusoft.fastdfs.FastDfsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DemoFastdfsController {

    @Autowired
  FastDfsService fastDfsService;

    @RequestMapping("/uploadFile")
    public Map<String, String> uploadFile(@RequestParam("file") MultipartFile multipartFile[]) throws Exception {
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("files", multipartFile);
            Map<String, String> resultParam = fastDfsService.uploadFile(param);
            return resultParam;
        } catch (Exception e) {
            throw new Exception(this.getClass() + "uploadFile() Exception:" + e);
        }
    }

    @RequestMapping("/downLoadFile")
    public void  download(HttpServletResponse response, HttpServletRequest request) throws Exception{
        String fileUrl = request.getParameter("fileUrl");
        fastDfsService.downloadFile(fileUrl,response);
    }
}
