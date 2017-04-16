<?php
    include 'db_connect.php';
    include '../inc/functions.php';

    if(isset($_POST['submit'])){
        $project_title = $_POST['project_title'];
        
        $allowed_file = array("jpeg","jpg","png","gif");
        
        $name = $_FILES['project_image']['name'];
        $tmp_name = $_FILES['project_image']['tmp_name'];
        $size = $_FILES['project_image']['size'];
        
        
        
        $extension = strtolower(end(explode(".",$name)));
        
        $file_name = "/assets/slider/".md5(rand()).".".$extension;
        
        if(!in_array($extension,$allowed_file)){
            echo "<span class='error'>Please Upload Valid Extension</span>";
        }else if($size > 30000000){
            echo "<span class='error'>Please Upload Small Image</span>";
        }else{
            $upload = move_uploaded_file($tmp_name,"..".$file_name);
            if($upload){
                $attributes = array(
                    "background"    => $file_name,
                    "title"         => $project_title
                );
                
                $slider = Slider::create($attributes);
                
                if($slider){
                    echo "Home Slider Added Successfully";
                    // version code method
                    Version::versionCode();
                }
            }
        }
        
    }


    if(isset($_POST['id'])){
        $delete_id = $_POST['id'];
        $slider = Slider::find($delete_id);
        $slider->delete();
        // version code method
        Version::versionCode();
        unlink("..".$slider->background);
    }


    if(isset($_GET["edit"])){
        $slider_id =  $_POST['slider_id'];
        $slider = Slider::find($slider_id);
        
        $slide_content[] = array();
        
        $slide_content['image'] = $slider->background;
        $slide_content['title'] = $slider->title;
        $slide_content['id'] = $slider->id;
        
        echo json_encode($slide_content);
        
    }


    if(isset($_GET['update'])){
        $project_title = $_POST['project_title'];
        
        
        $slide_id = $_POST['slide_id'];
        
        $slider = Slider::find($slide_id);
        
        
        $allowed_file = array("jpeg","jpg","png","gif");
        
        $name = $_FILES['project_image']['name'];
        $tmp_name = $_FILES['project_image']['tmp_name'];
        $size = $_FILES['project_image']['size'];
        
        if(!empty($name)){

            $extension = strtolower(end(explode(".",$name)));

            $file_name = "/assets/slider/".md5(rand()).".".$extension;

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
            $file_name = $slider->background;
            $upload = true;
        }
        
        
        if($upload !== false){
            
            $attributes = array(
            "background"    => $file_name,
            "title"         => $project_title
            );

            $update_slide = $slider->update_attributes($attributes);    
            
            if($update_slide){
                echo "success1";
                // version code method
                Version::versionCode();
            }
        }
        
    }
?>

<?php
    if(isset($_GET['show'])){
        $sliders = Slider::find('all');
        foreach($sliders as $slider){
    ?>
    <tr class="form-row" id="tr<?php echo $slider->id; ?>">
        <td><img src="<?php echo $base_url.$slider->background; ?>" alt="" /></td>
        <td><p class="title"><?php echo $slider->title; ?></p></td>
        <td>
            <a href="javascript:void(0);" onclick="editSlider(<?php echo $slider->id; ?>);" class="edit">Edit</a>
        </td>
        <td><a href="javascript:void(0);" onclick="deleteSlider(<?php echo $slider->id; ?>);" class="delete">Delete</a></td>
    </tr>
<?php }} ?>