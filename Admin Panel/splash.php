<?php include "inc/header.php"; ?>
	
	<section id="main" class="main">
		<div class="container-fluid">
			<div class="row">
				<?php require_once 'inc/splash_sidebar.php'; ?>
				<div id="content" class="content col-md-10 col-sm-10">
					<div class="space"></div>
					<div class="content-wrapper tab-content">
                   
                   
					   <div class="tab-pane active" id="start"><!--- tab splash  --->
                            <div id="content-header" class="clearfix" style="opacity:0;">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <div class="col-sm-2">

                                    </div>

                                    <h3 class="section-title col-sm-8">SPLASH SCREEN </h3>

                                    <div class="col-sm-2">
                                        
                                    </div>
                                </div>
                            </div>

                            <div class="data-area" style="border: 0px; display: table; width: 100%;">
							<div class="start-build">
								<img src="images/phone.png" alt="" />
								<h3>Start building your app</h3>
								<p>you can go forward sequencly or skip them</p>
<!--								<button class="btn btn-default">start</button>-->
								<li class=""><a href="javascript:void(0)" id="gosplash" class="btn btn-default">Start</a></li>
							</div>		
                            </div>

                            <div id="content-footer" class="clearfix" style="opacity:0;">
                                <button type="button" class="btn btn-default save" id="">SAVE</button>
                            </div>
						</div>  <!--// tab splash end-->
                   
                   
                   
                   
						<div class="tab-pane" id="sidebartab"> <!-- Sidebar tab start -->
                            <div id="content-header" class="clearfix">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <div class="col-sm-2">
                                    </div>

                                    <h3 class="section-title col-sm-8">SIDEBAR CONTENT </h3>

                                    <div class="col-sm-2">
                                    </div>
                                </div>
                            </div>

                            <div class="data-area">
                                <?php include "forms/sidebar_content.php"; ?>		
                            </div>

                            <div id="content-footer" class="clearfix">
                                <button type="button" class="btn btn-default save" id="sidebar_submit">SAVE</button>
                                <button type="button" class="btn btn-default next">NEXT</button>
                            </div>
                        </div><!--// Sidebar tab end-->
                   
                   
                   
                   
                   
					   <div class="tab-pane active1" id="splash"><!--- tab splash  --->
                            <div id="content-header" class="clearfix">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <div class="col-sm-2">

                                    </div>

                                    <h3 class="section-title col-sm-8">SPLASH SCREEN </h3>

                                    <div class="col-sm-2">
                                    </div>
                                </div>
                            </div>

                            <div class="data-area">
                                <?php include "forms/splash.php"; ?>		
                            </div>

                            <div id="content-footer" class="clearfix">
                                <button type="button" class="btn btn-default save" id="splash_submit">SAVE</button>
                                <button type="button" class="btn btn-default next">NEXT</button>
                            </div>
						</div>  <!--// tab splash end-->	
						
						<div class="tab-pane" id="home"> <!-- Home tab start -->
                            <div id="content-header" class="clearfix">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <div class="col-sm-2">
                                    </div>

                                    <h3 class="section-title col-sm-8">HOME SCREEN </h3>

                                    <div class="col-sm-2">
                                    </div>
                                </div>
                            </div>

                            <div class="data-area">
                                <?php include "forms/home.php"; ?>		
                            </div>

                            <div id="content-footer" class="clearfix">
                                <button type="button" class="btn btn-default add-more" id="add_more_slider">add more+</button>
                                <button type="button" id="homeform" class="btn btn-default save">SAVE</button>
                                <button type="button" class="btn btn-default next">NEXT</button>

                            </div>	
                        </div> <!--// home tab end-->
                                                                                                                   
                                                                                                                    
						<div class="tab-pane" id="overview"> <!-- Overview tab start -->
                            <div id="content-header" class="clearfix">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <div class="col-sm-2">
                                    </div>

                                    <h3 class="section-title col-sm-8">OVERVIEW STATIC CONTENT </h3>

                                    <div class="col-sm-2">
                                    </div>
                                </div>
                            </div>

                            <div class="data-area">
                                <?php include "forms/overview_content.php"; ?>		
                            </div>

                            <div id="content-footer" class="clearfix">
                                <button type="button" class="btn btn-default save" id="overview_submit">SAVE</button>
                                <button type="button" class="btn btn-default next">NEXT</button>
                            </div>
                        </div><!--// Overview tab end-->
                                                                                                                   
                                                                                                                   
						<div class="tab-pane" id="overview_company_info"> <!-- Overview company info  start -->
                            <div id="content-header" class="clearfix">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <div class="col-sm-2">
                                    </div>

                                    <h3 class="section-title col-sm-8">OVERVIEW SCREEN-Company Info </h3>

                                    <div class="col-sm-2">
                                    </div>
                                </div>
                            </div>

                            <div class="data-area">
                                <?php include "forms/overview_item.php"; ?>		
                            </div>

                            <div id="content-footer" class="clearfix">
                                <button type="button" class="btn btn-default add-more" id="add_comp_info">add more+</button>
                                <button type="button" class="btn btn-default save" id="company_submit">SAVE</button>
                                <button type="button" class="btn btn-default next">NEXT</button>
                            </div>
                        </div><!--// Overview company info end-->
                                                                                                                    
                                                                                                                    
						<div class="tab-pane" id="portfolio"> <!-- Portfolio tab start -->
                            <div id="content-header" class="clearfix">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <div class="col-sm-2">
                                    </div>

                                    <h3 class="section-title col-sm-8">portfolio SCREEN </h3>

                                    <div class="col-sm-2">
                                    </div>
                                </div>
                            </div>

                            <div class="data-area">
                                <?php include "forms/portfolio.php"; ?>		
                            </div>

                            <div id="content-footer" class="clearfix">
                                <button type="button" class="btn btn-default add-more" id="add_portfolio">add more+</button>
                                <button type="button" class="btn btn-default save" id="portfolio_submit">SAVE</button>
                                <button type="button" class="btn btn-default next">NEXT</button>
                            </div>
                        </div><!--// Portfolio tab end--> 
                                                                                                                    
                        <div class="tab-pane" id="team"> <!-- Team tab start -->
                            <div id="content-header" class="clearfix">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <div class="col-sm-2">
                                    </div>

                                    <h3 class="section-title col-sm-8">Team SCREEN </h3>

                                    <div class="col-sm-2">
                                    </div>
                                </div>
                            </div>

                            <div class="data-area">
                                <?php include "forms/team.php"; ?>		
                            </div>

                            <div id="content-footer" class="clearfix">
                                <button type="button" class="btn btn-default add-more" id="add_team">add more+</button>
                                <button type="button" class="btn btn-default save" id="team_submit">SAVE</button>
                                <button type="button" class="btn btn-default next">NEXT</button>
                            </div>	
                        </div>   <!-- // Team tab End -->                         
                        
                        
                        <div class="tab-pane" id="client"> <!-- Client tab start -->
                                <div id="content-header" class="clearfix">
                                    <div class="col-sm-6 col-sm-offset-3">
                                        <div class="col-sm-2">
                                        </div>

                                        <h3 class="section-title col-sm-8">Client SCREEN </h3>

                                        <div class="col-sm-2">
                                        </div>
                                    </div>
                                </div>

                                <div class="data-area">
                                    <?php include "forms/client.php"; ?>		
                                </div>

                                <div id="content-footer" class="clearfix">
                                    <button type="button" class="btn btn-default add-more" id="add_client">add more+</button>
                                    <button type="button" class="btn btn-default save" id="client_submit">SAVE</button>
                                    <button type="button" class="btn btn-default next">NEXT</button>
                                </div>	
                            </div><!---//client tab end--->                                                                                                                                                       
                            <div class="tab-pane" id="testimonial"> <!-- testimonial tab start -->
                            <div id="content-header" class="clearfix">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <div class="col-sm-2">
                                    </div>

                                    <h3 class="section-title col-sm-8">testimonial SCREEN </h3>

                                    <div class="col-sm-2">
                                    </div>
                                </div>
                            </div>

                            <div class="data-area">
                                <?php include "forms/testimonial.php"; ?>		
                            </div>

                            <div id="content-footer" class="clearfix">
                                <button type="button" class="btn btn-default add-more" id="add_testimonial">add more+</button>
                                <button type="button" class="btn btn-default save" id="testimonial_submit">SAVE</button>
                                <button type="button" class="btn btn-default next">NEXT</button>
                            </div>	
                        </div><!---//testimonial tab end--->  
                        
                        <div class="tab-pane" id="methodology"><!-- Methodology tab start -->
                            <div id="content-header" class="clearfix">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <div class="col-sm-2">
                                    </div>

                                    <h3 class="section-title col-sm-8">methodology SCREEN </h3>

                                    <div class="col-sm-2">
                                    </div>
                                </div>
                            </div>

                            <div class="data-area">
                                <?php include "forms/methodology_content.php"; ?>			
                            </div>

                            <div id="content-footer" class="clearfix">
                                <button type="button" class="btn btn-default save" id="submit_methodology_content">SAVE</button>
                                <button type="button" class="btn btn-default next">NEXT</button>
                            </div>	
                        </div><!-- //Methodology tab start -->
                        
                        
                        <div class="tab-pane" id="methodology_item"><!-- Methodology Item tab start -->
                            <div id="content-header" class="clearfix">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <div class="col-sm-2">
                                    </div>

                                    <h3 class="section-title col-sm-8">methodology Item </h3>

                                    <div class="col-sm-2">
                                    </div>
                                </div>
                            </div>

                            <div class="data-area">
                                <?php include "forms/methodology_item.php"; ?>			
                            </div>

                            <div id="content-footer" class="clearfix">
                                <button type="button" class="btn btn-default add-more" id="add_methodology_item">add more+</button>
                                <button type="button" class="btn btn-default save" id="submit_methodology_item" >SAVE</button>
                                <button type="button" class="btn btn-default next">NEXT</button>
                            </div>	
                        </div><!-- //Methodology Item tab start -->
                        
                        
                        <div class="tab-pane" id="faq"> <!-- Faq tab start -->
                            <div id="content-header" class="clearfix">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <div class="col-sm-2">
                                    </div>

                                    <h3 class="section-title col-sm-8">faq SCREEN </h3>

                                    <div class="col-sm-2">
                                    </div>
                                </div>
                            </div>

                            <div class="data-area">
                                <?php include "forms/faq.php"; ?>			
                            </div>

                            <div id="content-footer" class="clearfix">
                                <button type="button" class="btn btn-default save" id="submit_faq">SAVE</button>
                                <button type="button" class="btn btn-default next">NEXT</button>
                            </div>	
                        </div><!-- //Faq tab start -->
                                                
                        <div class="tab-pane" id="get-started"> <!-- Get Started tab start -->
                            <div id="content-header" class="clearfix">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <div class="col-sm-2">
                                    </div>

                                    <h3 class="section-title col-sm-8">Get Started SCREEN </h3>

                                    <div class="col-sm-2">
                                    </div>
                                </div>
                            </div>

                            <div class="data-area">
                                <?php include "forms/get_started.php"; ?>	
                            </div>

                            <div id="content-footer" class="clearfix">
                                <button type="button" class="btn btn-default save" id="getstarted_submit">SAVE</button>
                                <button type="button" class="btn btn-default next">NEXT</button>
                            </div>
                        </div><!-- //Get Started tab start -->                        
                        
                        
                        <div class="tab-pane" id="social"> <!-- Social tab start -->
                            <div id="content-header" class="clearfix">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <div class="col-sm-2">
                                    </div>

                                    <h3 class="section-title col-sm-8">Social SCREEN </h3>

                                    <div class="col-sm-2">
                                    </div>
                                </div>
                            </div>

                            <div class="data-area">
                                <?php include "forms/social.php"; ?>				
                            </div>

                            <div id="content-footer" class="clearfix">
                                <a type="button" class="btn btn-default add-more" id="add_social">add more+</a>
                                <button type="button" class="btn btn-default save" id="submit_social">SAVE</button>
                                <a href="display.php?status" class="btn btn-default next">finish</a>
                            </div>
                        </div><!-- //Social tab start -->
                        
					</div> <!--// tab wrapper -->
				</div>
			</div>
		</div>
	</section>
	
<?php require_once "modals/errorform.php"; ?>
	
<?php include "inc/footer.php"; ?>
