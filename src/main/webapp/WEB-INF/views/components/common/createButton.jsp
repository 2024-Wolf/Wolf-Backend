<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <% String currentUrl=request.getRequestURL().toString(); // 현재 URL을 문자열로 가져옴 String redirectUrl="" ; // 빈 문자열로 초기화
        if (currentUrl.startsWith("/notice")) { redirectUrl="/noticeCreate" ; } else if (currentUrl.startsWith("/faq"))
        { redirectUrl="/faqCreate" ; } else if (currentUrl.startsWith("/challenge")) { redirectUrl="/challengeCreate" ;
        } else { redirectUrl="/" ; } request.setAttribute("redirectUrl", redirectUrl); %>

        <span class="buttonSideContainer">
            <button type="button" class="formButton darkBackgroundButton" onclick="location.href='${redirectUrl}'">
                작성하기
            </button>
        </span>