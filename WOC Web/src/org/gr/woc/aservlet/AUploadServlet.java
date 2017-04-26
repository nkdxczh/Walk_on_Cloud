package org.gr.woc.aservlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.tomcat.util.http.fileupload.FileItem;

/**
 * Servlet implementation class AUploadServlet
 */
public class AUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fileUploadPath = this.getServletContext().getRealPath("/jsp/forum/post_images");
		String temp=this.getServletContext().getRealPath("/tempDir");
		System.out.println("[SingleFileUploadServlet] 设置服务器接受客户端上传文件的位置是："
				+ fileUploadPath);
		File fileUploadPathD = new File("/jsp/forum/post_images");
		// 1-2：设置服务器临时缓冲区的位置（临时缓冲的文件夹）
		File fileUploadTempPath = new File(temp);
		if (!fileUploadTempPath.exists()) {
			// 创建一个全新的
			fileUploadTempPath.mkdir();
		}
		if (!fileUploadPathD.exists()) {
			// 创建一个全新的
			fileUploadPathD.mkdir();
		}
		System.out.println("[SingleFileUploadServlet] 设置服务器接受客户端上传文件的临时位置是："
				+ fileUploadTempPath.getPath());

		DiskFileUpload fu=new DiskFileUpload();
		fu.setSizeMax(1*1024*1024);
		fu.setSizeThreshold(4096);
		fu.setRepositoryPath(temp);
		int index=0;
		List fileItems=null;
		try{
			fileItems=fu.parseRequest(request);
			System.out.println("fileItems="+fileItems);
		}catch(Exception e){
			e.printStackTrace();
		}
		Iterator iter=fileItems.iterator();
		while(iter.hasNext()){
			FileItem item=(FileItem)iter.next();
			if(!item.isFormField()){
				String name=item.getName();
				name=name.substring(name.lastIndexOf("\\")+1);
				long size=item.getSize();
				if((name==null||name.equals(""))&&size==0)continue;
				int point=name.indexOf(".");
				name=(new Date()).getTime()+name.substring(point, name.length())+index;
				index++;
				File fNew=new File(fileUploadPath,name);
				try{
					item.write(fNew);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			else{
				String fieldvalue=item.getString();
			}
		}
		String text1="11";
		
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print(fileUploadPath);
		
		out.flush();
		out.close();
	}

}
