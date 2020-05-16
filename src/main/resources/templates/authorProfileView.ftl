<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/css/mainStyle.css">
    <link rel="stylesheet" type="text/css" href="/css/userProfile.css">
    <link rel="stylesheet" type="text/css" href="/css/review.css">
    <script type="text/javascript" src="/js/signIn.js"></script>
</head>
<body>
<div class="header">
    <div class="logo">
        <form action="/home" method="get">
            <input id="mainButton" value="book_search" type="submit"/>
            <h2>easy way to find a favourite book</h2>
        </form>
    </div>
    <div class="search">
        <div class="registrationButtons">
            <a href="http://localhost:8081/logout">Sign Out</a>
        </div>
    </div>
</div>
</div>
<div class='navigation'>
    <ul>
        <li>
            <form action="/authors" method="get">
                <input id="menuButton" value="Authors" type="submit"/>
            </form>
        </li>
        <li>
            <form action="/books" method="get">
                <input id="menuButton" value="Books" type="submit"/>
            </form>
        </li>
    </ul>
</div>
<div class="main">
    <div class="profile">
        <h2>${author.nickName}</h2>
        <hr id="line">
        <div class="ffff">
            <div class="photo">
                <img src="${author.getImageLink()}" alt="userPhoto">
            </div>
            <div class="hrrrrr">
                <div class="wrapp">
                    <label>Books:</label>
                    <p>
                        <#list books as book>
                            ${book.title}<br>
                        </#list>
                    </p>
                </div>
                <div class="wrapp">
                    <label>Full name:</label>
                    <p>${author.fullName}</p>
                </div>
                <div class="wrapp">
                    <label>Country:</label>
                    <p>${author.country.name}</p>
                </div>
                <div class="wrapp">
                    <label>Birthday:</label>
                    <p>${author.birthday}</p>
                </div>
                <div class="wrapp">
                    <label>DeathDay:</label>
                    <p>${author.deathDay}</p>
                </div>
            </div>
        </div>
        <div class="Biography">
            <div class="wrapp">
                <label style="color: rgb(116, 14, 99);">Biography:</label>
            </div>
            <p>
                Info About authorInfo About authorInfo About authorInfo About authorInfo About authorInfo About author.
                Info About authorInfo About authorInfo About authorInfo About authorInfo About authorInfo About author.
                Info About authorInfo About authorInfo About authorInfo About authorInfo About authorInfo About author.
                Info About authorInfo About authorInfo About authorInfo About authorInfo About authorInfo About author.
                Info About authorInfo About authorInfo About authorInfo About authorInfo About authorInfo About author.
                Info About authorInfo About authorInfo About authorInfo About authorInfo About authorInfo About author.
                Info About authorInfo About authorInfo About authorInfo About authorInfo About authorInfo About author.
                Info About authorInfo About authorInfo About authorInfo About authorInfo About authorInfo About author.
                Info About authorInfo About authorInfo About authorInfo About authorInfo About authorInfo About author.
                Info About authorInfo About authorInfo About authorInfo About authorInfo About authorInfo About author.
                Info About authorInfo About authorInfo About authorInfo About authorInfo About authorInfo About author.
                Info About authorInfo About authorInfo About authorInfo About authorInfo About authorInfo About author.
                Info About authorInfo About authorInfo About authorInfo About authorInfo About authorInfo About author.
                Info About authorInfo About authorInfo About authorInfo About authorInfo About authorInfo About author.

            </p>
        </div>
    </div>
</div>
<footer>
    Made by Zufar
</footer>
</body>
</html>
