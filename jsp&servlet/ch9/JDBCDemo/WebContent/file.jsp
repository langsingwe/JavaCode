<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="fileService" 
             class="cc.openhome.FileService"
	         scope="application" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" 
              content="text/html; charset=UTF-8">
        <title>档案管理</title>
    </head>
    <body>
        <form method="post" enctype="multipart/form-data"
                            action="upload.do"><br>
                              选取档案：<input type="file" name="file"><br><br>
           <input type="submit" value="上传">
        </form>
        <hr>
        <table style="text-align: left;" border="1"
		       cellpadding="2" cellspacing="2">
		    <tbody>
		        <tr>
                    <td>文件名称</td>
                    <td>上传日期</td>
                    <td>操作</td>
                </tr>
		    <c:forEach var="file" items="${fileService.fileList}">
		        <tr>
		            <td>${file.filename}</td>
		            <td>${file.savedTime}</td>
		            <td><a href="download.do?id=${file.id}">下载</a> ／
		                <a href="delete.do?id=${file.id}">删除</a>
		            </td>
		        </tr>
		    </c:forEach>
		    </tbody>
	    </table>
    </body>
</html>