<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Список всех резюме</title></head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <table border="0" cellpadding="5" cellspacing="5">
        <tr>
            <th align="left" bgcolor="#525D76"><b>Имя</b></th>
            <th align="left" bgcolor="#525D76"><b>Email</b></th>
            <th align="left" bgcolor="#525D76"><a href="resume?&action=add"><img src="img/add.png"></a></th>
        </tr>
        <c:forEach items="${resumes}" var="resume">
            <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume"/>
            <tr>
                <td bgcolor="#525D76"><b><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></b>
                </td>
                <td bgcolor="#525D76"><b>${resume.getContact(ContactType.EMAIL)}</b></td>
                <td bgcolor="#525D76"><b><a href="resume?uuid=${resume.uuid}&action=delete"><img src="img/delete.png"></a></b></td>
                <td bgcolor="#525D76"><b><a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></b>
                </td>
                </b>
                </td>
            </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
