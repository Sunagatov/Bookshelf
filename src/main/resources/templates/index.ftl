<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/mainStyle.css">
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
    <h3>Book Search is a book site for users of all ages</h3>
    <p>Welcome to Book Search - a large book portal dedicated to literature and the selection of booksIds.
        Not sure what to do? Read booksIds! Use the search for booksIds on the site, with which you will find exactly what
        suits you, and a flexible rating system will allow you to choose from what you find - the best.</p>
    <p>World bestsellers, the best new products, favorite works of world importance - all of this is collected from us
        on
        Book Search. Here are not only the best works of all times and peoples, but also a complete
        information about authors who are loved by readers
        all over the world.</p>
    <h3>Book genres on Book Search</h3>
    <p>The site about booksIds - Book Search - offers a wide range of literary works. It allows everyone
        find what he likes. Most often, readers turn to such genres as:</p>
    <ul>
        <li>- classic works;</li>
        <li>- detective;</li>
        <li>- business booksIds;</li>
        <li>- self improvement booksIds;</li>
        <li>- fantasy;</li>
        <li>- fiction and other booksIds;</li>
    </ul>
    <p>The location of e-booksIds is such that finding the right version of reading is quite simple. In addition, the site
        provides readers with lists of the most interesting works presented on the portal. Similar ratings compiled by
        voting our users.</p>
    <p>Lists of the most interesting and popular book copies are constantly updated, updated with new ones, recently
        submitted to a wide readership. Leave a review and describe your own feelings after users reading the works may
        go to the book page. In addition, we have a list of booksIds that everyone should read.</p>
    <p>Fragments of works of world literature are available in electronic form in the following formats:</p>
    <ul>
        <li>- EPUB;</li>
        <li>- FB2;</li>
        <li>- PDF;</li>
        <li>- TXT.</li>
    </ul>
    <p>Register on the BookSearch portal, and you will easily find interesting literature for you personally, as well as
        choose what to advise to read to friends and family. Find popular booksIds, leave reviews, make your personal
        library
        invite your friends! Let's collect all reading people in one place!</p>
</div>
<footer>
    Made by Zufar
</footer>
<div id="id01" class="modal">
    <div class="imgcontainer">
    </div>
    <div class="container">
        <form action="signIn" method="post">
            <h1 style="text-align: center">Sign in</h1>
            <label><b>Login</b></label>
            <input type="email" placeholder="Enter your login.." name="login">
            <label><b>Password</b></label>
            <input type="password" placeholder="Enter your password.." name="psw">
            <button type="submit">Login</button>
        </form>
    </div>
</div>
</body>
</html>
