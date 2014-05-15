<%-- 
 * Bank Project - Andrew Willhoit
 * withdrawBox.jsp
 * Created: 4/13/14
 * Last Updated: 4/13/14
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form id="withdrawBoxForm" action="WithdrawServlet" onsubmit="return false" method="POST">

    <table>
        <tr>
            <td>Withdraw<br/>  from: </td>
            <td><c:out value="${acct.firstName}"/> <c:out value="${acct.lastName}"/>'s Account</td>
        </tr>
        
        <tr>
            <td>Current<br/> Balance: </td>
            <td><fmt:formatNumber type="currency" value="${acct.amount}" /></td>
            
        </tr>
        
        <tr>
            <td>Withdraw<br/>  Amount:</td>

            <td><input type="text" id="withdrawAmount" name="withdrawAmount" maxlength="10" /></td>
        </tr>
    </table>
</form>