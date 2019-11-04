<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<!DOCTYPE html>
	<html lang="en">
			<head>
				<meta charset="UTF-8">
					<title>mypage</title>
	<style type="text/css">
@import url("../../../css/view01.css");
</style>
				</head>
				<body>
					<div class="div">
						<table class="table-member">
							<tr>
								<td>아이디</td>
								<td><label id="id">${memberinfo.member.id}</td>
							</tr>
							<tr>
								<td>비밀번호</td>
								<td><input type="password" value="${memberinfo.member.password}"/></td>
							</tr>
							<tr>
								<td>이름</td>
								<td>${memberinfo.member.name}</td>
							</tr>
							<tr>
								<td>프로필작성여부</td>
								<td>${memberinfo.member.auth}</td>
							</tr>
							<tr>
								<td>자격증</td>
								<td>${memberinfo.member.certificate}</td>
							</tr>

						</table>
						<br>

							<!-- skills -->
							<table class="table" border="1">
								<thead>내 기술
								</thead>
								<c:forEach var="skill" items="${memberinfo.skills}">
									<tr>
										<td>${skill}</td><td>
										<a href="deleteMemberSkill.job">삭제</a>
									</td>
									</tr>
								</c:forEach>
							</table>
							<br>
								<!-- jobs -->
								<table class="table" border="1">
									<thead border="1">선택 직무
									</thead>
									<c:forEach var="job" items="${memberinfo.jobs}">
										<tr>
											<td>${job}</td><td>
											<a href="deleteMemberSkill.job">삭제</a>
										</td>
										</tr>
									</c:forEach>
								</table>

							</div>
						</body>
					</html>
