<?php
    session_start();
    if(!isset($_SESSION['id'])){
        header ("Location: login.php");
    }
?>
<?php require_once "init.php"; ?>
<?php require_once "functions.php"; ?>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<title>Company Profile Admin</title>
	<link rel="stylesheet" href="css/bootstrap.min.css" />
	<link rel="stylesheet" href="css/main.css" />
	<link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
<!--	<link rel="stylesheet" media="screen" type="text/css" href="css/colorpicker.css" />-->
	<link rel="stylesheet" href="css/bootstrap-colorpicker.min.css">
	<link rel="shortcut icon" href="images/favicon.png">
</head>
<body>
	<header id="header" class="clearfix">
		<div class="container-fluid">
			<div class="row">
				<div class="logo col-md-2 col-sm-2">
					<center>
						<img src="images/logo.png" alt="" />		
					</center>
				</div>
				<div class="col-sm-10">
					<div class="user-details pull-right">
						<span class="user-image pull-right">
						<img src="images/user.png" alt="" class="img-responsive">
						</span>
												
						<li class="user-email pull-right"><a href=""><?php echo $_SESSION['email']; ?></a>
							<ul class="dropdown-item">
								<li><a href="logout.php">logout</a></li>
							</ul>
						</li>
                        <li class='pull-right redirection'>
                            <?php 
                                if(bodyClass() == "splash.php"){
                                    echo "<a href='display.php' class='btn btn-success'>Go to Display</a>";
                                }else{
                                    echo "<a href='splash.php' class='btn btn-success'>Go to Splash</a>";
                                }
                            ?>

                        </li>  				
					</div>			
				</div>
			</div>
		</div>
	</header>