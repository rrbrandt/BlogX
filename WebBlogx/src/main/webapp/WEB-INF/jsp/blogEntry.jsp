<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
         pageEncoding="ISO-8859-1"%> 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
  <head>
    <title>New Blog Entry</title>
    <style>
      div.container {
        width: 100%;
        border: 1px solid gray;
      }

      td {
        vertical-align: top;
      }

      header, footer {
        padding: 1em;
        color: black;
        background-color: white;
        clear: left;
        text-align: center;
        border-bottom: 1px solid gray;
      }

      nav {
        float: left;
        max-width: 160px;
        margin: 0;
        padding: 1em;
      }

      nav ul {
        list-style-type: none;
        padding: 0;
      }

      nav ul a {
        text-decoration: none;
      }

      article {
        border-left: 1px solid gray;
        padding: 1em;
        overflow: hidden;
      }
    </style>
  </head>
  <body>

    <article>
      <header><h1>WebBlogx</h1> ver. 1.2</header>
      
      <nav>
        <h2>Blog History</h2>
        ${listOfBlogEntries}
      </nav>


      <article>
        <h2>Blog Entry</h2>
        <a href="${pageContext.servletContext.contextPath}">logout</a>
        <form:form  method="post" action="/WebBlogx/crudBlogEntry">

          <table style="width:100%">
            <tr>
              <td style="width:10%" align="right" ><form:label path="blogEntryDateTime" >DateTime</form:label></td>
              <td style="width:90%"><form:input id = "datepicker"
                          name="date"
                          itemLabel="date"
                          path="blogEntryDateTime" /> yyyy/MM/dd HH:mm:ss</td>
            </tr>
            <tr>
              <td align="right"><form:label path="blogSubject">Subject</form:label></td>
              <td><form:input size="80" path="blogSubject"/></td>
            </tr>
            <tr>
              <td align="right" ><form:label path="blogEntry">Entry</form:label></td>
              <td><form:textarea path="blogEntry"  rows="20" cols="82" /></td>
            </tr>
            <tr>
              <td colspan="2">
                <input type="submit" value="save" name="save"/>
                <input type="submit" value="delete" name="delete"/>
              </td>
            </tr>
          </table>  
        </form:form>
      </article>
    </article>
  </body>
</html>