package com.ch.kettle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ch.sys.FilesHandle;
import com.ch.sys.Kettle_config;

/**
 * 脚本批量维护
*java 修改kettle 文件
*/

public class AlterKettle {
//	private String path=".\\unit\\kettle\\kettle_original\\";
//	private String outpath=".\\unit\\kettle\\kettle_new\\";
	private Kettle_config kettleconfig=new Kettle_config();
	private FilesHandle filesHandle = new FilesHandle();
	
	private static final int RADIX = 16;
	private static final String SEED = "0933910847463829827159347601486730416058";
	public static final String PASSWORD_ENCRYPTED_PREFIX = "Encrypted ";

	//加密
	public static final String encryptPassword(String password) {
		if (password == null)
			return "";
		if (password.length() == 0)
			return "";

		BigInteger bi_passwd = new BigInteger(password.getBytes());

		BigInteger bi_r0 = new BigInteger(SEED);
		BigInteger bi_r1 = bi_r0.xor(bi_passwd);

		return bi_r1.toString(RADIX);
	}

	//解密
	public static final String decryptPassword(String encrypted) {
		if (encrypted == null)
			return "";
		if (encrypted.length() == 0)
			return "";

		BigInteger bi_confuse = new BigInteger(SEED);

		try {
			BigInteger bi_r1 = new BigInteger(encrypted, RADIX);
			BigInteger bi_r0 = bi_r1.xor(bi_confuse);

			return new String(bi_r0.toByteArray());
		} catch (Exception e) {
			return "";
		}
	}
	
	public void kettlexml(String filepath, String fileoutpath){
		String mserver=kettleconfig.getMysqlserver();
		String mtype="MYSQL";
		String maccess="Native";
		String mdatabase=kettleconfig.getMysqldatabase();
		String mport="3306";
		String musername=kettleconfig.getMysqlusername();
		String mpassword=PASSWORD_ENCRYPTED_PREFIX+encryptPassword(kettleconfig.getMysqlpassword().trim());//zfxmz
		
		String oserver=kettleconfig.getOrclserver();
		String otype="ORACLE";
		String oaccess="Native";
		String odatabase=kettleconfig.getOrcldatabase();
		String oport="1521";
		String ousername=kettleconfig.getMysqlusername();
		String opassword=PASSWORD_ENCRYPTED_PREFIX+encryptPassword(kettleconfig.getOrclpassword().trim());//123456
		
		Crypt_Kettyle password=new Crypt_Kettyle();
		
		SAXReader reader=new SAXReader();
		Document document = null;
		try {
			File fl=new File(filepath);
			document = reader.read(fl);
			
			//文件读到内存后删除原文件，重新生成文件时，直接替换原文件使用
			fl.delete();
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		System.out.println(document);
		
		//读取xml文件
		Element root = document.getRootElement();
//		System.out.println(root.asXML());
		
		int a=0;
		int b=0;
		for ( Iterator i = root.elementIterator("connection"); i.hasNext();) {
			Element connection = (Element) i.next();//�߳�������
//			System.out.println(connection.asXML());
			
			Element name =connection.element("name");
//			System.out.println(name.getText());
			if("mysql".equals(name.getText())){
				a=a+1;
				connection.element("server").setText(mserver);
				connection.element("type").setText(mtype);
				connection.element("access").setText(maccess);
				connection.element("database").setText(mdatabase);
				connection.element("port").setText(mport);
				connection.element("username").setText(musername);
				connection.element("password").setText(mpassword);
			}
			if("oracle".equals(name.getText())){
				b=b+1;
				connection.element("server").setText(oserver);
				connection.element("type").setText(otype);
				connection.element("access").setText(oaccess);
				connection.element("database").setText(odatabase);
				connection.element("port").setText(oport);
				connection.element("username").setText(ousername);
				connection.element("password").setText(opassword);
			}
	    }
		
		//如果缺少数据库连接
		if(a==0){
			root.addElement("connection").addElement("name").setText("mysql");
			for ( Iterator i = root.elementIterator("connection"); i.hasNext();) {
				Element connection = (Element) i.next();//�߳�������
//				System.out.println(connection.asXML());
				
				Element name =connection.element("name");
//				System.out.println(name.getText());
				if("mysql".equals(name.getText())){
					connection.addElement("server").setText(mserver);
					connection.addElement("type").setText(mtype);
					connection.addElement("access").setText(maccess);
					connection.addElement("database").setText(mdatabase);
					connection.addElement("port").setText(mport);
					connection.addElement("username").setText(musername);
					connection.addElement("password").setText(mpassword);
				}
		    }
		}
		if(b==0){
			root.addElement("connection").addElement("name").setText("oracle");
			for ( Iterator i = root.elementIterator("connection"); i.hasNext();) {
				Element connection = (Element) i.next();//�߳�������
//				System.out.println(connection.asXML());
				
				Element name =connection.element("name");
//				System.out.println(name.getText());
				if("oracle".equals(name.getText())){
					connection.addElement("server").setText(oserver);
					connection.addElement("type").setText(otype);
					connection.addElement("access").setText(oaccess);
					connection.addElement("database").setText(odatabase);
					connection.addElement("port").setText(oport);
					connection.addElement("username").setText(ousername);
					connection.addElement("password").setText(opassword);
				}
		    }
		}
		
		OutputFormat format = new OutputFormat();
        format.setEncoding("UTF-8");
        FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fileoutpath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        XMLWriter output = null;
		try {
			output = new XMLWriter(fos, format);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			output.write(document);
			output.flush();
			output.close(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
	}
	
	public void kettlerun(Kettle_config kettle_config ,String filepath, String bakpath){
		kettleconfig=kettle_config;
		
		//read all kettle files
		if(!filepath.substring(filepath.length()-1,filepath.length()).equals("\\".substring(0, 1))){
			filepath=filepath+"\\";
		}
		if(!bakpath.substring(bakpath.length()-1,bakpath.length()).equals("\\".substring(0, 1))){
			bakpath=bakpath+"\\";
		}
		
		try {
			filesHandle.findfiles(bakpath);
			filesHandle.createdir(bakpath);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		//备份文件到指定目录
		File file = new File(filepath);
		File[] tempList = file.listFiles();
		// System.out.println("files num:" + tempList.length);
		
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				System.out.println("filepath is:" + tempList[i]);
				
				SAXReader reader=new SAXReader();
				Document document = null;
				try {
					document = reader.read(tempList[i]);
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				OutputFormat format = new OutputFormat();
		        format.setEncoding("UTF-8");
		        FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(bakpath+tempList[i].getName());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        XMLWriter output = null;
				try {
					output = new XMLWriter(fos, format);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        try {
					output.write(document);
					output.flush();
			        output.close(); 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
			}
		}
		
		//修改kettle文件
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				System.out.println("filepath is:" + tempList[i]);
				
				//alter kettle file
				kettlexml(tempList[i].toString(),filepath+tempList[i].getName());
			}
		}
		
		
	}
}
