<html>
<body>
  	<h1>REST-API Description</h1>
	<p>REST calls are to be made under the following URL:</p>
	<p>pcai042.informatik.uni-leipzig.de:1810/restserver/webapi...</p>
	<ul>
		<li>/quizzes ... gets all quizzes in&nbsp;<strong>JSON</strong>
			<ul>
				<li>/{id} ... gets the quiz associated with the ID in&nbsp;<strong>JSON</strong>
					<ul>
						<li>/random_questions ... gets random questions from the quiz associated with the ID in&nbsp;<strong>JSON</strong></li>
					</ul>
				</li>
			</ul>
		</li>
		<li>/questions/{id}/answers ... gets the answers of the question associated with the ID in&nbsp;<strong>JSON</strong></li>
		<li>/answers ... gets all answers in&nbsp;<strong>JSON</strong>
			<ul>
				<li>/{id} ... gets the answer associated with the ID in&nbsp;<strong>JSON</strong></li>
			</ul>
		</li>
	</ul>
</body>
</html>
