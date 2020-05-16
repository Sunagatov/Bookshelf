<#--<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>-->

<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta http-equiv="content-type" content="text/html"/>
    <meta name="author" content=""/>
    <link rel="stylesheet" type="text/css" href="/css/mainStyle.css">
    <link rel="stylesheet" type="text/css" href="/css/booklist.css">
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
    </ul>
</div>
<div class="main">
<#--    <@security.authorize access="hasRole('ROLE_ADMIN')">-->
        <div class="addNewItem">
            <form action="getAddBookView" method="get">
                <input type="submit" value="Add new Book">
            </form>
        </div>
<#--    </@security.authorize>-->
    <main class="cards">
        <#list books as book>
            <article class="card">
                <img src="${book.imageLink}"
                     alt="Sample photo">
                <div class="text">
                    <div class="info">
                        <h3>Title:</h3>
                        <p>${book.title}</p>
                        <h3>Publication date:</h3>
                        <p>${book.publicationDate}</p>
                        <h3>Country:</h3>
                        <p>${book.country.name}</p>
                    </div>
                    <div class="learn">
                        <form action="/book/${book.id}" method="get">
                            <input type="submit" value="Learn">
                        </form>
                    </div>
<#--                    <@security.authorize access="hasRole('ROLE_ADMIN')">-->
                        <div class="learn">
                            <form action="/updateBookView/${book.id}" method="get">
                                <input type="submit" value="Update">
                            </form>
                        </div>
                        <div class="learn">
                            <form action="/deleteBook/${book.id}" method="get">
                                <input type="submit" value="Delete">
                            </form>
                        </div>
<#--                    </@security.authorize>-->
                </div>
            </article>
        </#list>
    </main>
</div>
<footer>
    Made by Zufar
</footer>
</body>
</html>
