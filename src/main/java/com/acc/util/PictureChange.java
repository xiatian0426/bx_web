package com.acc.util;

import com.acc.controller.BxHomePageController;
import com.acc.exception.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class PictureChange {
    private static Logger _logger = LoggerFactory.getLogger(PictureChange.class);

    static String suffix = "";

    public static void main(String[] args) {
        String newfilebase = "C:\\Users\\pc\\Desktop\\123\\";
        File file = new File(newfilebase);
        File[] oldfiles = file.listFiles();
        for (File file2 : oldfiles) {
            if (isImageFile(file2)) {
                if (!scaleImage(newfilebase + file2.getName(), newfilebase + file2.getName())) {
                    System.out.println(file2.getName()+"转化成功！");
                }
            }
        }
    }

    /**
     * 删除一个路径下所有文件
     * @param path
     */
    public static void deleteFile(String path){
        File fileTemp = new File(path);
        boolean falg = false;
        falg = fileTemp.exists();
        if (falg) {
            if (true == fileTemp.isDirectory()) {
                String[] png = fileTemp.list();
                for (int i = 0; i < png.length; i++) {
                    System.out.println("png[i]=========="+png[i]);
                    File file = new File(path + png[i]);
                    if (true == file.isFile()) {
                        file.delete();
                    }
                }
            }else{
                fileTemp.delete();
            }
        }
    }

    public static boolean isImageFile(File file) {

        String fileName = file.getName();

        //获取文件名的后缀，可以将后缀定义为类变量，共后面的函数使用
        suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());

        // 声明图片后缀名数组
        if (!suffix.matches("^[(jpg)|(png)|(gif)]+$")) {
            System.out.println("请输入png,jpg,gif格式的图片");
            return false;
        }
        return true;
    }

    public static boolean scaleImage(String imgSrc, String imgDist) {
        InputStream is = null;
        try {
            File file = new File(imgSrc);
            if (!file.exists()) {
                return false;
            }
            is = new FileInputStream(file);
            Image src = ImageIO.read(is);
            if (src.getWidth(null) <= 600) {
//                File tofile = new File(imgDist);
//                copyFile(file, tofile);
                is.close();
                return true;
            }
            //获取源文件的宽高
            int imageWidth = ((BufferedImage) src).getWidth();
            int imageHeight = ((BufferedImage) src).getHeight();

            double scale = (double) 600 / imageWidth;

            //计算等比例压缩之后的狂傲
            int newWidth = (int) (imageWidth * scale);
            int newHeight = (int) (imageHeight * scale);

            BufferedImage newImage = scaleImage((BufferedImage) src, scale, newWidth, newHeight);

            File file_out = new File(imgDist);
            ImageIO.write(newImage, suffix, file_out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(is != null){
                    is.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    //用于具体的转化
    public static BufferedImage scaleImage(BufferedImage bufferedImage, double scale, int width, int height) {
        int imageWidth = bufferedImage.getWidth();
        int imageHeight = bufferedImage.getHeight();
        AffineTransform scaleTransform = AffineTransform.getScaleInstance(scale, scale);
        AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);

        return bilinearScaleOp.filter(bufferedImage, new BufferedImage(width, height, bufferedImage.getType()));
    }


    //复制文件
    public static void copyFile(File fromFile, File toFile) throws IOException {
        FileInputStream ins = new FileInputStream(fromFile);
        FileOutputStream out = new FileOutputStream(toFile);
        try{
            byte[] b = new byte[1024];
            int n = 0;
            while ((n = ins.read(b)) != -1) {
                out.write(b, 0, n);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ins.close();
            out.close();
        }

    }

    /**
     * 上传图片
     * @param file
     * @param fileSavePath
     * @return
     */
    public static Map<String,Object> imageUpload(MultipartFile[] file, String fileSavePath,boolean deleteBoo,boolean scaleBoo)  {
        Map<String,Object> resultMap = new HashMap<String, Object>();
        int result = 0;//正常
        if (file!=null && file.length>0) {
            MultipartFile multipartFile;
            List<String> list = new ArrayList<String>();
            System.out.println("deleteBoo============================"+deleteBoo);
            if(deleteBoo){
                deleteFile(fileSavePath);
            }
            long time;
            for (int i = 0; i < file.length; i++) {
                multipartFile = file[i];
                if (null == multipartFile || multipartFile.getSize() <= 0) {
                    result = 1;//上传文件有问题
                    break;
                }
                //文件名
                String originalName = multipartFile.getOriginalFilename();
                String fileName = originalName.substring(originalName.lastIndexOf("."));
                time = System.currentTimeMillis();
                list.add(time + fileName);
                String imgRealPath = fileSavePath + time + fileName;
                try {
                    //保存图片-将multipartFile对象装入image文件中
                    File imageFile=new File(imgRealPath);
                    // 判断文件父目录是否存在
                    if (!imageFile.getParentFile().exists()) {
                        imageFile.getParentFile().mkdir();
                    }
                    multipartFile.transferTo(imageFile);
                    //等比例压缩
                    if(scaleBoo)
                        PictureChange.scaleImage(imgRealPath,fileSavePath);
                } catch (Exception e) {
                    result = 1;//上传文件有问题
                    _logger.info("imageUpload失败：" + ExceptionUtil.getMsg(e));
                    break;
                }
            }
            resultMap.put("list",list);
        }else{
            result = -1;//没有文件
        }
        resultMap.put("code",""+result);
        return resultMap;
    }
}
