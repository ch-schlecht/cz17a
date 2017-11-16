<?php 
/**
 * header.inc.php
 *
 * Header containing navbar for every file at server
 *
 * @category    Include
 * @package     public_html
 * @author      Michael Fritz <mf35luzo@studserv.uni-leipzig.de>
 * @copyright   2017 Michael Fritz
 * @version     1.0.0
 * @since       File avaible since Release 1.0.0
 *
 *
*/

 ?>

<!DOCTYPE HTML>
<html>
    <header>
        <title>Gamification</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no", charset="utf-8"/>   <!-- scale to device -->
        <link rel="stylesheet" media="(orientation: portrait)" type="text/css" href="style_mobile.css" /> <!-- load mobile css on all horizontal-orientated devices-->
        <link rel="stylesheet"  media="(orientation: landscape)" type="text/css" href="style.css"/>    <!-- load other css for vertial-orientated devices (like pc) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>   <!-- include jquery -->
    </header>
    <body>
    
        <nav class="navbar navbar-fixed-top">   <!-- navbar, fixed on top -->
            <div class="navbar-content">
                <ul>                                                         
                   <li class="nav" id="nav_name">Gamification</li>
                    <li><a class="nav" id="nav_home" href="index.php">Home</a></li>
                    <li class="dropdown"><a class="nav" id="nav_projekt"  class="dropbtn" href="projekt.php">Das Projekt</a>  <!-- Dropdown  Menu -->
                    <div class="dropdown-content"> <!-- content under Projekt -->
                        <a class="nav" href="protokolle.php">Protokolle</a>
                        <a class="nav" href="team.php">Das Team</a>
                        <a class="nav" href="aufwand.php">Aufwand</a>
                    </div>
                    </li>
                  <!--
                    <li class="nav">
                        <form action="filter.php" method="post">
                        <input type="Text" name="search"/>
                        <input type="Submit" value="Suche"/>
                        </form></li>
                      -->
                    <li id="git"><a class="nav" href="https://git.informatik.uni-leipzig.de/swp17/cz17a"><img width="18" height="18" src="res/gitlab.svg"/></a></li> <!-- link to git -->
                </ul>
                </div>
            </nav>

    
    <div class="content">

       
       <noscript>               <!-- Information for users who deactivate JavaScript-->
       <div id="info">Ohne JavaScript ist die Seite m&ouml;glicherweise eingeschr&auml;nkt</div>
       </noscript>
         
    </body>
</html>