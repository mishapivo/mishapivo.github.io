<div class="modal fade" id="update_testimonial_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
           
     <div class="modal-header">
       <div id="testimonial_response" style="float:left;"></div>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>

            <form class="form-horizontal" action="" method="post" id="testimonial_update_form">
               <input type="hidden" name="testimonial_id" class="form-control" id="testimonial_id">
                <div class="form-top">
                    <div class="form-group">
                        <label for="project-image" class="col-sm-4 control-label">Overview Image</label>
                        <div class="col-sm-8">
                            <div class="file-wrapper">
                               upload
                               <input type="file" id="client-image" name="client_image" class="smart-file" data-field-type="bootstrap-file-filed" data-preview="on"/>
                           </div>
                           <div id="image" style="height: 100px; width: 100px;"></div>
                        </div>
                    </div>									

                    <div class="form-group">
                        <label for="projec-title" class="col-sm-4 control-label">Client Name</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="client_name" id="testimonial_client_name" />
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="projec-title" class="col-sm-4 control-label">Project Name</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="project_name" id="testimonialproject_name" />
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="projec-title" class="col-sm-4 control-label">Project Url</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="project_url" id="project-url" />	
                        </div>
                    </div>                    
                    
                    <div class="form-group">
                        <label for="projec-title" class="col-sm-4 control-label">Project Feedback</label>
                        <div class="col-sm-8">
                            <textarea name="client_feedback" id="client-feedback" class="form-control"></textarea>
                        </div>
                    </div>
                    														
                </div>
                
                <div id="progress-wrp-testimonail" class="progress-wrp" style="display: none;"><div class="progress-bar" style="width: 100%;"></div><div class="status">100%</div></div>
                <center><input type="submit" value="UPDATE" name="" class="btn btn-default update" ></center>
                
            </form>
        </div>
    </div>
</div>