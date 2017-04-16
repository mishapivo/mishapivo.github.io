<div class="modal fade" id="update_team_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
           
     <div class="modal-header">
       <div id="team_response" style="float:left;"></div>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>

            <form class="form-horizontal" action="" method="post" id="team_update_form">
               <input type="hidden" name="team_id" class="form-control" id="team_id">
                <div class="form-top">
                    <div class="form-group">
                        <label for="project-image" class="col-sm-4 control-label">Memeber Image</label>
                        <div class="col-sm-8">
                            <div class="file-wrapper">
                               upload
                               <input type="file" name="member_image" class="smart-file" id="member_image" data-field-type="bootstrap-file-filed" data-preview="on">
                           </div>
                           <div id="image" style="height: 100px; width: 100px;"></div>
                        </div>
                    </div>									

                    <div class="form-group">
                        <label for="member_name" class="col-sm-4 control-label">Member Name</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="member_name" id="member_name" placeholder="John doe"/>
                        </div>
                    </div>	

                    <div class="form-group">
                        <label for="member_designation" class="col-sm-4 control-label">Member Designation</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="member_designation" id="member_designation" placeholder="Designer"/>
                        </div>
                    </div>						
                </div>
                <div id="progress-wrp-team" class="progress-wrp" style="display: none;"><div class="progress-bar" style="width: 100%;"></div><div class="status">100%</div></div>
                <center><input type="submit" value="UPDATE" name="" class="btn btn-default update" ></center>
                
            </form>
        </div>
    </div>
</div>