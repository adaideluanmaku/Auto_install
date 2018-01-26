package com.ch.sys;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;

import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

/*
 * 通过ANT-1.8.2.jar的方法操作解压缩文件
 * 这个方法好用，目前先用这个
 */
public class AntWarUtils {
	public final static String encoding = "UTF-8";//GBK  
	  
    // 压缩  
	/*
	 * srcPathname :压缩文件路径   E:\\workplaceNew2\\Auto_install\\unit\\install-war\\passrhaa\\"
	 * zipFilepath ：生成压缩包存放路径  E:\\workplaceNew2\\Auto_install\\unit\\install-war\\passrhaa.war"
	 */
    public void zip(String srcPathname, String zipFilepath)  
            throws BuildException, RuntimeException {  
        File file = new File(srcPathname);  
        if (!file.exists())  {
//        	 throw new RuntimeException("source file or directory "  
//                     + srcPathname + " does not exist.");  
        	System.out.println("zip file " + zipFilepath + " does not exist.");
 			return;
        }
  
        Project proj = new Project();  
        FileSet fileSet = new FileSet();  
        fileSet.setProject(proj);  
        // 判断是目录还是文件  
        if (file.isDirectory()) {  
            fileSet.setDir(file);  
            // ant中include/exclude规则在此都可以使用  
            // 比如:  
            // fileSet.setExcludes("**/*.txt");  
            // fileSet.setIncludes("**/*.xls");  
        } else {  
            fileSet.setFile(file);  
        }  
  
        Zip zip = new Zip();  
        zip.setProject(proj);  
        zip.setDestFile(new File(zipFilepath));  
        zip.addFileset(fileSet);  
        zip.setEncoding(encoding);  
        zip.execute();  
    }  
  
    // 解压缩  
    /*
     * zipFilepath:压缩包路径 E:\\workplaceNew2\\Auto_install\\unit\\install-war\\passrhaa.war
     *  destDir： 解压目的路径  E:\\workplaceNew2\\Auto_install\\unit\\install-war\\passrhaa\\
     */
    public boolean unzip(String zipFilepath, String destDir ,boolean isDelete)  
            throws BuildException, RuntimeException {  
    	
		if (!new File(zipFilepath).exists()) {
//			throw new RuntimeException("zip file " + zipFilepath + " does not exist.");
			System.out.println("zip file " + zipFilepath + " does not exist.");
			return false;
		}

		Project proj = new Project();
		Expand expand = new Expand();
		expand.setProject(proj);
		expand.setTaskType("unzip");
		expand.setTaskName("unzip");
		expand.setEncoding(encoding);

		expand.setSrc(new File(zipFilepath));
		expand.setDest(new File(destDir));
		expand.execute();
		
		if(isDelete){
			File file = new File(zipFilepath);  
            //判断文件是否存在  
            if(file.exists()){  
                file.delete();    
            }  
		}
		return true;
    } 
    
    public static void main(String args[]){
    	AntWarUtils antWarUtils = new AntWarUtils();
//    	antWarUtils.unzip("E:\\workplaceNew2\\Auto_install\\unit\\install-war\\passrhaa.war",
//    			"E:\\workplaceNew2\\Auto_install\\unit\\install-war\\123\\");
    	
    	antWarUtils.zip("E:\\workplaceNew2\\Auto_install\\unit\\install-war\\123\\",
    			"E:\\workplaceNew2\\Auto_install\\unit\\install-war\\passrhaa.war");
    }
}
