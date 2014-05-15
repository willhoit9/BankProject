/* 
 * Bank Project - Andrew Willhoit
 * bank.js
 * Created: 4/12/14
 * Last Updated: 4/12/14
 */


jQuery(function($)
{    

    function process() {


        $("button.deposit").button({
            icons: {
                primary: "ui-icon-circle-plus"
            }
        });

        $("button.withdraw").button({
            icons: {
                primary: "ui-icon-circle-minus"
            }
        });
        
        

        $("button.deposit").click(function()
        {
           var acctID = $(this).data("acctid");
           console.log("Deposit clicked for acct: " + acctID);    
           $.ajax({
               url: "GetAccountServlet",
               type: "POST",
               data: {acctID: acctID},
               success: function(data, txt, xhr)
               {
                   $("#depositDialog").html(data);
                   $("#depositDialog").dialog("open");                   
               }               
           });//end ajax()
        }); // end button.deposit.click()
        
        $("button.withdraw").click(function()
        {
            var acctID = $(this).data("acctid");
            console.log("Withdraw clicked for acct: " + acctID);
            $.ajax({
                url: "GetAccountServlet",
                type: "POST",
                data: {acctID: acctID},
                success: function(data, txt, xhr)
                {
                    $("#withdrawDialog").html(data);
                    $("#withdrawDialog").dialog("open");
                }
            });//end ajax()
        }); // end button.withdraw.click()


    } // end process()
    
    process();
    
    $("#loginSubmit").button({
        icons: {
            primary: "ui-icon-circle-minus"
        }
    });
    
    $("#createAccountButton").button({
        icons: {
            primary: "ui-icon-circle-minus"
        }
    });
    
    $("#createAccountButton").click(function()
    {
        $("#createDialog").dialog("open");
    });
    
    function closeCreateDialog()
    {
         $("#createDialog").dialog("close");
    } //end closeCreateDialog()
    
    
    $("#createDialog").dialog({
        title: "Create Account",
        modal: true,
        autoOpen: false,
        width: 450,
        buttons: [
            {
                text: "Submit",
                click: function()
                {
                    createAcct();
                    //$(this).dialog("close");
                }
            },
            {
                text: "Cancel",
                click: function()
                {
                    $(this).dialog("close");
                   // closeCreateDialog();  
                }
            }
        ]
        
    }); // end createDialog
    
    function createAcct()
    {
         var queryString = $("#createDialog form").serialize();
         
      //   alert(queryString);
         
         $.ajax({ 
             url: "CreateServlet",
             type: "POST",
             data: queryString,
             success: function(data, txt, xhr)
             {
                 var msg = xhr.getResponseHeader("X-Message");
                 if("success" === msg)
                 { 
                     alert("Your account was created. Please Login");
                     closeCreateDialog();       
                   //  $(this).dialog("close");
                 } else 
                 {
                     alert(msg);
                 }
             }             
        }); // end .ajax     
    } //end createAcct()
    
    
    
    $("#depositDialog").dialog({
        title: "Deposit",
        modal: true,
        autoOpen: false,        
        width: 450,
        buttons: [
            {
                text: "Deposit",
                click: function()
                {
                    depositAmt();
                    $(this).dialog("close");
                }
            },
            {
                text: "Cancel",
                click: function()
                {
                    $(this).dialog("close");
                }
            }
        ]        
    }); //end depositDialog 

    $("#withdrawDialog").dialog({
        title: "Withdraw",
        modal: true,
        autoOpen: false,        
        width: 450,
        buttons: [
            {
                text: "Withdraw",
                click: function()
                {
                    withdrawAmt();
                    $(this).dialog("close");
                }
            },
            {
                text: "Cancel",
                click: function()
                {
                    $(this).dialog("close");
                }
            }
        ]        
    }); //end withdrawDialog     
   
    function depositAmt()
    {
         var queryString = $("#depositDialog form").serialize();
         
         $.ajax({ 
             url: "DepositServlet",
             type: "POST",
             data: queryString,
             success: function(data, txt, xhr)
             {
                 var msg = xhr.getResponseHeader("X-Message");
                 if("success" === msg)
                 { 
                     $("table tbody#rows").html(data);
                     process();
                     

                   //  $(this).dialog("close");
                 } else 
                 {
                     alert(msg);
                 }
             }             
        }); // end .ajax     
    } //end depositAmt()
    
    function withdrawAmt()
    {
         var queryString = $("#withdrawDialog form").serialize();         
         $.ajax({ 
             url: "WithdrawServlet",
             type: "POST",
             data: queryString,
             success: function(data, txt, xhr)
             {
                 var msg = xhr.getResponseHeader("X-Message");
                 if("success" === msg)
                 { 
                     $("table tbody#rows").html(data);
                     process();

                   //  $(this).dialog("close");
                 } else 
                 {
                     alert(msg);
                 }
             }             
        }); // end .ajax     
    } //end withdrawAmt()
    
    
    $('input:text, input:password, input[type=email]').button()
            .addClass('ui-textfield')
            .off('mouseenter').off('mousedown').off('keydown');

    
    
    
    
    
    
    
    
    
}); //end jQuery(function($)