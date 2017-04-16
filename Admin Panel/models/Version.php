<?php 
class Version extends ActiveRecord\Model
{
   # explicit table name since our table is not "versions" 
   static $table_name = 'version';
    
    

    public static function versionCode(){
        $row = self::find('all');
        $rowCount = count($row);
        
        if($rowCount > 0){
            $last_row = self::first();
            $version_code = $last_row->version_code;
            $version_code += 1;
            
            $attributes = array('version_code' => $version_code);
            $update_version = $last_row->update_attributes($attributes);
        }else{
            $attributes = array('version_code' => 1);
            self::create($attributes);
        }
    }

}


?>