<html>
<body>
  	<h1>REST-API Description</h1>
	<p>REST calls are to be made under the following URL:</p>
	<p>pcai042.informatik.uni-leipzig.de:1810/restserver/webapi...</p>
	<ul>
		<li>/quizzes ... <strong>GET</strong>&nbsp;all quizzes in&nbsp;<strong>JSON</strong>
			<ul>
				<li>/{id} ... <strong>GET</strong> the quiz associated with the ID in&nbsp;<strong>JSON</strong>
					<ul>
						<li>/random_questions ... <strong>GET</strong> random questions from the quiz associated with the ID in&nbsp;<strong>JSON</strong></li>
					</ul>
				</li>
			</ul>
		</li>
		<li>/questions ...&nbsp;<strong>GET</strong> all questions in&nbsp;<strong>JSON</strong>
			<ul>
				<li>/{id} ...&nbsp;<strong>GET&nbsp;</strong>the question associated with the ID in&nbsp;<strong>JSON</strong>
					<ul>
						<li>/answers ... <strong>GET</strong> the answers of the question associated with the ID in&nbsp;<strong>JSON</strong></li>
					</ul>
				</li>
				<li>/played/{game_id}/{question_id}/{player_id} ...&nbsp;<strong>POST</strong>, add a PlayedQuestion</li>
			</ul>
		</li>
		<li>/answers ... <strong>GET</strong> all answers in&nbsp;<strong>JSON</strong>
			<ul>
				<li>/{id} ... <strong>GET</strong> the answer associated with the ID in&nbsp;<strong>JSON</strong></li>
			</ul>
		</li>
		<li>/Lobbies<br />
			<ul>
				<li>/{quiz_id}/join/{user_id}/{port} ... <strong>POST</strong>,&nbsp;join a lobby</li>
				<li>/{quiz_id}/leave/{user_id} ... <strong>POST</strong>,&nbsp;leave a lobby</li>
			</ul>
		</li>
		<li>/players
			<ul>
				<li>/register ... <strong>POST</strong>,&nbsp;register a player to DB, HTTP body has to be JSON in format {"mail":" ", "nickname":" ", "password": " "}</li>
				<li>/login ... <strong>POST</strong>,&nbsp;login as a player, HTTP body has to be JSON with the same format as above</li>
				<li>/logout/{name} ... <strong>POST</strong>,&nbsp;logout as a player</li>
				<li>/forgotPassword ...&nbsp;<strong>POST</strong>, sends an email to the User to reset his password, HTTP body has to JSON in format {"mail":" ", "nickname":" ","password":" ","salt": " "}</li>
				<li>/{id} ...&nbsp;<strong>DELETE</strong> the player from DB associated with the ID</li>
			</ul>
		</li>
	</ul>
</body>
</html>
