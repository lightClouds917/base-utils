package com.java4all.file;

import org.springframework.util.Base64Utils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: momo
 * Date: 2018/5/7
 * Description:
 * 文件转为二进制
 * 文件转为二进制字符串
 * 二进制字符串转文件
 * 文件转为二进制数组
 * 文件转为base64字符串
 * base64字符串转为文件
 * MultipartFile 转为File
 * 解析多文件上传
 */
public class BinUtil {

    //public static void main(String[] args){
    //    //File file = new File("E://ONO白皮书v2.0.pdf");
    //    String parentPath = "E://测试byte";
    //    String fileName = file.getName();
    //    byteStrToFile(fileToByteStr(file),fileName,"E://测试byte");
    //    getFileToByte(file);
    //
    //    String base64Str = fileToBase64Str(file);
    //    System.out.println(base64Str);
    //    base64StrToFile(base64Str,fileName,parentPath);
    //}

    /**
     * 文件转为二进制数组
     * @param file
     * @return
     */
    public static byte[] fileToByteArray(File file){
        InputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] bytes = FileCopyUtils.copyToByteArray(fis);
            return bytes;
        }catch (Exception ex){
            throw new RuntimeException("transform file into byte Array 出错",ex);
        }finally {
            try {
                if(null != fis){
                    fis.close();
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

    /**
     * 文件转为二进制字符串
     * @param file
     * @return
     */
    public static String fileToByteStr(File file){
        InputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] bytes = FileCopyUtils.copyToByteArray(fis);
            return new String(bytes,"ISO-8859-1");
        }catch (Exception ex){
            throw new RuntimeException("transform file into byte String 出错",ex);
        }finally {
            try {
                if(null != fis){
                    fis.close();
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }


    /**
     * 二进制字符串转文件
     * @param byteStr
     * @param fileName
     * @param parentPath
     * @return
     */
    public static File byteStrToFile(String byteStr,String fileName,String parentPath){
        try {
            File fout = new File(parentPath,fileName);
            fout.createNewFile();
            byte[] bytes1 = byteStr.getBytes("ISO-8859-1");
            FileCopyUtils.copy(bytes1,fout);

            //FileOutputStream outs = new FileOutputStream(fout);
            //outs.write(bytes1);
            //outs.flush();
            //outs.close();

            return fout;
        }catch (Exception ex){
            throw new RuntimeException("transform byte String into File 出错",ex);
        }
    }

    /**
     * 文件转为二进制数组
     * 等价于fileToBin
     * @param file
     * @return
     */
    public static byte[] getFileToByte(File file) {
        byte[] by = null;
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
            byte[] bb = new byte[2048];
            int ch;
            ch = is.read(bb);
            while (ch != -1) {
                bytestream.write(bb, 0, ch);
                ch = is.read(bb);
            }
            by = bytestream.toByteArray();
        } catch (Exception ex) {
            throw new RuntimeException("transform file into byte Array 出错",ex);
        }finally {
            try {
                if(null != is){
                    is.close();
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return by;
    }


    /**
     * 文件转为base64字符串
     * @param file
     * @return
     */
    public static String fileToBase64Str(File file){
        InputStream in = null;
        String base64 = null;
        try {
            in = new FileInputStream(file);
            byte[] bytes = FileCopyUtils.copyToByteArray(file);
            base64 = Base64Utils.encodeToString(bytes);
        }catch (Exception ex){
            throw new RuntimeException("transform file into base64 String 出错",ex);
        }finally {
            try {
                if(null != in){
                    in.close();
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        return base64;
    }

    /**
     * base64字符串转为文件
     * @param base64Str
     * @param fileName
     * @param parentPath
     * @return
     */
    public static File base64StrToFile(String base64Str,String fileName,String parentPath){
        File file = new File(parentPath,fileName);
        FileOutputStream out = null;
        try {
            //byte[] bytes = Base64Utils.decodeFromString(base64Str);
            byte[] bytes1 = new BASE64Decoder().decodeBuffer(base64Str);
            ByteArrayInputStream in = new ByteArrayInputStream(bytes1);
            byte[] buffer = new byte[1024];
            out = new FileOutputStream(file);
            int byteSum = 0;
            int byteRead = 0;
            while ((byteRead = in.read(buffer)) != -1){
                byteSum += byteRead;
                out.write(buffer,0,byteRead);
            }
        }catch (Exception ex){
            throw new RuntimeException("transform base64 String into file 出错",ex);
        }finally {
            try {
                if(null != out){
                    out.close();
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        return file;
    }

    /**
     * MultipartFile 转为File
     * @param file
     * @return
     */
    public static File multipartFile2File(MultipartFile file){
        File temFile = null;
        try {
            temFile = File.createTempFile("/tem/fileT",null);
            file.transferTo(temFile);
        }catch (Exception ex){
            throw new RuntimeException("文件转换失败：",ex);
        }
        return temFile;
    }

    /**
     * 多文件上传处理
     * @param request
     * @return
     */
    public List<File> parseMultiFile(MultipartHttpServletRequest request){
        MultiValueMap<String, MultipartFile> fileMap = request.getMultiFileMap();
        List<File> fileList = new ArrayList<>();
        try {
            if(fileMap != null){
                for (int i = 0,length = fileMap.size(); i < length; i++) {
                    List<MultipartFile> list = fileMap.get("file" + i);
                    if(!CollectionUtils.isEmpty(list)){
                        File tempFile = File.createTempFile("tmp/tempFile",null);
                        list.get(0).transferTo(tempFile);
                        fileList.add(tempFile);
                        tempFile.delete();
                    }else {
                        break;
                    }
                }
            }
        }catch (Exception ex){
            throw new RuntimeException("多文件处理出错：",ex);
        }
        return fileList;
    }
}

