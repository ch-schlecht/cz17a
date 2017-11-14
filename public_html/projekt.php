<?php

/** 
 * projekt.php
 * 
 * General Information for the project
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

<!DOCTYPE HTML>
<html>
   <script type="text/javascript">
     document.getElementById("nav_projekt").style.backgroundColor = '#D8D8D8';
    </script>
    
    <!--
            Imorted Header from header.php
            opened a <div id="content">
    -->


        <article class="article"> <!-- Infomation about the Project and meeting -->
            <h2>Das Projekt</h2>
            <p>Unser Projekt befasst sich mit der betrieblichen Weiterbildung durch Gamifiaction. </br>
            Ziel ist es eine App bzw. Weboberfl&auml;che zu entwickeln, welche es Firmen und deren Mitarbeitern erm&ouml;glicht &uuml;ber eine 
            Weiterbildungsapp ihr Wissen auszutauschen, zu pr&uuml;fen und dabei Spa&szlig; zu haben.</p>


            <p class="black-box">W&ouml;chentlicher Termin: <br/> Dienstag 15:00:00 <br/> Hainetra&szlig;e 11 - InfAI</p>



            <!-- links to under-categoris of project --> 
            <p><a href="team.php" role="button">Zum Team &raquo;</a>
            <a href="protokolle.php" role="button">Zu den Protokollen &raquo;</a>
            <a href="aufwand.php" role="button">Zu den Aufwandsanalysen &raquo;</a></p>

        </article>

        <article class="article">   <!-- short description of the project -->
            <h2>Richtung des Projektes</h2>
            <p>
                <b>"Gamifizierung" des Spiels:</b><br/>
                    Das eigentliche Wissensquiz wird zu Spiel mit strategischen und zuf&auml;lligen Elementen.
                    Sprich, es sollen Mechanismen entwickelt werden, die eine gewisse Zuf&auml;lligkeit beinhaltet und strategisches Vorgehen (Antworten) notwendig macht um zu gewinnen.
                    Damit gewinnt nicht der Kl&uuml;gste, sondern der mit Gl&uuml;ck und Strategie.
                    Ziel ist es einen solchen Mechanismus sowie einen Feedbackmechanismen zur F&ouml;rderung von strategischen Vorgehen zu entwickeln. 
                    Hei&szlig;t also, es soll eine oder mehrere "Spielvarianten" entwickelt werden UND es sollen Dashboards o.&auml;. entwickelt werden, 
                    welche das Verhalten auf diesen Spielvarianten visualisieren.
                    In der Recherchephase wird zu evaluieren sein, welche Spielvarianten denkbar sind, hiervon soll mindestens eine gemeinsam mit dem "Auftraggeber" abgestimmt 
                    und umgesetzt werden. Gleiches gilt f&uuml;r die Visualisierung, welche abh&auml;ngig von der Spielvariante ist. 
                    Grunds&auml;tzliche werden in der Recherchephase zun&auml;chst nur Feedbackmechanismen zu sammeln sein.
            </p>
        </article>



        </div>
    </body>

</html>