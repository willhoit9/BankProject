<%-- 
 * Bank Project - Andrew Willhoit
 * myaccount.jsp
 * Created: 4/12/14
 * Last Updated: 4/12/14
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-1.10.2.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-ui-1.10.4.custom.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/bank.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/aTheme/jquery-ui-1.10.4.custom.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/bank.css" />
        
        <title>Picklebarrel Bank</title>
    </head>
  
    <body class="ui-widget-content" id="accountBody">   
        
        
        
        <c:import url="/header.jsp"></c:import>
        
        <div id="myAcountMain" class="centeredStuff">
            
        
        <div id="myAccountGreeting">
            <h1>Welcome ${fn:escapeXml(account.firstName)} ${fn:escapeXml(account.lastName)} </h1>           
        </div>

        <p>You are currently signed in under the username: ${fn:escapeXml(account.userName)}<br/>
        Your latest account activity was on: <fmt:formatDate value="${account.lastDate}" pattern="MM/dd/yyyy hh:mm:ss a" /><br/>
        You latest balance was: <fmt:formatNumber type="currency" value="${account.amount}" /><br />
       
        </p>

            <table>
                <thead>
                    <tr class="ui-widget-header">
                        <th>Date</th>
                        <th>Type</th>
                        <th>Amount</th>
                        <th>Remaining</th>
                    </tr>
                </thead>
                <tbody id="rows">
                    <%@include file="transactionRows.jsp" %>
                </tbody>
            </table>
            <button class="deposit" data-acctid="${fn:escapeXml(account.acctID)}">Deposit</button>
            <button class="withdraw" data-acctid="${fn:escapeXml(account.acctID)}">Withdraw</button>
        
            <div id="depositDialog">
                
            </div>    
            <div id="withdrawDialog">
                
            </div> 
            <br><br>
        </div>
        
    </body>
</html>



