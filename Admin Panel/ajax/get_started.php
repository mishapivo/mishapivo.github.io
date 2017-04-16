<?php
    include 'db_connect.php';

    if(isset($_POST['submit'])){
        
        $getstarted_count = GetStarted::find('all');
        if( count($getstarted_count) > 0 ){
            echo "exist";
        }else{
            $range_one = $_POST['range_one'];
            $range_two = $_POST['range_two'];
            $range_three = $_POST['range_three'];
            $range_four = $_POST['range_four'];
            $contact_number = $_POST['contact_number'];
            $contact_mail = $_POST['contact_mail'];
            $web_address = $_POST['web_address'];


            if(isset($_POST['platform'])){
                $number =  count($_POST['platform']);
            }


            $attributes = array(
                "range1"    => $range_one,
                "range2"    => $range_two,
                "range3"    => $range_three,
                "range4"    => $range_four,
                "phone"     => $contact_number,
                "email"     => $contact_mail,
                "web"       => $web_address
            );

            $get_started = GetStarted::create($attributes);

            if($get_started){
                echo "Get Started Content Successfully";
                // version code method
                Version::versionCode();

                // Get Started tag 
                if(isset($_POST['platform']) && $number > 0){
                    for($i = 0; $i<$number; $i++){
                       $platform    = $_POST['platform'][$i];
                       if(!empty($platform)){
                           $attributes = array(
                               "platform"      => $platform
                           );

                          GetStartedTag::create($attributes);


                       }else {

                       }

                    }
                }else{

                } 



            }
            
            
            
            
        }
        
        


    }




    if(isset($_POST['getStartedId'])){
        $delete_id = $_POST['getStartedId'];
        $get_started = GetStarted::find($delete_id);
        $get_started->delete();
        // version code method
        Version::versionCode();
    }





    if(isset($_GET["edit"])){ 
        $id =  $_POST['id'];
        $getstarted = GetStarted::find($id);

        $getstarted_info['getstarted_data']= array();

        $getstarted_info['platforms']= array();



        $getstarted_info['getstarted_data']['range1'] = $getstarted->range1;
        $getstarted_info['getstarted_data']['range2'] = $getstarted->range2;
        $getstarted_info['getstarted_data']['range3'] = $getstarted->range3;
        $getstarted_info['getstarted_data']['range4'] = $getstarted->range4;
        $getstarted_info['getstarted_data']['phone'] = $getstarted->phone;
        $getstarted_info['getstarted_data']['web'] = $getstarted->web;
        $getstarted_info['getstarted_data']['email'] = $getstarted->email;
        $getstarted_info['getstarted_data']['id'] = $getstarted->id;
        
        
        


        $platforms = GetStartedTag::find('all');

        $tag_number = count($platforms);



        foreach($platforms as $platform){

            $platform_name['name']= $platform->platform;
            array_push($getstarted_info['platforms'], $platform_name);
        }

        echo json_encode($getstarted_info);

    }




    if(isset($_GET['update'])){
        
        $range_one = $_POST['range_one'];
        $range_two = $_POST['range_two'];
        $range_three = $_POST['range_three'];
        $range_four = $_POST['range_four'];
        $contact_number = $_POST['contact_number'];
        $contact_mail = $_POST['contact_mail'];
        $web_address = $_POST['web_address'];

        
        if(isset($_POST['platform'])){
            $number =  count($_POST['platform']);
        }
        
        
        $get_started_id = $_POST['get_started_id'];
        
        $getstarted = GetStarted::find($get_started_id);


        $attributes = array(
            "range1"    => $range_one,
            "range2"    => $range_two,
            "range3"    => $range_three,
            "range4"    => $range_four,
            "phone"    => $contact_number,
            "email"    => $contact_mail,
            "web"    => $web_address,

        );        
        
        $update_getstarted = $getstarted->update_attributes($attributes);
        
        // version code method
        Version::versionCode();
        
        $tags = GetStartedTag::find('all');
        foreach($tags as $tag){
            $tag->delete();
        }      


        // Portfolio tag 
        if(isset($number) && $number > 0){
            for($i = 0; $i<$number; $i++){
               $platform    = $_POST['platform'][$i];
               if(!empty($platform)){
                   $attributes = array(
                       "platform"   => $platform
                   );

                  GetStartedTag::create($attributes);


               }else {

               }

            }
        }else{

        }  

         
        if($update_getstarted){
            echo "success";
        }
            
 
            

        }






?>


<?php
    if(isset($_GET['show'])){
        $getstarteds = GetStarted::find('all', array('order' => 'id asc','limit' => 1 ));
        foreach($getstarteds as $getstarted){
    ?>
    <tr class="form-row" id="tr<?php echo $getstarted->id; ?>">
        <td><?php echo $getstarted->range1; ?></td>
        <td><?php echo $getstarted->range2; ?></td>
        <td><?php echo $getstarted->range3; ?></td>
        <td><?php echo $getstarted->range4; ?></td>
        <td><?php echo $getstarted->phone; ?></td>
        <td width="150px;"><?php echo $getstarted->email; ?></td>
        <td width="100px;"><?php echo $getstarted->web; ?></td>
        <td width="200px;">
        <?php 
            $tags = GetStartedTag::find('all');
            foreach($tags as $tag){
        ?>
        <a href="" class="tag" target="blank"><?php echo $tag->platform; ?></a>
        <?php } ?>
        </td>
        <td><a href="javascript:void(0);" onclick="editGetstarted(<?php echo $getstarted->id; ?>);" class="edit">Edit</a></td>
        <td><a href="javascript:void(0);" onclick="deleteGetStarted(<?php echo $getstarted->id; ?>);" class="delete">Delete</a></td>
    </tr>
<?php }} ?>