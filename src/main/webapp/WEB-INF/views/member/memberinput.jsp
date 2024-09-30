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
                <td>
                    <input type="text" name="id" id="id" placeholder="id를 입력해주세요" maxlength="12">
                    <input type="button" name="idcheck" id="idcheck" value="중복확인">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="password" name="pw" id="pw" placeholder="비밀번호를 입력해주세요" maxlength="16">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="password" name="pwconfirm" id="pwconfirm" placeholder="비밀번호 확인" maxlength="16">
                    <span id="confirmMsg"></span>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="nickname" id="nickname" placeholder="닉네임을 입력해주세요">
                    <input type="button" name="nicknamecheck" id="nicknamecheck" value="중복확인">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="name" id="name" placeholder="이름을 입력해주세요" maxlength="6">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="radio" name="gender" value="남성" id="gender-male">남성 
                    <input type="radio" name="gender" value="여성" id="gender-female">여성
                </td>
            </tr>
            <tr>
                <td>
                    <input type="date" name="birth" id="birth">
                </td>
            </tr>
            <tr>
                <td>
                    010-<input type="text" name="phone1" id="phone1" maxlength="4">-<input type="text" name="phone2" id="phone2" maxlength="4">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="address" id="address" placeholder="주소를 입력해주세요">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="fdomain" id="domain-txt">@ 
                    <select class="box" id="domain-list" name="bdomain">
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
                    <input type="button" value="회원가입" onclick="check()"> 
                    <input type="reset" value="취소">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>