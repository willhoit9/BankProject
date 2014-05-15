<%-- 
 * Bank Project - Andrew Willhoit
 * transactionRows.jsp
 * Created: 4/12/14
 * Last Updated: 4/12/14
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:forEach var="u" items="${requestScope.transactions}" varStatus="i">
    <tr class="${i.index % 2 == 0 ? "ui-state-default" : ""}">    
        <td><fmt:formatDate value="${u.transDate}" pattern="MM/dd/yyyy hh:mm:ss a" /></td>
        <td>${fn:escapeXml(u.transType)}</td>
        <td><fmt:formatNumber type="currency" value="${u.transAmount}" /></td>
        <td><fmt:formatNumber type="currency" value="${u.transRemaining}" /></td>
    </tr>
</c:forEach>


