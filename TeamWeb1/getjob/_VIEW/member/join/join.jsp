<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>회원가입</title>
<script type="text/javascript">
	$()
			.ready(
					function() {
						var userIdCheck = false;
						$('#user-id')
								.keyup(
										function() {
											var id = $(this).val();
											var regEmail = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
											if (id == '' || !regEmail.test(id)) {
												$('#confirm')
														.html(
																"올바른 이메일 주소를 입력하세요<br>")
														.css("color", "red");
												return false;
											} else {
												$('#confirm').html("").css(
														"color", "red");
												userIdCheck = true;
											}
										});

						var pwValidation = false;
						$('#pw1').keyup(
								function() {
									var pw1_length = $(this).val().length;

									if (pw1_length < 8) {
										$('#pw-confirm').html("8글자 미만입니다.<br>")
												.css("color", "red");
									} else {
										pwValidation = true;
										$('#pw-confirm').html("OK").css(
												"color", "green");
									}
								});
						var pwCheck = false;
						$('#pw2').keyup(
								function() {
									var pw1 = $('#pw1').val();
									var pw2 = $('#pw2').val();
									if (!pwValidation) {
										$('#pw-confirm').html("8글자 미만입니다.<br>")
												.css("color", "red");
										;
									} else if (pw1 == pw2) {
										$('#pwcheck-confirm').html("OK<br>")
												.css("color", "green");
										pwCheck = true;
									} else {
										$('#pwcheck-confirm').html(
												"비밀번호가 다릅니다.<br>").css("color",
												"red");
									}
								});
						$('#submit-join')
								.click(
										function() {
											if (userIdCheck && pwCheck) {
												alert("validation OK : "
														+ userIdCheck);
												$
														.ajax({
															url : "/memberjoin.job",
															method : "POST",
															data : {
																id : $(
																		'#user-id')
																		.val(),
																pw : $('#pw1')
																		.val(),
																name : $(
																		'#user-name')
																		.val()
															},
															success : function(
																	data) {
																alert("성공");
															},
															error : function(
																	request,
																	status,
																	error) {
																alert("code:  "
																		+ request.status
																		+ "\n"
																		+ "message:  "
																		+ request.responseText
																		+ "\n"
																		+ "error:  "
																		+ error);
															}

														});
											} else {
												alert("validation OK : "
														+ userIdCheck);
											}

										});

					});
</script>
<style>
.form-group {
	width: auto;
	position: relative;
	left: 45%;
	transform: translateX(-50%);
	/* 	margin: 0 auto; */
}

.div-join {
	top: 10%;
	width: 300px;
	height: 400px;
	background: #eeffee;
	margin: 0 auto;
	margin-top: 100px;
	padding-top: 100px;
	padding-left: 50px;
	padding-right: 50px;
	text-align: center;
}

.form-confirm1 {
	height: auto;
	width: 300px;
	left: 45%;
	padding: 10%;
	text-align: center;
	padding: 10%;
}
</style>
</head>
<body>
	<div>
		<div class="div-join">
			<div class="form-group has-feddback">
				<input id="user-id" type="text" name="id" class="form-control"
					placeholder="아이디">
			</div>
			<span class="form-confirm" id="confirm"></span>

			<div class="form-group has-feddback">
				<input id="user-name" type="text" name="name" class="form-control"
					placeholder="이름">
			</div>

			<div class="form-group has-feddback">
				<input id="pw1" type="password" name="pw" class="form-control"
					placeholder="비밀번호">
			</div>
			<span class="form-confirm" id="pw-confirm"></span>

			<div class="form-group has-feddback">
				<input id="pw2" type="password" name="pwcheck" class="form-control"
					placeholder="비밀번호 확인">
			</div>
			<span class="form-confirm" id="pwcheck-confirm"></span>
			<button id="submit-join" class="form-group has-feddback">가입하기</button>
		</div>


	</div>

</body>
</html>