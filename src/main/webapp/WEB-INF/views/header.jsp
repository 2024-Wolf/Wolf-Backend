<%@ page contentType="text/html; charset=UTF-8" %>
   <link rel="stylesheet" href="/resources/css/header.css">
   <header class="HeaderContainer">
      <a href="/" class="LogoM">WOLF</a>
      <% if (!request.getRequestURI().endsWith("adminLogin.jsp")) { %>
         <div class="HeaderContent">
            <button class="HeaderButton LinePurpleButton" onclick="alert('로그인/회원가입')">로그아웃</button>
         </div>
         <% } %>
   </header>