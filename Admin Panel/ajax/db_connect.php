<?php 
    require_once '../lib/php-activerecord/ActiveRecord.php';

    ActiveRecord\Config::initialize(function($cfg)
    {
        $cfg->set_model_directory('../models');
        $cfg->set_connections(array(
            'development' => 'mysql://wwwaudac_check:^&Jv2h@CW)&C@localhost/wwwaudac_check?charset=utf8'));
    });

?>