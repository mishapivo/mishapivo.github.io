<!--  Overview Content Modal	-->
<div class="modal fade" id="update_splash_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div id="splash_response" style="float:left;"></div>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span>
                </button>
            </div>
                <form class="form-horizontal" action="" method="post" id="splash_update_form">
                    <input type="hidden" name="splash_id" id="splash_id" value="">
                    <div class='form-top'>
                        <div class="form-group">
                            <label for="company-logo" class="col-sm-6 control-label">Upload company  logo</label>
                            <div class="col-sm-6">
                                <div class="file-wrapper">
                                   upload
                                   <input type="file" name="company_logo" id="company-logo" class="smart-file" data-field-type="bootstrap-file-filed" data-preview="on">
                               </div>
                               <div id="image"></div>
                            </div>
                        </div>									

                        <div class="form-group">
                            <label for="company-logo" class="col-sm-6 control-label">Set splash background</label>
                            <div class="col-sm-6">
                                <div id="cp3" class="input-group colorpicker-component">
                                    <input type="text" id="splash_color" class="form-control" value="#300" name="splash_color"  >
                                    <span class="input-group-addon"><i></i></span>
                                </div>     
                            </div>

                        </div>	
                    
                        </div>	
                        <br>
        <div id="progress-wrp-splash" class="progress-wrp" style="display:none;"><div class="progress-bar"></div><div class="status">0%</div></div>
        
			<center><input type="submit" value="SAVE" name="update" class="btn btn-default update"></center>							
                </form>
        </div>
    </div>
</div>	