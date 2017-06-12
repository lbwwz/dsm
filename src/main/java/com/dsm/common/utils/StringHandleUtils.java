package com.dsm.common.utils;

import com.dsm.common.utils.chineseSplit.core.IKSegmenter;
import com.dsm.common.utils.chineseSplit.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 关于字符串处理工具类
 *
 * @author lbwwz
 */
public class StringHandleUtils {

    /**
     * 将ajax中Json传回的属性值字符串分割成单个的属性值字符串
     *
     * @param valueStr: 红色;黄色;蓝色;绿色;···
     */
    public static String[] trimToValueItem(String valueStr) {

        return valueStr.trim().split("(\\s*;\\s*)+");
    }


    /**
     * 获取字符串中最后一个字符
     * @param str 要进行获取的字符串
     */
    public static char getLastChar(String str){
        return str.charAt(str.length()-1);
    }


    /**
     * 获取一个文件名中文件的扩展名称
     * @param fileName 文件名：类似 123145.txt，20160809.jpg
     * @return 文件的后缀名。默认小写
     */
    public static String getFileExt(String fileName){
        return fileName.substring(fileName.indexOf(".")+1).toLowerCase();
    }

    /**
     * 用于中文分词的操作方法
     * @param sentence
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<String> cutWords(String sentence){
        IKSegmenter ikSegmenter;
        Lexeme lexeme;
        //开始进行分词
        ikSegmenter = new IKSegmenter(new StringReader(sentence), true);  //采用智能分词
        List<String> words = null;
        try {
            words = new ArrayList<>();
            //循环取出对sentence里的内容所分出的所有词
            while ((lexeme = ikSegmenter.next()) != null) {
                words.add(lexeme.getLexemeText());
            }
            return words;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    public static Map<String,String> formatPropertiesToMap(String properties){
        Map<String,String> m = new HashMap<>();

        String[] itemList = properties.split(",");
        String[] temp;
        for(String item : itemList){
            temp = item.split(":");
            m.put(temp[0],temp[1]);
        }

        return  m;
    }

}