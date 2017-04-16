<?php
    include 'db_connect.php';
    include '../inc/functions.php';

    if(isset($_POST['submit'])){
        
        $methodology_count = Methodology::find('all');
        if( count($methodology_count) > 0 ){
            echo "exist";
            
        }else{
            $allowed_file = array("jpeg","jpg","png","gif");

            $methodology_bg = $_FILES['methodology_bg']['name'];
            $methodology_bg_tmp_name = $_FILES['methodology_bg']['tmp_name'];
            $methodology_bg_size = $_FILES['methodology_bg']['size'];


            $methodology_logo = $_FILES['methodology_logo']['name'];
            $methodology_logo_tmp_name = $_FILES['methodology_logo']['tmp_name'];
            $methodology_logo_size = $_FILES['methodology_logo']['size'];


            if(!empty($methodology_bg)){

                $methodology_bg_extension = strtolower(end(explode(".",$methodology_bg)));

                $methodology_bg_name = "/assets/methodology/".md5(rand()).".".$methodology_bg_extension;     


                if(!in_array($methodology_bg_extension,$allowed_file)){
                    echo "<span class='error'>Please Upload Valid Extension</span>";
                    $uploadbg = false;
                }else if($methodology_bg_size > 30000000){
                    echo "<span class='error'>Please Upload Small Image</span>";
                    $uploadbg = false;
                }else{
                    move_uploaded_file($methodology_bg_tmp_name,"..".$methodology_bg_name);
                    $uploadbg = true;
                }
            }else{
                $methodology_bg_name = "";
            }



            if(!empty($methodology_logo)){
                $methodology_logo_extension = strtolower(end(explode(".",$methodology_logo)));

                $methodology_logo_name = "/assets/methodology/".md5(rand()).".".$methodology_logo_extension;

                if(!in_array($methodology_logo_extension,$allowed_file)){
                    echo "<span class='error'>Please Upload Valid Extension</span>";
                    $uploadlogo = false;
                }else if($methodology_logo_size > 30000000){
                    echo "<span class='error'>Please Upload Small Image</span>";
                    $uploadlogo = false;
                }else{
                    move_uploaded_file($methodology_logo_tmp_name,"..".$methodology_logo_name);
                    $uploadlogo = true;
                }  
            }else{
                $methodology_logo_name = "";
            }


            if($uploadbg !== false && $uploadlogo !== false){
                $attributes = array(
                    "bacground" => $methodology_bg_name,
                    "logo"      => $methodology_logo_name
                );

                $methodology = Methodology::create($attributes);

                if($methodology){
                    echo "Methodology Content Added Successfully";
                    // version code method
                    Version::versionCode();
                }

            }   
            
            
            
        }



    }



    if(isset($_POST['id'])){
        $delete_id = $_POST['id'];
        $methodology_content = Methodology::find($delete_id);
        $methodology_content->delete();
        unlink("..".$methodology_content->bacground);
        unlink("..".$methodology_content->logo);
        
        // version code method
        Version::versionCode();
        
    }


    if(isset($_GET["edit"])){
        $methodology_content_id =  $_POST['methodology_content_id'];
        $methodology = Methodology::find($methodology_content_id);
        
        $methodology_content[] = array();
        
        $methodology_content['background']      = $methodology->bacground;
        $methodology_content['logo'] 	      = $methodology->logo;
        $methodology_content['id'] 		= $methodology->id;
        
        echo json_encode($methodology_content);
        
    }



    if(isset($_GET['update'])){

        
        
        $methodology_content_id = $_POST['methodology_content_id'];
        
        $methodology = Methodology::find($methodology_content_id);
        
        
        $allowed_file = array("jpeg","jpg","png","gif");
        
        $background = $_FILES['methodology_bg']['name'];
        $tmp_name = $_FILES['methodology_bg']['tmp_name'];
        $size = $_FILES['methodology_bg']['size'];
        
        if(!empty($background)){

            $extension = strtolower(end(explode(".",$background)));

            $background_img = "/assets/methodology/".md5(rand()).".".$extension;

            if(!in_array($extension,$allowed_file)){
                echo "Please Upload Valid Extension for Background";
                $uploadbg = false;
            }else if($size > 30000000){
                echo "Please Upload Small Image for Background";
                $uploadbg = false;
            }else{
                $upload = move_uploaded_file($tmp_name,"..".$background_img);
                if($upload){
                    $uploadbg = true;
                } 
            }
            
        }else{
            $background_img = $methodology->bacground;
            $uploadbg = true;
        }
        
        
        
        
        
        
        $logo = $_FILES['methodology_logo']['name'];
        $tmp_name = $_FILES['methodology_logo']['tmp_name'];
        $size = $_FILES['methodology_logo']['size'];
        
        if(!empty($logo)){

            $extension = strtolower(end(explode(".",$logo)));

            $logo_img = "/assets/methodology/".md5(rand()).".".$extension;

            if(!in_array($extension,$allowed_file)){
                echo "Please Upload Valid Extension for Logo";
                $uploadlogo = false;
            }else if($size > 30000000){
                echo "Please Upload Small Image for Logo";
                $uploadlogo = false;
            }else{
                $upload = move_uploaded_file($tmp_name,"..".$logo_img);
                if($upload){
                    $uploadlogo = true;
                } 
            }
            
        }else{
            $logo_img = $methodology->logo;
            $uploadlogo = true;
        }
        
        
        
        
        
        if($uploadbg !== false && $uploadlogo !== false){
            
            $attributes = array(
            "bacground"    => $background_img,
            "logo"         => $logo_img
            );

            $update_methodology_content = $methodology->update_attributes($attributes);    
            
            if($update_methodology_content){
                echo "success";
                
                // version code method
                Version::versionCode();
            }
        }
        
    }


?>

<?php
    if(isset($_GET['show'])){
        $methodology_contents = Methodology::find('all', array('order' => 'id asc','limit' => 1 ));
        foreach($methodology_contents as $methodology_content){
    ?>
    <tr class="form-row" id="tr<?php echo $methodology_content->id; ?>">
        <td>Logo</td>
        <td><img src="<?php echo $base_url.$methodology_content->logo; ?>" alt=""></td>

        <td>Background Image</td>
        <td><img src="<?php echo $base_url.$methodology_content->bacground; ?>" alt=""></td>
        <td><a href="javascript:void(0);" onclick="editMethodologyContent(<?php echo $methodology_content->id; ?>);" class="edit">Edit</a></td>
        <td><a href="javascript:void(0);" onclick="deleteMethodologyContent(<?php echo $methodology_content->id; ?>);" class="delete">Delete</a></td>
    </tr>
<?php } }?>