# LSMSD-Project
<h2>Brief description</h3>
<p>
  BGNet is an application for all board-games players. People can find here a place in which follow games, users, finding common interests and join tournaments.
</p>
<p>
  Games' pages are the core of the application, a user can follow the page, see posts about the game or a detailed description of it.
</p>
<p>
  The game page features a marketplace where you can offer or accept items related to the game, such as secondary parts or covers. In addition, you can participate in tournaments for the game by checking the tournaments section.
</p>
<p>
  Apart that, common social network features like games / users recomendation are still present
</p>
<h2>Dataset description</h2>
<p>
  Source: https://boardgamegeek.com/, https://it.boardgamearena.com/
 <p>
<p>
  Description / Variety:
  <ul>
    <li>Game’s information: boardgamegeek.com</li>
    <li>Game’s Post: boardgamegeek.com</li>
    <li>Comments: boardgamegeek.com</li>
    <li>Users: boardgamegeek.com</li>
    <li>Tournaments: boardgamearena.com / boardgamegeek.com </li>
   </ul>
  Volume: 500/600 Mb
 </p>
<h2>Software architecture</h2>
<p>
  BGNet was deployed as a web application. Server logic was developed using Java.
</p>
<p>
  We adopted Python to develop data scraping modules. Also, Python was used to normalize data and to retrieve some useful statistics from normalized data (i.e. mean number of post for each user, mean number of active tournaments for a period, ecc.)
</p>
<p>
  DBMS used: MongoDB and Neo4J.
</p>
