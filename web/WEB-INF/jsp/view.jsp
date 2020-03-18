<%@ page import="ru.javawebinar.basejava.model.DescriptionSection" %>
<%@ page import="ru.javawebinar.basejava.model.ListSection" %>
<%@ page import="ru.javawebinar.basejava.model.OrganizationSection" %>
<%@ page import="util.HtmlUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/viewStyle.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>

    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit">Edit</a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<ru.javawebinar.basejava.model.ContactType, java.lang.String>"/>
                <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br>
        </c:forEach>
    <hr>
    <table cellpadding="2">
        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<ru.javawebinar.basejava.model.SectionType, ru.javawebinar.basejava.model.Section>"/>
            <c:set var="type" value="${sectionEntry.key}"/>
            <c:set var="value" value="${sectionEntry.value}"/>
            <jsp:useBean id="value"
                         type="ru.javawebinar.basejava.model.Section"/>
            <c:if test="${value != null}">
                <tr>
                    <td colspan="2"><h2>${type.title}</h2></td>
                </tr>
                <c:choose>
                    <c:when test="${type=='OBJECTIVE'}">
                        <tr>
                            <td colspan="2"><h3><%=((DescriptionSection) value).getDescription()%>
                            </h3></td>
                        </tr>
                    </c:when>
                    <c:when test="${type=='PERSONAL'}">
                        <tr>
                            <td colspan="2"><%=((DescriptionSection) value).getDescription()%>
                            </td>
                        </tr>
                    </c:when>
                    <c:when test="${type=='ACHIEVEMENT'|| type=='QUALIFICATIONS'}">
                        <tr>
                            <td colspan="2">
                                <ul>
                                    <c:forEach var="listSection" items="<%=((ListSection)value).getItems()%>">
                                            <li>${listSection}</li
                                       >
                                    </c:forEach>
                                </ul>
                            </td>
                        </tr>
                    </c:when>
                    <c:when test="${type=='EXPERIENCE'|| type=='EDUCATION'}">
                        <c:forEach var="organization"
                                   items="<%=((OrganizationSection)value).getOrganizations()%>">
                            <tr>
                                <td colspan="2">
                                    <h3>${HtmlUtil.urlFormat(organization.homepage)}</h3>
                                </td>
                            </tr>
                            <c:forEach var="position" items="${organization.positions}">
                                <tr>
                                    <td width="15%"
                                        style="vertical-align: top">${HtmlUtil.dateFormat(position.beginDate)}-${HtmlUtil.dateFormat(position.endDate)}</td>
                                    <td><b>${position.position}</b><br>
                                            ${position.description}
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                    </c:when>
                </c:choose>
            </c:if>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>