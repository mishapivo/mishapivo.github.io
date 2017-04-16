<?php
    include 'db_connect.php';
    include '../inc/functions.php';
    if(isset($_POST['submit'])){
        $social_name = $_POST['social_name'];
        $social_url = $_POST['social_url'];
        
        $allowed_file = array("jpeg","jpg","png","gif");
        
        $name = $_FILES['social_image']['name'];
        $tmp_name = $_FILES['social_image']['tmp_name'];
        $size = $_FILES['social_image']['size'];
        
        
        
        $extension = strtolower(end(explode(".",$name)));
        
        $file_name = "/assets/social/".md5(rand()).".".$extension;
        
        if(!in_array($extension,$allowed_file)){
            echo "<span class='error'>Please Upload Valid Extension</span>";
        }else if($size > 30000000){
            echo "<span class='error'>Please Upload Small Image</span>";
        }else{
            $upload = move_uploaded_file($tmp_name,"..".$file_name);
            if($upload){
                $attributes = array(
                    "image" => $file_name,
                    "name"  => $social_name,
                    "url"   => $social_url
                );
                
                $social = Social::create($attributes);
                
                if($social){
                    echo "Social Item Added Successfully";
                    
                    // version code method
                    Version::versionCode();
                }
            }
        }
        
    }


    if(isset($_POST['id'])){
        $delete_id = $_POST['id'];
        $social = Social::find($delete_id);
        $social->delete();
        unlink("..".$social->image);
        
        // version code method
        Version::versionCode();
    }


    if(isset($_GET["edit"])){
        $social_id =  $_POST['social_id'];
        $social = Social::find($social_id);
        
        $social_content[] = array();
        
        $social_content['image'] 	= $social->image;
        $social_content['name'] 	= $social->name;
        $social_content['url'] 	    = $social->url;
        $social_content['id'] 		= $social->id;
        
        echo json_encode($social_content);
        
    }


    if(isset($_GET['update'])){
        $social_name = $_POST['social_name'];
        $social_url = $_POST['social_url'];
        
        
        $social_id = $_POST['social_id'];
        
        $social = Social::find($social_id);
        
        
        $allowed_file = array("jpeg","jpg","png","gif");
        
        $name = $_FILES['social_image']['name'];
        $tmp_name = $_FILES['social_image']['tmp_name'];
        $size = $_FILES['social_image']['size'];
        
        if(!empty($name)){

            $extension = strtolower(end(explode(".",$name)));

            $file_name = "/assets/social/".md5(rand()).".".$extension;

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
            $file_name = $social->image;
            $upload = true;
        }
        
        
        if($upload !== false){
            
            $attributes = array(
            "image"    => $file_name,
            "name"         => $social_name,
            "url"         => $social_url
            );

            $update_social = $social->update_attributes($attributes);    
            
            if($update_social){
                echo "success";
                
                // version code method
                Version::versionCode();
            }
        }
        
    }

?>


<?php 
    if(isset($_GET['show'])){
    $socials = Social::find('all');
    foreach($socials as $social){
?>
<tr class="form-row" id="tr<?php echo $social->id; ?>">
    <td><img src="<?php echo $base_url.$social->image; ?>" alt=""></td>
    <td><?php echo $social->name; ?></td>
    <td>
        <?php echo $social->url; ?>
    </td>
    <td>
       <a href="javascript:void(0);" onclick="editSocial(<?php echo $social->id; ?>);" class="edit">Edit</a>
    </td>
    <td><a href="javascript:void(0);" onclick="deleteSocial(<?php echo $social->id; ?>);" class="delete">Delete</a></td>
</tr>
<?php }} ?>