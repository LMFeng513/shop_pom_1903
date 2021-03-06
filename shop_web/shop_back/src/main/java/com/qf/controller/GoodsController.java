package com.qf.controller;

  /*
    @author: LMFeng
    @date: 2019-07-06 11:04
    @desc:
  */

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qf.entity.Goods;
import com.qf.service.IGoodsService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Reference
    private IGoodsService goodsService;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Value("${upload.path}")
    private String uploadPath;


//    @RequestMapping("/list")
//    public String toList(Model model){
//        List<Goods> goods = goodsService.list();
//        model.addAttribute("goods",goods);
//        System.err.println("goods---"+goods);
//        return "goodslist";
//    }


    @RequestMapping("/list")
    public String goodsList(Model model){

        List<Goods> goodsList = goodsService.queryGoodsList();
        model.addAttribute("goods", goodsList);

        return "goodslist";
    }

    @RequestMapping("/insert")
    public String insertGoods(Goods goods){
        goodsService.insertGoods(goods);
        return "redirect:/goods/list";
    }

    /**
     * 图片上传
     *
     * 上传到什么地方？
     *      直接上传到tomcat
     *      上传到本地硬盘的某个路径
     *
     * 上传后的文件名叫什么？
     *
     * @return
     */
    @RequestMapping("/uploadImg")
    @ResponseBody
    public String uploadImg(MultipartFile file){

        String uploadFile = "";

        //截取源图片的后缀
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String houzhui = originalFilename.substring(index+1);

        try {
            StorePath storePath=fastFileStorageClient.uploadImageAndCrtThumbImage(
                    file.getInputStream(),
                    file.getSize(),
                    houzhui,
                    null
                    );
            uploadFile=storePath.getFullPath();
            System.out.println("上传的路径为："+storePath.getFullPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "{\"filepath\":\"" + uploadFile + "\"}";

//        //生成文件名称
//        String filename = UUID.randomUUID().toString() + houzhui;
//        //设置上传的文件路径
//        uploadFile = uploadPath + filename;
//        try(
//                //输入流
//                InputStream in = file.getInputStream();
//                //输出流
//                OutputStream out = new FileOutputStream(uploadFile);
//        ) {
//
//            IOUtils.copy(in, out);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 获取服务器的图片
     */
    @RequestMapping("/getImg")
    public void getImg(String imgpath, HttpServletResponse response){
        //查询本地的文件
        File file = new File(imgpath);

        try (
                InputStream in = new FileInputStream(file);
                OutputStream out = response.getOutputStream();
        ){

            IOUtils.copy(in, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    }
