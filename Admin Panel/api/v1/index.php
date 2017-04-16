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
            'development' => 'mysql://root:@localhost/profile?charset=utf8')
        );
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
        if(isset($headers['authorization'])){
            $db =  new DbHandler();
            
            $api_key = $headers['authorization'];
            
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