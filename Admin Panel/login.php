<?php 
    session_start();
    if(isset($_SESSION['id'])){
        header ("Location: display.php");
    }

?>
   <?php 
    include "init.php";
    include "inc/functions.php";
?>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Company Profile Login Page</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/main.css" rel="stylesheet">
	<link rel="shortcut icon" href="images/favicon.png">	
</head>
<body>
	<div class="login-area">
		<h1>Login Panel</h1>
		<p>Please Login with your Profile</p>
		
		<?php
            if(isset($_POST['submit'])){
                $email = sanitize($_POST['email']);
                $password = sanitize($_POST['password']);
                $password = md5($password);
                
                $user = User::find_by_email($email);
                
                
                
                $email_exists = count($user);
                
                if($email_exists !== 0 ){
                    
                    $db_email = $user->email;
                    $db_password = $user->password;
                    
                   if( $email == $db_email &&  $password == $db_password ){
                       
                        $status = Status::find('all');
                       
                       
                       
                        $_SESSION['email'] = $db_email;
                        $_SESSION['profile_pic'] = $user->profile_pic;
                        $_SESSION['id'] = $user->id;
                      
                        // condition to redirect page
                        if( count($status) > 0){
                            header("Location: display.php");
                        }else{
                            header("Location: splash.php");
                        }
                       
                       
                   }else{
                       $message= "<span class='error'>Password Doesn't Match</span>";
                   }
                    
                }else{
                    $message=  "<span class='error'>Email Not Exist</span>";
                }
                
            }
        ?>
		
		
		<form class="form-horizontal login-form" method="post">
            <?php 
                if(isset($message)){
                    echo $message;
                }
            ?>
		  <div class="form-group">
			<label for="company-name" class="col-sm-6 control-label">Enter Your Email</label>
			<div class="col-sm-6">
			     <input type="text" class="form-control" name="email" id="email-id" placeholder="jondoe">
			</div>
		  </div>
		  <div class="form-group">
			<label for="company-slogan" class="col-sm-6 control-label">Enter Your Password</label>
			<div class="col-sm-6">
			     <input type="password" name="password" class="form-control" id="password" placeholder="Password">
			</div>
		  </div>		  

		  <div class="form-group">
            <div class="col-sm-12">
                <input type="submit" Value="login" name="submit" class="btn btn-default login">
            </div>
			  
		  </div>
		</form>	
	</div>	
</body>
</html>
