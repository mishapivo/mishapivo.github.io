<?php
    include 'db_connect.php';
    include '../inc/functions.php';

    if(isset($_POST['submit'])){
        
        $count_overview = Overview::find('all');
        if( count($count_overview) > 0 ){
            echo "exist";
        }else{
            
            $company_title      = $_POST['title'];
            $company_moto       = $_POST['moto'];
            $count_one_text     = $_POST['count-1-info'];
            $count_one_number   = $_POST['count-1-number'];
            $count_two_text     = $_POST['count-2-info'];
            $count_two_number   = $_POST['count-2-number'];
            $count_three_text   = $_POST['count-3-info'];
            $count_three_number = $_POST['count-3-number'];


            $allowed_file = array("jpeg","jpg","png","gif");

            $name = $_FILES['overview_bg']['name'];
            $tmp_name = $_FILES['overview_bg']['tmp_name'];
            $size = $_FILES['overview_bg']['size'];

            $logo_name = $_FILES['overview_logo']['name'];
            $logo_tmp_name = $_FILES['overview_logo']['tmp_name'];
            $logo_size = $_FILES['overview_logo']['size'];

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
                        "title"             => $company_title,
                        "moto"              => $company_moto,
                        "counter1_text"     => $count_one_text,
                        "counter1_number"   => $count_one_number,                    
                        "counter2_text"     => $count_two_text,
                        "counter2_number"   => $count_two_number,                    
                        "counter3_text"     => $count_three_text,
                        "counter3_number"   => $count_three_number
                    );
                    $overview = Overview::create($overview_static);
                    if($overview){
                        echo "Overview Content Add Successfully";
                        
                        // version code method
                        Version::versionCode();
                    }
                    
                }
            }
            
            
            
        }
        
    }






    if(isset($_POST['id'])){
        $delete_id = $_POST['id'];
        $poverview = Overview::find($delete_id);
        $poverview->delete();
        unlink("..".$poverview->background);
        unlink("..".$poverview->logo);
        
        // version code method
        Version::versionCode();
        
    }







    if(isset($_GET["edit"])){
        $overview_content_id =  $_POST['overview_content_id'];
        $overview = Overview::find($overview_content_id);
        
        $overview_content[] = array();
        
        $overview_content['background'] 	    = $overview->background;
        $overview_content['logo'] 	            = $overview->logo;
        $overview_content['title'] 	            = $overview->title;
        $overview_content['moto']               = $overview->moto;
        $overview_content['counter1_text']  	= $overview->counter1_text;
        $overview_content['counter1_number']    = $overview->counter1_number;
        $overview_content['counter2_text'] 	    = $overview->counter2_text;
        $overview_content['counter2_number'] 	= $overview->counter2_number;
        $overview_content['counter3_text'] 	    = $overview->counter3_text;
        $overview_content['counter3_number'] 	= $overview->counter3_number;
        $overview_content['id'] 		        = $overview->id;
        
        echo json_encode($overview_content);
        
    }





    if(isset($_GET['update'])){
        $company_title      = $_POST['title'];
        $company_moto       = $_POST['moto'];
        
        $count_one_text     = $_POST['count-1-info'];
        $count_one_number   = $_POST['count-1-number'];
            
        $count_two_text     = $_POST['count-2-info'];
        $count_two_number   = $_POST['count-2-number'];
            
        $count_three_text   = $_POST['count-3-info'];
        $count_three_number = $_POST['count-3-number'];
        
        
        $overview_content_id = $_POST['overview_content_id'];
        
        $overview = Overview::find($overview_content_id);
        
        
        $allowed_file = array("jpeg","jpg","png","gif");
        
        $name = $_FILES['overview_bg']['name'];
        $tmp_name = $_FILES['overview_bg']['tmp_name'];
        $size = $_FILES['overview_bg']['size'];
        
        
        $logo_name = $_FILES['overview_logo']['name'];
        $logo_tmp_name = $_FILES['overview_logo']['tmp_name'];
        $logo_size = $_FILES['overview_logo']['size'];
        
        
        if(!empty($name)){

            $extension = strtolower(end(explode(".",$name)));

            $file_name = "/assets/overview/".md5(rand()).".".$extension;

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
            $file_name = $overview->background;
            $bg_upload = true;
        }
        
        
        
        if(!empty($logo_name)){

            $logo_extension = strtolower(end(explode(".",$logo_name)));

            $logo_file_name = "/assets/overview/".md5(rand()).".".$logo_extension;

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
            $logo_file_name = $overview->logo;
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
                "title"             => $company_title,
                "moto"              => $company_moto,
                "counter1_text"     => $count_one_text,
                "counter1_number"   => $count_one_number,                    
                "counter2_text"     => $count_two_text,
                "counter2_number"   => $count_two_number,                    
                "counter3_text"     => $count_three_text,
                "counter3_number"   => $count_three_number
            );

            $update_overview = $overview->update_attributes($attributes);    
            
            if($update_overview){
                echo "success";
                
                // version code method
                Version::versionCode();
            }
        }
        
    }




?>


<?php
if(isset($_GET['show'])){
        $overview_contents = Overview::find('all', array('order' => 'id asc','limit' => 1 ));
        foreach($overview_contents as $overview_content){
    ?>
    <tr class="form-row" id="tr<?php echo $overview_content->id; ?>">
        <td><img src="<?php echo $base_url.$overview_content->background; ?>" alt=""></td>
        <td><img src="<?php echo $base_url.$overview_content->logo; ?>" alt=""></td>
        <td width="150px"><?php echo $overview_content->title; ?></td>
        <td width="150px"><?php echo $overview_content->moto; ?></td>
        <td><?php echo $overview_content->counter1_text; ?></td>
        <td><?php echo $overview_content->counter1_number; ?></td>        
        <td><?php echo $overview_content->counter2_text; ?></td>
        <td><?php echo $overview_content->counter2_number; ?></td>
        <td><?php echo $overview_content->counter3_text; ?></td>
        <td><?php echo $overview_content->counter3_number; ?></td>
        <td><a href="javascript:void(0);" onclick="editOverviewContent(<?php echo $overview_content->id; ?>);" class="edit">Edit</a></td>
        <td><a href="javascript:void(0);" onclick="deleteOverviewContent(<?php echo $overview_content->id; ?>);" class="delete">Delete</a></td>
    </tr>
<?php }} ?>