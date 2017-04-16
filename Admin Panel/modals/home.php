<div class="modal fade" id="update_slider_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
           
     <div class="modal-header">
       <div id="response" style="float:left;"></div>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>

            <form class="form-horizontal" action="" method="post" id="slider_update_form">
               <input type="hidden" name="slide_id" class="form-control" id="slide_id">
                <div class="form-top">
                    <div class="form-group">
                        <label for="project-image" class="col-sm-3 control-label">Upload Project Image</label>
                        <div class="col-sm-9">
                            <div class="file-wrapper">
                               upload
                               <input type="file" NAME="project_image" id="project-image" class="smart-file" data-field-type="bootstrap-file-filed" data-preview="on">
                           </div>
                           <div id="image" style="height: 100px; width: 100px;"></div>
                        </div>
                    </div>					

                    <div class="form-group">
                        <label for="projec-title" class="col-sm-3 control-label">Enter Project Title</label>
                        <div class="col-sm-9">
                            <input type="text" name="project_title" class="form-control" id="projec-title">
                        </div>
                    </div>								
                </div>
                
                <div id="progress-wrp-home" class="progress-wrp" style="display:none;"><div class="progress-bar"></div ><div class="status">0%</div></div>
                <center><input type="submit" value="UPDATE" name="" class="btn btn-default update" ></center>
                
            </form>
            
        </div>
    </div>
</div>