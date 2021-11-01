<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Accident</title>

    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="col-6">
        <h3>Accidents</h3>
        <p><a href="<c:url value='/create'/>">Добавить инцидент</a></p>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">Text</th>
                <th scope="col">Address</th>
                <th scope="col">Type</th>
                <th scope="col">Rules</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="accident" items="${accidents}">
                <tr>
                    <td><c:out value="${accident.getId()}"/></td>
                    <td>
                        <a href="<c:url value='/update?id=${accident.id}'/>">
                        <c:out value="${accident.getName()}"/>
                        </a>
                    </td>
                    <td><c:out value="${accident.getText()}"/></td>
                    <td><c:out value="${accident.getAddress()}"/></td>
                    <td><c:out value="${accident.getType().name}"/></td>
                    <td>
                        <c:forEach var="rule" items="${accident.rules}">
                            <c:out value="${rule.name}"/>
                            <br>
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>