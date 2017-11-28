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
       <p><a href="#2" role="button">Sitzung 2 &raquo;</a> vom 14-11-2017</p>
       <p><a href="#3" role="button">Sitzung 3 &raquo;</a> vom 21-11-2017</p>
    </article>



    <article class="article anchor" id="1">    <!-- First meeting -->
          
          <?php include 'protokolle/protokoll_1.html'?>

    </article>
    
    <article class="article anchor" id="2">    <!-- Second meeting -->

       
          
        <?php include 'protokolle/protokoll_2.html'?>

    </article>
    
    <article class="article anchor" id="3">    <!-- Third meeting -->


        <?php include 'protokolle/protokoll_3.html'?>

    </article>

    
    

         </div>
    </body>

</html>
