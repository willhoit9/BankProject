<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/bank.css" />
        <title>File Not Found</title>
    </head>
    <body>
        <div class="customError">
        <h3>404 Error</h3>
        
            <img src="images/404error.jpg">
        
            <p>
                The server was not able to find the file you were looking for. <br/>
                <br/>
                  Request that failed: ${pageContext.errorData.requestURI} <br/>

        
        
            </p>
        </div>
    </body>
</html>