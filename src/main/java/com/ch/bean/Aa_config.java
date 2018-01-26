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

import javax.print.DocFlavor.URL;

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

public class Aa_config {
	String path = ".\\unit\\install-war\\";
	FilesHandle filesHandle = new FilesHandle();
	AntWarUtils antWarUtils = new AntWarUtils();
	Files_config files_config=null;
	
	public void resources() {
		File file=null;
		
		try {
			//1.读取固定路径文件内容resources.properties
			file=new File(path+"passrhaa\\WEB-INF\\classes\\application.properties");
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
			pro.setProperty("passrhap.path", files_config.getPassrhap_path());
			pro.setProperty("passcore.path", files_config.getPasscore_path());
			pro.setProperty("birt.savepath", files_config.getBirt_savepath());
			
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
	
	public void ds_library() {
		File file=null;
		
		try {
			file = new File(path+"passrhaa\\reportFiles\\ds_library.rptlibrary");
			//1.读取固定路径文件内容resources.properties
			SAXReader reader=new SAXReader();
			Document document = null;
			try {
				document = reader.read(file);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Element root = document.getRootElement();
			
			Element library = root.element("library");
			Element data_sources = root.element("data-sources");
			Element oda_data_source = root.element("oda-data-source");
			Element property = root.element("property");
			
//			System.out.println(property.attributeValue("path"));
			property.setText("http://localhost:"+files_config.getAA_port()+"/passrhaa/ws/birtservice?wsdl");
			
			//2.删除目标文件ds_library.rptlibrary
			boolean success = file.delete();
			if(success){
				System.out.println("原配置ds_library.rptlibrary删除成功");
			}else{
				System.out.println("原配置ds_library.rptlibrary删除失败，请手动删除");
			}
//			System.out.println(pro.getProperty("jdbc.driver"));
			
			//3修改文件内容
			
			//4.新建配置文件resources.properties
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
	
	public void pa_review() {
		File file=null;
		
		try {
			file = new File(path+"passrhaa\\reportFiles\\pa_review.rptlibrary");
			//1.读取固定路径文件内容resources.properties
			SAXReader reader=new SAXReader();
			Document document = null;
			try {
				document = reader.read(file);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Element root = document.getRootElement();
			
			Element library = root.element("library");
			Element data_sources = root.element("data-sources");
			Element oda_data_source = root.element("oda-data-source");
			Element property = root.element("property");
			
//			System.out.println(property.attributeValue("path"));
			property.setText("http://localhost:"+files_config.getAA_port()+"/passrhaa/ws/paReviewbirtservice?wsdl");
			
			//2.删除目标文件ds_library.rptlibrary
			boolean success = file.delete();
			if(success){
				System.out.println("原配置pa_review.rptlibrary删除成功");
			}else{
				System.out.println("原配置pa_review.rptlibrary删除失败，请手动删除");
			}
//			System.out.println(pro.getProperty("jdbc.driver"));
			
			//3修改文件内容
			
			//4.新建配置文件resources.properties
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
	
	public void webservice_library() {
		File file=null;
		
		try {
			file = new File(path+"passrhaa\\reportFiles\\reportPacache\\webservice_library.rptlibrary");
			//1.读取固定路径文件内容resources.properties
			SAXReader reader=new SAXReader();
			Document document = null;
			try {
				document = reader.read(file);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Element root = document.getRootElement();
			
			Element library = root.element("library");
			Element data_sources = root.element("data-sources");
			Element oda_data_source = root.element("oda-data-source");
			Element property = root.element("property");
			
//			System.out.println(property.attributeValue("path"));
			property.setText("http://localhost:"+files_config.getAA_port()+"/passrhaa/ws/pabirtservice?wsdl");
			
			//2.删除目标文件webservice_library.rptlibrary
			boolean success = file.delete();
			if(success){
				System.out.println("原配置webservice_library.rptlibrary删除成功");
			}else{
				System.out.println("原配置webservice_library.rptlibrary删除失败，请手动删除");
			}
//			System.out.println(pro.getProperty("jdbc.driver"));
			
			//5修改文件内容
			
			//3.新建配置文件resources.properties
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
	
	public boolean config(Files_config files_config){
		this.files_config=files_config;
		//1清空作废压缩包文件夹
		try {
			filesHandle.findfiles(path+"passrhaa\\");
			filesHandle.createdir(path+"passrhaa\\");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//0.解压文件
		if(!antWarUtils.unzip(path+"passrhaa.war", path+"passrhaa\\", false)){
			return false;
		}
		
		
		//修改配置文件
		resources();
		ds_library();
		pa_review();
		webservice_library();
		
		//压缩文件
		antWarUtils.zip(path+"passrhaa\\", path+"passrhaa_new.war");
		return true;
	}
}
