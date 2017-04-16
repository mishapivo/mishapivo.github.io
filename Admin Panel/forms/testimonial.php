<form class="form-horizontal" action="" method="post" id="testimonial_form">
    <div class="response"><div id="testimonial_message"></div></div>
    <div class="form-group">
        <label for="client-logo" class="col-sm-3 control-label">Upload Client's Image</label>
        <div class="col-sm-9">
            <div class="file-wrapper">
                upload
                <input type="file" id="client-image" name="client_image" class="smart-file" data-field-type="bootstrap-file-filed" data-preview="on"/>
            </div>
        </div>
    </div>									

    <div class="col-sm-3">
        <label for="client-name" class="control-label">Enter Client's Name</label>
        <input type="text" class="form-control" name="client_name" id="client-name" placeholder="John Doe"/>
    </div>		

    <div class="col-sm-3">
        <label for="project-name" class="control-label">Enter Project's Name</label>
        <input type="text" class="form-control" name="project_name" id="project-name" placeholder="LoremIpsum" />
    </div>								

    <div class="col-sm-6">
        <label for="project-url" class="control-label">Enter Project's URL</label>
        <input type="text" class="form-control" name="project_url" id="project-url" placeholder="http://www.exampleproject.com" />								
    </div>

    <div class="form-row" style="clear: both;">
        <div class="col-sm-12">
            <label for="client-feedback" class="control-label">Enter Client's Feedback</label>
            <textarea name="client_feedback" id="client-feedback" class="form-control" placeholder="Just awesome experience with team!!"></textarea>
        </div>
    </div>
    <input type="hidden" value="SAVE" name="submit" class="btn btn-default ">
</form>	
<div id="progress-wrp-testimonail" class="progress-wrp" style="display: none;"><div class="progress-bar" style="width: 100%;"></div><div class="status">100%</div></div>