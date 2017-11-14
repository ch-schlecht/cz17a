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

session_start();
include 'header.php';

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
       <p><a href="#1" role="button">Sitzung 1 &raquo;</a> vom 07-11-2017</p>
    </article>



    <article class="article anchor" id="1">    <!-- First meeting -->
            <h2>Sitzung 1</h2>
            
            <p class="black-box"> <!-- Basic Information of Protocoll -->
              Ort:           Hainestra&szlig;e 11 - InfAI <br/>
              Datum:         Di, 07-11-2017 <br/>
              Uhrzeit:       16:00:00 <br/>
              Protokoll von: Christian Schlecht <br/>
            </p>
            
            
            <p>
            <b>Allgemein:</b><br/>
                -Kennenlernrunde  <br/>
                -Deadline der ersten Aufgabe: Montag 13-11-17 23:59:00 <br/>
                -St&auml;rken und Schw&auml;chen im Bezug auf Organisation, Strukturierung, Programmierung als Datei im Git <br/>
                -Git: Alle m&uuml;ssen einen Commit gemacht haben <br/>
                -Rollenverteilung muss gekl&auml;rt sein <br/>
                -Projektleiter vorerst: Christian Schlecht <br/>
                <br/>
            <b>Aufgaben:</b><br/>
                -Webseite starten (darauf Projektbeschreibung, Teamzusammensetzung) <br/>
                -Dokument mit St&auml;rken und Schw&auml;chen <br/>
                -Neue Issues, Meilenstein <br/>
                -Doodle f&uuml;r Pr&auml;senstermin <br/>
                <br/>
            <b>Themen:</b><br/>
                -Soziale Netzwerke innerhalb von Unternehmen<br/>
                -Quiz hat hohe Attraktivit&auml;t in der Gesellschaft<br/>
                <br/>
            <b>Fragestellungen zum n&auml;chsten Treffen:</b><br/>
                -Entweder Quizapp oder ContentCreation (genauere Beschreibung in der Mail vom Betreuer)<br/>
                -Gedanken machen, welche der beiden Richtungen<br/>
            </p>

    </article>

         </div>
    </body>

</html>
