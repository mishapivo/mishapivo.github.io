$(function() {
     var pgurl = window.location.href.substr(window.location.href
.lastIndexOf("/")+1);
     $("ul.side-menu li a").each(function(){
          if($(this).attr("href") == pgurl || $(this).attr("href") == '' )
          $(this).addClass("active");
     })
});

//$(window).resize(function(){location.reload();});


  function setHeight() {
    windowHeight = $(window).innerHeight();
    $('body').css('height', windowHeight);
  };
  setHeight();
  
  $(window).resize(function() {
    setHeight();
  });



  var totalHeight = $('body').height();


$('#header').height();
var headerHeight = $('#header').height();


var footerHeight = $('.footer').height();


var mainSectionHeight = totalHeight - (headerHeight + footerHeight);

var space = $('.space').height();

var contentHeight = mainSectionHeight;

var contentWrapper = mainSectionHeight - space;

var contentHeaderHeight = $('#content-header').height();

var contentFooterHeight = $('#content-footer').height();

var dataAreaHeight = contentWrapper - (contentHeaderHeight + contentFooterHeight);


$(".main,.side-menu").css({
    height: mainSectionHeight + 'px'
});


$(".content-wrapper").css({
    height: contentWrapper + 'px'
});


$(".content").css({
    height: contentHeight + 'px'
});

$(".data-area").css({
    height: dataAreaHeight + 'px'
});







/*inside-form*/
var formTop = $('.form-top').height();

var addMore = $('.add-more').height();



var repeatedArea = dataAreaHeight - (formTop + addMore + 40 );

//alert(addMore);




//$('.side-menu a').click(function (e) {
 // e.preventDefault()
 // $(this).tab('show')
//})


// click method to go splash page 
$("#gosplash").click(function(){
    $('#sidebardata').trigger('click');
});



// when click home menu will, request to again load data
$("#homedata").click(function(){
    showSlider();
});

// when click overview menu, will request to again load overview data
$("#overviewdata").click(function(){
    showOverviewContent();
    showCompanyInfo();
});

// when click portfolio menu, will request to again load portfolio data
$("#portfoliodata").click(function(){
    showPortolio();
});

// when click team menu, will request to again load team data
$("#teamdata").click(function(){
    showTeam();
});


// when click client menu, will request to again load client data
$("#clientdata").click(function(){
    showClient();
});

// when click testimonial menu, will request to again load testimonial data 
$("#testimonialdata").click(function(){
    showTestimonial();
});


// when click methodology menu, will request to again load methodology data 
$("#methodologydata").click(function(){
    showMethodologyContent();
    showMethodologyItem();
});


// when click faq menu, will request to again load faq data 
$("#faqdata").click(function(){
    showFaq();
});

// when click Getstarted menu, will request to again load Getstarted data 
$("#getstarteddata").click(function(){
    showGetstarted();
});

// when click social menu, will request to again load Social data 
$("#socialdata").click(function(){
    showSocial();
});


/*
========================
repeatable field script
========================
*/
jQuery(function() {
    jQuery('.repeat').each(function() {
        jQuery(this).repeatable_fields();
    });
});	





/*
===========================
Sidebar 
===========================
*/

$(document).ready(function() {

$("#sidebarform").validate({
      rules:
	  {		
         sidebar_bg: {
         required: true
         },		
         sidebar_logo: {
         required: true
         },
         phone: {
         required: true
         },
         email: {
         required: true,
         
         },
         web: {
         required: true
         }          
				 

	   },
	   submitHandler: submitForm	
       });  
    
        function submitForm(){
            $.ajax({
                url: "ajax/sidebar.php",
                type: "POST",
                data: new FormData($('#sidebarform')[0]),
                contentType: false,
                processData: false,
                xhr: function(){
                    //upload Progress
                    var xhr = $.ajaxSettings.xhr();
                    if (xhr.upload) {
                        xhr.upload.addEventListener('progress', function(event) {
                            var percent = 0;
                            var position = event.loaded || event.position;
                            var total = event.total;
                            if (event.lengthComputable) {
                                percent = Math.ceil(position / total * 100);
                            }
                            
                            //update progressbar
                            $('#progress-wrp-sidebar').css("display", "block");
                            $(".progress-bar").css("width", + percent +"%");
                            $(" .status").text(percent +"%");
                            
                        }, true);
                    }
                    return xhr;
                },
                success: function(data){
                    
                    var data = data.trim();
                    
                    if(data == 'exist'){
                        $('#progress-wrp-sidebar').hide();
  
                        $("#myModal").modal('show');
                        $('#myModal .modal-body h3').html('You already saved Sidebar content');
                        $('#myModal .modal-body #error_menu').html('<button type="button" class="btn btn-default cancel_modal" data-dismiss="modal">Cancel</button><a href="display.php?sidebar" class="btn btn-default edit_content">Edit Sidebar</a>');
                    }else{
                        //alert(data);
                       $('#sidebarmessage').fadeIn().html(data);

                        setTimeout(function(){
                          $('#sidebarmessage').fadeOut('slow');
                        }, 1500); 
                    } 
                    
                    

                }
            });   
        }

$('#sidebar_submit').click(function() {
    
    $(".progress-bar").css("width", "0%");
    $(".status").text("0%"); 
    
    $('#sidebarform').submit();
});

    
}); 


/*
===================
Display Sidebar Data
===================
*/
function showSidebar(){
    $.ajax({
    url: "ajax/sidebar.php?show",
    type: "POST",
    success: function(data){
        //alert(data);
        $('#display_sidebar').html(data);
    }
    });    
}

showSidebar();






/*
=====================
Edit Overview Content
=====================
*/
function editSidebarContent(sidebar_id){
    var sidebar_id = sidebar_id;
    
    $.ajax({
    url: "ajax/sidebar.php?edit",
    type: "POST",
    data: {sidebar_id:sidebar_id},
    success: function(data){

       //alert(data);
        $("#update_sidebar_modal").modal("show");
    
       var jsonData = JSON.parse(data);
     
        $('#phone').val(jsonData.phone);
        $('#email').val(jsonData.email);
        $('#web').val(jsonData.web);

       
        $('#sidebar_id').val(jsonData.id);
////        
        var image = jsonData.background;
        var imagenew = image.substring(1);
        
        var logo =jsonData.logo;
        var logo =logo.substring(1);
        

        $('div#image').css('background-image', 'url('+imagenew+')');
        $('div#image').show();
        
        $('div#logo').css('background-image', 'url('+logo+')');
        $('div#logo').show();
        
    }
    });
    
	$("#sidebar-bg").click(function() { // bCheck is a input type button
        $('div#image').fadeOut('slow');
	});
	$("#sidebar-logo").click(function() { // bCheck is a input type button
        $('div#logo').fadeOut('slow');
	});
}








/*
=====================
Update Overview content
=====================
*/
$(document).ready(function() {

$("#update_sidebar_form").validate({
      rules:
   {  
         phone: {
         required: true
         },
         email: {
         required: true
         },
         web: {
         required: true
         } 
    },
    submitHandler: submitForm 
       });  
    
        function submitForm(){
            $.ajax({
                url: "ajax/sidebar.php?update",
                type: "POST",
                data: new FormData($('#update_sidebar_form')[0]),
                contentType: false,
                processData: false,
                xhr: function(){
                    //upload Progress
                    var xhr = $.ajaxSettings.xhr();
                    if (xhr.upload) {
                        xhr.upload.addEventListener('progress', function(event) {
                            var percent = 0;
                            var position = event.loaded || event.position;
                            var total = event.total;
                            if (event.lengthComputable) {
                                percent = Math.ceil(position / total * 100);
                            }
                            
                            //update progressbar
                            $('#progress-wrp-sidebar').css("display", "block");
                            $(".progress-bar").css("width", + percent +"%");
                            $(" .status").text(percent +"%");
                            
                        }, true);
                    }
                    return xhr;
                },                
                success: function(data){
                    var data = data.trim();

                    if( data == "success" ){
                        //$("#update_slider_modal").modal("hide");
                        
                        $('#sidebarmessage').fadeIn().html(data);

                        setTimeout(function(){
                          $('#sidebarmessage').fadeOut('slow');
                        }, 1000);
                        $('.close').trigger('click');
                        
                        $('#progress-wrp-sidebar').fadeOut('slow');
                        
                         showSidebar();
                        
                   }else{
                       $('#sidebarmessage').fadeIn().html(data);

                        setTimeout(function(){
                          $('#sidebarmessage').fadeOut('slow');
                        }, 1500);
                   }



                }
            });   
        }


    
});

$('#update_sidebar_modal').on('hidden.bs.modal', function (e) {
    $('#update_sidebar_form')[0].reset();
    $( ".thumbs li" ).hide();
});



















/*
==============================
FORM SUBMIT JQUERY SPLASH PAGE
==============================
*/

$(document).ready(function() {

$("#splash_form").validate({
      rules:
	  {		
         company_logo: {
         required: true
         },			
         splash_color: {
         required: true
         }
				 

	   },
	   submitHandler: submitForm	
       });  
    
        function submitForm(){
            $.ajax({
                url: "ajax/splash.php",
                type: "POST",
                data: new FormData($('#splash_form')[0]),
                contentType: false,
                processData: false,
                xhr: function(){
                    //upload Progress
                    var xhr = $.ajaxSettings.xhr();
                    if (xhr.upload) {
                        xhr.upload.addEventListener('progress', function(event) {
                            var percent = 0;
                            var position = event.loaded || event.position;
                            var total = event.total;
                            if (event.lengthComputable) {
                                percent = Math.ceil(position / total * 100);
                            }
                            
                            //update progressbar
                            $('#progress-wrp-splash').css("display", "block");
                            $(".progress-bar").css("width", + percent +"%");
                            $(" .status").text(percent +"%");
                            
                        }, true);
                    }
                    return xhr;
                },
                success: function(data){
                    var data = data.trim();
                    
                    if(data == 'exist'){
                        $('#progress-wrp-splash').hide();
  
                        $("#myModal").modal('show');
                        $('#myModal .modal-body h3').html('You already saved splash content');
                        $('#myModal .modal-body #error_menu').html('<button type="button" class="btn btn-default cancel_modal" data-dismiss="modal">Cancel</button>');
                        //<a href="display.php?splash" class="btn btn-default edit_content">Edit splash</a>
                    }else{
                       $('#splashresponse').fadeIn().html(data);

                        setTimeout(function(){
                          $('#splashresponse').fadeOut('slow');
                        }, 1500);  
                    }
                    
                },
                
            });   
        }

$('#splash_submit').click(function() {
    $(".progress-bar").css("width", "0%");
    $(".status").text("0%");
    
    $('#splash_form').submit();
});
    
});




/*
===================
Display Splash Data
===================
*/
function showSplash(){
    $.ajax({
    url: "ajax/splash.php?show",
    type: "POST",
    success: function(data){
        //alert(data);
        $('#display_splash').html(data);
    }
    });    
}

showSplash();








/*
=====================
Edit Splash
=====================
*/
function editSplash(splash_id){
    var splash_id = splash_id;
    
    $.ajax({
    url: "ajax/splash.php?edit",
    type: "POST",
    data: {splash_id:splash_id},
    success: function(data){

        //alert(data);
        $("#update_splash_modal").modal("show");
    
       var jsonData = JSON.parse(data);

        $('#splash_id').val(jsonData.id);
        $('#splash_color').val(jsonData.bgcolor);
        
        var image = jsonData.logo;
        var image1 = image.substring(1);
   
        $('div#image').css('background-image', 'url('+image1+')');
        $('div#image').show();
        

        
    }
    });
    
	$("#company-logo").click(function() { // bCheck is a input type button
        $('div#image').fadeOut('slow');
	});
    
    
}









/*
=====================
Update Slider
=====================
*/
$(document).ready(function() {

$("#splash_update_form").validate({
      rules:
   {  

    },
    submitHandler: submitForm 
       });  
    
        function submitForm(){
            $.ajax({
                url: "ajax/splash.php?update",
                type: "POST",
                data: new FormData($('#splash_update_form')[0]),
                contentType: false,
                processData: false,
                xhr: function(){
                    //upload Progress
                    var xhr = $.ajaxSettings.xhr();
                    if (xhr.upload) {
                        xhr.upload.addEventListener('progress', function(event) {
                            var percent = 0;
                            var position = event.loaded || event.position;
                            var total = event.total;
                            if (event.lengthComputable) {
                                percent = Math.ceil(position / total * 100);
                            }
                            
                            //update progressbar
                            $('#progress-wrp-splash').css("display", "block");
                            $(".progress-bar").css("width", + percent +"%");
                            $(" .status").text(percent +"%");
                            
                        }, true);
                    }
                    return xhr;
                },
                success: function(data){
                    var data = data.trim();

                    if( data == "success" ){
                        //$("#update_slider_modal").modal("hide");
                        
                        $('#splash_response').fadeIn().html(data);

                        setTimeout(function(){
                          $('#splash_response').fadeOut('slow');
                        }, 1000);
                        $('.close').trigger('click');
                        
                        $('.progress-wrp').fadeOut('slow'); 
                        
                        showSplash();
                   }else{
                       $('#splash_response').fadeIn().html(data);

                        setTimeout(function(){
                          $('#splash_response').fadeOut('slow');
                        }, 1500);
                   }



                }
            });   
        }


    
});



$('#update_splash_modal').on('hidden.bs.modal', function (e) {
    $('#splash_update_form')[0].reset();
    $( ".thumbs li" ).hide();
    $('.progress-wrp').fadeOut('slow');
})








/*
===========================
HOMEPAGE OR SLIDER
===========================
*/

$(document).ready(function() {

$("#homepage").validate({
      rules:
	  {		
         project_image: {
         required: true
         },			
         project_title: {
         required: true
         }
				 

	   },
	   submitHandler: submitForm	
       });  
    
        function submitForm(){
            $.ajax({
                url: "ajax/home.php",
                type: "POST",
                data: new FormData($('#homepage')[0]),
                contentType: false,
                processData: false,
                xhr: function(){
                    //upload Progress
                    var xhr = $.ajaxSettings.xhr();
                    if (xhr.upload) {
                        xhr.upload.addEventListener('progress', function(event) {
                            var percent = 0;
                            var position = event.loaded || event.position;
                            var total = event.total;
                            if (event.lengthComputable) {
                                percent = Math.ceil(position / total * 100);
                            }
                            
                            //update progressbar
                            $('#progress-wrp-home').css("display", "block");
                            $(".progress-bar").css("width", + percent +"%");
                            $(" .status").text(percent +"%");
                            
                        }, true);
                    }
                    return xhr;
                },                
                success: function(data){
                    var data = data.trim();

                    
                   $('#slidemessage').fadeIn().html(data);
                    


                    setTimeout(function(){
                      $('#slidemessage').fadeOut('slow');
                    }, 1500);
                    

                    if( data == "Home Slider Added Successfully" ){
                        $('#homepage')[0].reset(); 
                        $( ".thumbs li" ).hide();
                        $('#progress-wrp-home').fadeOut('slow');
                    }
                    //alert(data);

                }
            });   
        }

$('#homeform').click(function() {
    
    $(".progress-bar").css("width", "0%");
    $(".status").text("0%");
    
    $('#homepage').submit();
});
    
});

$('#add_more_slider').click(function() {

    $('#homepage').submit();
});


/*
===================
Display All  Slider
===================
*/
function showSlider(){
    $.ajax({
    url: "ajax/home.php?show",
    type: "POST",
    success: function(data){
        //alert(data);
        $('#display_slider').html(data);
    }
    });    
}

showSlider();



/*
=====================
Edit Slider
=====================
*/
function editSlider(slider_id){
    var slider_id = slider_id;
    
    $.ajax({
    url: "ajax/home.php?edit",
    type: "POST",
    data: {slider_id:slider_id},
    success: function(data){

       // alert(data);
        $("#update_slider_modal").modal("show");
    
       var jsonData = JSON.parse(data);

        
        $('#projec-title').val(jsonData.title);
        $('#slide_id').val(jsonData.id);
        
        var image = jsonData.image;
        var image1 = image.substring(1);
        
        //alert(image1);
   
        $('div#image').css('background-image', 'url('+image1+')');
        $('div#image').show();
        

        
    }
    });
    
	$("#project-image").click(function() { // bCheck is a input type button
        $('div#image').fadeOut('slow');
	});
    
    
}




/*
=====================
Update Slider
=====================
*/
$(document).ready(function() {

$("#slider_update_form").validate({
      rules:
   {  

    },
    submitHandler: submitForm 
       });  
    
        function submitForm(){
            $.ajax({
                url: "ajax/home.php?update",
                type: "POST",
                data: new FormData($('#slider_update_form')[0]),
                contentType: false,
                processData: false,
                xhr: function(){
                    //upload Progress
                    var xhr = $.ajaxSettings.xhr();
                    if (xhr.upload) {
                        xhr.upload.addEventListener('progress', function(event) {
                            var percent = 0;
                            var position = event.loaded || event.position;
                            var total = event.total;
                            if (event.lengthComputable) {
                                percent = Math.ceil(position / total * 100);
                            }
                            
                            //update progressbar
                            $('#progress-wrp-home').css("display", "block");
                            $(".progress-bar").css("width", + percent +"%");
                            $(" .status").text(percent +"%");
                            
                        }, true);
                    }
                    return xhr;
                },
                success: function(data){
                    var data = data.trim();

                    if( data == "success1" ){
                        //$("#update_slider_modal").modal("hide");
                        
                        $('#response').fadeIn().html(data);

                        setTimeout(function(){
                          $('#response').fadeOut('slow');
                        }, 1000);
                        $('.close').trigger('click');
                        
                        $('.progress-wrp').fadeOut('slow'); 
                        
                        showSlider();
                   }else{
                       $('#response').fadeIn().html(data);

                        setTimeout(function(){
                          $('#response').fadeOut('slow');
                        }, 1500);
                   }



                }
            });   
        }


    
});


$('#update_slider_modal').on('hidden.bs.modal', function (e) {
    $('#slider_update_form')[0].reset();
    $( ".thumbs li" ).hide();
    $('.progress-wrp').fadeOut('slow');
})

/****************************************************************/





/*
===========================
OVERVIEW PAGE
===========================
*/

$(document).ready(function() {

$("#overviewform").validate({
      rules:
	  {		
         overview_bg: {
         required: true
         },		
         overview_logo: {
         required: true
         },
         title: {
         required: true
         },
         moto: {
         required: true
         }
				 

	   },
	   submitHandler: submitForm	
       });  
    
        function submitForm(){
            $.ajax({
                url: "ajax/overview.php",
                type: "POST",
                data: new FormData($('#overviewform')[0]),
                contentType: false,
                processData: false,
                xhr: function(){
                    //upload Progress
                    var xhr = $.ajaxSettings.xhr();
                    if (xhr.upload) {
                        xhr.upload.addEventListener('progress', function(event) {
                            var percent = 0;
                            var position = event.loaded || event.position;
                            var total = event.total;
                            if (event.lengthComputable) {
                                percent = Math.ceil(position / total * 100);
                            }
                            
                            //update progressbar
                            $('#progress-wrp-overview-content').css("display", "block");
                            $(".progress-bar").css("width", + percent +"%");
                            $(" .status").text(percent +"%");
                            
                        }, true);
                    }
                    return xhr;
                },
                success: function(data){
                    
                    var data = data.trim();
                    
                    if(data == 'exist'){
                        $('#progress-wrp-overview-content').hide();
  
                        $("#myModal").modal('show');
                        $('#myModal .modal-body h3').html('You already saved Overview content');
                        $('#myModal .modal-body #error_menu').html('<button type="button" class="btn btn-default cancel_modal" data-dismiss="modal">Cancel</button><a href="display.php?overview" class="btn btn-default edit_content">Edit Overview</a>');
                    }else{
                        //alert(data);
                       $('#overviewmessage').fadeIn().html(data);

                        setTimeout(function(){
                          $('#overviewmessage').fadeOut('slow');
                        }, 1500); 
                    } 
                    
                    

                }
            });   
        }

$('#overview_submit').click(function() {
    
    $(".progress-bar").css("width", "0%");
    $(".status").text("0%"); 
    
    $('#overviewform').submit();
});

    
});    
    
/***/
$(document).ready(function() {
    
$("#companyinfo").validate({
      rules:
	  {		
         overview_icon: {
         required: true
         },			
         overview_title: {
         required: true
         },
         overview_details: {
         required: true
         }
				 

	   },
	   submitHandler: submitForm	
       });  
    
        function submitForm(){
            $.ajax({
                url: "ajax/companyinfo.php",
                type: "POST",
                data: new FormData($('#companyinfo')[0]),
                contentType: false,
                processData: false,
                xhr: function(){
                    //upload Progress
                    var xhr = $.ajaxSettings.xhr();
                    if (xhr.upload) {
                        xhr.upload.addEventListener('progress', function(event) {
                            var percent = 0;
                            var position = event.loaded || event.position;
                            var total = event.total;
                            if (event.lengthComputable) {
                                percent = Math.ceil(position / total * 100);
                            }
                            
                            //update progressbar
                            $('#progress-wrp-overview-item').css("display", "block");
                            $(".progress-bar").css("width", + percent +"%");
                            $(" .status").text(percent +"%");
                            
                        }, true);
                    }
                    return xhr;
                },                 
                success: function(data){
                    var data = data.trim();
                  //  alert(data);
                   $('#company_info_message').fadeIn().html(data);

                    setTimeout(function(){
                      $('#company_info_message').fadeOut('slow');
                    }, 1500);
                    
                    if( data == "Company Info Added Successfully" ){
                        $('#companyinfo')[0].reset(); 
                        $( ".thumbs li" ).hide();
                        $('#progress-wrp-overview-item').fadeOut('slow');
                    }
                    
                }
            });   
        }

$('#company_submit').click(function() {
    $(".progress-bar").css("width", "0%");
    $(".status").text("0%"); 
    
    $('#companyinfo').submit();
});   
    
$('#add_comp_info').click(function() {
    $('#companyinfo').submit();
});    
    
    
    
});




/*
===================
Display Overview Content
===================
*/
function showOverviewContent(){
    $.ajax({
    url: "ajax/overview.php?show",
    type: "POST",
    success: function(data){
        //alert(data);
        $('#display_overview_content').html(data);
    }
    });    
}

 showOverviewContent();









/*
=====================
Edit Overview Content
=====================
*/
function editOverviewContent(overview_content_id){
    var overview_content_id = overview_content_id;
    
    $.ajax({
    url: "ajax/overview.php?edit",
    type: "POST",
    data: {overview_content_id:overview_content_id},
    success: function(data){

       //alert(data);
        $("#update_overviewcontent_modal").modal("show");
    
       var jsonData = JSON.parse(data);
     
        $('#title-text').val(jsonData.title);
        $('#promo-text').val(jsonData.moto);
        $('#count_1_info').val(jsonData.counter1_text);
        $('#count_1_number').val(jsonData.counter1_number);
        $('#count_2_info').val(jsonData.counter2_text);
        $('#count_2_number').val(jsonData.counter2_number);
        $('#count_3_info').val(jsonData.counter3_text);
        $('#count_3_number').val(jsonData.counter3_number);
        
        
        
        
        $('#overview_content_id').val(jsonData.id);
//        
        var image = jsonData.background;
        var imagenew = image.substring(1);
        
        var logo =jsonData.logo;
        var logo =logo.substring(1);
        

        $('div#image').css('background-image', 'url('+imagenew+')');
        $('div#image').show();
        
        $('div#logo').css('background-image', 'url('+logo+')');
        $('div#logo').show();
        
    }
    });
    
	$("#overview-bg").click(function() { // bCheck is a input type button
        $('div#image').fadeOut('slow');
	});
	$("#overview-logo").click(function() { // bCheck is a input type button
        $('div#logo').fadeOut('slow');
	});
}






/*
=====================
Update Overview content
=====================
*/
$(document).ready(function() {

$("#update_overviewcontent_form").validate({
      rules:
   {  

    },
    submitHandler: submitForm 
       });  
    
        function submitForm(){
            $.ajax({
                url: "ajax/overview.php?update",
                type: "POST",
                data: new FormData($('#update_overviewcontent_form')[0]),
                contentType: false,
                processData: false,
                xhr: function(){
                    //upload Progress
                    var xhr = $.ajaxSettings.xhr();
                    if (xhr.upload) {
                        xhr.upload.addEventListener('progress', function(event) {
                            var percent = 0;
                            var position = event.loaded || event.position;
                            var total = event.total;
                            if (event.lengthComputable) {
                                percent = Math.ceil(position / total * 100);
                            }
                            
                            //update progressbar
                            $('#progress-wrp-overview-content').css("display", "block");
                            $(".progress-bar").css("width", + percent +"%");
                            $(" .status").text(percent +"%");
                            
                        }, true);
                    }
                    return xhr;
                },                
                success: function(data){
                    var data = data.trim();

                    if( data == "success" ){
                        //$("#update_slider_modal").modal("hide");
                        
                        $('#overview_content_response').fadeIn().html(data);

                        setTimeout(function(){
                          $('#overview_content_response').fadeOut('slow');
                        }, 1000);
                        $('.close').trigger('click');
                        
                        $('#progress-wrp-overview-content').fadeOut('slow');
                        
                         showOverviewContent();
                        
                   }else{
                       $('#overview_content_response').fadeIn().html(data);

                        setTimeout(function(){
                          $('#overview_content_response').fadeOut('slow');
                        }, 1500);
                   }



                }
            });   
        }


    
});

$('#update_overviewcontent_modal').on('hidden.bs.modal', function (e) {
    $('#update_overviewcontent_form')[0].reset();
    $( ".thumbs li" ).hide();
});











/*
===================
Display ALL company info
===================
*/
function showCompanyInfo(){
    $.ajax({
    url: "ajax/companyinfo.php?show",
    type: "POST",
    success: function(data){
        //alert(data);
        $('#display_companyinfo').html(data);
    }
    });    
}

showCompanyInfo();



/*
=====================
  Edit Company Info
=====================
*/
function editCompanyInfo(company_info_id){
    var company_info_id = company_info_id;
    
    $.ajax({
    url: "ajax/companyinfo.php?edit",
    type: "POST",
    data: {company_info_id:company_info_id},
    success: function(data){

        //alert(data);
        $("#update_companyinfo_modal").modal("show");
    
       var jsonData = JSON.parse(data);

        
        $('#overview_title').val(jsonData.title);
        $('#overview_details').val(jsonData.description);
        $('#company_info_id').val(jsonData.id);
        
        var image = jsonData.icon;
        var imagenew = image.substring(1);
        
      //  alert(image);
   
        $('div#image').css('background-image', 'url('+imagenew+')');
        $('div#image').show();
        

        
    }
    });
    
	$("#overview_icon").click(function() { // bCheck is a input type button
        $('div#image').fadeOut('slow');
	});
    
    
}




/*
=====================
Update Company info / overview item
=====================
*/
$(document).ready(function() {

$("#company_update_form").validate({
      rules:
   {  

    },
    submitHandler: submitForm 
       });  
    
        function submitForm(){
            $.ajax({
                url: "ajax/companyinfo.php?update",
                type: "POST",
                data: new FormData($('#company_update_form')[0]),
                contentType: false,
                processData: false,
                xhr: function(){
                    //upload Progress
                    var xhr = $.ajaxSettings.xhr();
                    if (xhr.upload) {
                        xhr.upload.addEventListener('progress', function(event) {
                            var percent = 0;
                            var position = event.loaded || event.position;
                            var total = event.total;
                            if (event.lengthComputable) {
                                percent = Math.ceil(position / total * 100);
                            }
                            
                            //update progressbar
                            $('#progress-wrp-overview-item').css("display", "block");
                            $(".progress-bar").css("width", + percent +"%");
                            $(" .status").text(percent +"%");
                            
                        }, true);
                    }
                    return xhr;
                },                
                success: function(data){
                    var data = data.trim();

                    if( data == "success" ){
                        //$("#update_slider_modal").modal("hide");
                        
                        $('#overview_item_response').fadeIn().html(data);

                        setTimeout(function(){
                          $('#overview_item_response').fadeOut('slow');
                        }, 1000);
                        $('.close').trigger('click');
                        
                        showCompanyInfo();
                        
                   }else{
                       $('#overview_item_response').fadeIn().html(data);

                        setTimeout(function(){
                          $('#overview_item_response').fadeOut('slow');
                        }, 1500);
                   }



                }
            });   
        }


    
});


$('#update_companyinfo_modal').on('hidden.bs.modal', function (e) {
    $('#company_update_form')[0].reset();
    $( ".thumbs li" ).hide();
    $('.progress-wrp').fadeOut('slow');    
});





/***********************END OVERVIEW**********************************/



/*
===========================
PORTFOLIO
===========================
*/

$(document).ready(function() {

$("#portfolio_form").validate({
      rules:
	  {		
         portfolio_image: {
         required: true
         },			
         portfolio_title: {
         required: true
         },         
          'project_platform[]': {
             required: true
         }
				 

	   },
	   submitHandler: submitForm	
       });  
    
        function submitForm(){
            $.ajax({
                url: "ajax/portfolio.php",
                type: "POST",
                data: new FormData($('#portfolio_form')[0]),
                contentType: false,
                processData: false,
                xhr: function(){
                    //upload Progress
                    var xhr = $.ajaxSettings.xhr();
                    if (xhr.upload) {
                        xhr.upload.addEventListener('progress', function(event) {
                            var percent = 0;
                            var position = event.loaded || event.position;
                            var total = event.total;
                            if (event.lengthComputable) {
                                percent = Math.ceil(position / total * 100);
                            }
                            
                            //update progressbar
                            $('#progress-wrp-portfolio').css("display", "block");
                            $(".progress-bar").css("width", + percent +"%");
                            $(" .status").text(percent +"%");
                            
                        }, true);
                    }
                    return xhr;
                },                
                success: function(data){
                    var data = data.trim();
                   // alert(data);
                   $('#portfolio_message').fadeIn().html(data);

                    setTimeout(function(){
                      $('#portfolio_message').fadeOut('slow');
                    }, 1500);
                    
                    if( data == "Portfolio Added Successfully" ){
                        $('#portfolio_form')[0].reset(); 
                        $( ".thumbs li" ).hide();
                        $('#progress-wrp-portfolio').fadeOut('slow');
                    }
                    
                    
                }
            });   
        }

$('#portfolio_submit').click(function() {
    $('#portfolio_form').submit();
});
    
    
$('#add_portfolio').click(function() {
     $('#portfolio_form').submit();
});    
        
    
});



/*
===================
Display All  Portfolio
===================
*/
function showPortolio(){
    $.ajax({
    url: "ajax/portfolio.php?show",
    type: "POST",
    success: function(data){
        //alert(data);
        $('#display_portfolio').html(data);
    }
    });    
}

showPortolio();



/*
====================
EDIT PORTFOLIO
====================
*/

function editPortfolio(portfolio_id){
    var portfolio_id = portfolio_id;
    
    $.ajax({
    url: "ajax/portfolio.php?edit",
    type: "POST",
    data: {portfolio_id:portfolio_id},
    success: function(data){

        $("#update_portfolio_modal").modal("show");
    
        var jsonData = JSON.parse(data);
       // alert(data);

  
        var tags = jsonData.tags;
        var tagNunber = tags.length;
        
        
        $('#portfolio_projec_title').val(jsonData.portfolio_data.title);
        $('#portfolio_id').val(jsonData.portfolio_data.id);
        
        
        $( ".existing_tag" ).remove();
        $( ".client_repeat+.client_repeat" ).remove();
        if(tagNunber > 0){
            for(i=0; i<tagNunber; i++){
                $('#portfolio_repeatable_item').append('<div class="form-row existing_tag"> <div class="col-sm-4"> <input type="text" class="form-control" value="'+jsonData.tags[i].name+'" name="project_platform[]" value="android" id="project_url"> <input type="hidden" name="tag_id" value="'+jsonData.tags[i].id+'"> </div> <div class="col-sm-7"> <input type="text" class="form-control" value="'+jsonData.tags[i].url+'" name="project_url[]"> </div> <div class="col-sm-1"> <span class="remove"></span> </div> </div>');
            } 
        }

        var image = jsonData.portfolio_data.image;
        var imagenew = image.substring(1);
        
        //alert(image1);
   
        $('div#image').css('background-image', 'url('+imagenew+')');
        $('div#image').show();
   

        
        
        
        
    }
    });
    
	$("#portfolio_project_image").click(function() { // bCheck is a input type button
        $('div#image').fadeOut('slow');
	});
    
}




/*
=====================
Update PORTFOLIO
=====================
*/
$(document).ready(function() {

$("#portfolio_update_form").validate({
      rules:
   {  

    },
    submitHandler: submitForm 
       });  
    
        function submitForm(){
            $.ajax({
                url: "ajax/portfolio.php?update",
                type: "POST",
                data: new FormData($('#portfolio_update_form')[0]),
                contentType: false,
                processData: false,
                xhr: function(){
                    //upload Progress
                    var xhr = $.ajaxSettings.xhr();
                    if (xhr.upload) {
                        xhr.upload.addEventListener('progress', function(event) {
                            var percent = 0;
                            var position = event.loaded || event.position;
                            var total = event.total;
                            if (event.lengthComputable) {
                                percent = Math.ceil(position / total * 100);
                            }
                            
                            //update progressbar
                            $('#progress-wrp-portfolio').css("display", "block");
                            $(".progress-bar").css("width", + percent +"%");
                            $(" .status").text(percent +"%");
                            
                        }, true);
                    }
                    return xhr;
                },                 
                success: function(data){
                    var data = data.trim();

                    if( data == "success" ){
                        //$("#update_slider_modal").modal("hide");
                        
                        $('#portfolio_response').fadeIn().html(data);

                        setTimeout(function(){
                          $('#portfolio_response').fadeOut('slow');
                        }, 1000);
                        $('.close').trigger('click');
                        
                        showPortolio();
                        
                   }else{
                       $('#portfolio_response').fadeIn().html(data);

                        setTimeout(function(){
                          $('#portfolio_response').fadeOut('slow');
                        }, 1500);
                   }



                }
            });   
        }


    
});






$('#update_portfolio_modal').on('hidden.bs.modal', function (e) {
    $('#portfolio_update_form')[0].reset();
    $( ".thumbs li" ).hide();
    
    $('.progress-wrp').fadeOut('slow');
});

/***********************END PORTFOLIO JS*****************************/













/*
===========================
        TEAM SECTION
===========================
*/

$(document).ready(function() {

$("#teamform").validate({
      rules:
	  {		
         member_image: {
         required: true
         },			
         member_name: {
         required: true
         },         
         member_designation: {
             required: true
         }
				 

	   },
	   submitHandler: submitForm	
       });  
    
        function submitForm(){
            $.ajax({
                url: "ajax/team.php",
                type: "POST",
                data: new FormData($('#teamform')[0]),
                contentType: false,
                processData: false,
                xhr: function(){
                    //upload Progress
                    var xhr = $.ajaxSettings.xhr();
                    if (xhr.upload) {
                        xhr.upload.addEventListener('progress', function(event) {
                            var percent = 0;
                            var position = event.loaded || event.position;
                            var total = event.total;
                            if (event.lengthComputable) {
                                percent = Math.ceil(position / total * 100);
                            }
                            
                            //update progressbar
                            $('#progress-wrp-team').css("display", "block");
                            $(".progress-bar").css("width", + percent +"%");
                            $(" .status").text(percent +"%");
                            
                        }, true);
                    }
                    return xhr;
                },                 
                success: function(data){
                    var data = data.trim();
                   // alert(data);
                   $('#team_message').fadeIn().html(data);

                    setTimeout(function(){
                      $('#team_message').fadeOut('slow');
                    }, 1500);
                    
                    if( data == "Team Member Added Successfully" ){
                        $('#teamform')[0].reset(); 
                        $( ".thumbs li" ).hide();
                        $('#progress-wrp-team').fadeOut('slow');
                    }                    
                }
            });   
        }

$('#team_submit').click(function() {
    $(".progress-bar").css("width", "0%");
    $(".status").text("0%");
    
    $('#teamform').submit();
});
    
    
$('#add_team').click(function() {
    $('#teamform').submit();
});    
        
    
});




/*
===================
Display All  Team
===================
*/
function showTeam(){
    $.ajax({
    url: "ajax/team.php?show",
    type: "POST",
    success: function(data){
        //alert(data);
        $('#display_team').html(data);
    }
    });    
}

showTeam();



/*
=====================
Edit Team
=====================
*/
function editTeam(team_id){
    var team_id = team_id;
    
    $.ajax({
    url: "ajax/team.php?edit",
    type: "POST",
    data: {team_id:team_id},
    success: function(data){

        //alert(data);
        $("#update_team_modal").modal("show");
    
       var jsonData = JSON.parse(data);

        
        $('#member_name').val(jsonData.name);
        $('#member_designation').val(jsonData.designation);
        $('#team_id').val(jsonData.id);
        
        var image = jsonData.photo;
        var imagenew = image.substring(1);
        
        //alert(image1);
   
        $('div#image').css('background-image', 'url('+imagenew+')');
        $('div#image').show();
        

        
    }
    });
    
	$("#member_image").click(function() { // bCheck is a input type button
        $('div#image').fadeOut('slow');
	});
    
    
}





/*
=====================
Update Team
=====================
*/
$(document).ready(function() {

$("#team_update_form").validate({
      rules:
   {  

    },
    submitHandler: submitForm 
       });  
    
        function submitForm(){
            $.ajax({
                url: "ajax/team.php?update",
                type: "POST",
                data: new FormData($('#team_update_form')[0]),
                contentType: false,
                processData: false,
                xhr: function(){
                    //upload Progress
                    var xhr = $.ajaxSettings.xhr();
                    if (xhr.upload) {
                        xhr.upload.addEventListener('progress', function(event) {
                            var percent = 0;
                            var position = event.loaded || event.position;
                            var total = event.total;
                            if (event.lengthComputable) {
                                percent = Math.ceil(position / total * 100);
                            }
                            
                            //update progressbar
                            $('#progress-wrp-team').css("display", "block");
                            $(".progress-bar").css("width", + percent +"%");
                            $(" .status").text(percent +"%");
                            
                        }, true);
                    }
                    return xhr;
                },
                success: function(data){
                    var data = data.trim();

                    if( data == "success" ){
                        //$("#update_slider_modal").modal("hide");
                        
                        $('#team_response').fadeIn().html(data);

                        setTimeout(function(){
                          $('#team_response').fadeOut('slow');
                        }, 1000);
                        $('.close').trigger('click');
                        
                        showTeam();
                        
                   }else{
                       $('#team_response').fadeIn().html(data);

                        setTimeout(function(){
                          $('#team_response').fadeOut('slow');
                        }, 1500);
                   }



                }
            });   
        }


    
});


$('#update_team_modal').on('hidden.bs.modal', function (e) {
    $('#team_update_form')[0].reset();
    $( ".thumbs li" ).hide();
    $('.progress-wrp').fadeOut('slow');
})

/************ END TEAM JS **************/



/*
===========================
        CLIENT SECTION
===========================
*/

$(document).ready(function() {

$("#client_form").validate({
      rules:
	  {		
         client_logo: {
         required: true
         },			
         client_name: {
         required: true
         },         
         project_name: {
             required: true
         },
         client_country: {
             required: true
         }		 

	   },
	   submitHandler: submitForm	
       });  
    
        function submitForm(){
            $.ajax({
                url: "ajax/client.php",
                type: "POST",
                data: new FormData($('#client_form')[0]),
                contentType: false,
                processData: false,
                xhr: function(){
                    //upload Progress
                    var xhr = $.ajaxSettings.xhr();
                    if (xhr.upload) {
                        xhr.upload.addEventListener('progress', function(event) {
                            var percent = 0;
                            var position = event.loaded || event.position;
                            var total = event.total;
                            if (event.lengthComputable) {
                                percent = Math.ceil(position / total * 100);
                            }
                            
                            //update progressbar
                            $('#progress-wrp-client').css("display", "block");
                            $(".progress-bar").css("width", + percent +"%");
                            $(" .status").text(percent +"%");
                            
                        }, true);
                    }
                    return xhr;
                },                
                success: function(data){
                    
                    var data = data.trim();
                   // alert(data);
                   $('#client_message').fadeIn().html(data);

                    setTimeout(function(){
                      $('#client_message').fadeOut('slow');
                    }, 1500);
                    
                    if( data == "Client Added Successfully" ){
                        $('#client_form')[0].reset(); 
                        $( ".thumbs li" ).hide();
                        $('#progress-wrp-client').fadeOut('slow');
                    }                    
                }
            });   
        }

$('#client_submit').click(function() {
    
    $(".progress-bar").css("width", "0%");
    $(".status").text("0%");
    
    $('#client_form').submit();
});
    
    
$('#add_client').click(function() {
    
    $('#client_form').submit();
});    
        
    
});



/*
===================
Display All  Client
===================
*/
function showClient(){
    $.ajax({
    url: "ajax/client.php?show",
    type: "POST",
    success: function(data){
        //alert(data);
        $('#display_client').html(data);
    }
    });    
}

showClient();





/*
====================
EDIT CLEINT
====================
*/

function editClient(client_id){
    var client_id = client_id;
    
    $.ajax({
    url: "ajax/client.php?edit",
    type: "POST",
    data: {client_id:client_id},
    success: function(data){

        $("#update_client_modal").modal("show");
    
        var jsonData = JSON.parse(data);
        //alert(data);

  
        var tags = jsonData.tags;
        var tagNunber = tags.length;
        
        
        $('#client-name').val(jsonData.client_data.name);
        $('#project-name').val(jsonData.client_data.company);
        $('#client-country').val(jsonData.client_data.country);
        $('#client_id').val(jsonData.client_data.id);
        
        
        $( ".existing_tag" ).remove();
        $( ".client_repeat+.client_repeat" ).remove();
        if(tagNunber > 0){
            for(i=0; i<tagNunber; i++){
                $('#repeatable_item').append('<div class="form-row existing_tag"> <div class="col-sm-4"> <input type="text" class="form-control" value="'+jsonData.tags[i].name+'" name="project_platform[]" value="android" id="project_url"> <input type="hidden" name="tag_id" value="'+jsonData.tags[i].id+'"> </div> <div class="col-sm-7"> <input type="text" class="form-control" value="'+jsonData.tags[i].url+'" name="project_url[]"> </div> <div class="col-sm-1"> <span class="remove"></span> </div> </div>');
            } 
        }

        var image = jsonData.client_data.logo;
        var imagenew = image.substring(1);
        
        //alert(image1);
   
        $('div#image').css('background-image', 'url('+imagenew+')');
        $('div#image').show();
   

        
        
        
        
    }
    });
    
	$("#client-logo").click(function() { // bCheck is a input type button
        $('div#image').fadeOut('slow');
	});
    
}




/*
=====================
Update Client
=====================
*/
$(document).ready(function() {

$("#client_update_form").validate({
      rules:
   {  

    },
    submitHandler: submitForm 
       });  
    
        function submitForm(){
            $.ajax({
                url: "ajax/client.php?update",
                type: "POST",
                data: new FormData($('#client_update_form')[0]),
                contentType: false,
                processData: false,
                xhr: function(){
                    //upload Progress
                    var xhr = $.ajaxSettings.xhr();
                    if (xhr.upload) {
                        xhr.upload.addEventListener('progress', function(event) {
                            var percent = 0;
                            var position = event.loaded || event.position;
                            var total = event.total;
                            if (event.lengthComputable) {
                                percent = Math.ceil(position / total * 100);
                            }
                            
                            //update progressbar
                            $('#progress-wrp-client').css("display", "block");
                            $(".progress-bar").css("width", + percent +"%");
                            $(" .status").text(percent +"%");
                            
                        }, true);
                    }
                    return xhr;
                },                 
                success: function(data){
                    var data = data.trim();

                    if( data == "success" ){
                        //$("#update_slider_modal").modal("hide");
                        
                        $('#client_response').fadeIn().html(data);

                        setTimeout(function(){
                          $('#client_response').fadeOut('slow');
                        }, 1000);
                        $('.close').trigger('click');
                        
                        showClient();
                        
                   }else{
                       $('#client_response').fadeIn().html(data);

                        setTimeout(function(){
                          $('#client_response').fadeOut('slow');
                        }, 1500);
                   }



                }
            });   
        }


    
});






$('#update_client_modal').on('hidden.bs.modal', function (e) {
    $('#client_update_form')[0].reset();
    $( ".thumbs li" ).hide();
    
    $('.progress-wrp').fadeOut('slow');    
});



/*******************************************************/









/*
============    ===============
        TESTIMONAIL SECTION
============    ===============
*/

$(document).ready(function() {

$("#testimonial_form").validate({
      rules:
	  {		
         client_image: {
         required: true
         },			
         client_name: {
         required: true
         },         
         project_name: {
             required: true
         },
         project_url: {
             required: true
         },
         client_feedback: {
             required: true
         }	          

	   },
	   submitHandler: submitForm	
       });  
    
        function submitForm(){
            $.ajax({
                url: "ajax/testimonial.php",
                type: "POST",
                data: new FormData($('#testimonial_form')[0]),
                contentType: false,
                processData: false,
                xhr: function(){
                    //upload Progress
                    var xhr = $.ajaxSettings.xhr();
                    if (xhr.upload) {
                        xhr.upload.addEventListener('progress', function(event) {
                            var percent = 0;
                            var position = event.loaded || event.position;
                            var total = event.total;
                            if (event.lengthComputable) {
                                percent = Math.ceil(position / total * 100);
                            }
                            
                            //update progressbar
                            $('#progress-wrp-testimonail').css("display", "block");
                            $(".progress-bar").css("width", + percent +"%");
                            $(" .status").text(percent +"%");
                            
                        }, true);
                    }
                    return xhr;
                },                 
                success: function(data){
                    var data = data.trim();
                   // alert(data);
                   $('#testimonial_message').fadeIn().html(data);

                    setTimeout(function(){
                      $('#testimonial_message').fadeOut('slow');
                    }, 1500);
                    
                    if( data == "Testimonial Added Successfully" ){
                        $('#testimonial_form')[0].reset(); 
                        $( ".thumbs li" ).hide();
                        $('#progress-wrp-testimonail').fadeOut('slow');
                    }                    
                    
                }
            });   
        }

$('#testimonial_submit').click(function() {
    
    $(".progress-bar").css("width", "0%");
    $(".status").text("0%");    
    
    $('#testimonial_form').submit();
});
    
    
$('#add_testimonial').click(function() {
    $('#testimonial_form').submit();    

});    
        
    
});




/*
=========       ==========
Display All  Testimonials
=========       ==========
*/
function showTestimonial(){
    $.ajax({
    url: "ajax/testimonial.php?show",
    type: "POST",
    success: function(data){
        //alert(data);
        $('#display_testimonial').html(data);
    }
    });    
}

showTestimonial();




/*
=====================
Edit Testimonial
=====================
*/
function editTestimonial(testimonial_id){
    var testimonial_id = testimonial_id;
    
    $.ajax({
    url: "ajax/testimonial.php?edit",
    type: "POST",
    data: {testimonial_id:testimonial_id},
    success: function(data){

        //alert(data);
        $("#update_testimonial_modal").modal("show");
    
       var jsonData = JSON.parse(data);

        
        $('#testimonial_client_name').val(jsonData.name);
        $('#testimonialproject_name').val(jsonData.project);
        $('#project-url').val(jsonData.url);
        $('#client-feedback').val(jsonData.feedback);
        $('#testimonial_id').val(jsonData.id);
        
        var image = jsonData.image;
        var image1 = image.substring(1);
        
        //alert(image1);
   
        $('div#image').css('background-image', 'url('+image1+')');
        $('div#image').show();
        

        
    }
    });
    
	$("#client-image").click(function() { // bCheck is a input type button
        $('div#image').fadeOut('slow');
	});
    
    
}




/*
=====================
    Update Slider
=====================
*/
$(document).ready(function() {

$("#testimonial_update_form").validate({
      rules:
   {  

    },
    submitHandler: submitForm 
       });  
    
        function submitForm(){
            $.ajax({
                url: "ajax/testimonial.php?update",
                type: "POST",
                data: new FormData($('#testimonial_update_form')[0]),
                contentType: false,
                processData: false,
                xhr: function(){
                    //upload Progress
                    var xhr = $.ajaxSettings.xhr();
                    if (xhr.upload) {
                        xhr.upload.addEventListener('progress', function(event) {
                            var percent = 0;
                            var position = event.loaded || event.position;
                            var total = event.total;
                            if (event.lengthComputable) {
                                percent = Math.ceil(position / total * 100);
                            }
                            
                            //update progressbar
                            $('#progress-wrp-testimonail').css("display", "block");
                            $(".progress-bar").css("width", + percent +"%");
                            $(" .status").text(percent +"%");
                            
                        }, true);
                    }
                    return xhr;
                },                
                success: function(data){
                    var data = data.trim();

                    if( data == "success" ){
                        //$("#update_slider_modal").modal("hide");
                        
                        $('#testimonial_response').fadeIn().html(data);

                        setTimeout(function(){
                          $('#testimonial_response').fadeOut('slow');
                        }, 1000);
                        $('.close').trigger('click');
                        
                        showTestimonial();
                        
                   }else{
                       $('#testimonial_response').fadeIn().html(data);

                        setTimeout(function(){
                          $('#testimonial_response').fadeOut('slow');
                        }, 1500);
                   }



                }
            });   
        }


    
});


$('#update_testimonial_modal').on('hidden.bs.modal', function (e) {
    $('#testimonial_update_form')[0].reset();
    $( ".thumbs li" ).hide();
    $('.progress-wrp').fadeOut('slow');    
})
/****************TESTIMONIAL END JS*****************/






/*
=============    ===============
 METHODOLOGY     CONTENT SECTION
=============    ===============
*/

$(document).ready(function() {

$("#methodology_content_form").validate({
      rules:
	  {		
         methodology_bg: {
         required: true
         },			
         methodology_logo: {
         required: true
         }	          

	   },
	   submitHandler: submitForm	
       });  
    
        function submitForm(){
            $.ajax({
                url: "ajax/methodology_content.php",
                type: "POST",
                data: new FormData($('#methodology_content_form')[0]),
                contentType: false,
                processData: false,
                xhr: function(){
                    //upload Progress
                    var xhr = $.ajaxSettings.xhr();
                    if (xhr.upload) {
                        xhr.upload.addEventListener('progress', function(event) {
                            var percent = 0;
                            var position = event.loaded || event.position;
                            var total = event.total;
                            if (event.lengthComputable) {
                                percent = Math.ceil(position / total * 100);
                            }
                            
                            //update progressbar
                            $('#progress-wrp-methodology_content').css("display", "block");
                            $(".progress-bar").css("width", + percent +"%");
                            $(" .status").text(percent +"%");
                            
                        }, true);
                    }
                    return xhr;
                },                
                success: function(data){
                    
                    
                    var data = data.trim();
                    
                    if(data == 'exist'){
                        $('#progress-wrp-methodology_content').hide();
  
                        $("#myModal").modal('show');
                        $('#myModal .modal-body h3').html('You already saved Mthodology content');
                        $('#myModal .modal-body #error_menu').html('<button type="button" class="btn btn-default cancel_modal" data-dismiss="modal">Cancel</button><a href="display.php?methodolgy" class="btn btn-default edit_content">Edit Methodology</a>');
                    }else{
                        
                        //alert(data);
                       $('#methodology_content_message').fadeIn().html(data);

                        setTimeout(function(){
                          $('#methodology_content_message').fadeOut('slow');
                        }, 1500);
                        
                    }
                    
                    
                    

                }
            });   
        }

$('#submit_methodology_content').click(function() {
    
    $(".progress-bar").css("width", "0%");
    $(".status").text("0%");
    
    $('#methodology_content_form').submit();
});
    
   
      
    
});



/*
===================
Display Static Methodology content
===================
*/
function showMethodologyContent(){
    $.ajax({
    url: "ajax/methodology_content.php?show",
    type: "POST",
    success: function(data){
        //alert(data);
        $('#display_methodology_content').html(data);
    }
    });    
}

showMethodologyContent();



/*
=====================
Edit Methodology content
=====================
*/
function editMethodologyContent(methodology_content_id){
    var methodology_content_id = methodology_content_id;
    
    $.ajax({
    url: "ajax/methodology_content.php?edit",
    type: "POST",
    data: {methodology_content_id:methodology_content_id},
    success: function(data){

       // alert(data);
        $("#update_methodology_content_modal").modal("show");
    
       var jsonData = JSON.parse(data);

        $('#methodology_content_id').val(jsonData.id);
//        
        var image = jsonData.background;
        var background = image.substring(1);
        
        var logo = jsonData.logo;
        var logoImage = logo.substring(1);
        
        //alert(image1);
   
        $('div#image').css('background-image', 'url('+background+')');
        $('div#image').show();
        
        $('div#logo').css('background-image', 'url('+logoImage+')');
        $('div#logo').show();
        

        
    }
    });
    
	$("#methodology-bg").click(function() { // bCheck is a input type button
        $('div#image').fadeOut('slow');
	});

    
	$("#methodology-logo").click(function() { // bCheck is a input type button
        $('div#logo').fadeOut('slow');
	});
    
}





/*
=====================
Update Methodology Content
=====================
*/
$(document).ready(function() {

$("#update_methodology_content_form").validate({
      rules:
   {  

    },
    submitHandler: submitForm 
       });  
    
        function submitForm(){
            $.ajax({
                url: "ajax/methodology_content.php?update",
                type: "POST",
                data: new FormData($('#update_methodology_content_form')[0]),
                contentType: false,
                processData: false,
                xhr: function(){
                    //upload Progress
                    var xhr = $.ajaxSettings.xhr();
                    if (xhr.upload) {
                        xhr.upload.addEventListener('progress', function(event) {
                            var percent = 0;
                            var position = event.loaded || event.position;
                            var total = event.total;
                            if (event.lengthComputable) {
                                percent = Math.ceil(position / total * 100);
                            }
                            
                            //update progressbar
                            $('#progress-wrp-methodology_content').css("display", "block");
                            $(".progress-bar").css("width", + percent +"%");
                            $(" .status").text(percent +"%");
                            
                        }, true);
                    }
                    return xhr;
                },                
                success: function(data){
                    var data = data.trim();

                    if( data == "success" ){
                        //$("#update_slider_modal").modal("hide");
                        
                        $('#methodology_content_response').fadeIn().html(data);

                        setTimeout(function(){
                          $('#methodology_content_response').fadeOut('slow');
                        }, 1000);
                        $('.close').trigger('click');
                        
                        showMethodologyContent();
                        
                   }else{
                       $('#methodology_content_response').fadeIn().html(data);

                        setTimeout(function(){
                          $('#methodology_content_response').fadeOut('slow');
                        }, 1500);
                   }



                }
            });   
        }


    
});


$('#update_methodology_content_modal').on('hidden.bs.modal', function (e) {
    $('#update_methodology_content_form')[0].reset();
    $( ".thumbs li" ).hide();
    
    $('.progress-wrp').fadeOut('slow');     
})

/*********END METHODOLOGY CONTENT SECTION *******/



/*
=============    ===============
 METHODOLOGY  REPETABLE  ITEM
=============    ===============
*/

$(document).ready(function() {

$("#methodology_item_form").validate({
      rules:
	  {		
         methodology_icon: {
         required: true
         },			
         methodology_title: {
         required: true
         },
         methodology_details: {
         required: true
         }	 
	   },
	   submitHandler: submitForm	
       });  
    
        function submitForm(){
            $.ajax({
                url: "ajax/methodology_item.php",
                type: "POST",
                data: new FormData($('#methodology_item_form')[0]),
                contentType: false,
                processData: false,
                xhr: function(){
                    //upload Progress
                    var xhr = $.ajaxSettings.xhr();
                    if (xhr.upload) {
                        xhr.upload.addEventListener('progress', function(event) {
                            var percent = 0;
                            var position = event.loaded || event.position;
                            var total = event.total;
                            if (event.lengthComputable) {
                                percent = Math.ceil(position / total * 100);
                            }
                            
                            //update progressbar
                            $('#progress-wrp-methodology-item').css("display", "block");
                            $(".progress-bar").css("width", + percent +"%");
                            $(" .status").text(percent +"%");
                            
                        }, true);
                    }
                    return xhr;
                },                 
                success: function(data){
                    
                    var data = data.trim();
                    //alert(data);
                   $('#methodology_item_message').fadeIn().html(data);

                    setTimeout(function(){
                      $('#methodology_item_message').fadeOut('slow');
                    }, 1500);
                    
                    if( data == "Methodology Item Added Successfully" ){
                        $('#methodology_item_form')[0].reset(); 
                        $( ".thumbs li" ).hide();
                        $('#progress-wrp-methodology-item').fadeOut('slow');
                    } 
                    
                }
            });   
        }

$('#submit_methodology_item').click(function() {
    
    $(".progress-bar").css("width", "0%");
    $(".status").text("0%");    
    
    $('#methodology_item_form').submit();
});
    
   
$('#add_methodology_item').click(function() {
    $('#methodology_item_form').submit();

});          
    
});





/*
=========       ==========
Display Methodology Item
========       ===========
*/
function showMethodologyItem(){
    $.ajax({
    url: "ajax/methodology_item.php?show",
    type: "POST",
    success: function(data){
        //alert(data);
        $('#display_methodology_item').html(data);
    }
    });    
}

showMethodologyItem();



/*
=====================
Edit Methodology Item
=====================
*/
function editTMethodology(methodology_id){
    var methodology_id = methodology_id;
    
    $.ajax({
    url: "ajax/methodology_item.php?edit",
    type: "POST",
    data: {methodology_id:methodology_id},
    success: function(data){

       //alert(data);
        $("#update_methodology_item_modal").modal("show");
    
       var jsonData = JSON.parse(data);

        
        $('#methodology_title').val(jsonData.title);
        $('#methodology_details').val(jsonData.details);
        $('#methodology_item_id').val(jsonData.id);
        
        var image = jsonData.icon;
        var image1 = image.substring(1);
        
        //alert(image1);
   
        $('div#image').css('background-image', 'url('+image1+')');
        $('div#image').show();
        

        
    }
    });
    
	$("#methodology_icon").click(function() { // bCheck is a input type button
        $('div#image').fadeOut('slow');
	});
    
    
}



/*
=====================
Update Methodology Item
=====================
*/
$(document).ready(function() {

$("#methodology_item_update_form").validate({
      rules:
   {  

    },
    submitHandler: submitForm 
       });  
    
        function submitForm(){
            $.ajax({
                url: "ajax/methodology_item.php?update",
                type: "POST",
                data: new FormData($('#methodology_item_update_form')[0]),
                contentType: false,
                processData: false,
                xhr: function(){
                    //upload Progress
                    var xhr = $.ajaxSettings.xhr();
                    if (xhr.upload) {
                        xhr.upload.addEventListener('progress', function(event) {
                            var percent = 0;
                            var position = event.loaded || event.position;
                            var total = event.total;
                            if (event.lengthComputable) {
                                percent = Math.ceil(position / total * 100);
                            }
                            
                            //update progressbar
                            $('#progress-wrp-methodology-item').css("display", "block");
                            $(".progress-bar").css("width", + percent +"%");
                            $(" .status").text(percent +"%");
                            
                        }, true);
                    }
                    return xhr;
                },                 
                success: function(data){
                    var data = data.trim();

                    if( data == "success" ){
                        //$("#update_slider_modal").modal("hide");
                        
                        $('#methodology_item_response').fadeIn().html(data);

                        setTimeout(function(){
                          $('#methodology_item_response').fadeOut('slow');
                        }, 1000);
                        $('.close').trigger('click');
                        
                        showMethodologyItem();
                        
                   }else{
                       $('#methodology_item_response').fadeIn().html(data);

                        setTimeout(function(){
                          $('#methodology_item_response').fadeOut('slow');
                        }, 1500);
                   }



                }
            });   
        }


    
});


$('#update_methodology_item_modal').on('hidden.bs.modal', function (e) {
    $('#methodology_item_update_form')[0].reset();
    $( ".thumbs li" ).hide();
    
    $('.progress-wrp').fadeOut('slow');
})

/******************END METHODOLOGY REPEATABLE ITEM JS***********************/



/*
=============    ===============
            FAQ ITEM
=============    ===============
*/

$(document).ready(function() {

$("#faqform").validate({
      rules:
	  {		
	 
	   },
	   submitHandler: submitForm	
       });  
    
        function submitForm(){
            $.ajax({
                url: "ajax/faq.php",
                type: "POST",
                data: new FormData($('#faqform')[0]),
                contentType: false,
                processData: false,
                success: function(data){
                    //alert(data);
                    var data = data.trim();
                    
                    if(data == "success"){
                       $('#faq_message').fadeIn().html("Faq content added successfully");

                        setTimeout(function(){
                          $('#faq_message').fadeOut('slow');
                        }, 1500);
                        $('.append_faq').hide();
                        
                        //$('.append_faq').remove();
                        $( ".append_faq" ).not( ".template" ).remove(  );
                        
                        $('#faqform')[0].reset();
                    }else{
                       $('#faq_message').fadeIn().html(data);

                        setTimeout(function(){
                          $('#faq_message').fadeOut('slow');
                        }, 1500);
                    }
                    
                    $("#submit_faq").attr("disabled", false);


                }
            });   
        }

$('#submit_faq').click(function() {
    $('#faqform').submit();
    $("#submit_faq").attr("disabled", true);
});
    
         
    
});



/*
===================
Display All  Faq
===================
*/
function showFaq(){
    $.ajax({
    url: "ajax/faq.php?show",
    type: "POST",
    success: function(data){
        //alert(data);
        $('#display_faq').html(data);
    }
    });    
}

showFaq();



/*
=====================
Edit Slider
=====================
*/
function editFaq(faq_id){
    var faq_id = faq_id;
    
    $.ajax({
    url: "ajax/faq.php?edit",
    type: "POST",
    data: {faq_id:faq_id},
    success: function(data){

        //alert(data);
        $("#update_faq_modal").modal("show");
    
       var jsonData = JSON.parse(data);

        
        $('#question').val(jsonData.question);
        $('#answer').val(jsonData.answer);
        $('#faq_id').val(jsonData.id);
        
  
    }
    });
    
    
}



/*
=====================
Update Faq
=====================
*/
$(document).ready(function() {

$("#faq_update_form").validate({
      rules:
   {  
     question: {
     required: true
     },
     answer: {
     required: true
     }       
    },
    submitHandler: submitForm 
       });  
    
        function submitForm(){
            $.ajax({
                url: "ajax/faq.php?update",
                type: "POST",
                data: new FormData($('#faq_update_form')[0]),
                contentType: false,
                processData: false,
                success: function(data){
                    var data = data.trim();

                    if( data == "success" ){
                        //$("#update_slider_modal").modal("hide");
                        
                        $('#faq_response').fadeIn().html(data);

                        setTimeout(function(){
                          $('#faq_response').fadeOut('slow');
                        }, 1000);
                        $('.close').trigger('click');
                        
                        showFaq();
                        
                   }else{
                       $('#faq_response').fadeIn().html(data);

                        setTimeout(function(){
                          $('#faq_response').fadeOut('slow');
                        }, 1500);
                   }



                }
            });   
        }


    
});


$('#update_faq_modal').on('hidden.bs.modal', function (e) {
    $('#faq_update_form')[0].reset();
})

/**********END  FAQ  JS***********/




/*
=============    ===============
            GETSTARTED
=============    ===============
*/

$(document).ready(function() {

$("#getstarted_form").validate({
      rules:
	  {		
         range_one: {
         required: true
         },			
         range_two: {
         required: true
         },
         range_three: {
         required: true
         },
         range_four: {
         required: true
         },
         contact_number: {
         required: true,
         number: true
         },
         contact_mail: {
         required: true,
         email: true
         },
         web_address: {
         required: true,
         url: true
         }            
	   },
	   submitHandler: submitForm	
       });  
    
        function submitForm(){
            $.ajax({
                url: "ajax/get_started.php",
                type: "POST",
                data: new FormData($('#getstarted_form')[0]),
                contentType: false,
                processData: false,
                success: function(data){
                    
                    var data = data.trim();
                    if(data == 'exist'){
  
                        $("#myModal").modal('show');
                        $('#myModal .modal-body h3').html('You already saved Get Started content');
                        $('#myModal .modal-body #error_menu').html('<button type="button" class="btn btn-default cancel_modal" data-dismiss="modal">Cancel</button><a href="display.php?getstarted" class="btn btn-default edit_content">Edit getstarted</a>');
                    }else{
                        //  alert(data);
                       $('#get_started_message').fadeIn().html(data);

                        setTimeout(function(){
                          $('#get_started_message').fadeOut('slow');
                        }, 1500);  
                    }

                }
            });   
        }

$('#getstarted_submit').click(function() {
    $('#getstarted_form').submit();
});
 
    
         
    
});





/*
===================
Display Get Started Content
===================
*/
function showGetstarted(){
    $.ajax({
    url: "ajax/get_started.php?show",
    type: "POST",
    success: function(data){
        //alert(data);
        $('#display_getstarted').html(data);
    }
    });    
}

showGetstarted();





/*
=====================
Edit Get started
=====================
*/
function editGetstarted(id){
    var id = id;
    
    $.ajax({
    url: "ajax/get_started.php?edit",
    type: "POST",
    data: {id:id},
    success: function(data){

       // alert(data);
        $("#update_getstarted_modal").modal("show");
        var jsonData = JSON.parse(data);
        
        var platforms = jsonData.platforms;
        var platformNunber = platforms.length;
        
        
        $('#range-one').val(jsonData.getstarted_data.range1);
        $('#range-two').val(jsonData.getstarted_data.range2);
        $('#range-three').val(jsonData.getstarted_data.range3);
        $('#range-four').val(jsonData.getstarted_data.range4);
        $('#contact-number').val(jsonData.getstarted_data.phone);
        $('#contact-mail').val(jsonData.getstarted_data.email);
        $('#web-address').val(jsonData.getstarted_data.web);
        $('#get_started_id').val(jsonData.getstarted_data.id);
                
        
        $( ".existing_tag" ).remove();
        $( ".client_repeat+.client_repeat" ).remove();
        if(platformNunber > 0){
            for(i=0; i<platformNunber; i++){
                $('#getstarted_repeatable_item').append('<div class="form-row existing_tag"> <div class="col-sm-4"> <input type="text" class="form-control" value="'+jsonData.platforms[i].name+'" name="platform[]" value="android" id="platform"></div><div class="col-sm-1"><span class="remove"></span></div>  </div>');
            } 
        }
        

        
    }
    });
    

    
    
}




/*
=====================
Update Getstarted
=====================
*/
$(document).ready(function() {

$("#update_getstarted_form").validate({
      rules:
   {  

    },
    submitHandler: submitForm 
       });  
    
        function submitForm(){
            $.ajax({
                url: "ajax/get_started.php?update",
                type: "POST",
                data: new FormData($('#update_getstarted_form')[0]),
                contentType: false,
                processData: false,
                success: function(data){
                    var data = data.trim();

                    if( data == "success" ){
                        //$("#update_slider_modal").modal("hide");
                        
                        $('#get_started_response').fadeIn().html(data);

                        setTimeout(function(){
                          $('#get_started_response').fadeOut('slow');
                        }, 1000);
                        $('.close').trigger('click');
                        
                        showGetstarted();
                        
                   }else{
                       $('#get_started_response').fadeIn().html(data);

                        setTimeout(function(){
                          $('#get_started_response').fadeOut('slow');
                        }, 1500);
                   }



                }
            });   
        }


    
});



/***********End  Get Started  js**************/




/*
===========================
        SOCIAL SECTION
===========================
*/

$(document).ready(function() {

$("#social_form").validate({
      rules:
	  {		
         social_image: {
         required: true
         },			
         social_name: {
         required: true
         },         
         social_url: {
             required: true
         }
				 

	   },
	   submitHandler: submitForm	
       });  
    
        function submitForm(){
            $.ajax({
                url: "ajax/social.php",
                type: "POST",
                data: new FormData($('#social_form')[0]),
                contentType: false,
                processData: false,
                xhr: function(){
                    //upload Progress
                    var xhr = $.ajaxSettings.xhr();
                    if (xhr.upload) {
                        xhr.upload.addEventListener('progress', function(event) {
                            var percent = 0;
                            var position = event.loaded || event.position;
                            var total = event.total;
                            if (event.lengthComputable) {
                                percent = Math.ceil(position / total * 100);
                            }
                            
                            //update progressbar
                            $('#progress-wrp-social').css("display", "block");
                            $(".progress-bar").css("width", + percent +"%");
                            $(" .status").text(percent +"%");
                            
                        }, true);
                    }
                    return xhr;
                },                
                success: function(data){
                    
                    var data = data.trim();    
                    //alert(data);
                   $('#social_message').fadeIn().html(data);

                    setTimeout(function(){
                      $('#social_message').fadeOut('slow');
                    }, 1500);
                    
                    
                    if( data == "Social Item Added Successfully" ){
                        $('#social_form')[0].reset(); 
                        $( ".thumbs li" ).hide();
                        $('#progress-wrp-social').fadeOut('slow');
                    }                    
                    
                }
            });   
        }

$('#submit_social').click(function() {
    
    $(".progress-bar").css("width", "0%");
    $(".status").text("0%");
    
    $('#social_form').submit();
});
    
    
$('#add_social').click(function() {
    
    $('#social_form').submit();

});    
        
    
});




/*
===================
Display All  Social
===================
*/
function showSocial(){
    $.ajax({
    url: "ajax/social.php?show",
    type: "POST",
    success: function(data){
        //alert(data);
        $('#display_social').html(data);
    }
    });    
}

showSocial();




/*
=====================
Edit Social
=====================
*/
function editSocial(social_id){
    var social_id = social_id;
    
    $.ajax({
    url: "ajax/social.php?edit",
    type: "POST",
    data: {social_id:social_id},
    success: function(data){

       //alert(data);
        $("#update_social_modal").modal("show");
  
       var jsonData = JSON.parse(data);
       
        $('#social_name').val(jsonData.name);
        $('#social_url').val(jsonData.url);
        $('#social_id').val(jsonData.id);
      
        var image = jsonData.image;
        var imagenew = image.substring(1);
        
        //alert(image1);
   
        $('div#image').css('background-image', 'url('+imagenew+')');
        $('div#image').show();
        

        
    }
    });
    
	$("#social_image").click(function() { // bCheck is a input type button
        $('div#image').fadeOut('slow');
	});
    
    
}




/*
=====================
Update Social
=====================
*/
$(document).ready(function() {

$("#social_update_form").validate({
      rules:
   {  

    },
    submitHandler: submitForm 
       });  
    
        function submitForm(){
            $.ajax({
                url: "ajax/social.php?update",
                type: "POST",
                data: new FormData($('#social_update_form')[0]),
                contentType: false,
                processData: false,
                xhr: function(){
                    //upload Progress
                    var xhr = $.ajaxSettings.xhr();
                    if (xhr.upload) {
                        xhr.upload.addEventListener('progress', function(event) {
                            var percent = 0;
                            var position = event.loaded || event.position;
                            var total = event.total;
                            if (event.lengthComputable) {
                                percent = Math.ceil(position / total * 100);
                            }
                            
                            //update progressbar
                            $('#progress-wrp-social').css("display", "block");
                            $(".progress-bar").css("width", + percent +"%");
                            $(" .status").text(percent +"%");
                            
                        }, true);
                    }
                    return xhr;
                },                
                success: function(data){
                    var data = data.trim();

                    if( data == "success" ){
                        //$("#update_slider_modal").modal("hide");
                        
                        $('#social_response').fadeIn().html(data);

                        setTimeout(function(){
                          $('#social_response').fadeOut('slow');
                        }, 1000);
                        $('.close').trigger('click');
                        
                        showSocial();
                        
                   }else{
                       $('#social_response').fadeIn().html(data);

                        setTimeout(function(){
                          $('#social_response').fadeOut('slow');
                        }, 1500);
                   }



                }
            });   
        }


    
});


$('#update_social_modal').on('hidden.bs.modal', function (e) {
    $('#social_update_form')[0].reset();
    $( ".thumbs li" ).hide();
    
    $('.progress-wrp').fadeOut('slow');
})

/*****************EDN SOCIAL JS*******************/

























/*
Delete Slider or Home page item
*/
function deleteSlider(id){
   var id = id;
    
   if(confirm("Are you sure want to delete this ?")){
       
    $.ajax({
    url: "ajax/home.php",
    type: "POST",
    data: {id:id},
    success: function(data){
        //alert(data);
        $('#tr'+id).css('background-color', '#ebf7fd');
        $('#tr'+id).fadeOut('slow');

    }
    }); 
       
   }else{
       return false;
   }

}


/*
Delete Company Info Item
*/
function deleteCompanyInfo(id){
   var id = id;
    
   if(confirm("Are you sure want to delete this ?")){
       
    $.ajax({
    url: "ajax/companyinfo.php",
    type: "POST",
    data: {id:id},
    success: function(data){
        //alert(data);
        $('#tr'+id).css('background-color', '#ebf7fd');
        $('#tr'+id).fadeOut('slow');

    }
    }); 
       
   }else{
       return false;
   }

}







/*
Delete Portfolio  Item
*/
function portfolioDelete (id){
   var id = id;
    
   if(confirm("Are you sure want to delete this ?")){
       
    $.ajax({
    url: "ajax/portfolio.php",
    type: "POST",
    data: {id:id},
    success: function(data){
        //alert(data);
        $('#tr'+id).css('background-color', '#ebf7fd');
        $('#tr'+id).fadeOut('slow');

    }
    }); 
       
   }else{
       return false;
   }

}







/*
Delete Team  Item
*/
function deleteTeam(id){
   var id = id;
    
   if(confirm("Are you sure want to delete this ?")){
       
    $.ajax({
    url: "ajax/team.php",
    type: "POST",
    data: {id:id},
    success: function(data){
        //alert(data);
        $('#tr'+id).css('background-color', '#ebf7fd');
        $('#tr'+id).fadeOut('slow');

    }
    }); 
       
   }else{
       return false;
   }

}




/*
Delete Client  Item
*/
function clientDelete(id){
   var id = id;
    
   if(confirm("Are you sure want to delete this ?")){
       
    $.ajax({
    url: "ajax/client.php",
    type: "POST",
    data: {id:id},
    success: function(data){
        //alert(data);
        $('#tr'+id).css('background-color', '#ebf7fd');
        $('#tr'+id).fadeOut('slow');

    }
    }); 
       
   }else{
       return false;
   }

}



/*
Delete Client  Item
*/
function deleteTestimonial(id){
   var id = id;
    
   if(confirm("Are you sure want to delete this ?")){
       
    $.ajax({
    url: "ajax/testimonial.php",
    type: "POST",
    data: {id:id},
    success: function(data){
        //alert(data);
        $('#tr'+id).css('background-color', '#ebf7fd');
        $('#tr'+id).fadeOut('slow');

    }
    }); 
       
   }else{
       return false;
   }

}






/*
Methodology  Item Delete
*/
function deleteTMethodology(id){
   var id = id;
    
   if(confirm("Are you sure want to delete this ?")){
       
    $.ajax({
    url: "ajax/methodology_item.php",
    type: "POST",
    data: {id:id},
    success: function(data){
        //alert(data);
        $('#tr'+id).css('background-color', '#ebf7fd');
        $('#tr'+id).fadeOut('slow');

    }
    }); 
       
   }else{
       return false;
   }

}






/*
Faq   Item Delete
*/
function deleteFaq(id){
   var id = id;
    
   if(confirm("Are you sure want to delete this ?")){
       
    $.ajax({
    url: "ajax/faq.php",
    type: "POST",
    data: {id:id},
    success: function(data){
        //alert(data);
        $('#tr'+id).css('background-color', '#ebf7fd');
        $('#tr'+id).fadeOut('slow');

    }
    }); 
       
   }else{
       return false;
   }

}







/*
Social   Item Delete
*/
function deleteSocial(id){
   var id = id;
    
   if(confirm("Are you sure want to delete this ?")){
       
    $.ajax({
    url: "ajax/social.php",
    type: "POST",
    data: {id:id},
    success: function(data){
        //alert(data);
        $('#tr'+id).css('background-color', '#ebf7fd');
        $('#tr'+id).fadeOut('slow');

    }
    }); 
       
   }else{
       return false;
   }

}



/*
Overview Content   Item Delete
*/
function deleteOverviewContent(id){
   var id = id;
    
   if(confirm("Are you sure want to delete this ?")){
       
    $.ajax({
    url: "ajax/overview.php",
    type: "POST",
    data: {id:id},
    success: function(data){
        //alert(data);
        $('#tr'+id).css('background-color', '#ebf7fd');
        $('#tr'+id).fadeOut('slow');

    }
    }); 
       
   }else{
       return false;
   }

}




/*
Sidebar Content   Item Delete
*/
function deleteSidebarContent(id){
   var id = id;
    
   if(confirm("Are you sure want to delete this ?")){
       
    $.ajax({
    url: "ajax/sidebar.php",
    type: "POST",
    data: {id:id},
    success: function(data){
        //alert(data);
        $('#tr'+id).css('background-color', '#ebf7fd');
        $('#tr'+id).fadeOut('slow');

    }
    }); 
       
   }else{
       return false;
   }

}





/*
Methodology Content   Item Delete
*/
function deleteSplash(id){
   var id = id;
    
   if(confirm("Are you sure want to delete this ?")){
       
    $.ajax({
    url: "ajax/splash.php",
    type: "POST",
    data: {id:id},
    success: function(data){
        //alert(data);
        $('#tr'+id).css('background-color', '#ebf7fd');
        $('#tr'+id).fadeOut('slow');

    }
    }); 
       
   }else{
       return false;
   }

}







/*
Methodology Content   Item Delete
*/
function deleteMethodologyContent(id){
   var id = id;
    
   if(confirm("Are you sure want to delete this ?")){
       
    $.ajax({
    url: "ajax/methodology_content.php",
    type: "POST",
    data: {id:id},
    success: function(data){
        //alert(data);
        $('#tr'+id).css('background-color', '#ebf7fd');
        $('#tr'+id).fadeOut('slow');

    }
    }); 
       
   }else{
       return false;
   }

}




/*
Methodology Content   Item Delete
*/
function deleteGetStarted(id){
   var id = id;
    
   if(confirm("Are you sure want to delete this ?")){
       
    $.ajax({
    url: "ajax/get_started.php",
    type: "POST",
    data: {getStartedId:id},
    success: function(data){
        //alert(data);
        $('#tr'+id).css('background-color', '#ebf7fd');
        $('#tr'+id).fadeOut('slow');

    }
    }); 
       
   }else{
       return false;
   }

}

/*** color change ****/
//$(document).ready(function() {
//    $('#splash-color').focus(
//        
//    function(){
//        var value = $('#splash-color').val();
//        
//         
//         $(this).css({'background-color' : value});
//
//        
//        //alert(value);
//    });
//
//
//});

//$(function() {
//       
//    //$('body').css("backgroundColor",'#' + bgColor);
//    
//    $('#splash-color').keyup(function() {
//        var bgColor = $(this).val();
//       $(this).css({"background-color" : bgColor});
//    });
//});





/*** end color change ***/



 //homepage from 
//$(document).ready(function() {
//        
//        $('#splash_form').on('submit',function(e){
//           e.preventDefault(); 
//            
//        $.ajax({
//            url: "upload.php",
//            type: "POST",
//            data: new FormData(this),
//            contentType: false,
//            processData: false,
//            success: function(data){
//                alert(data);
//
//            }
//        });    
//
//        });
//
//});
//
//
//$('#splash_submit').click(function() {
//    $('#splash_form').submit();
//});










// homepage from 
//$(document).ready(function() {
//        
//        $('#homepage').on('submit',function(e){
//           e.preventDefault(); 
//            
//        $.ajax({
//            url: "upload.php",
//            type: "POST",
//            data: new FormData(this),
//            contentType: false,
//            processData: false,
//            success: function(data){
//                alert(data);
//
//            }
//        })    
//
//        });
//
//});
//
//
//$('#homeform').click(function() {
//    $('#homepage').submit();
//});





// overview form
//$(document).ready(function() {
//        
//        $('#overviewform').on('submit',function(e){
//           e.preventDefault(); 
//            
//        $.ajax({
//            url: "upload.php",
//            type: "POST",
//            data: new FormData(this),
//            contentType: false,
//            processData: false,
//            success: function(data){
//                alert(data);
//
//            }
//        })    
//
//        });
//
//});
//
//
//$('#overview_submit').click(function() {
//    $('#overviewform').submit();
//});









