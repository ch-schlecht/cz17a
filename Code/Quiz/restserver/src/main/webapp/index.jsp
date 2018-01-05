<html>
<body>
    <h2>Gamification</h2>
    <p><a href="webapi/">API</a></p>
    <p>
    Der Client fragt über SERVERNAME/restserver/webapi... Daten von Server ab, dabei gilt:
/user --> komplette Liste Nutzerdaten 
       /{ID}            Nutzer mit der ID
       /name/{name}     Nutzer mit dem Namen
/questions --> (komplette Liste) Fragen 
      /{ID}             Frage mit der ID
      /{ID}/answers     Antworten der Frage mit der ID
      ?topic=xyz        Alle Fragen mit dem Thema xyz
      ?min=x&max=y      Alle fragen mit der (dynamischen) schwierigkeit zwischen x unn y, ist eins nicht gesetzt so       
                        wird für beide der gesetzte wert angenommen
/answers --> komplette List der Antworten
      /{ID}             Antwort mit der ID
    </p>
</body>
</html>
