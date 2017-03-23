package com.test.webRequestTest;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Created by Lbwwz on 2016/8/10.
 */
public class HttpWebTest {

    //使用get请求向服务器发送数据
    @Test
    public void test1() throws UnsupportedEncodingException {
        //请求的发送路径
        String url = "http://127.0.0.1/ReceiverReply/saveReply.action";

        //get请求中国的中文参数编码
        String sendcontent = "你在逗我";
        //客户端两次编码
        sendcontent = URLEncoder.encode(URLEncoder.encode(sendcontent,"UTF-8"),"UTF-8");

        String param = "receiver=admin&pswd=12345&moTime=1208212205&mobile=13800210021&destcode=1065751600001&msg="+sendcontent+"&isems=1&emshead=255,5,5";

        System.out.println(sendGET(url,param));
    }


    /**
     * 设置参数，get请求访问相应的url。返回对应的结果数据显示
     * @param url
     * @param param
     * @return
     */
    public static String sendGET(String url,String param){
        String result="";//访问返回结果
        BufferedReader read=null;//读取访问结果

        try {
            //创建url
            URL realUrl=new URL(url+"?"+param);
            //打开连接
            URLConnection connection=realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //建立连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段，获取到cookies等
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            read = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"UTF-8"));

            //循环读取
            String line;
            while ((line = read.readLine()) != null) {
                result += line+"\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(read!=null){//关闭流
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}
