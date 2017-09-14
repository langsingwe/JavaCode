package cc.openhome;

import java.net.URLEncoder;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/download.do")
public class Download extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                             throws ServletException, IOException {
        String id = request.getParameter("id");
        File file = new File();
        file.setId(Long.parseLong(id));
        
        FileService fileService = 
            (FileService) getServletContext().getAttribute("fileService");
        file = fileService.getFile(file);
        
        String filename = null;
        if(request.getHeader("User-Agent").contains("MSIE")) {
            filename = URLEncoder.encode(file.getFilename(), "UTF-8");
        }
        else {
            filename = new String(
                    file.getFilename().getBytes("UTF-8"), "ISO-8859-1");
        }
        
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", 
                "attachment; filename=\"" + filename + "\"");

        OutputStream out = response.getOutputStream();
        out.write(file.getBytes());
        out.close();
    }
}
