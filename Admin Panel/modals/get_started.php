<div class="modal fade" id="update_getstarted_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
           
     <div class="modal-header">
		   <div id="get_started_response" style="float:left;"></div>
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		  </div>

		<form class="form-horizontal" action="" method="post" id="update_getstarted_form">
		    <input type="hidden" id="get_started_id" name="get_started_id">
			<div class="form-top">
				<div class="col-sm-4">
					<label for="range-one" class="control-label">1st Amount Range</label>
					<input type="text" id="range-one" class="form-control" name="range_one" />
				</div>

				<div class="col-sm-4">
					<label for="range-two" class="control-label">2nd Amount Range</label>
					<input type="text" id="range-two" class="form-control" name="range_two" />
				</div>

				<div class="col-sm-4">
					<label for="range-three" class="control-label">3rd Amount Range</label>
					<input type="text" id="range-three" class="form-control" name="range_three" />
				</div>


				<div class="col-sm-4">
					<label for="range-four" class="control-label">4th Amount Range</label>
					<input type="text" id="range-four" class="form-control" name="range_four" />
				</div>

				<div class="col-sm-4">
					<label for="contact-number" class="control-label">Contact Number</label>
					<input type="text" id="contact-number" class="form-control" name="contact_number" placeholder="01234556" />
				</div>

				<div class="col-sm-4">
					<label for="contact-mail" class="control-label">Address</label>
					<input type="email" id="contact-mail" class="form-control" name="contact_mail" placeholder="test@gmail.com" />
				</div>

				<div class="col-sm-6">
					<label for="web-address" class="control-label">Web Address</label>
					<input type="text" id="web-address" class="form-control" name="web_address" placeholder="example.com" />
				</div>
			</div>

			<div class="repeat">
				<div class="wrapper">
					<div class="add-more">
						<span class="add">Add platform +</span>
					</div>

					<div class="form-container" id="getstarted_repeatable_item">
						<div class="template form-row client_repeat" style="display: none;">
							<div class="col-sm-4">
								<input type="text" class="form-control" name="platform[]" />
							</div>

							<div class="col-sm-1">
								<span class="remove"></span>
							</div>
						</div>


					</div>


				</div>
			</div>
			<center><input type="submit" value="SAVE" name="update" class="btn btn-default update"></center>
			
		</form>
        </div>
    </div>
</div>