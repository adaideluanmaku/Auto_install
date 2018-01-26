package com.ch.bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.ch.sys.ProcessWait;

public class Install {
	String path = ".\\unit\\";

	ProcessWait processWait = new ProcessWait();
	
	public void jdk_install() {
		final Runtime runtime = Runtime.getRuntime();

		// read all kettle files 读取所有文件
		File file = new File(path);
		File[] tempList = file.listFiles();
		// System.out.println(file.getCanonicalPath());

		for (int i = 0; i < tempList.length; i++) {
			// System.out.println(tempList[i].getName());
			if (tempList[i].getName().contains("jdk")) {
				System.out.println("调用程序中：" + tempList[i].getName() + "请等待。。。");
				try {
					runtime.exec("cmd.exe /c start " + path + tempList[i].getName());

					//等待进程开启
					processWait.xxwaitstart("jdk");
				} catch (final Exception e) {
					System.out.println("Error exec!");
				}
			}
		}
	}

	public void setjdk_install(String str, int pathbak) {
		Runtime runtime = Runtime.getRuntime();
		Process process = null;
		byte[] content = null;
		InputStream is = null;

		// 环境变量path备份
		if (pathbak == 0) {
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
			System.out.println("在安装程序根目录\\unit文件夹下，可找到原环境变量path的备份原件，如出现问题可手动还原。");
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(new File(path + "误删" + "pathbak" + sf.format(date.getTime()) + ".txt"));
				try {
					fos.write(System.getenv("path").getBytes());
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		try {
			// 设置JAVA_HOME
			System.out.println("JAVA_HOME \"" + str + "\"");
			runtime.exec("cmd.exe /c setx JAVA_HOME \"" + str + "\"");

			// //设置CLASS_PATH
			System.out.println("CLASSPATH \".;%JAVA_HOME%\\lib\\dt.jar;%JAVA_HOME%\\lib\\tools.jar;\"");
			runtime.exec("cmd.exe /c setx CLASSPATH \".;%JAVA_HOME%\\lib\\dt.jar;%JAVA_HOME%\\lib\\tools.jar;\"");
			//
			// 设置PATH
			content = new byte[1024];
			process = runtime.exec("cmd.exe /c echo %Path%");
			is = process.getInputStream();
			is.read(content);
			is.close();
			String cont = new String(content, "UTF-8");
//			System.out.println(cont);
			if (!cont.contains("JAVA_HOME")) {
				System.out.println("path \"%JAVA_HOME%\\bin;%JAVA_HOME%\\jre\\bin;\"");
				runtime.exec("cmd.exe /c setx path \"%JAVA_HOME%\\bin;%JAVA_HOME%\\jre\\bin;\"");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("环境变量设置结束");
	}

	public void tomcat_install() {
		final Runtime runtime = Runtime.getRuntime();

		// read all kettle files 读取所有文件
		File file = new File(path);
		File[] tempList = file.listFiles();
		// System.out.println(file.getCanonicalPath());

		for (int i = 0; i < tempList.length; i++) {
			// System.out.println(tempList[i].getName());
			if (tempList[i].getName().contains("tomcat")) {
				System.out.println("调用程序中：" + tempList[i].getName() + "请等待。。。");
				try {
					runtime.exec("cmd.exe /c start " + path + tempList[i].getName());
					
					processWait.xxwaitstart("apache-tomcat");
				} catch (final Exception e) {
					System.out.println("Error exec!");
				}
			}
		}
	}

	public void mysql_install() {
		final Runtime runtime = Runtime.getRuntime();

		// read all kettle files 读取所有文件
		File file = new File(path);
		File[] tempList = file.listFiles();
		// System.out.println(file.getCanonicalPath());

		for (int i = 0; i < tempList.length; i++) {
			// System.out.println(tempList[i].getName());
			if (tempList[i].getName().contains("mysql-installer")) {
				System.out.println("调用程序中：" + tempList[i].getName() + "请等待。。。");
				try {
					runtime.exec("cmd.exe /c start " + path + tempList[i].getName());
					
					processWait.xxwaitstart("MySQLInstaller");
					
				} catch (final Exception e) {
					System.out.println("Error exec!");
				}
			}
		}
	}
}
