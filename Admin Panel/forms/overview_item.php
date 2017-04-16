<form class="form-horizontal" action="post" id="companyinfo">
   <div class="response"><div id="company_info_message"></div></div>
    <div class="form-container">
        <div class="form-row">
            <div class="col-sm-2">
               <label for="">Overview Image</label>
                <div class="file-wrapper">
                   upload
                   <input type="file" name="overview_icon" class="smart-file" data-field-type="bootstrap-file-filed" data-preview="on">
               </div>
            </div>

            <div class="col-sm-3">
                <label for="">Overview Title</label>
                <input type="text" class="form-control" name="overview_title" placeholder="Skill"/>
            </div>

            <div class="col-sm-7">
                <label for="">Overview Details</label>
<!--                <input type="text" class="form-control" name="overview_details" Placeholder="Android App, iOS App..." />-->
                <textarea name="overview_details" id="overview_details" class="form-control" cols="30" rows="3"></textarea>
            </div>
        </div>			
    </div>

    <input type="hidden" value="send" name="submit">
</form>
<div id="progress-wrp-overview-item" class="progress-wrp" style="display: none;"><div class="progress-bar"></div ><div class="status">0%</div></div>