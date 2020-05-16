<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta http-equiv="Content-type" content="text/html;charset=utf-8">
    <link rel="stylesheet" type="text/css" href="/css/mainStyle.css">
    <link rel="stylesheet" type="text/css" href="/css/bookProfile.css">
    <link rel="stylesheet" type="text/css" href="/css/review.css">
    <script type="text/javascript" src="/js/signIn.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
        <div class=" "></div>
        <h2 class="bookTitle">${book.title}</h2>
        <hr id="line">
        <div class="ffff">
            <div class="photo">
                <img src="${book.imageLink}"
                     alt="bookPhoto">
            </div>
            <div class="hrrrrr">
                <div class="wrapp">
                    <label>Authors:</label>
                    <p>
                        <#list authors as author>
                            ${author.nickName}<br>
                        </#list>
                    </p>
                </div>
                <div class="wrapp">
                    <label>Country:</label>
                    <p>${book.country.name}</p>
                </div>
                <div class="wrapp">
                    <label>Publication date:</label>
                    <p>${book.publicationDate}</p>
                </div>

                <div class="wrapp">
                    <label>Page:</label>
                    <p>${book.pageCount}</p>
                </div>
                <#if book.pdfLink != "null" >
                    <div class="wrapp">
                        <a class="btn" href="${book.pdfLink}" title="скачать книгу в формате PDF" data-format="pdf"
                           target="_blank"> PDF </a>
                    </div>
                </#if>
                <#if book.epubLink != "null" >
                <div class="wrapp">
                    <a class="btn" href="${book.epubLink}" title="скачать книгу в формате EPUB" data-format="pdf"
                       target="_blank"> EPUB </a>
                </div>
                </#if>
                <#if book.fb2Link != "null" >
                <div class="wrapp">
                    <a class="btn" href="${book.fb2Link}" title="скачать книгу в формате FB2" data-format="pdf"
                       target="_blank"> FB2 </a>
                </div>
                </#if>
            </div>
        </div>
        <div class="briefInfo">
            <div class="wrapp">
                <label style="color: rgb(116, 14, 99);">Biography:</label>
            </div>
            <p>
                Info About book Info About book Info About book Info About book Info About book Info About book.
                Info About book Info About book Info About book Info About book Info About book Info About book.
                Info About book Info About book Info About book Info About book Info About book Info About book.
                Info About book Info About book Info About book Info About book Info About book Info About book.
                Info About book Info About book Info About book Info About book Info About book Info About book.
                Info About book Info About book Info About book Info About book Info About book Info About book.
                Info About book Info About book Info About book Info About book Info About book Info About book.
                Info About book Info About book Info About book Info About book Info About book Info About book.
                Info About book Info About book Info About book Info About book Info About book Info About book.
                Info About book Info About book Info About book Info About book Info About book Info About book.
                Info About book Info About book Info About book Info About book Info About book Info About book.
                Info About book Info About book Info About book Info About book Info About book Info About book.
                Info About book Info About book Info About book Info About book Info About book Info About book.
                Info About book Info About book Info About book Info About book Info About book Info About book.
            </p>
        </div>
    </div>
</div>
</body>
<footer>
    Made by Zufar
</footer>
</html>
