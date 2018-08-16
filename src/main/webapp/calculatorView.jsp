<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Calculator</title>
<style>
span.inline {
	display: inline-block;
}
</style>
</head>
<body>
	
		<div class="container">
			<form name="inputForm" method="post" action="CalcWebApp">
				<h2>Input</h2>
				<textarea rows="25" cols="50" name="input">Write your JSON input here</textarea>
				<p>
					<input type="submit" value="Submit">
				</p>
			</form>
		</div>

	
		<div class="container">
			<form name="resultForm" method="get" action="CalcWebApp">
				<h2>Result</h2>
				<textarea rows="25" cols="50" name="result"></textarea>
			</form>
		</div>
</body>
</html>
