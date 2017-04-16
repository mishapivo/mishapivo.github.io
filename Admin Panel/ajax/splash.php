<?php
    include 'db_connect.php';
    include '../inc/functions.php';
    if(isset($_POST['submit'])){
        $splash_count = Splash::find('all');

        if( count($splash_count) > 0 ){
            echo "exist";
        }else{

            $bgcolor = $_POST['splash_color'];

            $allowed_file = array("jpeg","jpg","png","gif");

            $name       = $_FILES['company_logo']['name'];
            $tmp_name   = $_FILES['company_logo']['tmp_name'];
            $size       = $_FILES['company_logo']['size'];


            $extension = strtolower(end(explode(".",$name)));

            $file_name = "/assets/images/".md5(rand()).".".$extension;

            if(!in_array($extension,$allowed_file)){
                echo "<span class='error'>Please Upload Valid Extension</span>";
            }else if($size > 30000000){
                echo "<span class='error'>Please Upload Small Image</span>";
            }else{
                $upload = move_uploaded_file($tmp_name,"..".$file_name);
                if($upload){
                    $attributes = array(
                        "logo"      => $file_name,
                        "bgcolor"   => $bgcolor
                    );

                    $splash = Splash::create($attributes);

                    if($splash){
                        echo "Splash Content Added Successfully";
                        
                        // version code method
                        Version::versionCode();
                    }
                }
            }


        }
        

        
    }



   // Delete Splash Content
    if(isset($_POST['id'])){
        $delete_id = $_POST['id'];
        $client = Splash::find($delete_id);
        $client->delete();   
        
        // version code method
        Version::versionCode();
    }


    if(isset($_GET["edit"])){ 
        $splash_id =  $_POST['splash_id'];
        $splash = Splash::find($splash_id);

        $splashData= array();



        $splashData['id'] = $splash->id;
        $splashData['logo'] = $splash->logo;
        $splashData['bgcolor'] = $splash->bgcolor;



        echo json_encode($splashData);

    }





    if(isset($_POST['update'])){
        $splashId = $_POST['splash_id'];
        
        $splashContent = Splash::find($splashId);
        
        
        $bgcolor = $_POST['splash_color'];

        $allowed_file = array("jpeg","jpg","png","gif");

        $name       = $_FILES['company_logo']['name'];
        $tmp_name   = $_FILES['company_logo']['tmp_name'];
        $size       = $_FILES['company_logo']['size'];
        

        if( !empty($name) ){

            $extension = strtolower(end(explode(".",$name)));

            $file_name = "/assets/images/".md5(rand()).".".$extension;

            if(!in_array($extension,$allowed_file)){
                echo "<span class='error'>Please Upload Valid Extension</span>";
                $upload = false;
            }else if($size > 30000000){
                echo "<span class='error'>Please Upload Small Image</span>";
                $upload = false;
            }else{
                $upload = move_uploaded_file($tmp_name,"..".$file_name);
                if($upload){
                    $upload = true;
                    
                }
            }
            
            
        }else{
            $file_name = $splashContent->logo;
            $upload = true;

        }
        
        
        if($upload !== false){
            $attributes = array(
                "logo"      => $file_name,
                "bgcolor"   => $bgcolor
            );

           // $splash = Splash::create($attributes);
            
            $update_splash = $splashContent->update_attributes($attributes);

            if($update_splash){
                echo "success";
                
                // version code method
                Version::versionCode();
            }
        }
        

        
    }



?>
<?php
    if(isset($_GET['show'])){
        $splashContent = Splash::find('all', array('order' => 'id asc','limit' => 1 ));
        foreach($splashContent as $splashContent){
    ?>
    <tr class="form-row" id="tr<?php echo $splashContent->id; ?>">
        <td><img src="<?php echo $base_url.$splashContent->logo; ?>" alt=""></td>
        <td><span style="color:<?php echo $splashContent->bgcolor; ?>; font-weight: bold;"><?php echo $splashContent->bgcolor; ?></td>
        <td><a href="javascript:void(0);" onclick="editSplash(<?php echo $splashContent->id; ?>);" class="edit">Edit</a></td>
        <td><a href="javascript:void(0);" onclick="deleteSplash(<?php echo $splashContent->id; ?>);" class="delete">Delete</a></td>
    </tr>
<?php } }?>