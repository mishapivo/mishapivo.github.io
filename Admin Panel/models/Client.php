<?php 
class Client extends ActiveRecord\Model
{
   # explicit table name since our table is not "client" 
   static $table_name = 'client';

}


?>