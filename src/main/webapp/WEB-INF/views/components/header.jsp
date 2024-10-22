<%@ page contentType="text/html; charset=UTF-8" %>
   <link rel="stylesheet" href="/resources/css/header.css">
   <header class="HeaderContainer">
      <a href="/" class="LogoM">WOLF</a>
      <% if (!request.getRequestURI().endsWith("adminLogin.jsp")) { %>
         <div class="HeaderContent">
            <button class="HeaderButton LinePurpleButton" onclick="logout()">로그아웃</button>
         </div>
         <% } %>
   </header>

<script>
   function logout() {
      fetch('/admin/auth/logout', {
         method: 'POST',
         headers: {
            'Content-Type': 'application/json'
         }
      }).then(response => {
         if (response.ok) {
            window.location.href = '/';  // 성공 시 로그인 페이지로 이동
         } else {
            alert('로그아웃 실패');
         }
      }).catch(error => {
         alert('로그아웃 중 오류가 발생했습니다.');
      });
   }
</script>