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
									<thead border="1">선택 직무 <button id="insertMemberJob">추가</button>
									</thead>
									<c:forEach var="job" items="${memberinfo.jobs}">
										<tr>
											<td>${job}</td> <td>
											<button value="${job}">삭제</button>
										</td>
										</tr>
									</c:forEach>
								</table>
						</body>
					</html>
