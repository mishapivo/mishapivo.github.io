<form class="form-horizontal" action="" method="post" id="overviewform">
   <div class="response"><div id="overviewmessage"></div></div>
    <div class="form-top">
        <div class="form-group">
            <label for="overview-bg" class="col-sm-3 control-label">Upload background Image</label>
            <div class="col-sm-8">
                <div class="file-wrapper">
                   upload
                   <input type="file" name="overview_bg" id="overview-bg" class="smart-file" data-field-type="bootstrap-file-filed" data-preview="on">
               </div>
            </div>
        </div>
        
        <div class="form-group">
            <label for="overview-bg" class="col-sm-3 control-label">Upload logo</label>
            <div class="col-sm-8">
                <div class="file-wrapper">
                   upload
                   <input type="file" name="overview_logo" id="overview-logo" class="smart-file" data-field-type="bootstrap-file-filed" data-preview="on">
               </div>
            </div>
        </div>

        <div class="form-group">
            <label for="title-text" class="col-sm-3 control-label">Title text</label>
            <div class="col-sm-8">
                <input type="text" name="title" id="title-text" placeholder="Audacity IT Solutions Ltd." class="form-control">
            </div>
        </div>	

        <div class="form-group">
            <label for="promo-text" class="col-sm-3 control-label">Moto text</label>
            <div class="col-sm-8">
                <input type="text" name="moto" id="promo-text" placeholder="Best mobile apps design and development Company" class="form-control">
            </div>
        </div>							

        <div class="form-group">
            <div class="col-sm-2">
                <label for="">First Item</label>
                <input type="text" class="form-control" name="count-1-info" placeholder="Count Text"/>
                <input type="text" class="form-control" name="count-1-number" placeholder="Count number"/>
            </div>
            <div class="col-sm-2">
                <label for="">Second Item</label>
                <input type="text" class="form-control" name="count-2-info" placeholder="Count Text"/>
                <input type="text" class="form-control" name="count-2-number" placeholder="Count number"/>
            </div>
            <div class="col-sm-2">
                <label for="">Third Item</label>
                <input type="text" class="form-control" name="count-3-info" placeholder="Count Text"/>
                <input type="text" class="form-control" name="count-3-number" placeholder="Count number"/>
            </div>										
        </div>	
    </div>
         <input type="hidden" value="send" name="submit">

</form>


<div id="progress-wrp-overview-content" class="progress-wrp" style=
"display: none;"><div class="progress-bar"></div ><div class="status">0%</div></div>