<%-- 
 * Bank Project - Andrew Willhoit
 * login.jsp
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
    <body class="ui-widget-content" id="loginBody">
        
        <c:import url="header.jsp"></c:import>
        
        <div id="myAcountMain" class="centeredStuff">
        
        <div id="login">
            <h1>Welcome to Picklebarrel Bank.</h1>

            <p>Please sign into your account.<br>
               If you do not have an account please create one below.
            </p>
            
            

<!--            LoginServlet-->
            <form action="<c:url value='/MyAccount'  />" method="POST">
               Username:<input type="text" name="username" maxlength="25" /><br/>
               Password: <input type="password" name="password" maxlength="100"/><br>
               <input type="submit" id="loginSubmit" />
            </form>
               
            <div id="errorLogin">            
                <c:out value="${message}"/>
            </div>   
        </div>
          
           
        <br/>
        
        <div id="createButton">
            Create Account:
            <input type="submit" id="createAccountButton" value="Create" />
            
        </div>
        
        
        
        <div id="createAccount">
            <div id="createError">            
                
                <c:out value="${createMessage}"/>
            </div>
            
        </div>

            
            
            <div id="createDialog">
                <form action="CreateServlet" method="POST">
                 Please enter a Username:<br/>
                 <input type="text" name="username" /><br/>
                 Please enter a Password:<br/>
                 <input type="password" name="password" /><br>
                 Please enter your First Name:<br/>
                 <input type="text" name="firstName" /><br/>
                 Please enter your Last Name:<br/>
                 <input type="text" name="lastName" /><br/>
                 Please enter your Email:<br/>
                 <input type="text" name="email" /><br>
            </form> 
            </div>
        
        
        
        </div>
        
        
    </body>
</html>
