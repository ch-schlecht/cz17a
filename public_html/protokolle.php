<?php
/**
 * protokolle.php
 *
 * Collection of Protocoll
 *
 * @category    Information
 * @package     public_html
 * @author      Michael Fritz <mf35luzo@studserv.uni-leipzig.de>
 * @copyright   2017 Michael Fritz
 * @version     1.0.0
 * @since       File avaible since Release 1.0.0
 *
 *
*/

include 'header.inc.php';


$termin = array("07-11-2017","14-11-2017","21-11-2017","28-11-2017","05-12-2017","12-12-2017");

?>

<!DOCTYPE HTML>
<html>
   <script type="text/javascript">
     document.getElementById("nav_projekt").style.backgroundColor = '#D8D8D8';
    </script>
    
    
    <!--
            Imorted Header from header.php
            opened a <div id="content">
    -->

    

    <article class="article" id="uebersicht">  <!--  Links to all Protokolls-->
       <h2>&Uuml;bersicht</h2>
       <?php
       $i =1;
       while($i <= sizeof($termin)){
        echo '<p><a href="#'.$i.'" role="button">Sitzung '.$i.' &raquo;</a> vom '.$termin[$i-1].'</p>';
        $i++;   
       } 
       ?> 
     
    </article>


    <?php
    $i = 1; 
    while($i <= sizeof($termin)){
        
    
    
      echo '
      <article class="article anchor" id="'.$i.'">   
      ';
    
    include 'protokolle/protokoll_'.$i.'.html';
    
    echo'
    </article>
    ';
    $i++;
    }
     ?>



         </div>
    </body>

</html>
