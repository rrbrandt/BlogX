<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
  <head>
    <title>Spring MVC Form Handling</title>
  </head>
  <body>

    <h2>Submitted Student Information</h2>
    <table>
      <tr>
        <td>blogDateType</td>
        <td>${blogDateType}</td>
      </tr>
      <tr>
        <td>blogSubject</td>
        <td>${blogSubject}</td>
      </tr>
      <tr>
        <td>blogEntry</td>
        <td>${blogEntry}</td>
      </tr>
    </table>  
    <a href="${pageContext.servletContext.contextPath}/blogEntry">home</a>
  </body>
</html>