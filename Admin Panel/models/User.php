<?php 
class User extends ActiveRecord\Model
{
   # explicit table name since our table is not "overviews" 
   static $table_name = 'users';

}


?>