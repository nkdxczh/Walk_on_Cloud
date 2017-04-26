package org.gr.woc.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.gr.woc.biz.ICommodityBiz;
import org.gr.woc.biz.impl.CommodityBizImpl;
import org.gr.woc.dao.IDesireDao;
import org.gr.woc.dao.IPictureDao;
import org.gr.woc.dao.impl.DesireDaoImpl;
import org.gr.woc.dao.impl.PictureDaoImpl;
import org.gr.woc.po.Commodity;
import org.gr.woc.po.Desire;
import org.gr.woc.po.Picture;
import org.gr.woc.po.User;
import org.gr.woc.vo.Commodities;

/**
 * Servlet implementation class ReleaseCommodityServlet
 */
public class ReleaseCommodityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReleaseCommodityServlet() {
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
		User user = (User) request.getSession().getAttribute("user");
		ICommodityBiz commoditybiz = new CommodityBizImpl();
		Commodity commodity = new Commodity();
		String yongtu="";
		String leixing="";
		String price="";
		String describe="";
		String comRegion="";
		String comName="";
		String desire=null;
		int comType = 0;
		double dPrice = 0.0;

		List<String> path = new ArrayList<String>();
		String fileUploadPath = this.getServletContext().getRealPath(
				"jsp/Market/Images");
		System.out.println("[SingleFileUploadServlet] 设置服务器接受客户端上传文件的位置是："
				+ fileUploadPath);
		// 1-2：设置服务器临时缓冲区的位置（临时缓冲的文件夹）
		File fileUploadTempPath = new File(this.getServletContext()
				.getRealPath("/tempDir"));
		File fileUploadPath2=new  File(this.getServletContext()
				.getRealPath("jsp/Market/Images"));
		if (!fileUploadTempPath.exists()) {
			// 创建一个全新的
			fileUploadTempPath.mkdir();
		}
		if (!fileUploadPath2.exists()) {
			// 创建一个全新的
			fileUploadPath2.mkdir();
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
						if("yongtu".equalsIgnoreCase(name)){
							yongtu = fileItem.getString();
							yongtu = new String(yongtu.getBytes("iso8859-1"),"UTF-8");
							System.out.println("消息内容：> " + yongtu);
						}
						if("leixing".equalsIgnoreCase(name)){
							leixing = fileItem.getString();
							leixing = new String(leixing.getBytes("iso8859-1"),"UTF-8");
							System.out.println("消息内容：> " + leixing);
						}
						if("price".equalsIgnoreCase(name)){
							price = fileItem.getString();
							price = new String(price.getBytes("iso8859-1"),"UTF-8");
							System.out.println("消息内容：> " + price);
						}
						if("comRegion".equalsIgnoreCase(name)){
							comRegion = fileItem.getString();
							comRegion = new String(comRegion.getBytes("iso8859-1"),"UTF-8");
							System.out.println("消息内容：> " + comRegion);
						}
						if("describe".equalsIgnoreCase(name)){
							describe = fileItem.getString();
							describe = new String(describe.getBytes("iso8859-1"),"UTF-8");
							System.out.println("消息内容：> " + describe);
						}
						if("comName".equalsIgnoreCase(name)){
							comName = fileItem.getString();
							comName = new String(comName.getBytes("iso8859-1"),"UTF-8");
							System.out.println("消息内容：> " + comName);
						}
						
						
						
						
					} else {
						// 4-3:获取上传文件的名称
						String fileName = fileItem.getName().trim();
						// 扩展1：唯一命名
						String fileExtName = fileName.substring(fileName
								.lastIndexOf("."));
						fileName = generateUnqieName() + fileExtName;
						// 扩展2：限制上传文件类型
						String[] allowedTypes = new String[] { ".jpg", ".jpeg",
								".png", ".bmp" };
						Arrays.sort(allowedTypes);
						int searchIndex = Arrays.binarySearch(allowedTypes,
								fileExtName);
						if (searchIndex < 0) {
							System.out
									.println("[SingleFileUploadServlet] 该类型文件不允许上传！");
							return;
						}

						System.out
								.println("[SingleFileUploadServlet] 获取上传文件的名称为: "
										+ fileName);
						// 4-4:封装上传文件对象并写入到服务器
						File saveFile = new File(fileUploadPath, fileName);
						fileItem.write(saveFile);
						path.add(fileName);
						System.out.println("[SingleFileUploadServlet] 上传文件成功！");
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
		switch (Integer
				.parseInt(yongtu)) {
		case 1:
			switch (Integer.parseInt(leixing)) {
			case 1:

			{
				comType = 1;
				desire = null;
				dPrice = Double.parseDouble(price);
				break;
			}
			case 2: {
				comType = 2;
				desire = null;
				dPrice = Double.parseDouble(price);
				break;
			}

			case 3: {
				comType = 3;
				desire = null;
				dPrice = Double.parseDouble(price);
				break;
			}
			}
			break;
		case 2:
			switch (Integer.parseInt(leixing)) {
			case 1:

			{
				comType = 4;
				dPrice = 0;
				desire=price;
				break;
			}
			case 2:

			{
				comType = 5;
				dPrice = 0;
				desire=price;
				break;
			}
			case 3:

			{
				comType = 6;
				dPrice = 0;
				break;
			}
			}
			break;
		case 3:
			switch (Integer.parseInt(leixing)) {
			case 1:

			{
				comType = 7;
				dPrice = 0.0;
				desire = null;
				break;
			}
			case 2:

			{
				comType = 8;
				dPrice = 0.0;
				desire = null;
				break;
			}
			case 3: {
				comType = 9;
				dPrice = 0.0;
				desire = null;
				break;
			}
			}
			break;
		default:
			break;
		}
		commodity.setUserId(user.getUserId());
		commodity.setComName(comName);
		commodity.setProperty(0);
		commodity.setType(comType);
		commodity.setPrice(dPrice);
		commodity.setComRegion(comRegion);
		commodity.setDescribe(describe);
		commodity.setStatus(0);
		// commodity.setOffTime(null);
		commodity.setRequiredScore(0);
		commoditybiz.releaseCommodity(commodity);
		List<Commodities> lstCommodities = commoditybiz.searchByUserId(
				user.getUserId(), 0,1,0);
		int comId = lstCommodities.get(0).getComId();
		System.out.println(comId);
		IPictureDao pictureDao = new PictureDaoImpl();
		Picture picture = new Picture();
		for (int i = 0; i < path.size(); i++) {
			picture.setComId(comId);
			picture.setPath(path.get(i));
			pictureDao.insert(picture);
		}
		IDesireDao desireDao = new DesireDaoImpl();
		Desire desireCommodity = new Desire();
		desireCommodity.setComId(comId);
		desireCommodity.setDesire(desire);
		desireDao.insert(desireCommodity);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("jsp/Market/ReleaseSuccess.html");
		dispatcher.forward(request, response);
	}

	// 自定义方法完成上传文件名称的自动生成
	private synchronized String generateUnqieName() {
		return String.valueOf(System.nanoTime());
	}
}
