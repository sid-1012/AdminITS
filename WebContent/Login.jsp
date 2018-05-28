<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>AdminITS </title>

     <!-- Bootstrap core CSS -->

    <link href="css/bootstrap.min.css" rel="stylesheet">

    <link href="fonts/css/font-awesome.min.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">

    <!-- Custom styling plus plugins -->
    <link href="css/custom.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/maps/jquery-jvectormap-2.0.3.css" />
    <link href="css/icheck/flat/green.css" rel="stylesheet" />
    <link href="css/floatexamples.css" rel="stylesheet" type="text/css" />

    <script src="js/jquery.min.js"></script>
    <script src="js/nprogress.js"></script>

    <!--[if lt IE 9]>
        <script src="../assets/js/ie8-responsive-file-warning.js"></script>
        <![endif]-->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

</head>

<body style="background:#F7F7F7;">
    
   
    <div class="" >
        <a class="hiddenanchor" id="toregister" style="background:#F7F7F7;"></a>
        <a class="hiddenanchor" id="tologin" style="background:#F7F7F7;"></a>

        <div id="wrapper" style="background:#F7F7F7;">
            <div id="login" class="animate form" style="background:#F7F7F7;">
                <section class="login_content" style="background:#F7F7F7;">
                    <form action="AdminLoginController" method="post">
                        <h1>Login Form</h1>
                        
                        <center style="color: red;">
        				<% if(request.getAttribute("msg")!=null) {
        				out.println(request.getAttribute("msg").toString());
        				}
       					 %>
        
        				</center>
       					 <br><br>
                        
                        <div>
                            <input type="text" class="form-control" name="username" placeholder="Username" required="" />
                        </div>
                        <div>
                            <input type="password" class="form-control" name="password" placeholder="Password" required="" />
                        </div>
                        <div>
                            <input style="margin-left: 39%;" type="submit" value="Log in" name="Submit" class="btn btn-default"></input>
                            <br/>
                            <a style=margin-right:35%; class="reset_pass" href="ForgotPassword.jsp">Lost your password?</a>
                        </div>
                        <div class="clearfix"></div>
                        <br/>
                        <!-- <div class="separator">

                            <p class="change_link">New user?
                                <a href="#toregister" class="to_register"> Create Account </a>
                            </p>
                            <div class="clearfix"></div>
                            <br />
                            
                        </div> -->
                    </form>
                    <!-- form -->
                </section>
                <!-- content -->
            </div>
            <!-- <div id="register" class="animate form">
                <section class="login_content">
                    <form>
                        <h1>Create Account</h1>
                        <div>
                            <input type="text" class="form-control" placeholder="Username" required="" />
                        </div>
                        <div>
                            <input type="email" class="form-control" placeholder="Email" required="" />
                        </div>
                        <div>
                            <input type="password" class="form-control" placeholder="Password" required="" />
                        </div>
                        <div>
                            <input  style="margin-left: 39%;" type="submit" value="Register" name="Submit" class="btn btn-default"></input>
                        </div>
                        <div class="clearfix"></div>
                        <div class="separator">

                            <p class="change_link">Already a member ?
                                <a href="#tologin" class="to_register"> Log in </a>
                            </p>
                            <div class="clearfix"></div>
                            <br />
                           
                        </div>
                    </form>
                    form
                </section>
                content
            </div> -->
        </div>
    </div>

</body>

</html>