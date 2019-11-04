<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<!DOCTYPE html>
	<html lang="en">
			<head>
				<meta charset="UTF-8">
					<title>mypage</title>
				</head>
				<body>
							<!-- skills -->
							<table class="table" border="1">
								<thead>내 기술<button id="insertMemberSkill">추가</button>
								</thead>
								<c:forEach var="skill" items="${memberinfo.skills}">
									<tr>
										<td>${skill}</td><td>
										<button value="${skill}">삭제</button>
									</td>
									</tr>
								</c:forEach>
							</table>
						</body>
					</html>
