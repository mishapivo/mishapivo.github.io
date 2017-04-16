<div class="modal fade" id="update_faq_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
           
     <div class="modal-header">
       <div id="faq_response" style="float:left;"></div>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>

            <form class="form-horizontal" action="" method="post" id="faq_update_form">
               <input type="hidden" name="faq_id" class="form-control" id="faq_id">
                <div class="form-top">

                    <div class="form-group">
                        <label for="member_name" class="col-sm-4 control-label">Question</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="question" id="question"/>
                        </div>
                    </div>	

                    <div class="form-group">
                        <label for="member_designation" class="col-sm-4 control-label">Answer</label>
                        <div class="col-sm-8">
                            <textarea name="answer" id="answer" class="form-control" cols="30" rows="5"></textarea>
                        </div>
                    </div>						
                </div>
                <center><input type="submit" value="UPDATE" name="" class="btn btn-default update" ></center>
                
            </form>
        </div>
    </div>
</div>