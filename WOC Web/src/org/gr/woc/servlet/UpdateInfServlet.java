package org.gr.woc.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.gr.woc.biz.IPostBiz;
import org.gr.woc.biz.IUserInfBiz;
import org.gr.woc.biz.impl.PostBizImpl;
import org.gr.woc.biz.impl.UserInfBizImpl;
import org.gr.woc.dao.IPost_ResourceDao;
import org.gr.woc.dao.impl.Post_ResourceDaoImpl;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Detail_Inf;
import org.gr.woc.po.Post;
import org.gr.woc.po.Post_Resource;
import org.gr.woc.po.User;

/**
 * Servlet implementation class UpdateInfServlet
 */
public class UpdateInfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateInfServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		IUserInfBiz userInfBiz = new UserInfBizImpl();
		Detail_Inf uInf = userInfBiz.searchInfById(user.getUserId());

		String fileUploadPath = this.getServletContext().getRealPath(
				"/jsp/user_images");
		System.out.println("[SingleFileUploadServlet] 设置服务器接受客户端上传文件的位置是："
				+ fileUploadPath);
		File fileUploadPathD = new File("/jsp/user_images");
		// 1-2：设置服务器临时缓冲区的位置（临时缓冲的文件夹）
		File fileUploadTempPath = new File(this.getServletContext()
				.getRealPath("/tempDir"));
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

		// 步骤2：判断表单是否符合上传要求
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			// 步骤3：设置文件上传缓冲区对象
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 3-1:缓冲区对象与磁盘物理位置的绑定
			factory.setRepository(fileUploadTempPath);
			// 3-2：设置缓冲区对象的大小（4*1024 字节）
			factory.setSizeThreshold(4 * 1024);
			System.out
					.println("[SingleFileUploadServlet] 初始化服务器接受客户端上传文件的临时位置完毕！");

			// 步骤4：解析客户端表单待上传的数据
			// 4-1:创建一个ServletFileUpload对象完成对二进制表单数据的解析，并实现服务器上传功能
			ServletFileUpload sfu = new ServletFileUpload(factory);

			try {
				// 4-2:将解析到的二进制文件封装到FileItem的对象中
				@SuppressWarnings("unchecked")
				List<FileItem> fileItems = sfu.parseRequest(request);
				for (FileItem fileItem : fileItems) {
					// 扩展3：判断当前解析的请求数据是否为普通表单数据
					if (fileItem.isFormField()) {
						// 获取客户端表单输入元素的name属性的值
						String name = fileItem.getFieldName().trim();
						// 判断数据为哪个字段数据
						if ("nickName".equalsIgnoreCase(name)) {
							String value = fileItem.getString();
							value = new String(value.getBytes("iso8859-1"),
									"UTF-8");
							System.out.println("消息内容：> " + value);
							uInf.setNickName(value);
						}
						if ("gender".equalsIgnoreCase(name)) {
							String value = fileItem.getString();
							value = new String(value.getBytes("iso8859-1"),
									"UTF-8");
							System.out.println("消息内容：> " + value);
							uInf.setGender(value);
						}
						if ("major".equalsIgnoreCase(name)) {
							String value = fileItem.getString();
							value = new String(value.getBytes("iso8859-1"),
									"UTF-8");
							System.out.println("消息内容：> " + value);
							uInf.setMajor(value);
						}
						if ("region".equalsIgnoreCase(name)) {
							String value = fileItem.getString();
							value = new String(value.getBytes("iso8859-1"),
									"UTF-8");
							System.out.println("消息内容：> " + value);
							uInf.setUserRegion(value);
						}
						if ("hobby".equalsIgnoreCase(name)) {
							String value = fileItem.getString();
							value = new String(value.getBytes("iso8859-1"),
									"UTF-8");
							System.out.println("消息内容：> " + value);
							uInf.setHobby(value);
						}
						if ("email".equalsIgnoreCase(name)) {
							String value = fileItem.getString();
							value = new String(value.getBytes("iso8859-1"),
									"UTF-8");
							System.out.println("消息内容：> " + value);
							uInf.setEmail(value);
						}
						if ("postCode".equalsIgnoreCase(name)) {
							String value = fileItem.getString();
							value = new String(value.getBytes("iso8859-1"),
									"UTF-8");
							System.out.println("消息内容：> " + value);
							uInf.setPostCode(Integer.parseInt(value));
						}
						if ("phone".equalsIgnoreCase(name)) {
							String value = fileItem.getString();
							value = new String(value.getBytes("iso8859-1"),
									"UTF-8");
							System.out.println("消息内容：> " + value);
							uInf.setPhone(value);
						}
					} else {
						// 4-3:获取上传文件的名称
						String fileName = fileItem.getName().trim();
						if (fileName.equals("")) {
							// 扩展1：唯一命名
							uInf.setUserPhoto("default.jpg");
						    System.out.println("[SingleFileUploadServlet] 获取上传文件的名称为: "
										+ fileName);
						}
						else{
							String fileExtName = fileName.substring(fileName.lastIndexOf("."));
							fileName = generateUnqieName() + fileExtName;
							uInf.setUserPhoto(fileName);
						    System.out.println("[SingleFileUploadServlet] 获取上传文件的名称为: "
										+ fileName);
							// 4-4:封装上传文件对象并写入到服务器
							File saveFile = new File(fileUploadPath, fileName);
							fileItem.write(saveFile);
							System.out.println("[SingleFileUploadServlet] 上传文件成功！");
						}

					}
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("客户端表单不符合上传要求！");
		}
		boolean flag = userInfBiz.changeDetailInformation(uInf);
		String url = "";
		if (flag) {
			url = "1";
		} else {
			url = "2";
		}
		request.setAttribute("tip", url);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("jsp/forum/user_regif.jsp");
		dispatcher.forward(request, response);
	}

	// 自定义方法完成上传文件名称的自动生成
	private synchronized String generateUnqieName() {
		return String.valueOf(System.nanoTime());
	}

}