<form class="form-horizontal" action="" method="post" id="client_form">
    <div class="response"><div id="client_message"></div></div>
    <div class="form-top">
        <div class="col-sm-6">
            <div class="form-group">
                <label for="client-logo" class="col-sm-6 control-label">Upload Client's logo</label>
                <div class="col-sm-6">
                    <div class="file-wrapper">
                       upload
                       <input type="file" name="client_logo" id="client-logo" class="smart-file" data-field-type="bootstrap-file-filed" data-preview="on">
                   </div>
                </div>
            </div>									

            <div class="form-group">
                <label for="project-name" class="col-sm-6 control-label">Enter Project's Name</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" name="project_name" id="project-name" placeholder="Monasa Learning">
                </div>
            </div>									
        </div>

        <div class="col-sm-6">
            <div class="form-group">
                <label for="client-name" class="col-sm-6 control-label">Upload Client's Name</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" name="client_name" id="client-name" placeholder="Samad Miraly">
                </div>
            </div>									

            <div class="form-group">
                <label for="client-country" class="col-sm-6 control-label">Enter Client's Country</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" name="client_country" id="client-country" placeholder="Bangladesh">
                </div>
            </div>									
        </div>
    </div>									

        <div class="repeat">
            <div class="wrapper">
                <div class="add-more">
                    <span class="add">Add tag+</span>

                    <div class="form-row">
                        <label class="col-sm-3">Enter Project Platform</label>
                        <label class="col-sm-9">Enter Platform URL</label>
                    </div>
                </div>
                <div class="form-container">
                    <div class="template form-row" style="display: none;">
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="project_platform[]" placeholder="web"/>
                        </div>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="project_url[]" placeholder="http://www.example.com"/>
                        </div>
                        <div class="col-sm-1">
                            <span class="remove"></span>
                        </div>
                    </div>		

                    <div class="form-row">
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="project_platform[]" placeholder="web"/>
                        </div>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="project_url[]" placeholder="http://www.example.com"/>
                        </div>
                        <div class="col-sm-1">
                            <span class="remove"></span>
                        </div>
                    </div>			
                </div>

            </div>
        </div>
        <input type="hidden" value="SAVE" name="submit" class="btn btn-default ">

</form>
<div id="progress-wrp-client" class="progress-wrp" style="display: none;"><div class="progress-bar" style="width: 100%;"></div><div class="status">100%</div></div>