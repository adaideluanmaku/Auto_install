package com.ch.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.ch.dao.Mysqljdbc;

public class Jdbcbean {
	private String path=".\\unit\\mysql_table";
	
	public void ceatetable(String mysqlip,String mysqlname,String mysqlpassword,String basename) throws SQLException, ClassNotFoundException, IOException{
		Mysqljdbc mysqljdbc=new Mysqljdbc();
		Connection conn=null;
		try{
			conn=mysqljdbc.getConn(mysqlip,mysqlname,mysqlpassword,basename);
		}catch(Exception e){
			System.out.println("数据库连接失败，请检查数据库参数");
			return;
		}
		Statement st=null;
		ResultSet rs=null;
		String sql=null;
		
//		String path=".\\unit\\mysql_table";
		String encoding = "UTF-8";
		
		//read all kettle files 读取所有文件
		File file = new File(path);
		File[] tempList = file.listFiles();
//		System.out.println(file.getCanonicalPath());
		
		byte[] filecontent=null;
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
//				System.out.println("filepath is:" + tempList[i]);
//				System.out.println("filename is:" + tempList[i].getName());
				
				if(!"passpa.sql".equals(tempList[i].getName())){
					continue;
				}
				System.out.println("开始创建表结构");
				
				//read file context，读取文件内容
		        Long filelength = tempList[i].length();  
		        filecontent = new byte[filelength.intValue()];  
		        try {  
		            FileInputStream in = new FileInputStream(tempList[i]);  
		            in.read(filecontent);  
		            in.close();  
		        } catch (FileNotFoundException e) {  
		            e.printStackTrace();  
		        } catch (IOException e) {  
		            e.printStackTrace();  
		        }  
//		        System.out.println(new String(filecontent, encoding));
		        String obj[]=new String(filecontent, encoding).split(";");

		        
		        for(int i1=0;i1<obj.length;i1++){
//		        	System.out.println(obj[i1]);
		        	sql=obj[i1].toString();
		        	st=conn.createStatement();
		    		st.execute(sql);
		        }
		        
		        System.out.println("创建表结构结束");
			}
		}
        st.close();
		conn.close();
	}
	
	public void ceatehanshu(String mysqlurl,String mysqlname,String mysqlpassword,String basename) throws SQLException, ClassNotFoundException, IOException{
		Mysqljdbc mysqljdbc=new Mysqljdbc();
		Connection conn=null;
		try{
			conn=mysqljdbc.getConn(mysqlurl,mysqlname,mysqlpassword,basename);
		}catch(Exception e){
			System.out.println("数据库连接失败，请检查数据库参数");
			return;
		}
		Statement st=null;
		String sql=null;
		
//		String path=".\\unit\\\\mysql_table";
		String encoding = "UTF-8";
		
		//read all kettle files 读取所有文件
		File file = new File(path);
		File[] tempList = file.listFiles();
//		System.out.println(file.getCanonicalPath());
		
		byte[] filecontent=null;
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
//				System.out.println("filepath is:" + tempList[i]);
//				System.out.println("filename is:" + tempList[i].getName());
				
				if(!"hanshu.sql".equals(tempList[i].getName())){
					continue;
				}
				
				System.out.println("开始创建函数结构");
				
				//read file context，读取文件内容
		        Long filelength = tempList[i].length();  
		        filecontent = new byte[filelength.intValue()];  
		        try {  
		            FileInputStream in = new FileInputStream(tempList[i]);  
		            in.read(filecontent);  
		            in.close();  
		        } catch (FileNotFoundException e) {  
		            e.printStackTrace();  
		        } catch (IOException e) {  
		            e.printStackTrace();  
		        }  
//		        System.out.println(new String(filecontent, encoding));
		        String obj[]=new String(filecontent, encoding).split("-- ----------------------------");
		        
		        conn.setAutoCommit(false);
		        
		        for(int i1=0;i1<obj.length;i1++){
//		        	System.out.println(obj[i1]);
		        	
		        	String obj1[] =obj[i1].split(";");
		        	
		        	try{
		        		if(obj1[0].contains("DROP")){
//			        		System.out.println(obj1[0]);
			        		sql=obj1[0].toString();
				        	st=conn.createStatement();
				        	st.addBatch(sql);
				        	st.executeBatch();
				        	
				        	sql="";
				    		for(int i2=0;i2<obj1.length;i2++){
			        			if(i2==0){
			        				continue;
			        			}
//			        			System.out.println(obj1[i2]);
			        			if((i2+1)==obj1.length){
			        				sql=sql+obj1[i2].toString();
				        		}else{
				        			sql=sql+obj1[i2].toString()+";";
				        		}
				        	}
				    		String obj2[]=sql.split(";;");
				    		sql="";
				    		st=conn.createStatement();
				    		for(int i2=0;i2<obj2.length;i2++){
//				        		System.out.println(obj2[i2]);
				        		if((i2+1)==obj2.length){
				        			st.addBatch(obj2[i2]);
				        		}else{
				        			st.addBatch(obj2[i2]+";;");
				        		}
				        	}
				        	st.executeBatch();
				    		
			        	}else{
				    		sql=obj[i1].toString();
				        	st=conn.createStatement();
				        	st.addBatch(sql);
				        	st.executeBatch();
			        	}
		        	}catch(Exception e){
//		        		System.out.println(e);
		        	}
		        }
		        
		        System.out.println("创建函数结构结束");
			}
		}
		
		conn.commit();
        st.close();
		conn.close();
	}
	
	/*
	 * 暂时未用这个方法，太死板
	 */
	public void ceateview(String mysqlurl,String mysqlname,String mysqlpassword,String basename) throws SQLException, ClassNotFoundException, IOException{
		Mysqljdbc mysqljdbc=new Mysqljdbc();
		Connection conn=null;
		try{
			conn=mysqljdbc.getConn(mysqlurl,mysqlname,mysqlpassword,basename);
		}catch(Exception e){
			System.out.println("数据库连接失败，请检查数据库参数");
			return;
		}
		Statement st=null;
		String sql=null;
		
//		String path=".\\unit\\mysql_table";
		String encoding = "UTF-8";
		
		//read all kettle files 读取所有文件
		File file = new File(path);
		File[] tempList = file.listFiles();
//		System.out.println(file.getCanonicalPath());
		
		byte[] filecontent=null;
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
//				System.out.println("filepath is:" + tempList[i]);
//				System.out.println("filename is:" + tempList[i].getName());
				
				if(!"view.sql".equals(tempList[i].getName())){
					continue;
				}
				
				System.out.println("开始创建视图结构");
				
				//read file context，读取文件内容
		        Long filelength = tempList[i].length();  
		        filecontent = new byte[filelength.intValue()];  
		        try {  
		            FileInputStream in = new FileInputStream(tempList[i]);  
		            in.read(filecontent);  
		            in.close();  
		        } catch (FileNotFoundException e) {  
		            e.printStackTrace();  
		        } catch (IOException e) {  
		            e.printStackTrace();  
		        }  
//		        System.out.println(new String(filecontent, encoding));
		        String obj[]=new String(filecontent, encoding).split("-- ----------------------------");
		        Map map=new HashMap();
		        for(int i1=0;i1<obj.length;i1++){
		        	System.out.println(obj[i1]);
		        	
		        	if(obj[i1].contains("DROP")){
		        		String obj1[]=obj[i1].split(";");
			    		
			        	//住院
		        		if(obj1[1].contains("VIEW `view_inhosp_anticost`")){
			        		map.put("1", obj1[1].toString());
			        		continue;
			        	}
			        	if(obj1[1].contains("VIEW `view_inhosp_druguseway`")){
			        		map.put("2", obj1[1].toString());
			        		continue;
			        	}
			        	if(obj1[1].contains("VIEW `view_inhosp_operationdrug`")){
			        		map.put("3", obj1[1].toString());
			        		continue;
			        	}
			        	if(obj1[1].contains("VIEW `view_inhosp_operation`")){
			        		map.put("4", obj1[1].toString());
			        		continue;
			        	}
			        	if(obj1[1].contains("VIEW `view_inhosp_operation_yf`")){
			        		map.put("5", obj1[1].toString());
			        		continue;
			        	}
			        	if(obj1[1].contains("VIEW `view_inhosp_pt_anti`")){
			        		map.put("6", obj1[1].toString());
			        		continue;
			        	}
			        	if(obj1[1].contains("VIEW `view_inhosp_pt_cost`")){
			        		map.put("7", obj1[1].toString());
			        		continue;
			        	}
			        	if(obj1[1].contains("VIEW `view_inhosp_pt_num`")){
			        		map.put("8", obj1[1].toString());
			        		continue;
			        	}
			        	
			        	//出院
			        	if(obj1[1].contains("VIEW `view_outhosp_anticost`")){
			        		map.put("9", obj1[1].toString());
			        		continue;
			        	}
			        	if(obj1[1].contains("VIEW `view_outhosp_druguseway`")){
			        		map.put("10", obj1[1].toString());
			        		continue;
			        	}
			        	if(obj1[1].contains("VIEW `view_outhosp_operationdrug`")){
			        		map.put("11", obj1[1].toString());
			        		continue;
			        	}
			        	if(obj1[1].contains("VIEW `view_outhosp_operation`")){
			        		map.put("12", obj1[1].toString());
			        		continue;
			        	}
			        	if(obj1[1].contains("VIEW `view_outhosp_operation_yf`")){
			        		map.put("13", obj1[1].toString());
			        		continue;
			        	}
			        	if(obj1[1].contains("VIEW `view_outhosp_pt_anti`")){
			        		map.put("14", obj1[1].toString());
			        		continue;
			        	}
			        	if(obj1[1].contains("VIEW `view_outhosp_pt_cost`")){
			        		map.put("15", obj1[1].toString());
			        		continue;
			        	}
			        	if(obj1[1].contains("VIEW `view_outhosp_pt_num`")){
			        		map.put("16", obj1[1].toString());
			        		continue;
			        	}
			        	
			        	//执行删表脚本操作
			        	sql=obj1[0].toString();
			        	st=conn.createStatement();
			    		st.execute(sql);
			    		
			    		//执行建表脚本操作
			    		sql=obj1[1].toString();
			        	st=conn.createStatement();
			    		st.execute(sql);
		        	}else{
		        		sql=obj[i1].toString();
			        	st=conn.createStatement();
			    		st.execute(sql);
		        	}
		        }
		        
		        for(int i1=0;i1<map.size();i1++){
		        	System.out.println(map.get(String.valueOf(i1+1)));
		        	sql=map.get(String.valueOf(i1+1)).toString();
		        	st=conn.createStatement();
		    		st.execute(sql);
		        }
		        
		        System.out.println("创建视图结构结束");
			}
		}
        st.close();
		conn.close();
	}
	
	public void ceateview1(String mysqlurl,String mysqlname,String mysqlpassword,String basename) throws SQLException, ClassNotFoundException, IOException{
		Mysqljdbc mysqljdbc=new Mysqljdbc();
		Connection conn=null;
		try{
			conn=mysqljdbc.getConn(mysqlurl,mysqlname,mysqlpassword,basename);
		}catch(Exception e){
			System.out.println("数据库连接失败，请检查数据库参数");
			return;
		}
		Statement st=null;
		String sql=null;
		
		String encoding = "UTF-8";
		
		//read all kettle files 读取所有文件
		File file = new File(path);
		File[] tempList = file.listFiles();
//		System.out.println(file.getCanonicalPath());
		
		byte[] filecontent=null;
		List createsql=new ArrayList();
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
//				System.out.println("filepath is:" + tempList[i]);
//				System.out.println("filename is:" + tempList[i].getName());
				
				if(!"view.sql".equals(tempList[i].getName())){
					continue;
				}
				
				System.out.println("开始创建视图结构");
				
				//read file context，读取文件内容
		        Long filelength = tempList[i].length();  
		        filecontent = new byte[filelength.intValue()];  
		        try {  
		            FileInputStream in = new FileInputStream(tempList[i]);  
		            in.read(filecontent);  
		            in.close();  
		        } catch (FileNotFoundException e) {  
		            e.printStackTrace();  
		        } catch (IOException e) {  
		            e.printStackTrace();  
		        }  
//		        System.out.println(new String(filecontent, encoding));
		        String obj[]=new String(filecontent, encoding).split("-- ----------------------------");
//		        Map map=new HashMap();
		        for(int i1=0;i1<obj.length;i1++){
//		        	System.out.println(obj[i1]);
		        	
		        	if(obj[i1].contains("DROP")){
		        		String obj1[]=obj[i1].split(";");
			        	
		        		try{
				        	//执行删表脚本操作
				        	sql=obj1[0].toString();
				        	st=conn.createStatement();
				    		st.execute(sql);
			    		
				    		//执行建表脚本操作
			    			sql=obj1[1].toString();
				        	st=conn.createStatement();
				    		st.execute(sql);
			    		}catch(Exception e){
			    			createsql.add(obj[i1]);
			    			System.out.println("sql执行失败，将sql保护");
			    		}
			    		
		        	}else{
		        		sql=obj[i1].toString();
			        	st=conn.createStatement();
			    		st.execute(sql);
		        	}
		        }
		        
		        System.out.println("创建视图结构结束");
			}
		}
		
		while (createsql.size() != 0) {
			for (int i1 = 0; i1 < createsql.size(); i1++) {
				String obj[]=createsql.get(i1).toString().split(";");
//				System.out.println(createsql.get(i1));
				try{
					sql = obj[0].toString();
					st = conn.createStatement();
					st.execute(sql);
					
					sql = obj[1].toString();
					st = conn.createStatement();
					st.execute(sql);
	    		}catch(Exception e){
	    			System.out.println("视图执行失败保护");
	    			continue;
	    		}
				
				createsql.remove(i1);
				i1=i1-1;
			}
		}
        st.close();
		conn.close();
	}
}
