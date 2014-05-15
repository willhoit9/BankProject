<%-- 
 * Bank Project - Andrew Willhoit
 * create.jsp
 * Created: 4/12/14
 * Last Updated: 4/14/14
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

        
<form action="CreateServlet" onsubmit="return false"  method="POST">
    
    <table>        
        <tr>
            <td>Please enter <br/> a Username:</td>
            <td><input type="text" name="username" />
        </tr>
        <tr>
            <td>Please enter a Password:</td>
            <td><input type="password" name="password" /></td>
        </tr>         
        <tr>
            <td>Please enter your First Name:</td>
            <td><input type="text" name="firstName" /></td>
        </tr>
        <tr>
            <td>Please enter your Last Name:</td>
            <td><input type="text" name="lastName" /></td>
        </tr>
        <tr>
            <td>Please enter your Email:</td>
            <td><input type="text" name="email" /></td>
        </tr>
    </table>
</form>
        
        
        
        
        
        
        
        
        
