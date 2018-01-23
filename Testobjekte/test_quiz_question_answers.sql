insert into Quiz (id, title, length, min_participants, max_participants)
values ('1','test','10','1','5');

insert into Quiz (id, title, length, min_participants, max_participants)
values ('2','test2','10','1','5');

insert into Question(id, response_time, questioning, dynamic_difficulty, static_difficulty, topic, quiz_id)
values ('1','60','Wer bich ich?','1','1','testen','1');

insert into Question(id, response_time, questioning, dynamic_difficulty, static_difficulty, topic, quiz_id)
values ('2','60','Was bich ich?','1','1','testen2',2);

insert into Answer (question_id, id, content, type)
values ('1','1','Der Nikolaus','true');

insert into Answer (question_id, id, content, type)
values ('1','2','Der Weinachtsmann','false');

insert into Answer (question_id, id, content, type)
values ('2','3','Ein Auto','true');

insert into Answer (question_id, id, content, type)
values ('2','4','Ein Kreisel','false');

