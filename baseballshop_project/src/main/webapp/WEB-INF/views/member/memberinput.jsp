<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="membersave" method="post">
<table border="6" align="center" width="auto">
<caption>회원가입화면</caption>
	<tr>
		<th>아이디를 입력해주세요</th>
			<td>
			<input type="text" name="id" id="id1">
			<input type="button" name="idcheck" id="idcheck"  value="중복확인" onclick="#"> 
			</td>
	</tr>
	<tr>
		<th>비밀번호를 입력해주세요</th>
			<td><input type="password" name="pw" id="id1"></td>
	</tr>
	<tr>
		<th>닉네임를 입력해주세요</th>
			<td>
			<input type="text" name="nickname" id="nickname1">
			<input type="button" name="nicknamecheck" id="nicknamecheck" value=" 중복확인" onclick="#"> 
			</td>
	</tr>
	<tr>
		<th>이름을 입력해주세요</th>
			<td><input type="text" name="name" id="name1"></td>
	</tr>
	<tr>
		<th>성별을 입력해주세요</th>
			<td>
			<input type="radio" name="gender" value="남성" id="gender">남성
			<input type="radio" name="gender" value="여성" id="gender" >여성
			</td>
	</tr>
	<tr>
		<th>생년월일을 입력해주세요</th>
			<td><input type="date" name="birth" id="birth"></td>
	</tr>
	<tr>
		<th>전화번호를 입력해주세요</th>
			<td>010-<input type="text" name="phone1" id="phone1">-<input type="text" name="phone2" id="phone2">
			</td>
	</tr>
	<tr>
		<th>이메일주소를 입력해주세요</th>
			<td>
			<input type="text" name="domain" id="domain-txt">@
			<select  class="box"  id="domain-list">
			<option value="naver.com">naver.com</option>
			<option value="google.com">google.com</option>
			<option value="hanmail.net">hanmail.net</option>
			<option value="nate.com">nate.com</option>
			<option value="kakao.com">kakao.com</option>
			</select>
			</td>
	</tr>
	<tr>
	<td colspan="2">
	<input type="button" value="전송" onclick="location.href='#'" id="button1">
	<input type="reset" value="취소">
	</td>
	</tr>
</table>
</form>
</body>
</html>