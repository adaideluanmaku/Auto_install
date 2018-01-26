package com.ch.sys;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.jar.JarArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;

/*
 * 通过commons-compress-1.4.1.jar的方法操作解压缩文件
 */

public class WarUtils {  
    /**  
     * 解压war包  
     * @param unWarPath war解压的路径  
     * @param warFile 解压的war包文件  
     */  
    public static void unWar(String unWarPath,File warFile) {  
        try {  
            //读取文件流   
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(warFile));  
            //创建解压流的文件类型  
            ArchiveInputStream in = new ArchiveStreamFactory().createArchiveInputStream(ArchiveStreamFactory.JAR, bufferedInputStream);  
            JarArchiveEntry entry = null;  
            while ((entry = (JarArchiveEntry) in.getNextEntry()) != null) {  
                //判断目录是否存在  
                if (entry.isDirectory()) {  
                    //创建解压文件目录  
                    new File(unWarPath, entry.getName()).mkdir();  
                } else {  
                    //输出文件流  
                    OutputStream out = FileUtils.openOutputStream(new File(unWarPath, entry.getName()));  
                    IOUtils.copy(in, out);  
                    //关闭输出文件流  
                    out.close();  
                }  
            }  
            in.close();  
        } catch (FileNotFoundException e) {  
            System.err.println("未找到war文件");  
        } catch (ArchiveException e) {  
            System.err.println("不支持的压缩格式");  
        } catch (IOException e) {  
            System.err.println("文件写入发生错误");  
        }  
    }  
      
    /**  
     * 判断war包是否已经存在，若存在则删除  
     * @param filePath  
     */  
    public static void delWarFile(String filePath){  
        try {  
            File file = new File(filePath);  
            //判断文件是否存在  
            if(file.exists()){  
                file.delete();    
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
      
     /**  
      * 创建解压的war包目录，并返回目录路径  
      * @param warPath war所在路径  
      * @param unWarPath war包解压路径  
      * @return  
      */  
     public static String mkDirByFileName(String warPath, String unWarPath){  
         try {  
            //创建文件  
             File warFile = new File(warPath);  
             //获取文件全部名称，包括后缀，如test.war  
             String fileFullName = warFile.getName();  
             if(fileFullName!=null){  
                 //获取文件名称，如test  
                 String fileName = fileFullName.substring(0, fileFullName.lastIndexOf("."));  
                 //创建解压目录:解压路径+文件名称目录  
                 String unWarFilePath = unWarPath+"\\"+fileName;  
                 File file =new File(unWarFilePath);  
                 //如果文件夹不存在则创建      
                 if(!file.exists()&&!file.isDirectory()){  
                     file .mkdir();  
                 }  
                 //判断文件后后缀是否war  
                 String fileSuffix = fileFullName.substring(fileFullName.lastIndexOf(".")+1, fileFullName.length());  
                 if(fileSuffix.toLowerCase().equals("war")){  
                    //解压war包  
                     unWar(unWarFilePath,warFile);  
                     return unWarFilePath;  
                 }  
             }  
             return null;  
         } catch (Exception e) {  
             System.err.println(e.getMessage());  
             return null;  
         }  
     }  
       
     /**   
      * @desc 将源文件/文件夹生成指定格式的压缩文件,格式war   
      * @param resourePath 源文件/文件夹   
      * @param targetPath  目的压缩文件保存路径   
      * @return void   
      * @throws Exception    
     */    
    public static void compressedFile(String resourcesPath,String targetPath) throws Exception{  
        File resourcesFile = new File(resourcesPath);     //源文件    
        File targetFile = new File(targetPath);           //目的    
        //如果目的路径不存在，则新建    
        if(!targetFile.exists()){         
            targetFile.mkdirs();      
        }             
        String targetName = resourcesFile.getName()+".war";   //目的压缩文件名    
        //压缩文件路径  
        String targetCompPath = targetPath+"\\"+targetName;  
        FileOutputStream outputStream = new FileOutputStream(targetCompPath);    
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(outputStream));    
        createCompressedFile(out, resourcesFile, "");    
        out.close();      
    }         
    /**   
     * @desc 生成压缩文件。   
     * 如果是文件夹，则使用递归，进行文件遍历、压缩   
     * 如果是文件，直接压缩   
     * @param out  输出流   
     * @param file  目标文件   
     * @return void   
     * @throws Exception      
     */    
    public static void createCompressedFile(ZipOutputStream out,File file,String dir) throws Exception{    
        //如果当前的是文件夹，则进行进一步处理    
        if(file.isDirectory()){    
            //得到文件列表信息    
            File[] files = file.listFiles();    
            //将文件夹添加到下一级打包目录    
            out.putNextEntry(new ZipEntry(dir+"/"));    
            dir = dir.length() == 0 ? "" : dir +"/";    
            //循环将文件夹中的文件打包    
            for(int i = 0 ; i < files.length ; i++){    
                createCompressedFile(out, files[i], dir + files[i].getName());         //递归处理    
            }    
        } else{   //当前的是文件，打包处理    
            //文件输入流    
            FileInputStream fis = new FileInputStream(file);    
            out.putNextEntry(new ZipEntry(dir));    
            //进行写操作    
            int j =  0;    
            byte[] buffer = new byte[1024];    
            while((j = fis.read(buffer)) > 0){    
                out.write(buffer,0,j);    
            }    
            //关闭输入流    
            fis.close();    
        }    
    }  
      
    //删除当前目录以及目录下的文件  
    public static boolean deleteDir(File delFile) {  
        if (delFile.isDirectory()) {  
            String[] children = delFile.list();  
            //递归删除目录中的子目录以及文件  
            for (int i=0; i<children.length; i++) {  
                boolean success = deleteDir(new File(delFile, children[i]));  
                if (!success) {  
                    return false;  
                }  
            }  
        }  
        // 目录此时为空，可以删除  
        return delFile.delete();  
    }  
      
    //文件重新命名  
    public static void toFileRename(String filePath,String unWarPath){  
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");  
        String now = format.format(new Date());  
        File file = new File(filePath);  
        //判断文件是否存在  
        if(file.exists()){  
            //获取文件全部名称，包括后缀，如test.war  
            String fileFullName = file.getName();  
            //获取文件名称，如test  
            String fileName = fileFullName.substring(0, fileFullName.lastIndexOf("."));  
              
            String fileSuffix =  fileFullName.substring(fileFullName.lastIndexOf("."),fileFullName.length());  
            String renameFilePath = unWarPath +"\\"+fileName+"_bak"+now+fileSuffix;  
            file.renameTo(new File(renameFilePath));  
        }  
    }  
    
    public static void main(String args[]){
    	try {
			compressedFile("E:\\workplaceNew2\\Auto_install\\unit\\install-war\\passrhaa\\","E:\\workplaceNew2\\Auto_install\\unit\\install-war\\新建文件夹\\");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}  
