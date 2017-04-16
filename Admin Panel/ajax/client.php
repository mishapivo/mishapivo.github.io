<?php
    include 'db_connect.php';
    include '../inc/functions.php';

    if(isset($_POST['submit'])){
        $client_name = $_POST['client_name'];
        $project_name = $_POST['project_name'];
        $client_country = $_POST['client_country'];
        
        $number =  count($_POST['project_platform']);


        $allowed_file = array("jpeg","jpg","png","gif");
        
        $name = $_FILES['client_logo']['name'];
        $tmp_name = $_FILES['client_logo']['tmp_name'];
        $size = $_FILES['client_logo']['size'];
        
        
        
        $extension = strtolower(end(explode(".",$name)));
        
        $file_name = "/assets/client/".md5(rand()).".".$extension;
        
        if(!in_array($extension,$allowed_file)){
            echo "<span class='error'>Please Upload Valid Extension</span>";
        }else if($size > 30000000){
            echo "<span class='error'>Please Upload Small Image</span>";
        }else{
            $upload = move_uploaded_file($tmp_name,"..".$file_name);
            if($upload){
                $attributes = array(
                    "logo"          => $file_name,
                    "name"          => $client_name,
                    "company"       => $project_name,
                    "country"       => $client_country
                );
                
                $client = Client::create($attributes);
                
                if($client){
                    echo "Client Added Successfully";
                    Version::versionCode();
                    $client_id = $client->id;
                    
                    // Client tag 
                    if($number > 0){
                        for($i = 0; $i<$number; $i++){
                           $platform    = $_POST['project_platform'][$i];
                           $url         = $_POST['project_url'][$i];
                           if(!empty($platform) && !empty($url)){
                               $attributes = array(
                                   "client_id"      => $client_id,
                                   "tag"            => $platform,
                                   "url"            => $url
                               );

                              ClientTag::create($attributes);


                           }else {

                           }

                        }
                    }else{

                    } 
                    
                    
                    
                }
            }
        }
        
    }


    if(isset($_POST['id'])){
        $delete_id = $_POST['id'];
        $client = Client::find($delete_id);
        $client->delete();
        
        // version code method
        Version::versionCode();
        unlink("..".$client->logo);
        
        $tags = ClientTag::find('all',array('conditions' => array('client_id=?',$delete_id)));
        foreach($tags as $tag){
            $tag->delete();
        }
        
    }



    if(isset($_GET["edit"])){ 
        $client_id =  $_POST['client_id'];
        $clients = Client::find($client_id);

        $clients_info['client_data']= array();

        $clients_info['tags']= array();



        $clients_info['client_data']['name'] = $clients->name;
        $clients_info['client_data']['logo'] = $clients->logo;
        $clients_info['client_data']['company'] = $clients->company;
        $clients_info['client_data']['country'] = $clients->country;
        $clients_info['client_data']['id'] = $clients->id;

        $tags = ClientTag::find('all',array('conditions' => array('client_id=?',$client_id)));

        $tag_number = count($tags);



        foreach($tags as $single_tag){
            $tag = array();
            $tag['name']= $single_tag->tag;
            $tag['url']= $single_tag->url;
            $tag['id']= $single_tag->id;
            array_push($clients_info['tags'], $tag);
        }

        echo json_encode($clients_info);

    }




    if(isset($_GET['update'])){
        $project_name = $_POST['project_name'];
        $client_name = $_POST['client_name'];
        $client_country = $_POST['client_country'];
        
        if(isset($_POST['project_platform'])){
            $number =  count($_POST['project_platform']);
        }
        
        
        $client_id = $_POST['client_id'];
        
        $client = Client::find($client_id);
        
        
        $allowed_file = array("jpeg","jpg","png","gif");
        
        $name = $_FILES['client_logo']['name'];
        $tmp_name = $_FILES['client_logo']['tmp_name'];
        $size = $_FILES['client_logo']['size'];
        
        if(!empty($name)){

            $extension = strtolower(end(explode(".",$name)));

            $file_name = "/assets/client/".md5(rand()).".".$extension;

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
            $file_name = $client->logo;
            $upload = true;
        }
        
        
        if($upload !== false){
            
            $attributes = array(
            "logo"    => $file_name,
            "name"         => $client_name,
            "company"         => $project_name,
            "country"         => $client_country
            );

            $update_client = $client->update_attributes($attributes);  
            
            // version code method
            Version::versionCode();
            
            $tags = ClientTag::find('all',array('conditions' => array('client_id=?',$client_id)));
            foreach($tags as $tag){
                $tag->delete();
            }
            
            
            // Client tag 
            if(isset($number) && $number > 0){
                for($i = 0; $i<$number; $i++){
                   $platform    = $_POST['project_platform'][$i];
                   $url         = $_POST['project_url'][$i];
                   if(!empty($platform) && !empty($url)){
                       $attributes = array(
                           "client_id"      => $client_id,
                           "tag"            => $platform,
                           "url"            => $url
                       );

                      ClientTag::create($attributes);


                   }else {

                   }

                }
            }else{

            } 
            
            
            if($update_client){
                echo "success";
            }
        }
        
    }



?>


<?php
if(isset($_GET['show'])){
    $clients = Client::find('all');
    foreach($clients as $client){
?>
<tr class="form-row" id="tr<?php echo $client->id; ?>">
    <td><img src="<?php echo $base_url.$client->logo; ?>" alt="" /></td>
    <td><p><?php echo $client->name; ?></p></td>
    <td><p class="title"><?php echo $client->company; ?></p></td>
    <td>
        <?php 
            $client_id = $client->id;
            $tags = ClientTag::find('all',array('conditions' => array('client_id=?',$client_id)));
            foreach($tags as $tag){
        ?>
        <a href="<?php echo $tag->url; ?>" class="tag" target="blank"><?php echo $tag->tag; ?></a>
        <?php } ?>
    </td>
    <td>
        <a href="javascript:void(0);" onclick="editClient(<?php echo $client->id; ?>);" class="edit">Edit</a>
    </td>
    <td><a href="javascript:void(0);" onclick="clientDelete(<?php echo $client->id; ?>);" class="delete">Delete</a></td>
</tr>
<?php } } ?>