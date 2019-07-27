package com.qf.util;

  /*
    @author: LMFeng
    @date: 2019-07-27 9:24
    @desc:
  */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class OrderUtil {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /*生成订单号
    1、全局唯一
    2、不能有敏感的业务信息
    3、抗并发
    4、不宜过长（10-15）

    uuid

    当前时间
    用户的后四位（不足四位的补0）
    流水号1-N位


    * */
    public String createOrderId(Integer uid){
        StringBuffer stringBuffer =new StringBuffer("");

        //拼接当前时间
        SimpleDateFormat sdf =new SimpleDateFormat("yymmdd");
        stringBuffer.append(sdf.format(new Date()));

        //截取用户id后4位
        stringBuffer.append(getUid(uid));

        //拼接一个流水号
        String orderNumber =stringRedisTemplate.opsForValue().get("order_number");
        if (orderNumber ==null){
            stringRedisTemplate.opsForValue().set("order_number","0");
        }
        //获得自增的流水好
        Long orderNumbers =stringRedisTemplate.opsForValue().increment("order_number");
        stringBuffer.append(orderNumbers);

        return stringBuffer.toString();
    }


    public String getUid(Integer uid){

        StringBuffer stringBuffer =new StringBuffer("");

        String uidStr =uid +"";
        if (uidStr.length()<4){
            for (int i=0;i<4-uidStr.length();i++){
                stringBuffer.append("0");
            }
            stringBuffer.append(uidStr);
        }else {
            stringBuffer.append(uidStr.substring(uidStr.length()-4));

        }
        return stringBuffer.toString();

    }



}
