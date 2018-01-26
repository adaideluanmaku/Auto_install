package com.ch.sys;

import java.io.IOException;
import java.io.InputStream;

public class ProcessWait {
	
	//str:进程名称
	public void xxwaitstart(String str) {
		byte[] content = null;
		InputStream is = null;
		final Runtime runtime = Runtime.getRuntime();
		Process process = null;
		// 等待进程开启
		String cont = "";
		int a=0;
		while (!cont.contains(str)) {
			a++;
			content = new byte[1024];
			try {
				//查看当前活动的进程
				process = runtime.exec("cmd.exe /c tasklist /fi \"imagename eq " + str + "*\"");
				try {
					
					//JDK进程比程序要开启的快些，所以这里特别给予多点时间
					if("jdk".equals(str)){
						Thread.sleep(4000);
					}else{
						Thread.sleep(1000);
					}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				is = process.getInputStream();
				is.read(content);
				is.close();
				cont = new String(content, "gbk");// new String(content, "UTF-8")
				if(cont.contains(str)){
					System.out.println(cont);
				}else{
					System.out.println("正在启动程序，请等待。。。");
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(a>10){
				return;
			}
		}
	}
	
	//str:进程名称
	public void xxwaitstop(String str) {
		byte[] content = null;
		InputStream is = null;
		final Runtime runtime = Runtime.getRuntime();
		Process process = null;
		
		// 等待进程开启
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String cont = str;
		int a=0;
		while (cont.contains(str)) {
			a++;
			content = new byte[1024];
			try {
				//查看当前活动的进程
				process = runtime.exec("cmd.exe /c tasklist /fi \"imagename eq " + str + "*\"");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				is = process.getInputStream();
				is.read(content);
				is.close();
				cont = new String(content, "gbk");// new String(content, "UTF-8")
				if(cont.contains(str)){
					System.out.println("等待进程关闭。。。");
				}else{
					System.out.println("进程已经关闭"+cont);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(a>25){
				return;
			}
		}
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
