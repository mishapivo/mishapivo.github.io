<form class="form-horizontal" action="" method="post" id="portfolio_form">
    <div class="response"><div id="portfolio_message"></div></div>
    <div class="form-top">
        <div class="form-group">
            <label for="project-image" class="col-sm-3 control-label">Upload Project Image</label>
            <div class="col-sm-9">
                <div class="file-wrapper">
                   upload
                   <input type="file" name="portfolio_image" id="project-image" class="smart-file" data-field-type="bootstrap-file-filed" data-preview="on">
               </div>
            </div>
        </div>									

        <div class="form-group">
            <label for="projec-title" class="col-sm-3 control-label">Enter Project Title</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" name="portfolio_title" id="projec-title" placeholder="How I Work" >
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
                            <input type="text" class="form-control" name="project_platform[]" placeholder="iOS"/>
                        </div>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="project_url[]" placeholder="http://www.example.com" />
                        </div>
                        <div class="col-sm-1">
                            <span class="remove"></span>
                        </div>
                    </div>		

                    <div class="form-row">
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="project_platform[]" placeholder="iOS"/>
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
                            <input type="text" class="form-control" name="project_platform[1]" placeholder="iOS"/>
                        </div>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="project_url[1]" placeholder="http://www.example.com" />
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
<div id="progress-wrp-portfolio" class="progress-wrp" style="display: none;"><div class="progress-bar" style="width: 100%;"></div><div class="status">100%</div></div>