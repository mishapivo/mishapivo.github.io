<form class="form-horizontal" action="" method="post" id="splash_form">
   <div class="response"><div id="splashresponse"></div></div>

    
    <div class="col-sm-5">
        <div class="form-group">
            <label for="company-logo" class="col-sm-6 control-label">Upload company  logo</label>
            <div class="col-sm-6">
                <div class="file-wrapper">
                   upload
                   <input type="file" name="company_logo" id="company-logo" class="smart-file" data-field-type="bootstrap-file-filed" data-preview="on">
               </div>
            </div>
        </div>									

        <div class="form-group">
            <label for="company-logo" class="col-sm-6 control-label">Set splash background</label>
            <div class="col-sm-6">
                <div id="cp3" class="input-group colorpicker-component">
                    <input type="text" id="splash_color" class="form-control" value="#000000" name="splash_color"  >
                    <span class="input-group-addon"><i></i></span>
                </div>     
            </div>

            <input type="hidden" value="SAVE" name="submit" class="btn btn-default ">
        </div>									
    </div>
</form>
<div id="progress-wrp-splash" class="progress-wrp" style="display:none;"><div class="progress-bar"></div ><div class="status">0%</div></div>