<form class="form-horizontal" action="" method="post" id="social_form">
       <div class="response"><div id="social_message"></div></div>
        <div class="repeat" style="border:0;">
            <div class="wrapper">
                <div class="form-row">
                    <label class="col-sm-2">Social Image</label>
                    <label class="col-sm-5">Social Name</label>
                    <label class="col-sm-5">Social Url</label>
                </div>	

                <div class="form-container">
                    <div class="form-row">
                        <div class="col-sm-2">
                            <div class="file-wrapper">
                               upload
                               <input type="file" name="social_image" class="smart-file" data-field-type="bootstrap-file-filed" data-preview="on">
                           </div>
                        </div>

                        <div class="col-sm-5">
                            <input type="text" class="form-control" name="social_name" placeholder="facebook"/>
                        </div>

                        <div class="col-sm-5">
                            <input type="text" class="form-control" name="social_url" placeholder="http://www.facebook.com/audacityit"/>
                        </div>
                    </div>			
                </div>

            </div>
        </div>
        <input type="hidden" value="SAVE" name="submit" class="btn btn-default ">
</form>	
<div id="progress-wrp-social" class="progress-wrp" style="display: none;"><div class="progress-bar" style="width: 100%;"></div><div class="status">100%</div></div>