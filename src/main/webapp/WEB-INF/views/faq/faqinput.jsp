<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="faqsave" method="post" enctype="multipart/form-data">
	<table>
	<caption><span>문의글 작성</span></caption>
		<tr>
			<th>문의 종류</th>
			<td>
				<select name="tab">
					<option value="상품옵션 문의">상품옵션</option>
					<option value="교환/환불 문의">교환/환불</option>
					<option value="배송 문의">배송 문의</option>
					<option value="기타 문의">기타 문의</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title"></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="nickname"></td>
		</tr>
		<tr>
			<th>문의 내용</th>
			<td>
				<textarea rows="10" cols="25" name="fcontents"></textarea>
			</td>
		</tr>
		<tr>
			<th>첨부이미지</th>
			<td>
				<input type="file" name="fimage1">
				<input type="file" name="fimage2">
				<input type="file" name="fimage3">
			</td>
		</tr>
		<tr>
			<td><input type="submit" value="작성 완료"></td>
		</tr>
	</table>
</form>
</body>
</html>