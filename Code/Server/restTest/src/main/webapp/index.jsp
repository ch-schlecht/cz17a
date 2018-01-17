<html>
<body>
    <h2>Gamification</h2>
    <p><a href="webapi/">API</a></p>
    <p>
    
    Der Client fragt über SERVERNAME/restserver/webapi... Daten von Server ab, dabei gilt:
<li>/user --> komplette Liste Nutzerdaten<ul> 
       <li>/{ID}            Nutzer mit der ID</li>
       <li>/name/{name}     Nutzer mit dem Namen</li></ul></li>
<li>/questions --> (komplette Liste) Fragen<ul> 
      <li>/{ID}             Frage mit der ID</li>
      <li>/{ID}/answers     Antworten der Frage mit der ID</li>
      <li>?topic=xyz        Alle Fragen mit dem Thema xyz</li>
      <li>?min=x&max=y      Alle fragen mit der (dynamischen) schwierigkeit zwischen x unn y, ist eins nicht gesetzt so       
                        wird für beide der gesetzte wert angenommen</li>
<li>/answers --> komplette List der Antworten</li>
      <li>/{ID}             Antwort mit der ID</li></ul>
    </p>
</body>
</html>
