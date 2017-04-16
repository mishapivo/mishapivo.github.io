<?php
    // base url
    $base_url = "http://audacityit.com/profile";

    // sanitize input string
    function sanitize($string){
        $string = trim($string);
        $string = htmlspecialchars($string);
        return $string;
    }



	function bodyClass(){
		$url = $_SERVER['PHP_SELF'];
		$url = explode("/",$url);
		$url = end($url);
		return $url;
	}




?>