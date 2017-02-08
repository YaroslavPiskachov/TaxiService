<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
  <head>
      <title>Авторизация</title>
    <link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/structure.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/Login_Form_validator.js"/>" type="text/javascript"></script>
  </head>
  <body>


  <form:form name="logInForm" class="box login" method="post" action="j_spring_security_check" onsubmit="return LoginValidate_form()">
    <fieldset class="boxBody">

        <label path="j_username" for="j_username">ФИО: </label>
        <input path="j_username" id="j_username" name="j_username" size="20" maxlength="50" type="text" placeholder="Введите свои Фамилию Имя Отчество" />

        <label path="j_password" for="j_password">Личный код: </label>
        <input path="j_password" id="j_password" name="j_password" size="20" maxlength="50" type="password" placeholder="Введите свой личный код авторизации" />

    </fieldset>
    <footer>
        <label style="color:RED; font-size:smaller;">${error}</label>
        <label style="color:BLUE; font-size:smaller;">${logout}</label>
        <input type="submit" class="btn" value="Войти" tabindex="4">
    </footer>
      <footer id="main">
          <a href="/regestrationService">Регистраиця нового офиса</a>
      </footer>
  </form:form>


  </body>
</html>




















