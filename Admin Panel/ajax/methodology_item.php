<?php
    include 'db_connect.php';
    include '../inc/functions.php';

    if(isset($_POST['submit'])){
        $methodology_title = $_POST['methodology_title'];
        $methodology_details = $_POST['methodology_details'];
        
        $allowed_file = array("jpeg","jpg","png","gif");
        
        $name = $_FILES['methodology_icon']['name'];
        $tmp_name = $_FILES['methodology_icon']['tmp_name'];
        $size = $_FILES['methodology_icon']['size'];
        
        
        
        $extension = strtolower(end(explode(".",$name)));
        
        $file_name = "/assets/methodology/".md5(rand()).".".$extension;
        
        if(!in_array($extension,$allowed_file)){
            echo "<span class='error'>Please Upload Valid Extension</span>";
        }else if($size > 30000000){
            echo "<span class='error'>Please Upload Small Image</span>";
        }else{
            $upload = move_uploaded_file($tmp_name,"..".$file_name);
            if($upload){
                $attributes = array(
                    "icon"      => $file_name,
                    "title"     => $methodology_title,
                    "details"   => $methodology_details
                );
                
                $methodology_item = MethodologyItem::create($attributes);
                
                if($methodology_item){
                    echo "Methodology Item Added Successfully";
                    // version code method
                    Version::versionCode();
                }
            }
        }
        
    }


    if(isset($_POST['id'])){
        $delete_id = $_POST['id'];
        $methodology = MethodologyItem::find($delete_id);
        $methodology->delete();
        unlink("..".$methodology->icon);
        
        // version code method
        Version::versionCode();
    }


    if(isset($_GET["edit"])){
        $methodology_id =  $_POST['methodology_id'];
        $methodology = MethodologyItem::find($methodology_id);
        
        $methodology_content[] = array();
        
        $methodology_content['icon'] 	= $methodology->icon;
        $methodology_content['title'] 	= $methodology->title;
        $methodology_content['details'] = $methodology->details;
        $methodology_content['id'] 		= $methodology->id;
        
        echo json_encode($methodology_content);
        
    }




    if(isset($_GET['update'])){
        $methodology_title = $_POST['methodology_title'];
        $methodology_details = $_POST['methodology_details'];
        
        
        $methodology_item_id = $_POST['methodology_item_id'];
        
        $methodology = MethodologyItem::find($methodology_item_id);
        
        
        $allowed_file = array("jpeg","jpg","png","gif");
        
        $name = $_FILES['methodology_icon']['name'];
        $tmp_name = $_FILES['methodology_icon']['tmp_name'];
        $size = $_FILES['methodology_icon']['size'];
        
        if(!empty($name)){

            $extension = strtolower(end(explode(".",$name)));

            $file_name = "/assets/methodology/".md5(rand()).".".$extension;

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
            $file_name = $methodology->icon;
            $upload = true;
        }
        
        
        if($upload !== false){
            
            $attributes = array(
            "icon"    => $file_name,
            "title"         => $methodology_title,
            "details"         => $methodology_details
            );

            $update_methodology_item = $methodology->update_attributes($attributes);    
            
            if($update_methodology_item){
                echo "success";
                
                // version code method
                Version::versionCode();
            }
        }
        
    }


?>


<?php 
    if(isset($_GET['show'])){
    $methodologies = MethodologyItem::find('all');
    foreach($methodologies as $methodology){
?>
<tr class="form-row" id="tr<?php echo $methodology->id; ?>">
    <td><img src="<?php echo $base_url.$methodology->icon; ?>" alt="" /></td>
    <td><p class="title"><?php echo $methodology->title; ?></p></td>
    <td>
        <p><?php echo $methodology->details; ?></p>
    </td>
    <td>
        <a href="javascript:void(0);" onclick="editTMethodology(<?php echo $methodology->id; ?>);" class="edit">Edit</a>
    </td>
    <td><a href="javascript:void(0);" onclick="deleteTMethodology(<?php echo $methodology->id; ?>);" class="delete">Delete</a></td>
</tr>
<?php }} ?>