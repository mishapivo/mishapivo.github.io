<!-- Modal - Update Client details -->
<div class="modal fade" id="update_portfolio_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div id="portfolio_response" style="float:left;"></div>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
            </div>
            <form class="form-horizontal" action="" method="post" id="portfolio_update_form">
                <div class="form-top">
                    <div class="col-sm-12">
                        <div class="form-group">
                            <input type="hidden" id="portfolio_id" name="portfolio_id">
                            <label for="client-logo" class="col-sm-6 control-label">Portfolio Image</label>
                            <div class="col-sm-6">
                                <div class="file-wrapper">
                                    upload
                                    <input type="file" name="portfolio_image" id="portfolio_project_image" class="smart-file" data-field-type="bootstrap-file-filed" data-preview="on">

                                </div>
                                <div id="image" style="height:100px; width:100px;"></div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="project-name" class="col-sm-6 control-label">Project Title</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="portfolio_title" id="portfolio_projec_title">
                            </div>
                        </div>
                    </div>

                </div>

                <div class="repeat">
                    <div class="wrapper">
                        <div class="add-more">
                            <span class="add">Add tag+</span>

                            <div class="form-row">
                                <label class="col-sm-5">Enter Project Platform</label>
                                <label class="col-sm-7">Enter Platform URL</label>
                            </div>
                        </div>
                        <div class="form-container" id="portfolio_repeatable_item">
                            <div class="template form-row client_repeat" style="display: none;">
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="project_platform[]" id="" disabled="">
                                </div>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" name="project_url[]" disabled="">
                                </div>
                                <div class="col-sm-1">
                                    <span class="remove"></span>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div id="progress-wrp-portfolio" class="progress-wrp" style="display: none;"><div class="progress-bar" style="width: 100%;"></div><div class="status">100%</div></div>
                    <center><input type="submit" value="SAVE" name="update" class="btn btn-default update"></center>
                </div>
            </form>
        </div>
    </div>
</div>