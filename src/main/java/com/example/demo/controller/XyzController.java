package com.example.demo.controller;

import com.example.demo.entity.ConstantQiniu;
import com.example.demo.utils.R;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.security.util.KeyUtil;

import java.io.FileInputStream;
import java.io.IOException;
@RestController
@EnableAutoConfiguration
@RequestMapping("/oss/")
public class XyzController{

    @Autowired
    private ConstantQiniu constantQiniu;

//    获取token
    @RequestMapping("/gettoken")
    @ResponseBody
    public  R gettoken()
    {
        Auth auth = Auth.create(constantQiniu.getAccessKey(), constantQiniu.getSecretKey());
        return R.ok().put("token", auth.uploadToken(constantQiniu.getBucket()));
    }

    /**
     * 上传文件到七牛云存储
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @RequestMapping("/qiniu")
    @ResponseBody
    public R uploadImgQiniu(@RequestParam("file") MultipartFile multipartFile) throws IOException {

        FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
        String path = uploadQNImg(inputStream,multipartFile.getName()); // KeyUtil.genUniqueKey()生成图片的随机名
        return R.ok().put("msg",path);
    }

    /**
     * 将图片上传到七牛云
     */
    private String uploadQNImg(FileInputStream file, String key) {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        // 其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        // 生成上传凭证，然后准备上传

        try {
            Auth auth = Auth.create(constantQiniu.getAccessKey(), constantQiniu.getSecretKey());
            String upToken = auth.uploadToken(constantQiniu.getBucket());
            try {
                Response response = uploadManager.put(file, key, upToken, null, null);
                // 解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

                String returnPath = constantQiniu.getCdnPrefix() + "/" + putRet.key;
                return returnPath;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
