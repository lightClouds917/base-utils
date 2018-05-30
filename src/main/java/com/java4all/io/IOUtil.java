package com.java4all.io;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Author: momo
 * Date: 2018/5/26
 * Description:各种io操作工具类
 */
public class IOUtil {

    private static Logger logger = LoggerFactory.getLogger(IOUtil.class);

    public static void main(String[] args){
        //copyFileByBytes();
        //copyFileByCharacter();
    }

    /**
     * 使用字节流复制文件
     * 测试结果：
     * 2.28M 文件 ，耗时12034ms
     */
    public static void copyFileByBytes(){
        long l1 = System.currentTimeMillis();
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream("F:/iotest/ONO白皮书v2.0.pdf");
            out = new FileOutputStream("F:/iotest2/复制的白皮书.pdf");
            int c ;

            while ((c = in.read()) != -1){
                logger.info("读出:"+c);
                out.write(c);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                if(in != null){
                    in.close();
                }
                if(out != null){
                    out.close();
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        long l2 = System.currentTimeMillis();
        logger.info("耗时："+(l2-l1)+"ms");
    }

    /**
     * 使用字符流复制文件
     * 测试结果：
     * 2.28M 文件 ，耗时6922ms
     */
    public static void copyFileByCharacter(){
        long l1 = System.currentTimeMillis();
        FileReader in = null;
        FileWriter out = null;

        try {
            in = new FileReader("F:/iotest/ONO白皮书v2.0.pdf");
            out = new FileWriter("F:/iotest2/copyFileByCharacterONO白皮书v2.0.pdf");
            int c;
            while ((c = in.read()) != -1){
                logger.info("读出:"+c);
                out.write(c);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                if(in != null){
                    in.close();
                }
                if(out != null){
                    out.close();
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        long l2 = System.currentTimeMillis();
        logger.info("耗时："+(l2-l1)+"ms");
    }

}
