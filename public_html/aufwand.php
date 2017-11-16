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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script> 

        
    <!--
            Imorted Header from header.php
            opened a <div id="content">
    -->

   <?php 
   
$i = 0;  

$gesamt = 0;

if (file_exists('Aufwand.xml')) {
   $xml = simplexml_load_file('Aufwand.xml');

   /*für alle Analysen*/
   foreach($xml->Analyse as $Analyse){
       
        $FF = 0.0;
        $CS = 0.0;
        $SL = 0.0;
        $LV = 0.0;
        $TG = 0.0;
        $MF = 0.0;
        $FZ = 0.0;
        $WS = 0.0;

       

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


            $user = explode(",",$done['who']);
            foreach($user as $p){
                             //TODO wenn mehrere
            switch($p){
                case "FF":
                    $FF +=(double)$done['Zeit'];
                    break;
                case "CS":
                    $CS +=(double)$done['Zeit'];
                    break;
                case "SL":
                    $SL += (double)$done['Zeit'];
                    break;
                case "LV":
                    $LV += (double)$done['Zeit'];
                    break;
                case "TG":
                    $TG += (double)$done['Zeit'];
                    break;
                case "MF":
                    $MF += (double)$done['Zeit'];
                    break;
                case "FZ":
                    $FZ += (double)$done['Zeit'];
                    break;
                case "WS":
                    $WS += (double)$done['Zeit'];
                    break;
                }
            }

            $gesamt += (double)$done['Zeit'];

            echo"</tr>";
        }

       echo "</table>";
       
       echo "<p><b>Gesamt: ".$gesamt." h</b></p>";
       
       echo "<div class='canvas-container'><canvas id='chart".$i."'></canvas></div>";
       
       echo "
       <script type='text/javascript'>
       var ctx = document.getElementById('chart".$i."').getContext('2d');
       var myPieChart = new Chart(ctx,{
    type: 'pie',
    data: {
        labels: ['FF','CS','SL','LV','TG','MF','FZ','WS'],
        datasets: [{
            label: 'Pie-Chart',
            backgroundColor: ['rgb(255,255,255)','rgb(255,0,0)','rgb(0,255,0)','rgb(255,255,50)','rgb(255,128,0)','rgb(0,0,255)','rgb(51,255,255)','rgb(255,0,255)'],
            borderColor: 'rgb(0,0,0)',
            data: [".$FF.",".$CS.",".$SL.",".$LV.",".$TG.",".$MF.",".$FZ.",".$WS."]
        }]
    },
    options: {responsive: true,
    maintainAspectRatio: false
    }
});
       
       
     </script>  
       
       ";

        echo "</article>";
        $i++;
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
