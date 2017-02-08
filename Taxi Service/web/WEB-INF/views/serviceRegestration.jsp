<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Регестрация нового офиса</title>
    <link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/structure.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/Registr_Service_Form_validator.js"/>" type="text/javascript"></script>
</head>
<body>

<form:form name="regForm"  modelAttribute="Service" method="POST" action="/addService"  class="box login1" onsubmit="return validate_form ( );">
    <fieldset class="boxBody">
        <form:label  path="city">Город :</form:label>
        <form:input name="city"  path="city" type="text" tabindex="1" placeholder="Введите город где находится офис"/>

        <form:label path="adress">Адресс :</form:label>
        <form:input name="adress"  path="adress" type="text" tabindex="1" placeholder="Введите адресс где находится офис"/>

        <form:label path="phoneNumber">Номер телефона :</form:label>
        <form:input name="phoneNumber" path="phoneNumber" type="text" tabindex="1" placeholder="Введите номер телефона службы" />
    </fieldset>
    <footer>
        <input type="submit" class="btn" value="Зарегестрировать" tabindex="4">
    </footer>
    <footer id="main"/>

</form:form>

</body>
</html>
