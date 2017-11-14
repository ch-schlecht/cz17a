<?php 
$file = "team.php";

$text = '/'.$_POST['search'].'/';
// Text bzw. String mit file_get_contents() holen 
// andere Möglichkeit wäre mit fopen() oder CURL 
$string = file_get_contents($file);


// RegEx mit preg_match_all() auswerten 
preg_match_all($text, $string, $array, PREG_OFFSET_CAPTURE);



// formatierte Ausgabe 
echo '<pre>', print_r($array, true), '</pre>';

 ?>