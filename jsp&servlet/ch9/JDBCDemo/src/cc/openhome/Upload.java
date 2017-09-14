package cc.openhome;

import java.util.Date;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@MultipartConfig
@WebServlet("/upload.do")
public class Upload extends HttpServlet {
	protected void doPost(HttpServletRequest request, 
	                      HttpServletResponse response) 
	                 throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    Part part = request.getPart("file");
        
	    String filename = getFilename(part);
        byte[] bytes = getBytes(part);
        
        File file = new File(); 
        file.setFilename(filename);
        file.setBytes(bytes);
        file.setSavedTime(new Date());
        
        FileService service = (FileService) 
        getServletContext().getAttribute("fileService");
        service.save(file);
        
        response.sendRedirect("file.jsp");
	}

	private String getFilename(Part part) {
        String header = part.getHeader("Content-Disposition");
        String filename =
           header.substring(header.indexOf("filename=\"") + 10,
           header.lastIndexOf("\""));
        return filename;
    }
	
	private byte[] getBytes(Part part) throws IOException {
	    InputStream in = part.getInputStream();
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = -1;
        while ((length = in.read(buffer)) != -1) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.close();
        return out.toByteArray();
	}

}
