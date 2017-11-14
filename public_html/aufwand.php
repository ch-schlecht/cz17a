<?php

/**
 * aufwand.php
 *
 * Time of Members Spend for Project
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

session_start();
include 'header.inc.php';





?>

<html>
   <script type="text/javascript">
     document.getElementById("nav_projekt").style.backgroundColor = '#D8D8D8';       //team is an under category of project --> color project in navbar
    </script>

        
    <!--
            Imorted Header from header.php
            opened a <div id="content">
    -->

   <?php 
   
   
if (file_exists('Aufwand.xml')) {
   $xml = simplexml_load_file('Aufwand.xml');

   /*für alle Analysen*/
   foreach($xml->Analyse as $Analyse){
        echo '<article class="article">';
        echo "<h2>Analyse von ".$Analyse['von']." bis ".$Analyse['bis']."</h2>";
        echo"<table>";
        echo"<tr><th>Mitglieder</th><th>Thema</th><th>Aufwand</th><th>Schwierigkeit</th><th>Zeit</th></tr>";


        foreach($Analyse->done as $done){
            echo "<tr>";
            echo "<td>".$done['who']."</td>";
            echo "<td>".$done."</td>";
            echo"<td>".$done['A']."</td>";
            echo"<td>".$done['S']."</td>";
            echo"<td>".$done['Zeit']."</td>";

            echo"</tr>";
        }

       echo "</table>";

        echo "</article>";
   }

    /*Für alle Dones*/
    echo $xml->done[0]['who'];
    echo $xml->done[0]['A'];
    echo $xml->done[0]['S'];
    echo $xml->done[0]['Zeit'];
    echo $xml->done[0];

} else {
   exit('Konnte Datei nicht laden.');
}
   
    ?>
        </div>
    </body>
        
</html>
