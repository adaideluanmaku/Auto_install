package com.ch.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.ch.sys.AntWarUtils;
import com.ch.sys.FilesHandle;
import com.ch.sys.Files_config;
import com.ch.sys.ProcessWait;

public class Pass_config {
	String path = ".\\unit\\install-war\\";
	FilesHandle filesHandle = new FilesHandle();
	AntWarUtils antWarUtils = new AntWarUtils();
	Files_config files_config=null;
	
	public void resources() {
		File file=null;
		
		try {
			//3.读取固定路径文件内容resources.properties
			file=new File(path+"pass\\WEB-INF\\classes\\resources.properties");
			FileInputStream fs=new FileInputStream(file);
			Properties pro = new Properties();
			pro.load(fs);
			fs.close();
			
			//4.删除目标文件resources.properties
			boolean success = file.delete();
			if(success){
				System.out.println("原配置resources.properties删除成功");
			}else{
				System.out.println("原配置resources.properties删除失败，请手动删除");
			}
//			System.out.println(pro.getProperty("jdbc.driver"));
			
			//5修改文件内容
			pro.setProperty("jdbc.url", files_config.getJdbc_url());
			pro.setProperty("jdbc.username", files_config.getJdbc_username());
			pro.setProperty("jdbc.password", files_config.getJdbc_password());
			pro.setProperty("seccache.dir",files_config.getSeccache_dir());
			pro.setProperty("cache.dir", files_config.getCache_dir());
			pro.setProperty("redis.host", files_config.getRedis_host());
			pro.setProperty("redis.pass", files_config.getRedis_pass());
			pro.setProperty("redis.default.db", files_config.getRedis_default_db());
			
			//6.新建配置文件resources.properties
			FileOutputStream fos = new FileOutputStream(file);
			Set<Object> keys = pro.keySet();
			for(Object key: keys){
				
				fos.write((key+"="+pro.getProperty(key.toString())).getBytes());
				fos.write("\r\n".getBytes());
			}
			fos.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ehcache_pass() {
		File file=null;
		
		try {
			file = new File(path+"pass\\WEB-INF\\classes\\ehcache_pass.xml");
			//3.读取固定路径文件内容resources.properties
			SAXReader reader=new SAXReader();
			Document document = null;
			try {
				document = reader.read(file);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Element root = document.getRootElement();
			
			Element diskStore = root.element("diskStore");
//			System.out.println(diskStore.attributeValue("path"));
			diskStore.setAttributeValue("path",files_config.getCache_dir());
			
			//4.删除目标文件resources.properties
			boolean success = file.delete();
			if(success){
				System.out.println("原配置ehcache_pass.xml删除成功");
			}else{
				System.out.println("原配置ehcache_pass.xml删除失败，请手动删除");
			}
//			System.out.println(pro.getProperty("jdbc.driver"));
			
			//5修改文件内容
			
			//6.新建配置文件resources.properties
			OutputFormat format = new OutputFormat();
	        format.setEncoding("UTF-8");
	        FileOutputStream fos = new FileOutputStream(file);
	        XMLWriter output = new XMLWriter(fos, format);
	        output.write(document);
	        output.flush();
	        output.close(); 
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void rs_pass() {
		File file=null;
		
		try {
			file = new File(path+"pass\\rs\\McConfig.js");
			//3.读取固定路径文件内容resources.properties
			FileInputStream fs=new FileInputStream(file);
			Properties pro = new Properties();
			pro.load(fs);
			fs.close();
			
//			System.out.println(pro.getProperty("this.PassWebServiceUrl"));
			pro.setProperty("this.PassWebServiceUrl", files_config.getThis_PassWebServiceUrl());
			//4.删除目标文件resources.properties
			boolean success = file.delete();
			if(success){
				System.out.println("原配置McConfig.js删除成功");
			}else{
				System.out.println("原配置McConfig.js删除失败，请手动删除");
			}
//			System.out.println(pro.getProperty("jdbc.driver"));
			
			//5修改文件内容
			
			//6.新建配置文件resources.properties
			FileOutputStream fos = new FileOutputStream(file);
			Set<Object> keys = pro.keySet();
			for(Object key: keys){
				
				fos.write((key+"="+pro.getProperty(key.toString())).getBytes());
				fos.write("\r\n".getBytes());
			}
			fos.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean config(Files_config files_config){
		this.files_config=files_config;
		//1清空作废压缩包文件夹
		try {
			filesHandle.findfiles(path+"pass");
			filesHandle.createdir(path+"pass");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//0.解压文件
		if(!antWarUtils.unzip(path+"pass.war", path+"pass\\", false)){
			return false;
		}
		
		//修改配置文件
		resources();
		ehcache_pass();
		rs_pass();
		
		//压缩文件
		antWarUtils.zip(path+"pass\\", path+"pass_new.war");
		return true;
	}
}
