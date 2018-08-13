insert into fixture (id, date, status, matchday, homeTeamName, awayTeamName, goalsHomeTeam, goalsAwayTeam, halfTime)
values (1, 123456789, 'IN_PLAY', 1, 'Poland', 'Senegal', 5, 0, '2:0')
;
insert into fixture (id, date, status, matchday, homeTeamName, awayTeamName, goalsHomeTeam, goalsAwayTeam, halfTime)
values (2, 223456789, 'TIMED', 1, 'Poland', 'Uruguay', 3, 1, '1:1')
;
insert into fixture (id, date, status, matchday, homeTeamName, awayTeamName, goalsHomeTeam, goalsAwayTeam, halfTime)
values (3, 323456789, 'FINISHED', 1, 'Poland', 'Japan', 2, 2, '0:1')
;

insert into "group" (id, letter)
values (1, 'A')
;
insert into "group" (id, letter)
values (2, 'B')
;
insert into "group" (id, letter)
values (3, 'C')
;
insert into "group" (id, letter)
values (4, 'D')
;
insert into "group" (id, letter)
values (5, 'E')
;
insert into "group" (id, letter)
values (6, 'F')
;
insert into "group" (id, letter)
values (7, 'G')
;

insert into player (id, name, position, jerseyNumber, teamName)
values (1, 'Krzysztof Kwiecinski', 'Midfielder', 22, 'Poland')
;
insert into player (id, name, position, jerseyNumber, teamName)
values (2, 'Robert Lewandowski', 'Striker', 10, 'Poland')
;
insert into player (id, name, position, jerseyNumber, teamName)
values (3, 'Wojciech Szczesny', 'Goalkeeper', 1, 'Poland')
;

insert into standings (id, rank, team, teamId, playedGames, points, goals, goalsAgainst, goalDifference, groupLetter)
values (1, 1, 'Poland', 1, 1, 3, 5, 0, 5, 'A')
;
insert into standings (id, rank, team, teamId, playedGames, points, goals, goalsAgainst, goalDifference, groupLetter)
values (2, 2, 'Uruguay', 2, 2, 1, 3, 2, 1, 'A')
;
insert into standings (id, rank, team, teamId, playedGames, points, goals, goalsAgainst, goalDifference, groupLetter)
values (3, 3, 'Japan', 3, 1, 1, 1, 3, -1, 'A')
;
insert into standings (id, rank, team, teamId, playedGames, points, goals, goalsAgainst, goalDifference, groupLetter)
values (4, 4, 'Senegal', 4, 1, 0, 0, 6, -6, 'A')
;

insert into team (id, teamId, name, code, crest_url)
values (1, 1, 'Poland', 'PL', "https://upload.wikimedia.org/wikipedia/en/1/12/Flag_of_Poland.svg")
;
insert into team (id, teamId, name, code, crest_url)
values (2, 2, 'Uruguay', 'UR', "https://upload.wikimedia.org/wikipedia/commons/f/fe/Flag_of_Uruguay.svg")
;
insert into team (id, teamId, name, code, crest_url)
values (3, 3, 'Japan', 'JA', "https://upload.wikimedia.org/wikipedia/en/9/9e/Flag_of_Japan.svg")
;
insert into team (id, teamId, name, code, crest_url)
values (4, 4, 'Senegal', 'SE', "https://upload.wikimedia.org/wikipedia/commons/f/fd/Flag_of_Senegal.svg")
;





