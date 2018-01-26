package com.ch.sys;

import java.io.File;
import java.io.IOException;

/**
 * 
 * @author 陈辉
 * 读取文件夹和文件夹下的所有文件,files listFiles
 */
public class FilesHandle {
	//删除操作
	public void deletefile(File file){
		file.delete();
	}
	
	//递归读取所有文件
	public void findfiles(String path) throws IOException {
		File file = new File(path);
		
		if(file.isFile()){
			//文件操作
			deletefile(file);
		}else{
			if(file.isDirectory()){
				if(file.exists()){
					File[] tempList = file.listFiles();
					for (File fl : tempList) {
						findfiles(fl.getPath());
					}
					//文件操作
					deletefile(file);
				}else{
					//文件操作
					deletefile(file);
				}
			}
		}
	}
	
	//创建目录
	public boolean createdir(String name){
		File dir = new File(name);
		if (dir.exists()) {// 判断目录是否存在
			System.out.println("创建目录失败，目标目录已存在！");
			return false;
		}
		if (!name.endsWith(File.separator)) {// 结尾是否以"/"结束
			name = name + File.separator;
		}
		if (dir.mkdirs()) {// 创建目标目录
			System.out.println("创建目录成功！" + name);
			return true;
		} else {
			System.out.println("创建目录失败！");
			return false;
		}
	}
}
