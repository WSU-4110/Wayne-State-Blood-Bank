<?php 
  
header("Content-Type: application/json"); 
  
$data = json_decode(file_get_contents("php://input")); 
  
echo "Hello $data->fname, your email is $data->email"; 
  
?> 