package com.qf.controller;

  /*
    @author: LMFeng
    @date: 2019-07-13 10:31
    @desc:
  */

import com.qf.entity.Goods;
import com.qf.service.IGoodsService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/item")
public class ItmeController {
    @Reference
    private IGoodsService iGoodsService;

    @Autowired
    private Configuration configuration;

    @RequestMapping("/createhtml")
    @ResponseBody
    public void createHtml(Integer gid, HttpServletRequest request) throws IOException {
        Goods goods =iGoodsService.queryById(gid);

    Template template =configuration.getTemplate("goodsitem.ftl");

        Map<String ,Object>map =new HashMap<>();
        map.put("goods",goods);
        map.put("images",goods.getGimage().split("\\|"));
        map.put("contextPath",request.getContextPath());

        //获得classpath路径
        String path =ItmeController.class.getResource("/static").getPath().replace("20%","");
        System.out.println("获得的classpath的路径为： "+path);

        File file =new File(path+"/page");
        if (!file.exists()){
            file.mkdirs();

        }
        try (
                Writer writer =new FileWriter(file.getAbsolutePath()+"/"+gid+".html");

        ){
            template.process(map,writer);
        } catch (TemplateException e) {
            e.printStackTrace();
        }


    }




}
