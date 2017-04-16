<?php
    include 'db_connect.php';
    include '../inc/functions.php';

    if(isset($_POST['submit'])){
        $member_name = $_POST['member_name'];
        $member_designation = $_POST['member_designation'];
        
        $allowed_file = array("jpeg","jpg","png","gif");
        
        $name = $_FILES['member_image']['name'];
        $tmp_name = $_FILES['member_image']['tmp_name'];
        $size = $_FILES['member_image']['size'];
        
        
        
        $extension = strtolower(end(explode(".",$name)));
        
        $file_name = "/assets/team/".md5(rand()).".".$extension;
        
        if(!in_array($extension,$allowed_file)){
            echo "<span class='error'>Please Upload Valid Extension</span>";
        }else if($size > 30000000){
            echo "<span class='error'>Please Upload Small Image</span>";
        }else{
            $upload = move_uploaded_file($tmp_name,"..".$file_name);
            if($upload){
                $attributes = array(
                    "photo"         => $file_name,
                    "name"          => $member_name,
                    "designation"   => $member_designation
                );
                
                $team = Team::create($attributes);
                
                if($team){
                    echo "Team Member Added Successfully";
                    
                    // version code method
                    Version::versionCode();
                }
            }
        }
        
    }


    if(isset($_POST['id'])){
        $delete_id = $_POST['id'];
        $team = Team::find($delete_id);
        $team->delete();
        unlink("..".$team->photo);
        
        // version code method
        Version::versionCode();
    }


    if(isset($_GET["edit"])){
        $team_id =  $_POST['team_id'];
        $team = Team::find($team_id);
        
        $team_content[] = array();
        
        $team_content['name'] = $team->name;
        $team_content['designation'] = $team->designation;
        $team_content['photo'] = $team->photo;
        $team_content['id'] = $team->id;
        
        echo json_encode($team_content);
        
    }



    if(isset($_GET['update'])){
        $member_name = $_POST['member_name'];
        $member_designation = $_POST['member_designation'];
        
        
        $team_id = $_POST['team_id'];
        
        $team = Team::find($team_id);
        
        
        $allowed_file = array("jpeg","jpg","png","gif");
        
        $name = $_FILES['member_image']['name'];
        $tmp_name = $_FILES['member_image']['tmp_name'];
        $size = $_FILES['member_image']['size'];
        
        if(!empty($name)){

            $extension = strtolower(end(explode(".",$name)));

            $file_name = "/assets/team/".md5(rand()).".".$extension;

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
            $file_name = $team->photo;
            $upload = true;
        }
        
        
        if($upload !== false){
            
            $attributes = array(
            "name"    => $member_name,
            "photo"         => $file_name,
            "designation"         => $member_designation
            );

            $team_slide = $team->update_attributes($attributes);    
            
            if($team_slide){
                echo "success";
                
                // version code method
                Version::versionCode();
            }
        }
        
    }



?>


<?php
if(isset($_GET['show'])){
    $teams = Team::find('all');
    foreach($teams as $team){
?>
<tr class="form-row" id="tr<?php echo $team->id; ?>">
    <td><img src="<?php echo $base_url.$team->photo; ?>" alt="" /></td>
    <td><p class="title"><?php echo $team->name; ?></p></td>
    <td>
        <p><?php echo $team->designation; ?></p>
    </td>
    <td>
        <a href="javascript:void(0);" onclick="editTeam(<?php echo $team->id; ?>);" class="edit">Edit</a>
    </td>
    <td><a href="javascript:void(0);" onclick="deleteTeam(<?php echo $team->id; ?>);" class="delete">Delete</a></td>
</tr>

<?php } } ?>