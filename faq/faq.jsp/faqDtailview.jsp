<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<!-- 
<script type="text/javascript">
$(document).ready(function() {
	$("#deletecheck").click(function() {
		if(confirm("정말 삭제하시겠습니까?")){
			var dch = $(this).data("#cnum");
			$.ajax({
				type: "post",
				url: "faqdelete",
				data:{"cnumber":dch},
				async:true,
				success:function(data){
					alert("삭제가 완료되었습니다.")
					window.location.href = "/faqout";
				}
			});
		}
		
	});
});
</script> -->
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="faqreply" method="post" enctype="multipart/form-data">
<table align="center" width="900px" border="1">
<c:forEach items="${list}" var="faq">
<caption><h1>${faq.nickname}님의 문의 내용</h1></caption>
	<input type="hidden" value="${faq.cnum}" name="cnum">
	<tr>
		<th colspan="4" id="cnum">${faq.cnum} ${faq.tab} ${faq.fdate}</th>
	</tr>
	<tr>
		<th>제 목</th><td>${faq.title}</td>
		<th>작성자</th><td>${faq.nickname}</td>
	<tr>
		<td colspan="4">${faq.fcontents}</td>
	</tr>
	<tr>
		<td colspan="4">
			<img src="./image/${faq.fimage1}" width="80px" height="70px">
			<img src="./image/${faq.fimage2}" width="80px" height="70px">
			<img src="./image/${faq.fimage3}" width="80px" height="70px">
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<input type="submit" value="(관리자 문의 답변)">
			<input type="button" value="문의글 수정" onclick="location.href='faqupdate?cnum=${faq.cnum}'">
			<input id="deletecheck" type="button" value="문의글 삭제" onclick="location.href='faqdelete?cnum=${faq.cnum}'">
			<input type="button" value="돌아가기" onclick="location.href='./faqout'">
		</td>
	</tr>
</c:forEach>
</table>
</form>
</body>
</html>