<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.trlink:hover{
	cursor: pointer;
	background-color: #ddd;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="faqsearch" method="post">
<table width="200px">
	<tr>
		<td>
			<select name="faqkey">
				<option value="title">제목</option>
				<option value="nickname">작성자</option>
			</select>
		</td>
		<td><input type="text" name="faqvalue"></td>
		<td><input type="submit" value="검색"></td>
	</tr>
</table>
</form>
<br>
<br>
<table align="center" width="900px" border="1">
<caption>문의</caption>
	<tr>
		<th>문의번호</th><th>TAB</th><th>제목</th><th>작성자</th><th>시간</th>
	</tr>
<c:forEach items="${list}" var="faq">
	<tr onclick="location.href='faqdetail?cnum=${faq.cnum}'" class="trlink">
		<td>${faq.cnum}</td>
		<td>${faq.tab}</td>
		<td>${faq.title}</td>
		<td>${faq.nickname}</td>
		<td>${faq.fdate}</td>
	</tr>		
</c:forEach>
<!-- 페이지-->
<tr style="border-left: none;border-right: none;border-bottom: none">
   <td colspan="8" style="text-align: center;">
   
   <c:if test="${paging.startPage!=1 }">
      <a href="boardout?nowPage=${paging.startPage-1 }&cntPerPage=${paging.cntPerPage}"></a>
   </c:if>   
      <c:forEach begin="${paging.startPage }" end="${paging.endPage}" var="p"> 
         <c:choose>
            <c:when test="${p == paging.nowPage }">
               <b><span style="color: red;">${p}</span></b>
            </c:when>   
            <c:when test="${p != paging.nowPage }">
               <a href="faqout?nowPage=${p}&cntPerPage=${paging.cntPerPage}">${p}</a>
            </c:when>   
         </c:choose>
      </c:forEach>
      
      <c:if test="${paging.endPage != paging.lastPage}">
      <a href="faqout?nowPage=${paging.endPage+1}&cntPerPage=${paging.cntPerPage }">  </a>
   </c:if>
   </td>
</tr>
</table>
<div class="container">
<h1>FAQ : 자주 묻는 질문</h1>
<!-- 
<h2>
	1. text : texttexttexttexttexttexttexttexttexttexttexttext<br><br>
	2. text : texttexttexttexttexttexttexttexttexttexttexttext<br><br>
	3. text : texttexttexttexttexttexttexttexttexttexttexttext<br><br>
	4. text : texttexttexttexttexttexttexttexttexttexttexttext<br><br>
	5. text : texttexttexttexttexttexttexttexttexttexttexttext<br><br>
	6. text : texttexttexttexttexttexttexttexttexttexttexttext<br><br>
	7. text : texttexttexttexttexttexttexttexttexttexttexttext<br><br>
	8. text : texttexttexttexttexttexttexttexttexttexttexttext<br><br>
	9. text : texttexttexttexttexttexttexttexttexttexttexttext<br><br>
	10. text : texttexttexttexttexttexttexttexttexttexttexttext<br><br>
	11. text : texttexttexttexttexttexttexttexttexttexttexttext<br><br>
	12. text : texttexttexttexttexttexttexttexttexttexttexttext<br><br>
	13. text : texttexttexttexttexttexttexttexttexttexttexttext<br><br>
</h2> -->
</div>
</body>
</html>