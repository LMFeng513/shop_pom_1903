package com.qf.network;

  /*
    @author: LMFeng
    @date: 2019-07-13 14:07
    @desc:
  */


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtil {

    public static String sendGet(String urlStr){
        try (
                ByteArrayOutputStream out =new ByteArrayOutputStream();

        ){
            URL url =new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(10000);

            //发送请求到支架到指定服务器
            conn.connect();

            //获取返回的结果
            InputStream in = conn.getInputStream();
            byte[]bytes =new byte[1024*10];
            int len;
            while((len=in.read(bytes))!=-1){
                out.write(bytes,0,len);
            }
            byte[] buffer=out.toByteArray();
            return  new String(buffer);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

public static void main(String[] args){
        sendGet("http://localhost:8083/item/createhtml?gid=13");
}

}
