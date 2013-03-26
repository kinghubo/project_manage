package com.taogongbao.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;

import com.taogongbao.common.constant.FileConstants;

/**
 * 文件工具类
 * @author xiexiaozhou
 *
 */
public class FileUtil {

	/**
	 * 把源文件写入到目标文件中，用字节流方法写
	 * @param fileSrc 源文件file对象
	 * @param fileDirPath 目标文件对象所在文件夹
	 * @param fileName 目标文件名
	 */
	public static void writeFile(File fileSrc,String fileDirPath,String fileName){
		File dirFile=new File(fileDirPath);
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		String fileDec=fileDirPath+fileName;
		File file=new File(fileDec);
		if(!file.exists()){

			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		BufferedInputStream bi=null;
		BufferedOutputStream bout=null;
		try {
			bi=new BufferedInputStream(new FileInputStream(fileSrc));
			bout=new BufferedOutputStream(new FileOutputStream(file));
			byte[] b=new byte[1024];
			while(bi.read(b)!=-1){
				bout.write(b);
				bout.flush();
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			if(bi!=null){
				try {
					bi.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bout!=null){
				try {
					bout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 把输入流写入到目标文件中，用utf-8编码的字符流写
	 * @param fileSrc 源文件file对象
	 * @param fileDec 目标文件file对象
	 */
	public static void writeFile(InputStream ioSrc,String fileDirPath,String fileName){
		File dirFile=new File(fileDirPath);
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		String fileDec=fileDirPath+fileName;
		File file=new File(fileDec);
		if(!file.exists()){

			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		InputStreamReader bi=null;
		
		OutputStreamWriter bout=null;
		try {
			bi=new InputStreamReader(ioSrc);
			bout= new OutputStreamWriter(new FileOutputStream(file,true),"UTF-8");
			bout.write("<html><head><meta http-equiv='content-Type' content='text/html; charset=UTF-8'></meta></head><body>");
			char[] b=new char[1024];
			while(bi.read(b)!=-1){
				bout.write(b);
				bout.flush();
			}
			bout.write("</body></html>");
			bout.flush();
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			if(bi!=null){
				try {
					bi.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bout!=null){
				try {
					bout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 把字符串写入到目标文件中，用utf-8编码的字符流写
	 * @param fileSrc 源文件file对象
	 * @param fileDec 目标文件file对象
	 */
	public static void writeFile(String str,String fileDirPath,String fileName){
		//获得输入流

		File dirFile=new File(fileDirPath);
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		String fileDec=fileDirPath+fileName;
		File file=new File(fileDec);
		if(!file.exists()){
			
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		
		OutputStreamWriter bout=null;
		try {
	
			bout= new OutputStreamWriter(new FileOutputStream(file,false),"UTF-8");
			bout.write("<html><head><meta http-equiv='content-Type' content='text/html; charset=UTF-8'></meta></head><body>");
			bout.write(str);
			bout.write("</body></html>");
			bout.flush();
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			
			if(bout!=null){
				try {
					bout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 根据url获得对应的文件内容
	 * @param htmlUrl
	 * @return
	 */
	public static StringBuffer getContentByUrl(String htmlUrl){
		/*
		URL url=null;
		StringBuffer sb=new StringBuffer("");
		  try
		   {
			 url=new URL(htmlUrl);
		    //创建URLConnection对象，用URL的openConnection方法将连接通过返回给URLConnection的对象
		      //实际上URL的openConnection的返回值就是一个URLConnection
		    URLConnection c = url.openConnection(); //*
		    //用URLConnection的connect()方法建立连接
		    c.connect();                            //*
		    // 显示该连接的相关信息，这些都是URLConnection的方法 
		    InputStream is=c.getInputStream();
		    
		    InputStreamReader isr=new InputStreamReader(is,"UTF-8");
		  
		    BufferedReader br=new BufferedReader(isr);
		  
		    String str=null;
		    
		    while((str=br.readLine())!=null)
		    {
		    
		      sb.append(str);
		    }
		      
		   
		   }
		   catch(IOException e)
		   {
		    System.out.println(e);
		     }
		   return sb; */
		htmlUrl=htmlUrl.replaceFirst(FileConstants.FILE_SAVE_VIRTUAL_PATH, "");
		String filePath=FileConstants.FILE_SAVE_REAL_PATH+htmlUrl;
		File file=new File(filePath);
		StringBuffer sb=new StringBuffer("");
		  try
		   {
			
		    InputStream is=new FileInputStream(file);
		    
		    InputStreamReader isr=new InputStreamReader(is,"UTF-8");
		  
		    BufferedReader br=new BufferedReader(isr);
		  
		    String str=null;
		    
		    while((str=br.readLine())!=null)
		    {
		    
		      sb.append(str);
		    }
		      
		   
		   }
		   catch(IOException e)
		   {
		    System.out.println(e);
		     }
		   return sb; 
	}
	
	/**
	 * 更新url获得对应的文件内容
	 * @param htmlUrl
	 * @return
	 */
	public static void updateContentByUrl(String url,String content){

		 //得到html的实际存储地址
		url=url.replaceFirst(FileConstants.FILE_SAVE_VIRTUAL_PATH, "");
		String filePath=FileConstants.FILE_SAVE_REAL_PATH+url;

		File file=new File(filePath);
		OutputStreamWriter bout=null;
		try {
			//不需要再编码为utf-8，因在写入的时候已经编码了，再编码则会乱码
			bout= new OutputStreamWriter(new FileOutputStream(file,false),"UTF-8");
			bout.write("<html><head><meta http-equiv='content-Type' content='text/html; charset=UTF-8'></meta></head><body>");
			bout.write(content);
			bout.write("</body></html>");
			bout.flush();
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			
			if(bout!=null){
				try {
					bout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
			
		   
	}
	
	/**
	 * 更新url获得对应的文件内容
	 * @param htmlUrl
	 * @return
	 */
	public static int updateImageByUrl(String url,File pic)throws Exception{

		int res=0;
		//判断是否以前上传了图片
		//如果之前没有上传图片
		if(null == url || "".equals(url) ){
			
		}
		 //得到html的实际存储地址
		url=url.replaceFirst(FileConstants.FILE_SAVE_VIRTUAL_PATH, "");
		String filePath=FileConstants.FILE_SAVE_REAL_PATH+url;
		//System.out.println(filePath+"*************************************");
		File file=new File(filePath);
		file.createNewFile();
		BufferedInputStream bi=null;
		BufferedOutputStream bout=null;
		try {
			bi=new BufferedInputStream(new FileInputStream(pic));
			bout=new BufferedOutputStream(new FileOutputStream(file));
			byte[] b=new byte[1024];
			while(bi.read(b)!=-1){
				bout.write(b);
				bout.flush();
			}
			res=1;
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			if(bi!=null){
				try {
					bi.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bout!=null){
				try {
					bout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
			
		  return res;
	}
	
	/**
	 * 根据当前时间和文件后缀名生成文件名
	 * @param fileSrcName
	 * @return
	 */
	public static synchronized String getFileName(String fileSrcName){
		
		return new Date().getTime()+fileSrcName.substring(fileSrcName.lastIndexOf("."));
	}
}
