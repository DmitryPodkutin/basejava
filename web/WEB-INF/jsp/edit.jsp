<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page import="ru.javawebinar.basejava.model.ListSection" %>
<%@ page import="org.apache.commons.lang3.StringEscapeUtils" %>
<%@ page import="java.lang.String" %>
<%@ page import="ru.javawebinar.basejava.model.OrganizationSection" %>
<%@ page import="util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <b>
                <dt>Имя:</dt>
            </b>
            <dd><input type="text" name="fullName" size=50 value="${resume.fullName}"></dd>
        </dl>
        <b>Контакты:</b>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type}" size=30 value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <c:forEach var="type" items="<%=SectionType.values()%>">
        <c:set var="section" value="${resume.getSection(type)}"/>
        <jsp:useBean id="section" type="ru.javawebinar.basejava.model.Section"/>
        <dl>
            <hr>
            <dl>
                <dt><b>${type.title}</b></dt>
                <br>
                <dl>
                    <c:choose>
                        <c:when test="${type=='OBJECTIVE'}">
                            <input type="text" name="${type}" size=80
                                   value="${section}">
                        </c:when>
                        <c:when test="${type=='PERSONAL'}">
                            <input type="text" name="${type}" size=80
                                   value="${section}">
                        </c:when>
                        <c:when test="${type=='ACHIEVEMENT'|| type=='QUALIFICATIONS'}">
                        <textarea rows="10" cols="110"
                                  name=${type}><%=String.join("\n", ((ListSection) section).getItems())%></textarea>
                        </c:when>
                        <c:when test="${type=='EXPERIENCE'|| type=='EDUCATION'}">

                            <c:forEach var="org" items="<%=((OrganizationSection)section).getOrganizations()%>"
                                       varStatus="counter">
                                <input type="text" name="${type}" size=32 value="${org.homepage.name}"
                                       placeholder="Название организации">
                                <input type="text" name='${type}url' size=50 value="${org.homepage.url}"
                                       placeholder="Сайт организации URL"><br>

                                <c:forEach var="position" items="${org.positions}">
                                    <input type="text" name='${type}${counter.index}beginDate' size=15
                                           value="${DateUtil.dateFormat(position.beginDate)}" placeholder="MM/yyyy">/
                                    <input type="text" name='${type}${counter.index}endDate' size=15
                                           value="${DateUtil.dateFormat(position.endDate)}" placeholder="MM/yyyy">
                                    <input type="text" name='${type}${counter.index}position' size=50
                                           value="${position.position}" placeholder="Занимаемая позиция"><br>
                                    <textarea rows="10" cols="110"
                                              name='${type}${counter.index}description'>${position.description}</textarea><br>
                                </c:forEach>

                            </c:forEach>
                        </c:when>
                    </c:choose>
                </dl>
                </c:forEach>
                <hr>
                <button type="submit">Сохранить</button>
                <button type="reset"  onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
