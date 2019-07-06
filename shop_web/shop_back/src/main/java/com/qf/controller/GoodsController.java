package com.qf.controller;

  /*
    @author: LMFeng
    @date: 2019-07-06 11:04
    @desc:
  */

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Goods;
import com.qf.service.IGoodsService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/goods")
public class GoodsController {

        @Reference
        private IGoodsService goodsService;

        @Value("${upload.path}")
        private String uploadPath;
        @RequestMapping("/list")
        public String goodslist(Model model) {
            List<Goods> goodsList = goodsService.queryGoodsList();
           model.addAttribute("goods",goodsList);
            return "goodslist";
        }

        @RequestMapping("/uploadImg")
        @ResponseBody
        public String uploadImg(MultipartFile file){
            System.out.println("开始上传图片"+file.getOriginalFilename());
            String uploadFile="";

            //截取原图片的后组i
            String originalFilename=file.getOriginalFilename();
            int index=originalFilename.lastIndexOf(".");
            String houzhui=originalFilename.substring(index);

            //生成文件名称
            String filename= UUID.randomUUID().toString()+houzhui;
            uploadFile = uploadPath + filename;
            //输入流
            try(
                    InputStream in =file.getInputStream();

                    OutputStream out = new FileOutputStream(uploadFile);

            ) {

                //kaobei
                IOUtils.copy(in,out);

            } catch (IOException e) {
                e.printStackTrace();
            }


            return "succ";
        }

}
