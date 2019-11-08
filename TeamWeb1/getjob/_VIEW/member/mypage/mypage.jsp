<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>mypage</title>
<script type="text/javascript" >
$().ready(function(){
	$('#getjoblist').click(function(){
		alert("getjoblist");
/* 		$.get("insertMemberSkill.job", function(data){
			alert("data : "+data);
				$("#job-list").html(decodeURIComponent(data));
			}); */
		$.get("../getjoblist.job", function(data){
			alert(data);
			document.write(data);
		});
	});
  $('#test').click(function(){
    alert("test");
  });
});
</script>
<style type="text/css">
@import url("../css/view01.css");
</style>
</head>
<body>
	<h2> 회원가입 </h2>
	<button id="test">test</button>
	<div class="div">
		<div id="member-table">
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
					<td><input type="text" value="${memberinfo.member.name}"/></td>
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
		</div>
		<br>
		<button id="getjoblist">직업 가져오기</button>
		<div id="job-list"></div>
		<br />
		<div id="skill-table">
			<!-- skills -->
			<table class="table">
				<thead>내 기술</thead>
				<c:forEach var="skill" items="${memberinfo.skills}">
					<tr>
						<td>${skill}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<br>

		<div id="job-table">
			<!-- jobs -->
			<table class="table">
				<thead>선택 직무</thead>
				<c:forEach var="job" items="${memberinfo.jobs}">
					<tr>
						<td>${job}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
