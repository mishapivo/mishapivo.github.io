<?php
    include 'db_connect.php';
    include '../inc/functions.php';

    if(isset($_POST['submit'])){
        $client_name = $_POST['client_name'];
        $project_name = $_POST['project_name'];
        $project_url = $_POST['project_url'];
        $client_feedback = $_POST['client_feedback'];



        $allowed_file = array("jpeg","jpg","png","gif");
        
        $name = $_FILES['client_image']['name'];
        $tmp_name = $_FILES['client_image']['tmp_name'];
        $size = $_FILES['client_image']['size'];
        
        
        
        $extension = strtolower(end(explode(".",$name)));
        
        $file_name = "/assets/testimonial/".md5(rand()).".".$extension;
        
        if(!in_array($extension,$allowed_file)){
            echo "<span class='error'>Please Upload Valid Extension</span>";
        }else if($size > 30000000){
            echo "<span class='error'>Please Upload Small Image</span>";
        }else{
            $upload = move_uploaded_file($tmp_name,"..".$file_name);
            if($upload){
                $attributes = array(
                    "image"         => $file_name,
                    "name"          => $client_name,
                    "project"       => $project_name,
                    "url"           => $project_url,
                    "feedback"      => $client_feedback
                );
                
                $testimonial = Testimonial::create($attributes);
                
                if($testimonial){
                    echo "Testimonial Added Successfully";
                    
                    // version code method
                    Version::versionCode();
                    
                }
            }
        }
        
    }

    if(isset($_POST['id'])){
        $delete_id = $_POST['id'];
        $testimonial = Testimonial::find($delete_id);
        $testimonial->delete();
        unlink("..".$testimonial->image);
        
        // version code method
        Version::versionCode();
    }



    if(isset($_GET["edit"])){
        $testimonial_id =  $_POST['testimonial_id'];
        $testimonial = Testimonial::find($testimonial_id);
        
        $testimonial_content[] = array();
        
        $testimonial_content['image'] = $testimonial->image;
        $testimonial_content['name'] = $testimonial->name;
        $testimonial_content['project'] = $testimonial->project;
        $testimonial_content['url'] = $testimonial->url;
        $testimonial_content['feedback'] = $testimonial->feedback;
        $testimonial_content['id'] = $testimonial->id;
        
        echo json_encode($testimonial_content);
        
    }


    if(isset($_GET['update'])){
        $client_name        = $_POST['client_name'];
        $project_name       = $_POST['project_name'];
        $project_url        = $_POST['project_url'];
        $client_feedback    = $_POST['client_feedback'];
        
        
        $testimonial_id = $_POST['testimonial_id'];
        
        $testimonial = Testimonial::find($testimonial_id);
        
        
        $allowed_file = array("jpeg","jpg","png","gif");
        
        $name = $_FILES['client_image']['name'];
        $tmp_name = $_FILES['client_image']['tmp_name'];
        $size = $_FILES['client_image']['size'];
        
        if(!empty($name)){

            $extension = strtolower(end(explode(".",$name)));

            $file_name = "/assets/testimonial/".md5(rand()).".".$extension;

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
            $file_name = $testimonial->image;
            $upload = true;
        }
        
        
        if($upload !== false){
            
            $attributes = array(
            "image"    => $file_name,
            "name"         => $client_name,
            "project"         => $project_name,
            "url"         => $project_url,
            "feedback"         => $client_feedback
            );

            $update_testimonial = $testimonial->update_attributes($attributes);    
            
            if($update_testimonial){
                echo "success";
                
                // version code method
                Version::versionCode();
            }
        }
        
    }


?>


<?php
if(isset($_GET['show'])){
    $testimonials = Testimonial::find('all');
    foreach($testimonials as $testimonial){
?>
<tr class="form-row" id="tr<?php echo $testimonial->id; ?>">
    <td><img src="<?php echo $base_url.$testimonial->image; ?>" alt="" /></td>
    <td><p><?php echo $testimonial->name; ?></p></td>
    <td><p class="title"><?php echo $testimonial->project; ?></p></td>
    <td>
        <?php echo $testimonial->url; ?>
    </td>
    <td width="25%"><?php echo $testimonial->feedback; ?></td>
    <td>
        <a href="javascript:void(0);" onclick="editTestimonial(<?php echo $testimonial->id; ?>);" class="edit">Edit</a>
    </td>
    <td><a href="javascript:void(0);" onclick="deleteTestimonial(<?php echo $testimonial->id; ?>);" class="delete">Delete</a></td>
</tr>
<?php } } ?>