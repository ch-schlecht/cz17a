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


session_start();
if(!isset($_SESSION['old'])){
    $_SESSION['old'] = false;   
}

if(isset($_GET['old'])){
    if($_GET['old'] === "true"){
        $_SESSION['old'] = true;
    }
    else{
        $_SESSION['old'] = false;
    }
    
}


 ?>

<!DOCTYPE HTML>
<html lang="de">
    <header>
        <title>Gamification</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"; charset="utf-8"/>   <!-- scale to device -->
        <link rel="stylesheet" media="(orientation: portrait)" type="text/css" href="style_mobile.css" /> <!-- load mobile css on all horizontal-orientated devices-->
<?php
  
if(isset($_SESSION['old']) && $_SESSION['old'] == true){
    echo '        <link rel="stylesheet"  media="(orientation: landscape)" type="text/css" href="style_old.css"/>';
}else{
    echo ' <link rel="stylesheet"  media="(orientation: landscape)" type="text/css" href="style.css"/>';

}

?>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>   <!-- include jquery -->
    </header>
    <body>
    
        <nav class="navbar navbar-fixed-top">   <!-- navbar, fixed on top -->
            <div class="navbar-content">
                <ul>                                                         
                   <li class="nav" id="nav_name">Gamification</li>
                    <li><a class="nav" id="nav_home" href="index.php">Home</a></li>
                    <li class="dropdown"><a class="nav" id="nav_member"  class="dropbtn" href="team.php">Unser Team</a>  <!-- Dropdown  Menu -->
                    <div class="dropdown-content"> <!-- content under Projekt -->
                        <a class="nav" href="aufwand.php">Aufwand</a>
                    </div>
                    </li>
                    
                    <li class="dropdown"><a class="nav" id="nav_projekt"  class="dropbtn" href="projekt.php">Das Projekt</a>  <!-- Dropdown  Menu -->
                    <div class="dropdown-content"> <!-- content under Projekt -->
                        <a class="nav" href="protokolle.php">Protokolle</a>
                        <a class="nav" href="phasen.php">Phasen</a>
                        <a class="nav" href="abgabe.php">Abgaben</a>
                    </div>
                    </li>
                    
                    <div class="right">
                    <li id="git">
                    <?php 
                        if(isset($_SESSION['old']) && $_SESSION['old'] == true){
                          echo '<a class="nav" href="?old=false">Neues Design</a>';

                        }else{
                          echo '<a class="nav" href="?old=true">Altes Design</a>';
    
                        }                    
                     ?>
                     </li>
                    
                  <!--
                    <li class="nav">
                        <form action="filter.php" method="post">
                        <input type="Text" name="search"/>
                        <input type="Submit" value="Suche"/>
                        </form></li>
                      -->
                    <li id="git"><a class="nav" href="https://git.informatik.uni-leipzig.de/swp17/cz17a"><img width="18" height="18" src="res/gitlab.svg"/></a></li> <!-- link to git -->
                </div>
                </ul>
                </div>
            </nav>

    
    <div class="content">

       
       <noscript>               <!-- Information for users who deactivate JavaScript-->
       <div id="info">Ohne JavaScript ist die Seite m&ouml;glicherweise eingeschr&auml;nkt</div>
       </noscript>
         
    </body>
</html>