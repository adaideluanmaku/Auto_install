package com.ch.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import com.ch.sys.AntWarUtils;
import com.ch.sys.FilesHandle;
import com.ch.sys.Files_config;

public class Ap_config {
	String path = ".\\unit\\install-war\\";
	FilesHandle filesHandle = new FilesHandle();
	AntWarUtils antWarUtils = new AntWarUtils();
	Files_config files_config=null;
	
	public void resources() {
		File file=null;
		
		try {
			//1.读取固定路径文件内容resources.properties
			file=new File(path+"passrhap\\WEB-INF\\classes\\application.properties");
			FileInputStream fs=new FileInputStream(file);
			Properties pro = new Properties();
			pro.load(fs);
			fs.close();
			
			//2.删除目标文件resources.properties
			boolean success = file.delete();
			if(success){
				System.out.println("原配置application.properties删除成功");
			}else{
				System.out.println("原配置application.properties删除失败，请手动删除");
			}
//			System.out.println(pro.getProperty("jdbc.driver"));
			
			//3修改文件内容
			pro.setProperty("jdbc.url", files_config.getJdbc_url());
			pro.setProperty("jdbc.username", files_config.getJdbc_username());
			pro.setProperty("jdbc.password", files_config.getJdbc_password());
			pro.setProperty("redis.host", files_config.getRedis_host());
			pro.setProperty("redis.pass", files_config.getRedis_pass());
			pro.setProperty("redis.default.db", files_config.getRedis_default_db());
			pro.setProperty("passcore.path", files_config.getPasscore_path());
			pro.setProperty("passrhmm.url", files_config.getPassrhmm_url());
			
			String DLLSERVER[]=files_config.getJdbc_url().split("[?]");
			String DLLSERVER1[]=DLLSERVER[0].split("/");
			String DLLSERVER2[]=DLLSERVER1[2].split(":");
			
			//DLL配置
			pro.setProperty("dll.dbserver", DLLSERVER2[0]);
			pro.setProperty("dll.dbname", DLLSERVER1[3]);
			pro.setProperty("dll.dbuser", files_config.getJdbc_username());
			pro.setProperty("dll.dbpass", files_config.getJdbc_password());
			
			//4.新建配置文件resources.properties
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
			filesHandle.findfiles(path+"passrhap\\");
			filesHandle.createdir(path+"passrhap\\");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//0.解压文件
		if(!antWarUtils.unzip(path+"passrhap.war", path+"passrhap\\", false)){
			return false;
		}
		
		
		//修改配置文件
		resources();
		
		//压缩文件
		antWarUtils.zip(path+"passrhap\\", path+"passrhap_new.war");
		return true;
	}
}
