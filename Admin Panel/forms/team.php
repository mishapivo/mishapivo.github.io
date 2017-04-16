<form class="form-horizontal" action="" method="post" id="teamform">
       <div class="response"><div id="team_message"></div></div>
        <div class="repeat" style="border:0;">
            <div class="wrapper">
                <div class="form-row">
                    <label class="col-sm-2">Profile Image</label>
                    <label class="col-sm-5">Enter Name</label>
                    <label class="col-sm-5">Enter Designation</label>
                </div>	

                <div class="form-container">
                    <div class="form-row">
                        <div class="col-sm-2">
                            <div class="file-wrapper">
                               upload
                               <input type="file" name="member_image" class="smart-file" data-field-type="bootstrap-file-filed" data-preview="on">
                           </div>
                        </div>

                        <div class="col-sm-5">
                            <input type="text" class="form-control" name="member_name" placeholder="John doe"/>
                        </div>

                        <div class="col-sm-5">
                            <input type="text" class="form-control" name="member_designation" placeholder="Designer"/>
                        </div>
                    </div>			
                </div>

            </div>
        </div>
        <input type="hidden" value="SAVE" name="submit" class="btn btn-default ">
</form>
<div id="progress-wrp-team" class="progress-wrp" style="display: none;"><div class="progress-bar" style="width: 100%;"></div><div class="status">100%</div></div>