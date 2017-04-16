<div class="modal fade" id="update_methodology_content_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
           
     <div class="modal-header">
       <div id="methodology_content_response" style="float:left;"></div>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>

            <form class="form-horizontal" action="" method="post" id="update_methodology_content_form">
               <input type="hidden" name="methodology_content_id" class="form-control" id="methodology_content_id">
                <div class="form-top">
                    <div class="form-group">
                        <label for="project-image" class="col-sm-5 control-label">Methodology Background</label>
                        <div class="col-sm-4">
                            <div class="file-wrapper">
                               upload
                               <input type="file" id="methodology-bg" name="methodology_bg" class="smart-file" data-field-type="bootstrap-file-filed" data-preview="on">
                           </div>
                           <div id="image" style=""></div>
                        </div>
                    </div>									

                    <div class="form-group">
                        <label for="project-image" class="col-sm-5 control-label">Methodology Logo </label>
                        <div class="col-sm-4">
                            <div class="file-wrapper">
                               upload
                               <input type="file" id="methodology-logo" name="methodology_logo" class="smart-file" data-field-type="bootstrap-file-filed" data-preview="on">
                           </div>
                           <div id="logo" style=""></div>
                        </div>
                    </div>
							
                </div>
                <div id="progress-wrp-methodology_content" class="progress-wrp" style="display: none;"><div class="progress-bar" style="width: 100%;"></div><div class="status">100%</div></div>
                <center><input type="submit" value="UPDATE" name="" class="btn btn-default update" ></center>
                
            </form>
        </div>
    </div>
</div>
	
	
	
	
	
	
	
<div class="modal fade" id="update_methodology_item_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
           
     <div class="modal-header">
       <div id="methodology_item_response" style="float:left;"></div>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>

            <form class="form-horizontal" action="" method="post" id="methodology_item_update_form">
               <input type="hidden" name="methodology_item_id" class="form-control" id="methodology_item_id">
                <div class="form-top">
                    <div class="form-group">
                        <label for="methodology_icon" class="col-sm-4 control-label">Methodology Icon</label>
                        <div class="col-sm-8">
                            <div class="file-wrapper">
                               upload
                               <input type="file" name="methodology_icon" class="smart-file" id="methodology_icon" data-field-type="bootstrap-file-filed" data-preview="on">
                           </div>
                           <div id="image" ></div>
                        </div>
                    </div>									

                    <div class="form-group">
                        <label for="member_name" class="col-sm-4 control-label">Methodology Title</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="methodology_title" id="methodology_title"/>
                        </div>
                    </div>	

                    <div class="form-group">
                        <label for="member_designation" class="col-sm-4 control-label">Methodology Details</label>
                        <div class="col-sm-8">
                            <textarea name="methodology_details" id="methodology_details" class="form-control" ></textarea>
                        </div>
                    </div>						
                </div>
                <div id="progress-wrp-methodology-item" class="progress-wrp" style="display: none;"><div class="progress-bar" style="width: 100%;"></div><div class="status">100%</div></div>
                <center><input type="submit" value="UPDATE" name="" class="btn btn-default update" ></center>
                
            </form>
        </div>
    </div>
</div>