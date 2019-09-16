/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.dragon.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dragon.enums.FileType;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/load/UploadServlet")
public class UploadServlet extends HttpServlet {

	/**
	 * 文件上传
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String root = request.getServletContext().getRealPath("/upload");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem it : list) {
				// 如果是file文件类型
				if (!it.isFormField()) {
					 FileType fileType = getFileType(it.getInputStream());
					 if (fileType == null) {
						 // 非图片格式
						 response.getWriter().write("fail");
						 return;
					 }
					String imgValue = fileType.getValue();
					System.out.println("imgValue:" + imgValue);
					// 是图片格式
					it.write(new File(root + "/" + it.getName()));
					response.getWriter().write("success");
				}
			}
		} catch (Exception e) {
			try {
				response.getWriter().write("exception");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	/** 判断文件是图片格式*/
	public static FileType getFileType(InputStream is) throws IOException {
		byte[] src = new byte[28];
		is.read(src, 0, 28);
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v).toUpperCase();
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		FileType[] fileTypes = FileType.values();
		for (FileType fileType : fileTypes) {
			if (stringBuilder.toString().startsWith(fileType.getValue())) {
				return fileType;
			}
		}
		return null;
	}

}
