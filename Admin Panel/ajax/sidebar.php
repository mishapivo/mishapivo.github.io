<?php
    include 'db_connect.php';
    include '../inc/functions.php';

    if(isset($_POST['submit'])){
        
        $count_sidebar = Sidebar::find('all');
        if( count($count_sidebar) > 0 ){
            echo "exist";
        }else{
            
            $phone      = $_POST['phone'];
            $email       = $_POST['email'];
            $web       = $_POST['web'];


            $allowed_file = array("jpeg","jpg","png","gif");

            $name = $_FILES['sidebar_bg']['name'];
            $tmp_name = $_FILES['sidebar_bg']['tmp_name'];
            $size = $_FILES['sidebar_bg']['size'];

            $logo_name = $_FILES['sidebar_logo']['name'];
            $logo_tmp_name = $_FILES['sidebar_logo']['tmp_name'];
            $logo_size = $_FILES['sidebar_logo']['size'];

            $extension = strtolower(end(explode(".",$name)));
            $logo_extension = strtolower(end(explode(".",$logo_name)));
            

            $file_name = "/assets/images/".md5(rand()).".".$extension;
            $logo_file_name = "/assets/images/".md5(rand()).".".$logo_extension;

            if(!in_array($extension,$allowed_file)){
                echo "<span class='error'>Please Upload Valid Extension for Background</span>";
                $bg_upload = false;
            }else if($size > 300000000){
                echo "<span class='error'>Please Upload Small Background Image</span>";
                $bg_upload = false;
            }else{
                $bg_upload = true;

            }
            
            
            
            if(!in_array($logo_extension,$allowed_file)){
                echo "<span class='error'>Please Upload Valid Extension for Logo</span>";
                $logo_upload = false;
            }else if($logo_size > 300000000){
                echo "<span class='error'>Please Upload Small Background Image</span>";
                $logo_upload = false;
            }else{
                $logo_upload = true;

            }
            
            
            if($bg_upload == true && $logo_upload == true){
                $upload_bg_file = move_uploaded_file($tmp_name,"..".$file_name);
                $upload_logo_file = move_uploaded_file($logo_tmp_name,"..".$logo_file_name);
                
                if($bg_upload && $logo_upload){
                    $overview_static = array(
                        "background"        => $file_name,
                        "logo"              => $logo_file_name,
                        "phone"             => $phone,
                        "email"             => $email,
                        "web"               => $web
                    );
                    $sidebar = Sidebar::create($overview_static);
                    if($sidebar){
                        echo "Sidebar Content Added Successfully";
                        
                        // version code method
                        Version::versionCode();
                    }
                    
                }
            }
            
            
            
        }
        
    }


    if(isset($_POST['id'])){
        $delete_id = $_POST['id'];
        $sidebar = Sidebar::find($delete_id);
        $sidebar->delete();
        unlink("..".$sidebar->background);
        unlink("..".$sidebar->logo);
        
        // version code method
        Version::versionCode();
        
    }


    if(isset($_GET["edit"])){
        $sidebar_id =  $_POST['sidebar_id'];
        $sidebar = Sidebar::find($sidebar_id);
        
        $sidebar_content[] = array();
        
        $sidebar_content['background'] 	        = $sidebar->background;
        $sidebar_content['logo'] 	            = $sidebar->logo;
        $sidebar_content['phone'] 	            = $sidebar->phone;
        $sidebar_content['email']               = $sidebar->email;
        $sidebar_content['web']  	            = $sidebar->web;
        $sidebar_content['id'] 		        = $sidebar->id;
        
        echo json_encode($sidebar_content);
        
    }





    if(isset($_GET['update'])){
        $phone      = $_POST['phone'];
        $email      = $_POST['email'];
        $web      = $_POST['web'];

        $sidebar_content_id = $_POST['sidebar_id'];
        
        $sidebar = Sidebar::find($sidebar_content_id);
        
        
        $allowed_file = array("jpeg","jpg","png","gif");
        
        $name = $_FILES['sidebar_bg']['name'];
        $tmp_name = $_FILES['sidebar_bg']['tmp_name'];
        $size = $_FILES['sidebar_bg']['size'];
        
        
        $logo_name = $_FILES['sidebar_logo']['name'];
        $logo_tmp_name = $_FILES['sidebar_logo']['tmp_name'];
        $logo_size = $_FILES['sidebar_logo']['size'];
        
        
        if(!empty($name)){

            $extension = strtolower(end(explode(".",$name)));

            $file_name = "/assets/images/".md5(rand()).".".$extension;

            if(!in_array($extension,$allowed_file)){
                echo "<span>Please Upload Valid Extension for background</span>";
                $bg_upload = false;
            }else if($size > 30000000){
                echo "<span>Please Upload Small Background Image</span>";
                $bg_upload = false;
            }else{
                
                $bg_upload = true;

            }
            
        }else{
            $file_name = $sidebar->background;
            $bg_upload = true;
        }
        
        
        
        if(!empty($logo_name)){

            $logo_extension = strtolower(end(explode(".",$logo_name)));

            $logo_file_name = "/assets/images/".md5(rand()).".".$logo_extension;

            if(!in_array($logo_extension,$allowed_file)){
                echo "<span>Please Upload Valid Extension Logo</span>";
                $logo_upload = false;
            }else if($size > 30000000){
                echo "<span>Please Upload Small Logo</span>";
                $logo_upload = false;
            }else{
                
                $logo_upload = true;

            }
            
        }else{
            $logo_file_name = $sidebar->logo;
            $logo_upload = true;
        }
        
        
        if($bg_upload !== false && $logo_upload !== false ){
            
            
            if(!empty($name)){
                $move_background = move_uploaded_file($tmp_name,"..".$file_name);
            }
            
            if(!empty($logo_name)){
                $move_logo = move_uploaded_file($logo_tmp_name,"..".$logo_file_name);
            }
            
            $attributes = array(
                "background"        => $file_name,
                "logo"              => $logo_file_name,
                "phone"             => $phone,
                "email"             => $email,
                "web"               => $web,

            );

            $update_sidebar = $sidebar->update_attributes($attributes);    
            
            if($update_sidebar){
                echo "success";
                
                // version code method
                Version::versionCode();
            }
        }
        
    }








?>
<?php
if(isset($_GET['show'])){
        $sidebar_contents = Sidebar::find('all', array('order' => 'id asc','limit' => 1 ));
        foreach($sidebar_contents as $sidebar_content){
    ?>
    <tr class="form-row" id="tr<?php echo $sidebar_content->id; ?>">
        <td><img src="<?php echo $base_url.$sidebar_content->background; ?>" alt=""></td>
        <td><img src="<?php echo $base_url.$sidebar_content->logo; ?>" alt=""></td>
        <td width="150px"><?php echo $sidebar_content->phone; ?></td>
        <td width="150px"><?php echo $sidebar_content->email; ?></td>
        <td><?php echo $sidebar_content->web; ?></td>
        <td><a href="javascript:void(0);" onclick="editSidebarContent(<?php echo $sidebar_content->id; ?>);" class="edit">Edit</a></td>
        <td><a href="javascript:void(0);" onclick="deleteSidebarContent(<?php echo $sidebar_content->id; ?>);" class="delete">Delete</a></td>
    </tr>
<?php }} ?>