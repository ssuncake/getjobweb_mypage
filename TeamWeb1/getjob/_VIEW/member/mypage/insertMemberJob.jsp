<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<!DOCTYPE html>
		<html lang="en">
			<head>
				<meta charset="UTF-8">
					<title>mypage</title>
				</head>
				<body>
					<!-- jobs -->
					<table class="table" border="1">
						<thead border="1">선택 가능 직무
						</thead>
						<c:forEach var="job" items="${jobs}">
							<tr>
								<td>${job}</td>
							</tr>
						</c:forEach>
					</table>
				</body>
			</html>
