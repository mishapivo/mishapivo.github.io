<?php
    include 'db_connect.php';
    include '../inc/functions.php';

    if(isset($_POST['submit'])){
        $portfolio_title = $_POST['portfolio_title'];
        
        

        $number =  count($_POST['project_platform']);



        
        $allowed_file = array("jpeg","jpg","png","gif");
        
        $name = $_FILES['portfolio_image']['name'];
        $tmp_name = $_FILES['portfolio_image']['tmp_name'];
        $size = $_FILES['portfolio_image']['size'];
        
        
        
        $extension = strtolower(end(explode(".",$name)));
        
        $file_name = "/assets/portfolio/".md5(rand()).".".$extension;
        
        if(!in_array($extension,$allowed_file)){
            echo "<span class='error'>Please Upload Valid Extension</span>";
        }else if($size > 30000000){
            echo "<span class='error'>Please Upload Small Image</span>";
        }else{
            $upload = move_uploaded_file($tmp_name,"..".$file_name);
            if($upload){
                $attributes = array(
                    "image"      => $file_name,
                    "title"   => $portfolio_title
                );
                
                $portfolio = Portfolio::create($attributes);
                
                if($portfolio){
                    echo "Portfolio Added Successfully";
                    
                    // version code method
                    Version::versionCode();
                    
                    $portfolio_id = $portfolio->id;
                    // portfolio tag 
                    if($number > 0){
                        for($i = 0; $i<$number; $i++){
                           $platform    = $_POST['project_platform'][$i];
                           $url         = $_POST['project_url'][$i];
                           if(!empty($platform) ){  //&& !empty($url)
                               $attributes = array(
                                   "portfolio_id"   => $portfolio_id,
                                   "tag"            => $platform,
                                   "url"            => $url
                               );

                              PortfolioTag::create($attributes);


                           }else {

                           }

                        }
                    }else{

                    } 
                    
                    
                    
                }
            }
        }
        
    }





    if(isset($_POST['id'])){
        $delete_id = $_POST['id'];
        $portfolio = Portfolio::find($delete_id);
        $portfolio->delete();
        unlink("..".$portfolio->image);
        
        $tags = PortfolioTag::find('all',array('conditions' => array('portfolio_id=?',$delete_id)));
        foreach($tags as $tag){
            $tag->delete();
        }
        
        // version code method
        Version::versionCode();
        
    }


    if(isset($_GET["edit"])){ 
        $portfolio_id =  $_POST['portfolio_id'];
        $portfolio = Portfolio::find($portfolio_id);

        $portfolio_info['portfolio_data']= array();

        $portfolio_info['tags']= array();



        $portfolio_info['portfolio_data']['title'] = $portfolio->title;
        $portfolio_info['portfolio_data']['image'] = $portfolio->image;
        $portfolio_info['portfolio_data']['id'] = $portfolio->id;

        $tags = PortfolioTag::find('all',array('conditions' => array('portfolio_id=?',$portfolio_id)));

        $tag_number = count($tags);



        foreach($tags as $single_tag){
            $tag = array();
            $tag['name']= $single_tag->tag;
            $tag['url']= $single_tag->url;
            $tag['id']= $single_tag->id;
            array_push($portfolio_info['tags'], $tag);
        }

        echo json_encode($portfolio_info);

    }






    if(isset($_GET['update'])){
        $portfolio_title = $_POST['portfolio_title'];

        
        if(isset($_POST['project_platform'])){
            $number =  count($_POST['project_platform']);
        }
        
        
        $portfolio_id = $_POST['portfolio_id'];
        
        $portfolio = Portfolio::find($portfolio_id);
        
        
        $allowed_file = array("jpeg","jpg","png","gif");
        
        $name = $_FILES['portfolio_image']['name'];
        $tmp_name = $_FILES['portfolio_image']['tmp_name'];
        $size = $_FILES['portfolio_image']['size'];
        
        if(!empty($name)){

            $extension = strtolower(end(explode(".",$name)));

            $file_name = "/assets/portfolio/".md5(rand()).".".$extension;

            if(!in_array($extension,$allowed_file)){
                echo "Please Upload Valid Extension";
                $upload = false;
            }else if($size > 30000000){
                echo "Please Upload Small Image";
                $upload = false;
            }else{
                $upload = move_uploaded_file($tmp_name,"..".$file_name);
                if($upload){
                    $upload = true;
                } 
            }
            
        }else{
            $file_name = $portfolio->image;
            $upload = true;
        }
        
        
        if($upload !== false){
            
            $attributes = array(
            "image"    => $file_name,
            "title"         => $portfolio_title
            );

            $update_portfolio = $portfolio->update_attributes($attributes);  
            
            
            
            $tags = PortfolioTag::find('all',array('conditions' => array('portfolio_id=?',$portfolio_id)));
            foreach($tags as $tag){
                $tag->delete();
            }
            
            
            // Portfolio tag 
            if(isset($number) && $number > 0){
                for($i = 0; $i<$number; $i++){
                   $platform    = $_POST['project_platform'][$i];
                   $url         = $_POST['project_url'][$i];
                   if(!empty($platform) && !empty($url)){
                       $attributes = array(
                           "portfolio_id"   => $portfolio_id,
                           "tag"            => $platform,
                           "url"            => $url
                       );

                      PortfolioTag::create($attributes);


                   }else {

                   }

                }
            }else{

            } 
            
            
            if($update_portfolio){
                echo "success";
                
                // version code method
                Version::versionCode();
            }
        }
        
    }





?>



<?php
if(isset($_GET['show'])){
    $portfolios = Portfolio::find('all');
    foreach($portfolios as $portfolio){
?>
<tr class="form-row" id="tr<?php echo $portfolio->id; ?>">
    <td><img src="<?php echo $base_url.$portfolio->image; ?>" alt="" /></td>
    <td><p class="title"><?php echo $portfolio->title; ?></p></td>
    <td>
        <?php 
            $posrtfolio_id = $portfolio->id;
            $tags = PortfolioTag::find('all',array('conditions' => array('portfolio_id=?',$posrtfolio_id)));
            foreach($tags as $tag){
        ?>
        <a href="<?php echo $tag->url; ?>" class="tag" target="blank"><?php echo $tag->tag; ?></a>
        <?php } ?>
    </td>
    <td>
        <a href="javascript:void(0);" onclick="editPortfolio(<?php echo $portfolio->id; ?>);" class="edit">Edit</a>
    </td>
    <td><a href="javascript:void(0);" onclick="portfolioDelete(<?php echo $portfolio->id; ?>);" class="delete">Delete</a></td>
</tr>
<?php } } ?>