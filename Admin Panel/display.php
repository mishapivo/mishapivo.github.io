<?php include "inc/header.php"; ?>
	
	<?php 
        if(isset($_GET['status'])){
            $status_count = Status::find('all');
            if( !count($status_count) > 0){
                $attributes = array(
                    "status" => 1
                );
                Status::create($attributes);
            }
        }
    ?>
	
	
	<section id="main" class="main">
		<div class="container-fluid">
			<div class="row">
				<?php require_once 'inc/display_sidebar.php'; ?>
				<div id="content" class="content col-md-10 col-sm-10">
					<div class="space"></div>
					<div class="content-wrapper tab-content customize_tab">
                   
					   <div class="tab-pane active" id="sidebartab"><!--- tab Sidebar  --->
                            <div id="content-header" class="clearfix">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <h3 class="section-title">Sidebar SCREEN </h3>
                                </div>
                            </div>

                            <div class="data-area">
                                <form class="form-horizontal" action="">
                                    <div class="repeat">
                                        <table class="wrapper" width="100%">
                                            <thead>
                                                <tr>
                                                    <th><span style="margin-left: 10px;">Background</span></th>
                                                    <th>Logo</th>
                                                    <th>Phone</th>
                                                    <th>Email</th>
                                                    <th>Web</th>
                                                    <th colspan="2">Actions</th>
                                                </tr>
                                            </thead>
                                            <tbody class="form-container" id="display_sidebar">


                                            </tbody>
                                        </table>
                                    </div>											 

                                </form>		
                            </div>

                            <div id="content-footer" class="clearfix">
                                <button type="button" class="btn btn-default next">NEXT</button>
                            </div>
						</div>  <!--// tab Sidebar end-->
                   
					   <div class="tab-pane" id="splash"><!--- tab Splash  --->
                            <div id="content-header" class="clearfix">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <h3 class="section-title">Splash SCREEN </h3>
                                </div>
                            </div>

                            <div class="data-area">
                                <form class="form-horizontal" action="">
                                    <div class="repeat">
                                        <table class="wrapper" width="100%">
                                            <thead>
                                                <tr>
                                                    <th><span style="margin-left: 10px;">Image</span></th>
                                                    <th>Background Color</th>
                                                    <th colspan="2">Actions</th>
                                                </tr>
                                            </thead>
                                            <tbody class="form-container" id="display_splash">


                                            </tbody>
                                        </table>
                                    </div>											 

                                </form>		
                            </div>

                            <div id="content-footer" class="clearfix">
                                <button type="button" class="btn btn-default next">NEXT</button>
                            </div>
						</div>  <!--// tab Splash end-->	
                   
					   <div class="tab-pane " id="home"><!--- tab Home  --->
                            <div id="content-header" class="clearfix">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <h3 class="section-title">HOME SCREEN </h3>
                                </div>
                            </div>

                            <div class="data-area">
                                <form class="form-horizontal" action="">
                                    <div class="repeat">
                                        <table class="wrapper" width="100%">
                                            <thead>
                                                <tr>
                                                    <th><span style="margin-left: 10px;">Image</span></th>
                                                    <th>Title</th>
                                                    <th colspan="2">Actions</th>
                                                </tr>
                                            </thead>
                                            <tbody class="form-container" id="display_slider">


                                            </tbody>
                                        </table>
                                    </div>											 

                                </form>		
                            </div>

                            <div id="content-footer" class="clearfix">
                                <a href="splash.php?home" type="button" class="btn btn-default add-more">add more+</a>
                                <button type="button" class="btn btn-default save">SAVE</button>
                                <button type="button" class="btn btn-default next">NEXT</button>
                            </div>
						</div>  <!--// tab Home end-->	
						

                                                                                                                   
                                                                                                                    
						<div class="tab-pane" id="overview"> <!-- Overview tab start -->
                            <div id="content-header" class="clearfix">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <h3 class="section-title">Overview SCREEN </h3>
                                </div>
                            </div>

                            <div class="data-area">
                                <form class="form-horizontal" action="">

                                    <div class="repeat">
                                        <table class="wrapper" width="100%">
                                            <thead>
                                                <tr>
                                                    <th><span style="margin-left: 10px;">Background</span></th>
                                                    <th>Logo</th>
                                                    <th>Ttitle</th>
                                                    <th>Promo</th>
                                                    <th>Count1</th>
                                                    <th>Count1 No</th>
                                                    <th>Count2</th>
                                                    <th>Count2 No</th>
                                                    <th>Count3</th>
                                                    <th>Count3 No</th>
                                                    <th></th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody class="form-container" id="display_overview_content">

                                            </tbody>										
                                        </table>
                                    </div>


                                    <div class="repeat">
                                        <table class="wrapper" width="100%">
                                            <thead>
                                                <tr>
                                                    <th><span style="margin-left: 10px;">Overview Icon</span></th>
                                                    <th>Overview Title</th>
                                                    <th>Overview Details</th>
                                                    <th></th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody class="form-container" id="display_companyinfo">

                                            </tbody>
                                        </table>
                                    </div>											 						
                                </form>		
                            </div>

                            <div id="content-footer" class="clearfix">
                                <a href="splash.php?companyinfo" class="btn btn-default add-more" >add more+</a>
                                <button type="button" class="btn btn-default save" id="overview_submit">SAVE</button>
                                <button type="button" class="btn btn-default next">NEXT</button>
                            </div>
                        </div><!--// Overview tab end-->
                                                                                                                                                                                                           
						<div class="tab-pane" id="portfolio"> <!-- Portfolio tab start -->
                            <div id="content-header" class="clearfix">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <h3 class="section-title">PORTFOLIO SCREEN </h3>
                                </div>
                            </div>

                            <div class="data-area">
                                <form class="form-horizontal" action="">
                                    <div class="repeat">
                                        <table class="wrapper" width="100%">
                                            <thead>
                                                <tr>
                                                    <th><span style="margin-left: 10px;">Image</span></th>
                                                    <th>Title</th>
                                                    <th>Tag</th>
                                                    <th></th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody class="form-container" id="display_portfolio">

                                            </tbody>
                                        </table>
                                    </div>											 
                                </form>	
                            </div>

                            <div id="content-footer" class="clearfix">
                                <a href="splash.php?portfolio" class="btn btn-default add-more" id="add_portfolio">add more+</a>
                                <button type="button" class="btn btn-default save" id="portfolio_submit">SAVE</button>
                                <button type="button" class="btn btn-default next">NEXT</button>
                            </div>
                        </div><!--// Portfolio tab end--> 
                                                                                                                    
                        <div class="tab-pane" id="team"> <!-- Team tab start -->
                            <div id="content-header" class="clearfix">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <h3 class="section-title">Team SCREEN </h3>
                                </div>
                            </div>

                            <div class="data-area">
							<form class="form-horizontal" action="">
								<div class="repeat">
									<table class="wrapper" width="100%">
										<thead>
											<tr>
												<th><span style="margin-left: 10px;">Image</span></th>
												<th>Name</th>
												<th>Designation</th>
												<th></th>
												<th></th>
											</tr>
										</thead>
										<tbody class="form-container" id="display_team">
										</tbody>
									</table>
								</div>											 
							</form>		
                            </div>

                            <div id="content-footer" class="clearfix">
                                <a href="splash.php?team" class="btn btn-default add-more" id="add_team">add more+</a>
                                <button type="button" class="btn btn-default save" id="team_submit">SAVE</button>
                                <button type="button" class="btn btn-default next">NEXT</button>
                            </div>	
                        </div>   <!-- // Team tab End -->                         
                        
                        
                        <div class="tab-pane" id="client"> <!-- Client tab start -->
                                <div id="content-header" class="clearfix">
                                    <div class="col-sm-6 col-sm-offset-3">
                                        <h3 class="section-title">Client SCREEN </h3>
                                    </div>
                                </div>

                                <div class="data-area">
                                    <form class="form-horizontal" action="">
                                        <div class="repeat">
                                            <table class="wrapper" width="100%">
                                                <thead>
                                                    <tr>
                                                        <th><span style="margin-left: 10px;">Image</span></th>
                                                        <th>Name</th>
                                                        <th>Company</th>
                                                        <th>Tag</th>
                                                        <th></th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody class="form-container" id="display_client">

                                                </tbody>
                                            </table>
                                        </div>											 

                                    </form>	
                                </div>

                                <div id="content-footer" class="clearfix">
                                    <a href="splash.php?client" class="btn btn-default add-more" id="add_client">add more+</a>
                                    <button type="button" class="btn btn-default save" id="client_submit">SAVE</button>
                                    <button type="button" class="btn btn-default next">NEXT</button>
                                </div>	
                            </div><!---//client tab end--->                                                                                                                                                       
                        <div class="tab-pane" id="testimonial"> <!-- testimonial tab start -->
                            <div id="content-header" class="clearfix">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <h3 class="section-title">testimonial SCREEN </h3>
                                </div>
                            </div>

                            <div class="data-area">
                                <form class="form-horizontal" action="">
                                    <div class="repeat">
                                        <table class="wrapper" width="100%">
                                            <thead>
                                                <tr>
                                                    <th><span style="margin-left: 10px;">Image</span></th>
                                                    <th>Name</th>
                                                    <th>Project Name</th>
                                                    <th>Project Url</th>
                                                    <th>Feedback</th>
                                                    <th></th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody class="form-container" id="display_testimonial">

                                            </tbody>
                                        </table>
                                    </div>											 

                                </form>	
                            </div>

                            <div id="content-footer" class="clearfix">
                                <a href="splash.php?testimonial" class="btn btn-default add-more" id="add_testimonial">add more+</a>
                                <button type="button" class="btn btn-default save" id="testimonial_submit">SAVE</button>
                                <button type="button" class="btn btn-default next">NEXT</button>
                            </div>	
                        </div><!---//testimonial tab end--->  
                        
                        <div class="tab-pane" id="methodology"><!-- Methodology tab start -->
                            <div id="content-header" class="clearfix">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <h3 class="section-title">Methodology SCREEN </h3>
                                </div>
                            </div>

                            <div class="data-area">
                                <form class="form-horizontal" action="">

                                    <div class="repeat">
                                        <table class="wrapper" width="100%">
                                            <tbody id="display_methodology_content">

                                            </tbody>
                                        </table>								
                                    </div>

                                    <div class="repeat">
                                        <table class="wrapper" width="100%">
                                            <thead>
                                                <tr>
                                                    <th><span style="margin-left: 10px;">Methodology Icon</span></th>
                                                    <th>Methodology Icon</th>
                                                    <th>Methodology Details</th>
                                                    <th></th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody class="form-container" id="display_methodology_item">

                                            </tbody>
                                        </table>
                                    </div>											 						
                                </form>			
                            </div>

                            <div id="content-footer" class="clearfix">
                                <a href="splash.php?methodology" class="btn btn-default add-more">add more+</a>
                                <button type="button" class="btn btn-default save" id="submit_methodology_content">SAVE</button>
                                <button type="button" class="btn btn-default next">NEXT</button>
                            </div>	
                        </div><!-- //Methodology tab start -->
                        
                        <div class="tab-pane" id="faq"> <!-- Faq tab start -->
                            <div id="content-header" class="clearfix">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <h3 class="section-title">Faq SCREEN </h3>
                                </div>
                            </div>

                            <div class="data-area">
                                <form class="form-horizontal" action="">
                                    <div class="repeat">
                                        <table class="wrapper" width="100%">
                                            <thead>
                                                <tr>
                                                    <th><span style="margin-left: 10px;">Question</span></th>
                                                    <th>Answer</th>
                                                    <th></th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody class="form-container" id="display_faq">

                                            </tbody>
                                        </table>
                                    </div>											 
                                </form>		
                            </div>

                            <div id="content-footer" class="clearfix">
                                <a href="splash.php?faq" class="btn btn-default add-more">add more+</a>
                                <button type="button" class="btn btn-default save" id="submit_faq">SAVE</button>
                                <button type="button" class="btn btn-default next">NEXT</button>
                            </div>	
                        </div><!-- //Faq tab start -->
                                                
                        <div class="tab-pane" id="get-started"> <!-- Get Started tab start -->
                            <div id="content-header" class="clearfix">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <h3 class="section-title">Get STARTED SCREEN </h3>
                                </div>
                            </div>

                            <div class="data-area">
                                <form class="form-horizontal" action="">
                                    <div class="repeat">
                                        <table class="wrapper get-started" width="100%">
                                            <thead>
                                                <tr>
                                                    <th><span style="margin-left: 10px;">Range1</span></th>
                                                    <th>Range2</th>
                                                    <th>Range3</th>
                                                    <th>Range4</th>
                                                    <th>Phone</th>
                                                    <th>Email</th>
                                                    <th>Web</th>
                                                    <th>Platform</th>
                                                    <th></th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody class="form-container" id="display_getstarted">


                                            </tbody>
                                        </table>
                                    </div>											 
                                </form>		
                            </div>

                            <div id="content-footer" class="clearfix">
                                <button type="button" class="btn btn-default save" id="getstarted_submit">SAVE</button>
                                <button type="button" class="btn btn-default next">NEXT</button>
                            </div>
                        </div><!-- //Get Started tab start -->                        
                        
                        
                        <div class="tab-pane" id="social"> <!-- Social tab start -->
                            <div id="content-header" class="clearfix">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <h3 class="section-title">Social SCREEN </h3>
                                </div>
                            </div>

                            <div class="data-area">
                                <form class="form-horizontal" action="">
                                    <div class="repeat">
                                        <table class="wrapper social" width="100%">
                                            <thead>
                                                <tr>
                                                    <th><span style="margin-left: 10px;">Social Image</span></th>
                                                    <th><span style="margin-left: 10px;">Social Title</span></th>
                                                    <th>Lebel Link</th>
                                                    <th></th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody class="form-container" id="display_social">

                                            </tbody>
                                        </table>
                                    </div>											 
                                </form>					
                            </div>

                            <div id="content-footer" class="clearfix">
                                <a href="splash.php?social" class="btn btn-default add-more">add more+</a>
                                <button type="button" class="btn btn-default save" id="submit_social">SAVE</button>
                                <button type="button" class="btn btn-default next">finish</button>
                            </div>
                        </div><!-- //Social tab start -->
                        
					</div> <!--// tab wrapper -->
				</div>
			</div>
		</div>
	</section>
	
<?php include "modals/modals.php"; ?>	

	
<?php include "inc/footer.php"; ?>