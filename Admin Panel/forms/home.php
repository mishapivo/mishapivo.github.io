<form class="form-horizontal" action="post" id="homepage">
    <div class="response"><div id="slidemessage"></div></div>
    
    <div class="form-top">
        <div class="form-group">
            <label for="project-image" class="col-sm-3 control-label">Upload Project Image</label>
            <div class="col-sm-9">
                <div class="file-wrapper">
                   upload
                   <input type="file" NAME="project_image" id="project-image" class="smart-file" data-field-type="bootstrap-file-filed" data-preview="on">
               </div>
            </div>
        </div>									

        <div class="form-group">
            <label for="projec-title" class="col-sm-3 control-label">Enter Project Title</label>
            <div class="col-sm-9">
                <input type="text" name="project_title" class="form-control" id="projec-title" placeholder="Colours FM 101.6" >
            </div>
        </div>								
    </div>

        <input type="hidden" value="submit" name="submit">

</form>


<div id="progress-wrp-home" class="progress-wrp" style="display:none;"><div class="progress-bar"></div ><div class="status">0%</div></div>