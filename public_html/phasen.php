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

    <article class="article">
    
        <h2>Fortschritt</h2>
        
        <div class="bar">
            <div class="bar-el" id="A1">A1</div>
            <div class="bar-el" id="A2">A2</div>
            <div class="bar-el" id="A3">A3</div>
            <div class="bar-el" id="M1">M1</div>
            <div class="bar-el" id="A4">A4</div>
            <div class="bar-el" id="R1">R1</div>
            <div class="bar-el" id="M2">M2</div>
            <div class="bar-el" id="R2">R2</div>
            <div class="bar-el" id="R3">R3</div>
            <div class="bar-el" id="R4">R4</div>
            <div class="bar-el" id="R5">R5</div>
        </div>
        
        <br/>
        <p>
        Die erste Phase und Abgabe <b>A1</b> wurde <font color="green">abgeschlossen</font>   <br/>
        Aspekte dieser Phase waren: <br/>
        <li>Gruppen findung</li>
        <li>Einrichten der Projekt umgebung &raquo; Git</li>
        <li>Aufsetzten der Website</li>
        <li>Vergabe der Rollen</li>
        </p>

    </article>
    
    </div>
</body>
</html>