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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>  <!--scripts for charts (used pie-chart) -->
    <script type="text/javascript">   //script for toggle display of pie-chart
        $(document).ready(function(){
	       $("dt").click(function(e){ // trigger
		      $(this).next("dd").slideToggle("fast"); // enables next "dd" at click on "dt"
		      $(this).children("a").toggleClass("closed open"); // changed class of a in "dt" from "closed" to "open"
	   
	           e.preventDefault(); //remove jump effect of a href
            });
        });
    </script>
   
    <!--
            Imorted Header from header.php
            opened a <div id="content">
    -->

<?php 
   
$i = 0;  //counter for number of "analysen"
$gesamt = 0;  //time spend by all (team time only counts once)
$team = 0;    //time spendet as team (min. 6 people)
$time = 0;    //var for cast time out of xml




if (file_exists('Aufwand.xml')) {
   $xml = simplexml_load_file('Aufwand.xml'); //load xml

   /*for all Analysen*/
   foreach($xml->Analyse as $Analyse){
       
       /*Time spendet of every one in this week*/
        $FF = 0.0;
        $CS = 0.0;
        $SL = 0.0;
        $LV = 0.0;
        $TG = 0.0;
        $MF = 0.0;
        $FZ = 0.0;
        $WS = 0.0;
        $team = 0.0;
        $geasmt = 0.0;

       

        echo '<article class="article">';                                                           //create article for ever week
        echo "<h2 id='analyse'>Analyse von ".$Analyse['von']." bis ".$Analyse['bis']."</h2>";       //Title of article
        echo"<table>";                                                                              //start table
        echo"<tr><th>Mitglieder</th><th>Thema</th><th>Aufw.</th><th>Schw.</th><th>Zeit</th></tr>"; //Table header


        foreach($Analyse->done as $done){  //for every solved task
            $time = (double)$done['Zeit']; //cast time to double
            
            /*basic info into table*/
            echo "<tr>";
            echo "<td>".$done['who']."</td>"; 
            echo "<td>".$done."</td>";
            echo"<td>".$done['A']."</td>";
            echo"<td>".$done['S']."</td>";
            if($time < 1){         //if time spendet is less then 1 hour
                echo "<td>".($time*60)."m</td>";  //calculate minutes
            }
            else{
                echo"<td>".$time."h</td>"; //else write in hours   
            }
            
            

            $user = explode(",",$done['who']);
            foreach($user as $p){ //for every one worked on one task
                             
            switch($p){
                case "FF":
                    $FF +=$time;
                    break;
                case "CS":
                    $CS +=$time;
                    break;
                case "SL":
                    $SL +=$time;
                    break;
                case "LV":
                    $LV +=$time;
                    break;
                case "TG":
                    $TG +=$time;
                    break;
                case "MF":
                    $MF +=$time;
                    break;
                case "FZ":
                    $FZ +=$time;
                    break;
                case "WS":
                    $WS +=$time;
                    break;
                }
            }

            $gesamt +=$time;

            if(sizeof($user) >= 6){
                $team += $time;
            }

            echo"</tr>";
        }

       echo "</table>";
       
       echo "<p><b>Gesamt: ".$gesamt." h</b>  ||  <b>Team: ".$team." h</b></p>";
       
       echo "<dt><a id='switch' href='#' class='closed'><div id='sign'>&#9658;</div> Diagramm<hr style='display:inline-block; vertical-align: middle;  width:40vw'/></a></dt>";
       echo "<dd><div class='canvas-container'><canvas id='chart".$i."'></canvas></div></dd>";
       
       echo "
       <script type='text/javascript'>
       var ctx = document.getElementById('chart".$i."').getContext('2d');
       var myPieChart = new Chart(ctx,{
    type: 'pie',
    data: {
        labels: ['FF','CS','SL','LV','TG','MF','FZ','WS'],
        datasets: [{
            label: 'Pie-Chart',
            backgroundColor: ['rgb(200,200,200)','rgb(200,0,0)','rgb(0,200,0)','rgb(200,200,50)','rgb(200,120,0)','rgb(0,0,200)','rgb(45,200,200)','rgb(200,0,200)'],
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

} else {
   exit('Konnte Datei nicht laden.');
}
   
    ?>
        </div>
    </body>
        
</html>
