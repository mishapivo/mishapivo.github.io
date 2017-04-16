<div class="modal fade" id="update_social_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">

         <div class="modal-header">
           <div id="social_response" style="float:left;"></div>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          </div>

                <form class="form-horizontal" action="" method="post" id="social_update_form">
                   <input type="hidden" name="social_id" class="form-control" id="social_id">
                    <div class="form-top">
                        <div class="form-group">
                            <label for="methodology_icon" class="col-sm-4 control-label">Social Icon</label>
                            <div class="col-sm-8">
                                <div class="file-wrapper">
                                   upload
                                   <input type="file" name="social_image" id="social_image" class="smart-file" data-field-type="bootstrap-file-filed" data-preview="on">
                               </div>
                               <div id="image" style="height: 100px; width: 100px;"></div>
                            </div>
                        </div>									

                        <div class="form-group">
                            <label for="member_name" class="col-sm-4 control-label">Social Name</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="social_name" id="social_name" placeholder="facebook"/>
                            </div>
                        </div>	

                        <div class="form-group">
                            <label for="member_designation" class="col-sm-4 control-label">Social Url</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="social_url" id="social_url" placeholder="http://www.facebook.com/audacityit"/>
                            </div>
                        </div>						
                    </div>
                    <center><input type="submit" value="UPDATE" name="" class="btn btn-default update" ></center>

                </form>
            </div>
        </div>
    </div>	