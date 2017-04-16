<!-- Modal - Update Client details -->
<div class="modal fade" id="update_client_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div id="client_response" style="float:left;"></div>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
            </div>
            <form class="form-horizontal" action="" method="post" id="client_update_form">
                <div class="form-top">
                    <div class="col-sm-12">
                        <div class="form-group">
                            <input type="hidden" id="client_id" name="client_id">
                            <label for="client-logo" class="col-sm-6 control-label">Upload Client's logo</label>
                            <div class="col-sm-6">
                                <div class="file-wrapper">
                                    upload
                                    <input type="file" name="client_logo" id="client-logo" class="smart-file" data-field-type="bootstrap-file-filed" data-preview="on">

                                </div>
                                <div id="image" style="height:100px; width:100px;"></div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="project-name" class="col-sm-6 control-label">Enter Project's Name</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="project_name" id="project-name">
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-12">
                        <div class="form-group">
                            <label for="client-name" class="col-sm-6 control-label">Upload Client's Name</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="client_name" id="client-name">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="client-country" class="col-sm-6 control-label">Enter Client's Country</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="client_country" id="client-country">
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
                        <div class="form-container" id="repeatable_item">
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
                    <div id="progress-wrp-client" class="progress-wrp" style="display: none;"><div class="progress-bar" style="width: 100%;"></div><div class="status">100%</div></div>
                    <center><input type="submit" value="SAVE" name="update" class="btn btn-default update"></center>
                </div>
            </form>
        </div>
    </div>
</div>