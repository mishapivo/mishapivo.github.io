<form class="form-horizontal" action="" method="post" id="methodology_item_form">
    <div class="response"><div id="methodology_item_message"></div></div>
    <div class="form-container">
        <div class="form-row">
            <div class="col-sm-2">
                <label for="">Methodology Image</label>
                <div class="file-wrapper">
                   upload
                   <input type="file" name="methodology_icon" class="smart-file" data-field-type="bootstrap-file-filed" data-preview="on">
               </div>
            </div>

            <div class="col-sm-3">
               <label for="">Methodology Title</label>
                <input type="text" class="form-control" name="methodology_title" placeholder="Weekly Deliverables" />
            </div>

            <div class="col-sm-7">
               <label for="">Methodology Details</label>
                <input type="text" class="form-control" name="methodology_details" placeholder="The products is in your handes every week." />
            </div>
        </div>			
    </div>
    <input type="hidden" value="SAVE" name="submit" class="btn btn-default ">
</form>
<div id="progress-wrp-methodology-item" class="progress-wrp" style="display: none;"><div class="progress-bar" style="width: 100%;"></div><div class="status">100%</div></div>