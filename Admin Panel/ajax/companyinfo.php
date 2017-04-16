<?php
    include 'db_connect.php';
    include '../inc/functions.php';

    if(isset($_POST['submit'])){
        $overview_title = $_POST['overview_title'];
        $overview_details = $_POST['overview_details'];
        
        $allowed_file = array("jpeg","jpg","png","gif");
        
        $name = $_FILES['overview_icon']['name'];
        $tmp_name = $_FILES['overview_icon']['tmp_name'];
        $size = $_FILES['overview_icon']['size'];
        
        
        
        $extension = strtolower(end(explode(".",$name)));
        
        $file_name = "/assets/overview/".md5(rand()).".".$extension;
        
        if(!in_array($extension,$allowed_file)){
            echo "<span class='error'>Please Upload Valid Extension</span>";
        }else if($size > 30000000){
            echo "<span class='error'>Please Upload Small Image</span>";
        }else{
            $upload = move_uploaded_file($tmp_name,"..".$file_name);
            if($upload){
                $attributes = array(
                    "icon"      => $file_name,
                    "title"   => $overview_title,
                    "description"   => $overview_details
                );
                
                $company_info = CompanyInfo::create($attributes);
                
                if($company_info){
                    echo "Company Info Added Successfully";
                    // version code method
                    Version::versionCode();
                }
            }
        }
        
    }



    if(isset($_POST['id'])){
        $delete_id = $_POST['id'];
        $company_info = CompanyInfo::find($delete_id);
        $company_info->delete();
        
        // version code method
        Version::versionCode();
        unlink("..".$company_info->icon);
    }



    if(isset($_GET["edit"])){
        $company_info_id =  $_POST['company_info_id'];

        $company_info = CompanyInfo::find($company_info_id);
        
        $company_content[] = array();
        
        $company_content['icon'] = $company_info->icon;
        $company_content['title'] = $company_info->title;
        $company_content['description'] = $company_info->description;
        $company_content['id'] = $company_info->id;
        
        echo json_encode($company_content);

    }

    if(isset($_GET['update'])){
        $overview_title = $_POST['overview_title'];
        $overview_details = $_POST['overview_details'];
        
        
        $company_info_id = $_POST['company_info_id'];
        
        $company_info = CompanyInfo::find($company_info_id);
        
        
        $allowed_file = array("jpeg","jpg","png","gif");
        
        $name = $_FILES['overview_icon']['name'];
        $tmp_name = $_FILES['overview_icon']['tmp_name'];
        $size = $_FILES['overview_icon']['size'];
        
        if(!empty($name)){

            $extension = strtolower(end(explode(".",$name)));

            $file_name = "/assets/overview/".md5(rand()).".".$extension;

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
            $file_name = $company_info->icon;
            $upload = true;
        }
        
        
        if($upload !== false){
            
            $attributes = array(
            "icon"    => $file_name,
            "title"         => $overview_title,
            "description"         => $overview_details
            );

            $update_company_info = $company_info->update_attributes($attributes);    
            
            if($update_company_info){
                echo "success";
                // version code method
                Version::versionCode();
            }
        }
        
    }

?>

<?php 
    if(isset($_GET['show'])){
    $company_infos = CompanyInfo::find('all');
    foreach($company_infos as $company_info){
?>
<tr class="form-row" id="tr<?php echo $company_info->id; ?>">
    <td><img src="<?php echo $base_url.$company_info->icon; ?>" alt="" /></td>
    <td><p class="title"><?php echo $company_info->title; ?></p></td>
    <td>
        <?php echo $company_info->description; ?>
    </td>
    <td>
        <a href="javascript:void(0);" onclick="editCompanyInfo(<?php echo $company_info->id; ?>);" class="edit">Edit</a>
    </td>
    <td><a href="javascript:void(0);" onclick="deleteCompanyInfo(<?php echo $company_info->id; ?>);" class="delete">Delete</a></td>
</tr>
<?php } } ?>



