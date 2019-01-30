<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Article Data</title>
</head>
<body>
	<h2>Article Data</h2>
	<table>
		<c:if test="empty total">
			<tr>
				<td>Sorry, Title is not available.</td>
			</tr>
		</c:if>
		<tr>
			<td><h1>${total.title}</h1></td>
		</tr>
		<tr>
			<td><b>Author: </b><span>${total.author}</span></td>
		</tr>
		<tr>
			<td>${total.description}</td>
		</tr>
	</table>
	<br />
	<input type="button" value="Back" onclick="javascript:history.back()" />
</body>
</html>