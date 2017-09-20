package cc.wei.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
/*
 * 文件上传
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
@Controller
public class UploadController {
	@RequestMapping("upload")
	public String upload(String username,@RequestParam("file")CommonsMultipartFile file,HttpServletRequest req) throws IOException {
		System.out.println("username===="+username);
		//获取上传路径
		String path = req.getServletContext().getRealPath("/upload");
		//获取文件名
		String fileName = file.getOriginalFilename();
		InputStream inputStream = file.getInputStream();
		OutputStream outputStream = new FileOutputStream(new File(path,fileName));
		int len = 0;
		byte[] buffer = new byte[200];
		while((len=inputStream.read(buffer))!=-1) {
			outputStream.write(buffer, 0, len);
		}
		outputStream.close();
		inputStream.close();
		return "redirect:success.jsp";
	}
}
