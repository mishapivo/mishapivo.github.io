<div class="modal fade" id="update_companyinfo_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
           
     <div class="modal-header">
       <div id="overview_item_response" style="float:left;"></div>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>

            <form class="form-horizontal" action="" method="post" id="company_update_form">
               <input type="hidden" name="company_info_id" class="form-control" id="company_info_id">
                <div class="form-top">
                    <div class="form-group">
                        <label for="project-image" class="col-sm-4 control-label">Overview Image</label>
                        <div class="col-sm-8">
                            <div class="file-wrapper">
                               upload
                               <input type="file" name="overview_icon" class="smart-file" id="overview_icon" data-field-type="bootstrap-file-filed" data-preview="on">
                           </div>
                           <div id="image" style="height: 100px; width: 100px;"></div>
                        </div>
                    </div>									

                    <div class="form-group">
                        <label for="projec-title" class="col-sm-4 control-label">Overview Title</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="overview_title" id="overview_title"/>
                        </div>
                    </div>	
                    
                    <div class="form-group">
                        <label for="projec-title" class="col-sm-4 control-label">Overview Details</label>
                        <div class="col-sm-8">
                            <textarea name="overview_details" class="form-control" id="overview_details" cols="30" rows="5"></textarea>
                        </div>
                    </div>
                    														
                </div>
                
                <div id="progress-wrp-overview-item" class="progress-wrp" style="display: none;"><div class="progress-bar"></div ><div class="status">0%</div></div>
                
                <center><input type="submit" value="UPDATE" name="" class="btn btn-default update" ></center>
                
            </form>
        </div>
    </div>
</div>
	
	
	
<!--  Overview Content Modal	-->
<div class="modal fade" id="update_overviewcontent_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div id="overview_content_response" style="float:left;"></div>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span>
                </button>
            </div>
            <form class="form-horizontal" action="" method="post" id="update_overviewcontent_form">
			<input type="hidden" name="overview_content_id" class="form-control" id="overview_content_id">
                <div class="form-top">
                    <div class="form-group">
                        <label for="overview-bg" class="col-sm-4 control-label">background Image</label>
                        <div class="col-sm-8">
                            <div class="file-wrapper">
                                upload
                                <input type="file" name="overview_bg" id="overview-bg" class="smart-file" data-field-type="bootstrap-file-filed" data-preview="on">
                            </div>
                            <div id="image" style="height: 100px; width: 100px;"></div>
                        </div>
                    </div>
                    
                    
                    <div class="form-group">
                        <label for="overview-bg" class="col-sm-4 control-label">Upload logo</label>
                        <div class="col-sm-8">
                            <div class="file-wrapper">
                               upload
                               <input type="file" name="overview_logo" id="overview-logo" class="smart-file" data-field-type="bootstrap-file-filed" data-preview="on">
                           </div>
                           <div id="logo" style="height: 100px; width: 100px;"></div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="title-text" class="col-sm-4 control-label">Title text</label>
                        <div class="col-sm-8">
                            <input type="text" name="title" id="title-text" placeholder="Audacity IT Solutions Ltd." class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="promo-text" class="col-sm-4 control-label">Moto text</label>
                        <div class="col-sm-8">
                            <input type="text" name="moto" id="promo-text" placeholder="Best mobile apps design and development Company" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-4">
                            <label for="">First Item</label>
                            <input type="text" class="form-control" name="count-1-info" id="count_1_info" placeholder="Count Text" />
                            <input type="text" class="form-control" name="count-1-number" id="count_1_number" placeholder="Count number" />
                        </div>
                        <div class="col-sm-4">
                            <label for="">Second Item</label>
                            <input type="text" class="form-control" name="count-2-info" id="count_2_info" placeholder="Count Text" />
                            <input type="text" class="form-control" name="count-2-number" id="count_2_number" placeholder="Count number" />
                        </div>
                        <div class="col-sm-4">
                            <label for="">Third Item</label>
                            <input type="text" class="form-control" name="count-3-info" id="count_3_info" placeholder="Count Text" />
                            <input type="text" class="form-control" name="count-3-number" id="count_3_number" placeholder="Count number" />
                        </div>
                    </div>
                </div>
                
                                <div id="progress-wrp-overview-content" class="progress-wrp" style=
"display: none;"><div class="progress-bar"></div ><div class="status">0%</div></div>
                <center><input type="submit" value="UPDATE" name="" class="btn btn-default update" ></center>

            </form>
        </div>
    </div>
</div>	