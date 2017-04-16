<?php 
    require_once "../../inc/functions.php";
    require_once "../inc/DbHandler.php";
    require_once "../../lib/Slim/Slim.php";
    require_once "../../lib/php-activerecord/ActiveRecord.php";

    \Slim\Slim::registerAutoloader();

    // creating slim instance
    $app = new \Slim\Slim();

    // active record database setup 
    ActiveRecord\Config::initialize(function($cfg){
        $cfg->set_model_directory('../../models');
        $cfg->set_connections(array(
            'development' => 'mysql://wwwaudac_check:^&Jv2h@CW)&C@localhost/wwwaudac_check?charset=utf8')
        );
    });







    /**
     # Will Provide all data in a single Request
     * URL: http://localhost/profile/api/v2/all
     * Parameters: none
     * authorization: Put API Key in Request Header
     * Method: GET
     **/
    $app->get('/all', 'authenticate', function() use ($app){
        global $base_url;
        
        $db = new DbHandler();
        
        
        
        

        // Sidebar data
        $response['data']['sidebar'] = array();
        
        $sidebar = Sidebar::first();

        if($sidebar){
            $response['data']['sidebar']['background']  = $base_url.$sidebar->background;
            $response['data']['sidebar']['logo']        = $base_url.$sidebar->logo;
            $response['data']['sidebar']['phone']       = $sidebar->phone;
            $response['data']['sidebar']['email']       = $sidebar->email;
            $response['data']['sidebar']['web']         = $sidebar->web;
            
            $response['data']['sidebar']['error']      = false;
            $response['data']['sidebar']['message']    = "Sidebar content available";


        }else{
            $response['data']['splash']['error']      = true;
            $response['data']['splash']['message']    = "Sidebar content not available";
        }   
        
        
        
        
        
        
        
        
        // splash data
        $response['data']['splash'] = array();
        
        $result = $db->getSplashContent();

        if($result){
            foreach($result as $splash){
                
                $response['data']['splash']["logo"] = $base_url.$splash->logo;
                $response['data']['splash']["bgcolor"] = $splash->bgcolor;
                
//                $temp = array();
//                $temp['logo']       = $base_url.$splash->logo;
//                $temp['bgcolor']    = $splash->bgcolor;
//                array_push($response['data']['splash'],$temp);  
            }
            
            
            $response['data']['splash']['error']      = false;
            $response['data']['splash']['message']    = "Splash content available";


        }else{
            $response['data']['splash']['error']      = true;
            $response['data']['splash']['message']    = "Splash content not available";
        }
        
        
        
        
        // overview data
       $response['data']['overview'] = array();
        
        $overviewContents   = $db->getOverviewContent();
        $overviewItems      = $db->getOverviewItem();
        
        if($overviewContents && $overviewItems ){
            
            $response['data']['overview']['overviewcontent'] = array();
            $response['data']['overview']['overviewitems'] = array();
            
            if( $overviewContents > 0 ){
                foreach($overviewContents as $overviewContent){
                   // $temp = array();
                    $response['data']['overview']['overviewcontent']['title']      = $overviewContent->title;
                    $response['data']['overview']['overviewcontent']['background'] = $base_url.$overviewContent->background;
                    $response['data']['overview']['overviewcontent']['logo'] = $base_url.$overviewContent->logo;
                    $response['data']['overview']['overviewcontent']['moto']       = $overviewContent->moto;
                    $response['data']['overview']['overviewcontent']['count1text'] = $overviewContent->counter1_text;
                    $response['data']['overview']['overviewcontent']['count1no']   = $overviewContent->counter1_number;
                    $response['data']['overview']['overviewcontent']['count2text'] = $overviewContent->counter2_text;
                    $response['data']['overview']['overviewcontent']['count2no']   = $overviewContent->counter2_number;
                    $response['data']['overview']['overviewcontent']['count3text'] = $overviewContent->counter3_text;
                    $response['data']['overview']['overviewcontent']['count3no']   = $overviewContent->counter3_number;
                    //array_push($response['data']['overview']['overviewcontent'],$temp);
                }  
            }

            
            if($overviewItems > 0){
                foreach($overviewItems as $overviewItem){
                    $temp = array();
                    $temp['title']          = $overviewItem->title;
                    $temp['description']    = $overviewItem->description;
                    $temp['icon']           = $base_url.$overviewItem->icon;
                    array_push($response['data']['overview']['overviewitems'],$temp);
                }
            }
            
            $response['data']['overview']['error']      = false;
            $response['data']['overview']['message']    = "Overview content available";
           // echoResponse(200,$response);
            
        }else{
            $response['data']['overview']['error']      = true;
            $response['data']['overview']['message']    = "Overview content not available";
            
        }
        
        

        
        
        
        // home part
        $response['data']['home'] = array();

        $results = $db->getHomeContent();

        $response['data']['home']['items'] = array();
        if($results){
            foreach($results as $result){
                $temp = array();
                $temp['title']      = $result->title;
                $temp['background'] = $base_url.$result->background;
                array_push($response['data']['home']['items'],$temp);  
            }
            $response['data']['home']['error']      = false;
            $response['data']['home']['message']    = "Home content available";

        }else{
            $response['data']['home']['error']      = true;
            $response['data']['home']['message']    = "Home content not available";

        }  
        
        
        
        
        
        /// portfolio data
        
        $response['data']['portfolio'] = array();
        
        $portfolios = $db->getPortfolioItem();
        
        if( $portfolios ){
            $response['data']['portfolio']['items'] = array();
            
            foreach($portfolios as $portfolio){
                $portfolioInfo = array();
                $portfolioInfo['title'] = $portfolio->title;
                $portfolioInfo['image'] = $base_url.$portfolio->image;
                $portfolioInfo['tags']  = array();
                
                
                
                $tags = $db->getPortfolioTagById($portfolio->id);
                if($tags){
                    foreach( $tags as $tag ){
                        $temp = array();
                        $temp['tag'] = $tag->tag;   
                        $temp['url'] = $tag->url;
                        array_push($portfolioInfo['tags'],$temp);
                    }

                }
                
                array_push($response['data']['portfolio']['items'],$portfolioInfo);
            }  

            
            $response['data']['portfolio']['error']      = false;
            $response['data']['portfolio']['message']    = "Portfolio content available";
            
        }else{
            $response['data']['portfolio']['error']      = true;
            $response['data']['portfolio']['message']    = "Portfolio content not available";
        }        
        
        
        
        
        
        // team data
        
        $response['data']['team'] = array();
        
        $teams = $db->getTeamContent();
        
        if( $teams ){
            
            $response['data']['team']['items'] = array();
            foreach($teams as $team){
                $temp = array();
                $temp['name']           = $team->name;
                $temp['designation']    = $team->designation;
                $temp['photo']          = $base_url.$team->photo;

                array_push($response['data']['team']['items'],$temp);
            }  

            
            $response['data']['team']['error']      = false;
            $response['data']['team']['message']    = "Team content available";

            
        }else{
            $response['data']['team']['error']      = true;
            $response['data']['team']['message']    = "Team content not available";

        }        
        
        
        // client data
        
        $response['data']['client'] = array();
        
        $clients = $db->getClientItem();
        
        if( $clients ){
            
            $response['data']['client']['items'] = array();
            
            foreach($clients as $client){
                $clientInfo = array();
                $clientInfo['name'] = $client->name;
                $clientInfo['logo'] = $base_url.$client->logo;
                $clientInfo['company'] = $client->company;
                $clientInfo['country'] = $client->country;
                $clientInfo['tags']  = array();
                
                
                
                $tags = $db->getClientTagById($client->id);
                if($tags){
                    foreach( $tags as $tag ){
                        $temp = array();
                        $temp['tag'] = $tag->tag;   
                        $temp['url'] = $tag->url;
                        array_push($clientInfo['tags'],$temp);
                    }

                }
                
                array_push($response['data']['client']['items'],$clientInfo);
            }  

            
            $response['data']['client']['error']      = false;
            $response['data']['client']['message']    = "Client content available";

            
        }else{
            $response['data']['client']['error']      = true;
            $response['data']['client']['message']    = "Client content not available";

        }        
        
        
        
        //testimonial data
        
        
        $response['data']['testimonial'] = array();

        $results = $db->getTestimonialItem();

        if($results){
            $response['data']['testimonial']['items'] = array();
            
            foreach($results as $result){
                $temp = array();
                $temp['name']     = $result->name;
                $temp['image']    = $base_url.$result->image;
                $temp['project']  = $result->project;
                $temp['url']      = $result->url;
                $temp['feedback'] = $result->feedback;
                array_push($response['data']['testimonial']['items'],$temp);  
            }
            $response['data']['testimonial']['error']      = false;
            $response['data']['testimonial']['message']    = "Testimonial content available";

        }else{
            $response['data']['testimonial']['error']      = true;
            $response['data']['testimonial']['message']    = "Testimonial content not available";

        }
        
        
        
        
        
        
        // methodology data
        
        $response['data']['methodology'] = array();
        
        $methodologyContents   = $db->getMethodologyContent();
        $methodologyItems      = $db->getMethodologyItem();
        
        if($methodologyContents && $methodologyItems ){
            
            $response['data']['methodology']['methodologycontent'] = array();
            $response['data']['methodology']['methodologyitems'] = array();
            
            if( $methodologyContents > 0 ){
                foreach($methodologyContents as $methodologyContent){
                    //$temp = array();
                    $response['data']['methodology']['methodologycontent']['background'] = $base_url.$methodologyContent->bacground;
                    $response['data']['methodology']['methodologycontent']['logo']       = $base_url.$methodologyContent->logo;

                    //array_push($response['data']['methodology']['methodologycontent'],$temp);
                }  
            }

            
            if($methodologyItems > 0){
                foreach($methodologyItems as $methodologyItem){
                    $temp = array();
                    $temp['title']          = $methodologyItem->title;
                    $temp['details']    = $methodologyItem->details;
                    $temp['icon']           = $base_url.$methodologyItem->icon;
                    array_push($response['data']['methodology']['methodologyitems'],$temp);
                }
            }
            
            $response['data']['methodology']['error']      = false;
            $response['data']['methodology']['message']    = "Methodology content available";
            
        }else{
            $response['data']['methodology']['error']      = true;
            $response['data']['methodology']['message']    = "Methodology content not available";

        }
        
        
        
        
        
        
        // faq data data
        
        
        $response['data']['faq'] = array();

        $results = $db->getFaqItems();

        if($results){
            $response['data']['faq']['items'] = array();
            
            foreach($results as $result){
                $temp = array();
                $temp['question']    = $result->question;
                $temp['answer']      = $result->answer;
                array_push($response['data']['faq']['items'],$temp);  
            }
            $response['data']['faq']['error']      = false;
            $response['data']['faq']['message']    = "Faq content available";


        }else{
            $response['data']['faq']['error']      = true;
            $response['data']['faq']['message']    = "Faq content not available";

        }   
        

        
        // getstarted data
        
        $response['data']['getstarted'] = array();

        $results = $db->getGetstartedContent();
        $tags    = $db->getAllGetStartedTag();

        if($results){
            foreach($results as $result){
                
                $response['data']['getstarted']['range1'] = $result->range1;
                $response['data']['getstarted']['range2'] = $result->range2;
                $response['data']['getstarted']['range3'] = $result->range3;
                $response['data']['getstarted']['range4'] = $result->range4;
                $response['data']['getstarted']['phone']  = $result->phone;
                $response['data']['getstarted']['email']  = $result->email;
                $response['data']['getstarted']['web']    = $result->web;
                $response['data']['getstarted']['tags']   = array();
                
                foreach($tags as $tag){
                    $getStartedTag = array();
                    $getStartedTag['tag'] = $tag->platform;
                    array_push($response['data']['getstarted']['tags'],$getStartedTag);
                }
                
                //array_push($response['data']['getstarted'],$temp);  
            }
            $response['data']['getstarted']['error']      = false;
            $response['data']['getstarted']['message']    = "Get Started content available";


        }else{
            $response['data']['getstarted']['error']      = true;
            $response['data']['getstarted']['message']    = "Get Started content not available";

        }
        
        
        
        
        // social data
        
        $response['data']['social'] = array();

        $results = $db->getSocialItem();

        if($results){
            
            $response['data']['social']['items'] = array();
            foreach($results as $result){
                $temp = array();
                $temp['image']  = $base_url.$result->image;
                $temp['url']    = $result->url;
                array_push($response['data']['social']['items'],$temp);  
            }
            $response['data']['social']['error']      = false;
            $response['data']['social']['message']    = "Social content available";

        }else{
            $response['data']['social']['error']      = true;
            $response['data']['social']['message']    = "Social content not available";
        }
        
        
        
        
        // version code API
        $response['data']['version'] = array();
        
        $version = Version::first();
        
        if($version){
            $response['data']['version']['version_code'] = $version->version_code;
            $response['data']['version']['error']      = false;
            $response['data']['version']['message']    = "Version code available";
        }else{
            $response['data']['version']['error']      = true;
            $response['data']['version']['message']    = "Version code not available";
        }
        
        
        
        
        
        $splash = $db->getSplashContent();
        $home = $db->getHomeContent();
        $testimonials = $db->getTestimonialItem();
        $faqs = $db->getFaqItems();
        $get_started = $db->getGetstartedContent();
        $social = $db->getSocialItem();
        
        if(!$splash){
            $response['data']['error']      = true;
            $response['data']['message']    = "Splash content not available";
        }elseif(!$overviewContents && !$overviewItems){
            $response['data']['error']      = true;
            $response['data']['message']    = "Overview content not available";
        }else if(!$home){
            $response['data']['error']      = true;
            $response['data']['message']    = "Home content not available";
        }else if(!$portfolios){
            $response['data']['error']      = true;
            $response['data']['message']    = "Portfolio content not available";
        }else if(!$teams){
            $response['data']['error']      = true;
            $response['data']['message']    = "Team content not available";
        }else if(!$clients){
            $response['data']['error']      = true;
            $response['data']['message']    = "Client content not available";
        }else if(!$testimonials){
            $response['data']['error']      = true;
            $response['data']['message']    = "Testimonial content not available";
        }else if(!$methodologyContents && !$methodologyItems){
            $response['data']['error']      = true;
            $response['data']['message']    = "Methodology content not available";
        }else if(!$faqs){
            $response['data']['error']      = true;
            $response['data']['message']    = "Faq content not available";
        }else if(!$get_started){
            $response['data']['error']      = true;
            $response['data']['message']    = "Get Started content not available";
        }else if(!$social){
            $response['data']['error']      = true;
            $response['data']['message']    = "Social content not available";
        }else if(!$sidebar){
            $response['data']['error']      = true;
            $response['data']['message']    = "Sidebar content not available";
        }else{
            $response['data']['error']      = false;
            $response['data']['message']    = "Data content available"; 
        }
        

        
        
        echoResponse(200,$response);
    });



    /**
     * URL: http://localhost/profile/api/v1/version
     * Parameters: none
     * authorization: Put API Key in Request Header
     * Method: GET
     **/
    $app->get('/version', 'authenticate', function() use ($app){

        $response = array();
        
        $version = Version::first();
        
        if($version){
            $response['version_code'] = $version->version_code;
            $response['error']      = false;
            $response['message']    = "Version code available";

            echoResponse(200,$response);
        }else{
            $response['error']      = true;
            $response['message']    = "Version code not available";
            echoResponse(200,$response);
        }
        

    });





    /**
     * URL: http://localhost/profile/api/v1/splash
     * Parameters: none
     * authorization: Put API Key in Request Header
     * Method: GET
     **/
    $app->get('/splash', 'authenticate', function() use ($app){
        global $base_url;
        
        $db = new DbHandler();
        $response['splash'] = array();
        
        $result = $db->getSplashContent();

        if($result){
            foreach($result as $splash){
                $temp = array();
                $temp['logo']       = $base_url.$splash->logo;
                $temp['bgcolor']    = $splash->bgcolor;
                array_push($response['splash'],$temp);  
            }
            $response['error']      = false;
            $response['message']    = "Splash content available";

            echoResponse(200,$response);

        }else{
            $response['error']      = true;
            $response['message']    = "Splash content not available";
            echoResponse(200,$response);
        }

    });




    /**
     * URL: http://localhost/profile/api/v1/overview
     * Parameters: none
     * authorization: Put API Key in Request Header
     * Method: GET
     **/
    $app->get('/overview', 'authenticate', function() use ($app){
        global $base_url;
        $db = new DbHandler();
        $response['overview'] = array();
        
        $overviewContents   = $db->getOverviewContent();
        $overviewItems      = $db->getOverviewItem();
        
        if($overviewContents || $overviewItems ){
            
            $response['overview']['overviewcontent'] = array();
            $response['overview']['overviewitems'] = array();
            
            if( $overviewContents > 0 ){
                foreach($overviewContents as $overviewContent){
                    $temp = array();
                    $temp['title']      = $overviewContent->title;
                    $temp['background'] = $base_url.$overviewContent->background;
                    $temp['moto']       = $overviewContent->moto;
                    $temp['count1text'] = $overviewContent->counter1_text;
                    $temp['count1no']   = $overviewContent->counter1_number;
                    $temp['count2text'] = $overviewContent->counter2_text;
                    $temp['count2no']   = $overviewContent->counter2_number;
                    $temp['count3text'] = $overviewContent->counter3_text;
                    $temp['count3no']   = $overviewContent->counter3_number;
                    array_push($response['overview']['overviewcontent'],$temp);
                }  
            }

            
            if($overviewItems > 0){
                foreach($overviewItems as $overviewItem){
                    $temp = array();
                    $temp['title']          = $overviewItem->title;
                    $temp['description']    = $overviewItem->description;
                    $temp['icon']           = $base_url.$overviewItem->icon;
                    array_push($response['overview']['overviewitems'],$temp);
                }
            }
            
            $response['error']      = false;
            $response['message']    = "Overview content available";
            echoResponse(200,$response);
            
        }else{
            $response['error']      = true;
            $response['message']    = "Overview content not available";
            echoResponse(200,$response);
        }


    });






    /**
     * URL: http://localhost/profile/api/v1/home
     * Parameters: none
     * authorization: Put API Key in Request Header
     * Method: GET
     **/
    $app->get('/home', 'authenticate', function() use ($app){
        global $base_url;
        $db = new DbHandler();
        $response['home'] = array();

        $results = $db->getHomeContent();

        if($results){
            foreach($results as $result){
                $temp = array();
                $temp['title']      = $result->title;
                $temp['background'] = $base_url.$result->background;
                array_push($response['home'],$temp);  
            }
            $response['error']      = false;
            $response['message']    = "Home content available";

            echoResponse(200,$response);

        }else{
            $response['error']      = true;
            $response['message']    = "Home content not available";
            echoResponse(200,$response);
        }

    });



    /**
     * URL: http://localhost/profile/api/v1/portfolio
     * Parameters: none
     * authorization: Put API Key in Request Header
     * Method: GET
     **/
    $app->get('/portfolio', 'authenticate', function() use ($app){
        global $base_url;
        $db = new DbHandler();
        $response['portfolio'] = array();
        
        $portfolios = $db->getPortfolioItem();
        
        if( $portfolios ){
            foreach($portfolios as $portfolio){
                $portfolioInfo = array();
                $portfolioInfo['title'] = $portfolio->title;
                $portfolioInfo['image'] = $base_url.$portfolio->image;
                $portfolioInfo['tags']  = array();
                
                
                
                $tags = $db->getPortfolioTagById($portfolio->id);
                if($tags){
                    foreach( $tags as $tag ){
                        $temp = array();
                        $temp['tag'] = $tag->tag;   
                        $temp['url'] = $tag->url;
                        array_push($portfolioInfo['tags'],$temp);
                    }

                }
                
                array_push($response['portfolio'],$portfolioInfo);
            }  

            
            $response['error']      = false;
            $response['message']    = "Portfolio content available";
            echoResponse(200,$response);
            
        }else{
            $response['error']      = true;
            $response['message']    = "Portfolio content not available";
            echoResponse(200,$response);
        }


    });






    /**
     * URL: http://localhost/profile/api/v1/team
     * Parameters: none
     * authorization: Put API Key in Request Header
     * Method: GET
     **/
    $app->get('/team', 'authenticate', function() use ($app){
        global $base_url;
        $db = new DbHandler();
        $response['team'] = array();
        
        $teams = $db->getTeamContent();
        
        if( $teams ){
            foreach($teams as $team){
                $temp = array();
                $temp['name']           = $team->name;
                $temp['designation']    = $team->designation;
                $temp['photo']          = $base_url.$team->photo;

                array_push($response['team'],$temp);
            }  

            
            $response['error']      = false;
            $response['message']    = "Team content available";
            echoResponse(200,$response);
            
        }else{
            $response['error']      = true;
            $response['message']    = "Team content not available";
            echoResponse(200,$response);
        }


    });





    /**
     * URL: http://localhost/profile/api/v1/client
     * Parameters: none
     * authorization: Put API Key in Request Header
     * Method: GET
     **/
    $app->get('/client', 'authenticate', function() use ($app){
        global $base_url;
        $db = new DbHandler();
        $response['client'] = array();
        
        $clients = $db->getClientItem();
        
        if( $clients ){
            foreach($clients as $client){
                $clientInfo = array();
                $clientInfo['name'] = $client->name;
                $clientInfo['logo'] = $base_url.$client->logo;
                $clientInfo['company'] = $client->company;
                $clientInfo['country'] = $client->country;
                $clientInfo['tags']  = array();
                
                
                
                $tags = $db->getClientTagById($client->id);
                if($tags){
                    foreach( $tags as $tag ){
                        $temp = array();
                        $temp['tag'] = $tag->tag;   
                        $temp['url'] = $tag->url;
                        array_push($clientInfo['tags'],$temp);
                    }

                }
                
                array_push($response['client'],$clientInfo);
            }  

            
            $response['error']      = false;
            $response['message']    = "Client content available";
            echoResponse(200,$response);
            
        }else{
            $response['error']      = true;
            $response['message']    = "Client content not available";
            echoResponse(200,$response);
        }


    });







    /**
     * URL: http://localhost/profile/api/v1/testimonial
     * Parameters: none
     * authorization: Put API Key in Request Header
     * Method: GET
     **/
    $app->get('/testimonial', 'authenticate', function() use ($app){
        global $base_url;
        $db = new DbHandler();
        $response['testimonial'] = array();

        $results = $db->getTestimonialItem();

        if($results){
            foreach($results as $result){
                $temp = array();
                $temp['name']     = $result->name;
                $temp['image']    = $base_url.$result->image;
                $temp['project']  = $result->project;
                $temp['url']      = $result->url;
                $temp['feedback'] = $result->feedback;
                array_push($response['testimonial'],$temp);  
            }
            $response['error']      = false;
            $response['message']    = "Testimonial content available";

            echoResponse(200,$response);

        }else{
            $response['error']      = true;
            $response['message']    = "Testimonial content not available";
            echoResponse(200,$response);
        }

    });




    /**
     * URL: http://localhost/profile/api/v1/methodology
     * Parameters: none
     * authorization: Put API Key in Request Header
     * Method: GET
     **/
    $app->get('/methodology', 'authenticate', function() use ($app){
        global $base_url;
        $db = new DbHandler();
        $response['methodology'] = array();
        
        $methodologyContents   = $db->getMethodologyContent();
        $methodologyItems      = $db->getMethodologyItem();
        
        if($methodologyContents || $methodologyItems ){
            
            $response['methodology']['methodologycontent'] = array();
            $response['methodology']['methodologyitems'] = array();
            
            if( $methodologyContents > 0 ){
                foreach($methodologyContents as $methodologyContent){
                    $temp = array();
                    $temp['background'] = $base_url.$methodologyContent->bacground;
                    $temp['logo']       = $base_url.$methodologyContent->logo;

                    array_push($response['methodology']['methodologycontent'],$temp);
                }  
            }

            
            if($methodologyItems > 0){
                foreach($methodologyItems as $methodologyItem){
                    $temp = array();
                    $temp['title']          = $methodologyItem->title;
                    $temp['details']    = $methodologyItem->details;
                    $temp['icon']           = $base_url.$methodologyItem->icon;
                    array_push($response['methodology']['methodologyitems'],$temp);
                }
            }
            
            $response['error']      = false;
            $response['message']    = "Methodology content available";
            echoResponse(200,$response);
            
        }else{
            $response['error']      = true;
            $response['message']    = "Methodology content not available";
            echoResponse(200,$response);
        }


    });







    /**
     * URL: http://localhost/profile/api/v1/faq
     * Parameters: none
     * authorization: Put API Key in Request Header
     * Method: GET
     **/
    $app->get('/faq', 'authenticate', function() use ($app){
        global $base_url;
        $db = new DbHandler();
        $response['faq'] = array();

        $results = $db->getFaqItems();

        if($results){
            foreach($results as $result){
                $temp = array();
                $temp['question']    = $result->question;
                $temp['answer']      = $result->answer;
                array_push($response['faq'],$temp);  
            }
            $response['error']      = false;
            $response['message']    = "Faq content available";

            echoResponse(200,$response);

        }else{
            $response['error']      = true;
            $response['message']    = "Faq content not available";
            echoResponse(200,$response);
        }

    });






    /**
     * URL: http://localhost/profile/api/v1/getstarted
     * Parameters: none
     * authorization: Put API Key in Request Header
     * Method: GET
     **/
    $app->get('/getstarted', 'authenticate', function() use ($app){
        $db = new DbHandler();
        $response['getstarted'] = array();

        $results = $db->getGetstartedContent();
        $tags    = $db->getAllGetStartedTag();

        if($results){
            foreach($results as $result){
                $temp = array();
                $temp['range1'] = $result->range1;
                $temp['range2'] = $result->range2;
                $temp['range3'] = $result->range3;
                $temp['range4'] = $result->range4;
                $temp['phone']  = $result->phone;
                $temp['email']  = $result->email;
                $temp['web']    = $result->web;
                $temp['tags']   = array();
                
                foreach($tags as $tag){
                    $getStartedTag = array();
                    $getStartedTag['tag'] = $tag->platform;
                    array_push($temp['tags'],$getStartedTag);
                }
                
                array_push($response['getstarted'],$temp);  
            }
            $response['error']      = false;
            $response['message']    = "Get Started content available";
            echoResponse(200,$response);

        }else{
            $response['error']      = true;
            $response['message']    = "Get Started content not available";
            echoResponse(200,$response);
        }

    });




    /**
     * URL: http://localhost/profile/api/v1/social
     * Parameters: none
     * authorization: Put API Key in Request Header
     * Method: GET
     **/
    $app->get('/social', 'authenticate', function() use ($app){
        global $base_url;
        $db = new DbHandler();
        $response['social'] = array();

        $results = $db->getSocialItem();

        if($results){
            foreach($results as $result){
                $temp = array();
                $temp['name']   = $result->name;
                $temp['image']  = $base_url.$result->image;
                $temp['url']    = $result->url;
                array_push($response['social'],$temp);  
            }
            $response['error']      = false;
            $response['message']    = "Social content available";

            echoResponse(200,$response);

        }else{
            $response['error']      = true;
            $response['message']    = "Social content not available";
            echoResponse(200,$response);
        }

    });










    /*
    # Adding middle layer to authenticate every request 
    # Checking every  request has valid API KEY or not
    */
    function authenticate(\Slim\Route $route){
        // getting request header
        $headers = apache_request_headers();
        $response = array();
        $app = \Slim\Slim::getInstance();
        
        
        // varifying authorization header
        if(isset($headers['Authorization'])){
            $db =  new DbHandler();
            
            $api_key = $headers['Authorization'];
            
            // validate api key
            if(!$db->isValidApiKey($api_key)){
                // api key not valid
                $response["error"] = true;
                $response["message"] = "Api key is not valid";
                echoResponse(401, $response);
                $app->stop(); 
            }else{
               // something else 
            }
            
        }else{
            // api key is missing in header
            $response["error"] = true;
            $response["message"] = "Api key is misssing";
            echoResponse(400, $response);
            $app->stop();
        }
        
    }



    /**
    # Echo json reponse to client
    # @param int $status_code Http response code
    # @param String $response json response
    **/
    function echoResponse($status_code,$response){
        $app = \Slim\Slim::getInstance();
        
        // http request header
        $app->status($status_code);
        
        // setting content type to JSON
        $app->contentType('application/json');
        
        echo json_encode($response);
    }






// run Slim App
$app->run();


?>