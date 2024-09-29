<%@ page contentType="text/html; charset=UTF-8" %>
   <link rel="stylesheet" href="/resources/css/header.css">
   <header class="HeaderContainer">
      <a href="/" class="LogoM">WOLF</a>
      <% if (!request.getRequestURI().endsWith("adminLogin.jsp")) { %>
         <div class="HeaderContent">
            <button class="HeaderButton LinePurpleButton" onclick="location.href='/'">로그아웃</button>
         </div>
         <% } %>
   </header>