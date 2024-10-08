<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <script type="text/javascript">
    var isNicknameAvailable = false;
    var isIdAvailable =false;
      
   		$(document).ready(function() {
            $("#idcheck").click(function() {
                var id = $("#id").val();
                var idRegex = /^[a-zA-Z0-9]{4,12}$/;
                if (!idRegex.test(id)) {
                    alert('아이디는 4자 이상 12자 이하의 영문자 또는 숫자만 가능합니다');
                    $("#id").focus();
                    return;
                }
                $.ajax({
                    type: "post",
                    url: "idcheck1",
                    data: { "id": id },
                    async: true,
                    success: function(data) {
                        if (data == "ok") {
                            alert("사용 가능한 아이디입니다.");
                            isIdAvailable =true;
                        } else {
                            alert("이미 사용중인 아이디입니다.");
                            isIdAvailable =false;
                            $("#id").focus();
                        }
                    }
                });
            });

            $("#nicknamecheck").click(function() {
                var nickname = $("#nickname").val();
                $.ajax({
                    type: "post",
                    url: "nicknamecheck1",
                    data: { "nickname": nickname },
                    async: true,
                    success: function(data) {
                        if (data == "ok") {
                            alert("사용 가능한 닉네임입니다.");
                            isNicknameAvailable=true;
                        } else {
                            alert("이미 사용중인 닉네임입니다.");
                            isNicknameAvailable=false;
                            $("#nickname").focus();
                        }
                    }
                });
            });
        });
   		
        function check() {
            var id = $("#id").val();
            var pw = $("#pw").val();
            var pwconfirm = $("#pwconfirm").val();
            var nickname = $("#nickname").val();
            var name = $("#name").val();
            var phone1 = $("#phone1").val();
            var phone2 = $("#phone2").val();
            var idRegex = /^[a-zA-Z0-9]{4,12}$/;
            var pwRegex = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{4,16}$/; // 영문자, 숫자, 특수문자 포함
            var correctColor = "#00ff00";
            var wrongColor = "#ff0000";
            var confirmMsg = document.getElementById('confirmMsg');

            if (id.length == 0) {
                alert('아이디를 입력해주세요');
                $('#id').focus();
                return false;
            }
            if (!idRegex.test(id)) {
                alert('아이디는 4자 이상 12자 이하의 영문자 또는 숫자만 가능합니다');
                $('#id').focus();
                return false;
            }
            if(!isIdAvailable)
            	{
            	alert('아이디 중복 확인을 해주세요');
            	$('#idcheck').focus();
            	return false;
            	}
            if (!pwRegex.test(pw)) {
                alert('비밀번호는 4자 이상 16자 이하로 영문자,숫자,특수문자를 포함해야합니다.');
                $('#pw').focus();
                return false;
            }
            if (pw !== pwconfirm) {
                confirmMsg.style.color = wrongColor;
                confirmMsg.innerHTML = "비밀번호 불일치";
                $('#pwconfirm').focus();
                return false;
            } else {
                confirmMsg.style.color = correctColor;
                confirmMsg.innerHTML = "비밀번호 일치";
            }
            if(nickname.length=="")
            	{
            	alert('닉네임을 입력해주세요');
            	$('#nickname').focus();
            	return false;
            	}
            if(!isNicknameAvailable)
            	{
            	alert('닉네임 중복 확인해주세요');
            	$('#nickname').focus();
            	return false;
            	}
            if (name.trim() == "") {
                alert('이름을 입력해주세요');
                $('#name').focus();
                return false;
            }
            if (phone1.trim() == "" ) {
                alert('전화번호 앞자리를 입력해주세요');
                $('#phone1').focus();
                return false;
            }
            if ( phone2.trim() == "") {
                alert('전화번호 뒷자리를 입력해주세요');
                $('#phone2').focus();
                return false;
            }
            document.forms[0].submit();
        }
    </script>
    <meta charset="UTF-8">
    <title>회원가입 화면</title>
</head>
<body>
    <form action="membersave" method="post" id="myform">
        <table border="6" align="center" width="auto">
            <caption>회원가입화면</caption>
            <tr>
            <th>아이디☆</th>
                <td>
                    <input type="text" name="id" id="id" placeholder="id를 입력해주세요" maxlength="12">
                    <input type="button" name="idcheck" id="idcheck" value="중복확인">
                </td>
            </tr>
            <tr>
             <th>비밀번호☆</th>
                <td>
                    <input type="password" name="pw" id="pw" placeholder="비밀번호를 입력해주세요" maxlength="16">
                </td>
            </tr>
            <tr>
             <th>비밀번호 확인☆</th>
                <td colspan="2">
                    <input type="password" name="pwconfirm" id="pwconfirm" placeholder="비밀번호 확인" maxlength="16">
                    <span id="confirmMsg"></span>
                </td>
            </tr>
            <tr>
             <th>닉네임☆</th>
                <td>
                    <input type="text" name="nickname" id="nickname" placeholder="닉네임을 입력해주세요">
                    <input type="button" name="nicknamecheck" id="nicknamecheck" value="중복확인">
                </td>
            </tr>
            <tr>
             <th>이름☆</th>
                <td>
                    <input type="text" name="name" id="name" placeholder="이름을 입력해주세요" maxlength="6">
                </td>
            </tr>
            <tr>
              <th>생년월일</th>
                <td>
                    <input type="date" name="birth" id="birth">
                </td>
            </tr>
            <tr>
             <th>전화번호</th>
                <td>
                    010-<input type="text" name="phone1" id="phone1" maxlength="4">-<input type="text" name="phone2" id="phone2" maxlength="4">
                </td>
            </tr>
            <tr>
            <th>주소</th>
                <td>
                    <input type="text" id="postcode" placeholder="우편번호">
<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
<input type="text" id="mainaddress" placeholder="주소" name="mainaddress"><br>
<input type="text" id="detailAddress" placeholder="상세주소" name="detailaddress">
<input type="text" id="extraAddress" placeholder="참고항목"name="extraaddress">

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode;
                document.getElementById("mainaddress").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("detailAddress").focus();
            }
        }).open();
    }
</script>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="button" value="회원가입" onclick="check()"> 
                    <input type="reset" value="취소">
                </td>
            </tr>
        </table>
    </form>
</body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</html>