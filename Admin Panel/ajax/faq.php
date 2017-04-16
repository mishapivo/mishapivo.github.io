<?php
    include 'db_connect.php';

    if(isset($_POST['submit'])){

        if(isset($_POST['question'])){
            $number =  count($_POST['question']);
            
            if($number > 0){
                for($i = 0; $i<$number; $i++){
                   $question    = $_POST['question'][$i];
                   $ans         = $_POST['ans'][$i];
                   if(!empty($question) && !empty($ans)){
                       $upload = true;
                   }else {
                       $upload = false;
                   }

                }
            }else{
                $upload = false;
            }   
            
        }else{
            $upload = false;
        }
        
                    
        if($upload !== false){
            
            for($i = 0; $i<$number; $i++){
               $question    = $_POST['question'][$i];
               $ans         = $_POST['ans'][$i];
                
                $attributes = array(
                   "question"   => $question,
                   "answer"     => $ans
                );

                $faq = Faq::create($attributes);
            }
                       
            if($faq){
                echo "success";
                // version code method
                Version::versionCode();
            }
            
        }else{
            echo "<span class='error'>Please Submit Question And Answer</span>";
        }
    }


    if(isset($_POST['id'])){
        $delete_id = $_POST['id'];
        $faq = Faq::find($delete_id);
        $faq->delete();
        // version code method
        Version::versionCode();
    }


    if(isset($_GET["edit"])){
        $faq_id =  $_POST['faq_id'];
        $faq = Faq::find($faq_id);
        
        $faq_content[] = array();
        
        $faq_content['question'] 	= $faq->question;
        $faq_content['answer'] 	= $faq->answer;
        $faq_content['id'] 		= $faq->id;
        
        echo json_encode($faq_content);
        
    }



    if(isset($_GET['update'])){
        $question = $_POST['question'];
        $answer = $_POST['answer'];
        
        $faq_id = $_POST['faq_id'];
        
        $faq = Faq::find($faq_id);
            
        $attributes = array(
        "question"    => $question,
        "answer"         => $answer
        );

        $update_faq = $faq->update_attributes($attributes);    

        if($update_faq){
            echo "success";
            // version code method
            Version::versionCode();
        }

        
    }



?>


<?php 
if(isset($_GET['show'])){
    $faqs = Faq::find('all');
    foreach($faqs as $faq){
?>
<tr class="form-row" id="tr<?php echo $faq->id; ?>">
    <td><p class="title"><?php echo $faq->question; ?></p></td>
    <td width="50%">
        <p><?php echo $faq->answer; ?></p>
    </td>
    <td>
        <a href="javascript:void(0);" onclick="editFaq(<?php echo $faq->id; ?>);" class="edit">Edit</a>
    </td>
    <td><a href="javascript:void(0);" onclick="deleteFaq(<?php echo $faq->id; ?>);" class="delete">Delete</a></td>
</tr>
<?php }} ?>