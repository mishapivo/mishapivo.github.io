<form class="form-horizontal" action="" method="post" id="faqform">
       <div class="response"><div id="faq_message"></div></div>
        <div class="repeat" style="border:0px;">
            <div class="wrapper">
                <div class="add-more">
                    <span class="add">Add faq +</span>

                    <div class="form-row">
                        <label class="col-sm-5">Enter Your Question</label>
                        <label class="col-sm-7">Enter Answer</label>
                    </div>											
                </div>


                <div class="form-container">
                    <div class="template form-row append_faq" style="display: none;">
                        <div class="col-sm-5">
                            <textarea name="question[]" id="" class="form-control" cols="30" rows="4"></textarea>
                        </div>
                        <div class="col-sm-6">
                            <textarea name="ans[]" id="" class="form-control" cols="30" rows="4"></textarea>
                        </div>
                        <div class="col-sm-1">
                            <span class="remove"></span>
                        </div>
                    </div>	

                    <div class="form-row">
                        <div class="col-sm-5">
                            <textarea name="question[]" id="" class="form-control" cols="30" rows="4"></textarea>
                        </div>
                        <div class="col-sm-6">
                            <textarea name="ans[]" id="" class="form-control" cols="30" rows="4"></textarea>
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