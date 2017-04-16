<?php

/**
* This class will handle Database Operation
* Database connection have CRUD Methods
* @author Shafikul Islam
* @company Audacity It Solutiins Ltd.
**/

class DbHandler {
    
    public function __construct(){
        
        require_once '../../lib/php-activerecord/ActiveRecord.php';
        include_once dirname(__FILE__) . '/Config.php';
        ActiveRecord\Config::initialize(function($cfg){
            $cfg->set_model_directory('../../models');
            $cfg->set_connections(array(
                'development' => 'mysql://wwwaudac_check:^&Jv2h@CW)&C@localhost/wwwaudac_check?charset=utf8')
            );
        });
    }

    
    /*
    # method for get all splash content
    */
    public function getSplashContent(){
        $options = array('order' => 'id asc', 'limit' => 1);
        $splash = Splash::find('all', $options);
        
        if(count($splash) > 0){
            return $splash;
        }else{
            return false;
        }
    }
    
    
    /*
    # method for get all home/ slider content
    */
    public function getHomeContent(){
        $home = Slider::find('all');
        
        if( count($home) > 0 ){
            return $home;
        }else{
            return false;
        }
    }
    
    
    /*
    # method for get overview static contents
    */
    public function getOverviewContent(){
        $options = array('order' => 'id asc', 'limit' => 1);
        $overviewContent = Overview::find('all', $options);
        
//        $overviewItems = Companyinfo::find('all');
        
        if( count($overviewContent) > 0 ){
            return $overviewContent;
        }else{
            return false;
        }
    }
    
    
    /*
    # method for get overview repeated item
    */
    public function getOverviewItem(){
        
        $overviewItems = CompanyInfo::find('all');
        
        if( count($overviewItems) > 0 ){
            return $overviewItems;
        }else{
            return false;
        }
    } 
    
    
    
    /*
    # Method for get portfolio content
    */
    public function getPortfolioItem(){
        $portfolios = Portfolio::find('all');
        if($portfolios > 0){
            return $portfolios;
        }else{
            return false;
        }
    }
    
    
    /*
    # Method for get portfolio tag by Specific portfolio id, 
    from "portfolio_tag" table
    */
    public function getPortfolioTagById($portfolio_id){
        $options = array('conditions' => array( 'portfolio_id=?', $portfolio_id ));
        $tags = PortfolioTag::find('all',$options);
        if($tags > 0){
            return $tags;
        }else{
            return false;
        }
    }
    
    
    /*
    # Method for get all team content
    */
    public function getTeamContent(){
        $teams = Team::find('all');
        if($teams > 0){
            return $teams;
        }else{
           return false; 
        }
    }
    
    
    
    
   
    
    /*
    # Method for get client content
    */
    public function getClientItem(){
        $clients = Client::find('all');
        if($clients > 0){
            return $clients;
        }else{
            return false;
        }
    }
    
    
    
    /*
    # Method for get cleint tag by Specific client id, 
    from "client_tag" table
    */
    public function getClientTagById($client_id){
        $options = array('conditions' => array( 'client_id=?', $client_id ));
        $tags = ClientTag::find('all',$options);
        if($tags > 0){
            return $tags;
        }else{
            return false;
        }
    }    

    
    
    /*
    # Method for get Testimonial content
    */
    public function getTestimonialItem(){
        $testimonials = Testimonial::find('all');
        if($testimonials > 0){
            return $testimonials;
        }else{
            return false;
        }
    }    
    
    
    
    
    
    /*
    # method for get Methodology static contents
    */
    public function getMethodologyContent(){
        $options = array('order' => 'id asc', 'limit' => 1);
        $methodologyContent = Methodology::find('all', $options);
        
        if( count($methodologyContent) > 0 ){
            return $methodologyContent;
        }else{
            return false;
        }
    }
    
    
    /*
    # method for get Methodology repeated item
    */
    public function getMethodologyItem(){
        
        $methodologyItems = MethodologyItem::find('all');
        
        if( count($methodologyItems) > 0 ){
            return $methodologyItems;
        }else{
            return false;
        }
    }  
    
    
    /*
    # method for get Faq repeated item
    */
    public function getFaqItems(){
        
        $faqItems = Faq::find('all');
        
        if(count($faqItems) > 0 ){
            return $faqItems;
        }else{
            return false;
        }
    }    
    
    
    
    
    /*
    # method for get GetStarted static contents
    */
    public function getGetstartedContent(){
        $options = array('order' => 'id asc', 'limit' => 1);
        $getStartedContent = GetStarted::find('all', $options);
        
        if( count($getStartedContent) > 0 ){
            return $getStartedContent;
        }else{
            return false;
        }
    }    
    
    
    /*
    # method for get all tag for testimonial
    */
    public function getAllGetStartedTag(){
        $getStartedtags = GetStartedTag::find('all');
        if(count($getStartedtags) > 0){
            return $getStartedtags;
        }else{
            return false;
        }
    }
    
    

    
    /*
    # Method for get Social Item's content
    */
    public function getSocialItem(){
        $socials = Social::find('all');
        if($socials > 0){
            return $socials;
        }else{
            return false;
        }
    }    
    
    
    
    
    
    /*
    # validating Api Key 
    # if requested API KEY match with stored API Key , it is a valid API KEY
    # return boolean data type
    */
    public function isValidApiKey($api_key){
        if( $api_key == API_KEY ){
            return true;
        }else{
            return false;    
        }
    }   
   
    

    
    
    
    
    
}
// end DbHandler class




//$db = new DbHandler();
//echo $db->isValidApiKey("32DFCFD@#&DSxFDSFSDF!L@?hh7@32DF");
//echo API_KEY;
?>