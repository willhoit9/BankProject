<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/bank.css" />
        <title>Error</title>
    </head>
    <body>
        <div class="customError">
        <h3>Java Error</h3>
        Status code: ${pageContext.errorData.statusCode} <br/>
        
         <img src="images/500error.jpg">
        <h4>Details:</h4>
        <p>
            
        Request that failed: ${pageContext.errorData.requestURI} <br/>

        

        Exception: ${pageContext.errorData.throwable} <br/>
 
        

            
        </p>
        </div>
    </body>
</html>