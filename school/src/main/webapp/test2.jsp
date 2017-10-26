<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>  
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
request.setAttribute("basePath", basePath);  
%>  
<!DOCTYPE html>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">  
<title>FileUpload</title>  
</head>  
<body>  
<form action="${basePath}post/newSavePost.action" method="post" enctype="multipart/form-data">  
	<input type="file" name="files" id="files">
	<br>
	context<input type="text" name="context" id="context"><br>
	tMId<input type="text" name="tMId" id="tMId"><br>
	title<input type="text" name="title" id="title"><br>
	<input type="submit" value="tijiao">
</form> 

</body>  
</html>