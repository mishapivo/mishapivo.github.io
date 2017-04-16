<form class="form-horizontal" action="" method="post" id="getstarted_form">
   <div class="response"><div id="get_started_message"></div></div>
    <div class="form-top">
        <div class="col-sm-3">
            <label for="range-one" class="control-label">Enter 1st Amount Range</label>
            <input type="text" id="range-one" class="form-control" name="range_one" placeholder="$1000" />
        </div>

        <div class="col-sm-3">
            <label for="range-two" class="control-label">Enter 2nd Amount Range</label>
            <input type="text" id="range-two" class="form-control" name="range_two" placeholder="$2000"/>
        </div>

        <div class="col-sm-3">
            <label for="range-three" class="control-label">Enter 3rd Amount Range</label>
            <input type="text" id="range-three" class="form-control" name="range_three" placeholder="$3000"/>
        </div>								


        <div class="col-sm-3">
            <label for="range-four" class="control-label">Enter 4th Amount Range</label>
            <input type="text" id="range-four" class="form-control" name="range_four" placeholder="$4000"/>
        </div>	

        <div class="col-sm-3">
            <label for="contact-number" class="control-label">Enter Contact Number</label>
            <input type="text" id="contact-number" class="form-control" name="contact_number" placeholder="01234556" />
        </div>

        <div class="col-sm-3">
            <label for="contact-mail" class="control-label">Enter Mail Address</label>
            <input type="email" id="contact-mail" class="form-control" name="contact_mail" placeholder="test@gmail.com" />
        </div>

        <div class="col-sm-6">
            <label for="web-address" class="control-label">Enter Web Address</label>
            <input type="text" id="web-address" class="form-control" name="web_address" placeholder="example.com" />
        </div>								
    </div>

        <div class="repeat">
            <div class="wrapper">
                <div class="add-more">
                    <span class="add">Add platform +</span>
                </div>

                <div class="form-container">
                    <div class="template form-row" style="display: none;">
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="platform[]" placeholder="android"/>
                        </div>

                        <div class="col-sm-1">
                            <span class="remove"></span>
                        </div>
                    </div>	

                    <div class="form-row">
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="platform[]" placeholder="android"/>
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