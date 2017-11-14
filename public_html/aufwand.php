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
//include 'header.inc.php';


if (file_exists('aufwand.xml')) {
   $xml = simplexml_load_file('aufwand.xml');

   /*für alle Analysen*/

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

<html>
   <script type="text/javascript">
     document.getElementById("nav_projekt").style.backgroundColor = '#D8D8D8';       //team is an under category of project --> color project in navbar
    </script>


    <!--
    <script type="text/javascript">//<![CDATA[
        window.onload = function(){
            
        // Create a connection to the file.
        var Connect = new XMLHttpRequest();
        // Define which file to open and send the request.
        Connect.open("GET", "Aufwand.xml", false);
        Connect.setRequestHeader("Content-Type", "text/xml");
        Connect.send(null);
        // Place the response in an XML document.
        var TheDocument = Connect.responseXML;
        // Place the root node in an element.
        var Rows = TheDocument.childNodes[0];
        // Retrieve each customer in turn.
        for (var i = 0; i < Rows.children.length; i++){
            var Row = Rows.children[i];
            // Access each of the data values.
            var who = Row.getElementsByTagName("who");
            var A = Row.getElementsByTagName("A");
            var S = Row.getElementsByTagName("S");
            var Zeit = Row.getElementsByTagName("Zeit");
            // Write the data to the page.
            document.write("<tr><td>");
            document.write(who[0].textContent.toString());
            document.write("</td><td>");
   document.write(A[0].textContent.toString());
   document.write("</td><td>");
   document.write(S[0].textContent.toString());
   document.write("</td><td>");
   document.write(Zeit[0].textContent.toString());
   document.write("</td></tr>");
  }
  alert("Done");    
        }
//]]>        
</script>

        -->
        
    <!--
            Imorted Header from header.php
            opened a <div id="content">
    -->

    <!--
        <article class="article" id="team"> <!-- table of team-members an positions 
            <h2>Phase A1</h2>
            
            <p>Anfang bis 13-11-17</p>
            
            <table>
            <tr>
                <th>Wer?</th>
                <th>Aufwand</th>
                <th>Schwierigkeit</th>
                <th>Zeit</th>
            </tr>
            <!-- HERE 
            
            
            </table>


        </article>
        -->
        </div>
    </body>
        
</html>
