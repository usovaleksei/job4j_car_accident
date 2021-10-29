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
<div class = "container">
Hello : ${user}
    <div class="col-3">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Strings</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="string" items="${strings}">
                <tr>
                    <td><c:out value="${string}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>>

</body>
</html>