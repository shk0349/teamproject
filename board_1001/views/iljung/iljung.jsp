<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<h2>경기일정</h2>
<link href="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.min.js"></script>
<script>
document.addEventListener('DOMContentLoaded', function () {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        locale: 'ko',
        initialView: 'dayGridMonth',
        events: {
            url: '/events',  // 서버에서 일정 데이터를 불러옴
            method: 'GET',   // 요청 방식
            failure: function() {
                alert('일정을 불러오는 데 실패했습니다.');
            }
        },
        dayCellContent: function (info) {
            var number = document.createElement("a");
            number.classList.add("fc-daygrid-day-number");
            number.innerHTML = info.dayNumberText.replace("일", "").replace("日", "");

            if (info.view.type === "dayGridMonth") {
                return {
                    html: number.outerHTML
                };
            }
            return {
                domNodes: []
            };
        }
    });

    calendar.render();
});

</script>

<style type="text/css">
    .calendar-container {
        display: flex;
        justify-content: center; /* 가로 중앙 정렬 */
        margin-top: 50px; /* 캘린더 상단에 여백 추가 */
    }

    #calendar {
        width: 60%;
        height: 60%;
    }

    th, td {
        text-align: center;
    }

    /* 버튼 스타일 */
    .add-event-button {
        display: block;
        margin: 20px auto; /* 버튼을 화면 가운데 정렬 */
        padding: 10px 20px;
        background-color: #007BFF; /* 버튼 색상 */
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 16px;
    }

    .add-event-button:hover {
        background-color: #0056b3; /* 버튼 호버 시 색상 변경 */
    }
</style>
</head>
<body>
    <div class="calendar-container">
        <div id="calendar"></div>
    </div>

    <!-- 일정 추가 버튼 -->
    <input type="button" class="add-event-button" onclick="location.href='iljunginput'" value="일정추가">
    
</body>
</html>
