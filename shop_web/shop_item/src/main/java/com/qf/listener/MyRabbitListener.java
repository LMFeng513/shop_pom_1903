package com.qf.listener;

  /*
    @author: LMFeng
    @date: 2019-07-13 14:03
    @desc:
  */

import com.qf.controller.ItmeController;
import com.qf.entity.Goods;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;


@Component
public class MyRabbitListener {

@Autowired
    private Configuration configuration;

    @RabbitListener(queues = "item_queue")
    public void msgHandler(Goods goods) throws IOException {

            Template template =configuration.getTemplate("goodsitem.ftl");

        Map<String,Object>map =new HashMap<>();
        map.put("goods",goods);
        map.put("images",goods.getGimage().split("\\|"));
        map.put("contextPath","");


        String path = ItmeController.class.getResource("/static").getPath();
        System.out.println("获得的classpath的路径为 ： "+path);
        File file =new File(path+"/page");
        if (!file.exists()){
        file.mkdirs();
        }
        try
                (
                        Writer writer =new FileWriter(file.getAbsolutePath()+"/"+goods.getId()+".html");

                )
        {
            template.process(map,writer);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

}
