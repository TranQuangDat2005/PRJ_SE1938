<!DOCTYPE html>
<html lang="en">

    <head>
    <description>Welcome to CARDLORD</description>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300i,400" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Sigmar&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Kooperativ&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" />
    <style>
        .logo {
            font-family: 'Sigmar';
            text-shadow:
                -2px -2px 0 black,
                2px -2px 0 black,
                -2px  2px 0 black,
                2px  2px 0 black;
        }


        body,
        html,
        .container-fluid {
            margin: 0px;
            padding: 0px;
        }

        .menu-icon {
            width: 2rem;
        }

        .home-icon,
        .folder-icon,
        .bell-icon,
        .robot-icon {
            width: 3rem;
            filter: invert(100%);
        }

        .logo-name {
            font-size: 0.8em;
            color: white;
        }

        .image-button {
            background: none;
            border: none;
            padding-left: 20px;
            padding-right: 20px;
            margin: 0px;
            cursor: pointer;
            border-radius: 50px;
        }

        .left-content {
            background-color: #545454;
            height: 100vh;
        }

        .lower-left-inner {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 16em;
        }

        .offcanvas-start {
            width: 5rem;
            max-width: 20%;
            background-color: #ececec;
            color: black;
        }

        .offcanvas.show {
            width: 20%;
        }

        .offcanvas-item {
            display: flex;
            align-items: center;
            color: black;
            padding: 1rem;
            cursor: pointer;
            border-radius: 10px;
        }

        .offcanvas-item:hover {
            background-color: #d3d3d3; /* Light gray */
            transition: background-color 0.3s ease;
        }

        .image-button:hover{
            background-color: #d3d3d3; /* Light gray */
            transition: background-color 0.3s ease;
        }

        .offcanvas-item img {
            width: 2rem;
            margin-right: 1rem;
        }

        .offcanvas-item span {
            display: none;
            white-space: nowrap;
        }

        .offcanvas.show .offcanvas-item span {
            display: inline;
        }

        .search-box {
            align-self: center;
            width: 100%;
        }

        .offcanvas-body {

        }

        .offcanvas-header {
            padding: 10% 10% 0px 10%;
        }


        .offcanvas-header button{
            border-radius: 50px;
        }

        .offcanvas-header button:hover{
            background-color: #d3d3d3; /* Light gray */
            transition: background-color 0.3s ease;
        }

        .search-box input {
            width: 60%;
            border-width: 2px;
            border-style: solid;
            border-color: #545454;
            border-radius: 10px;
            padding: 0.5rem 1rem;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            outline: none;
            justify-self: right;
        }

        .right-top-content-container {
            display: flex;
            grid-template-columns: 80% 20%;
            padding: 10px;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            color: white;
            text-align: center;
            font-size: 20px;
            z-index: 1000;
            background-color: rgb(235,235,235);
            border-bottom: #b8b8b8 2px solid;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .right-top-content-container button {
        }

        .right-small-utility {
            display: flex;
            flex-direction: row;
            gap: 1em;
            justify-content: flex-end;
            align-items: center;
        }

        .add-new-flashcard {
            font-size: 2em;
            width: 40px;
            align-self: center;
            border-radius: 50px;
            display: flex;
            justify-content: center;
            cursor: pointer;
            color: black;
            margin-right: 2%;
            align-items: center;
        }

        .right-small-utility {
            margin-right: 2%;
        }

        footer p {
            color: white;
            margin: 0.3rem;
        }

        .logo {
            width: 15vw;
            align-self: center;
            margin-left: 3%;

        }

        .logo a{
            text-decoration: none;
            color: white;
        }

        .functions {
            position: static;
            height: 500px;
            padding: 50px;
            width: 100%;
            display: flex;
            justify-content: center;
        }

        .function-card {
            display: flex;
            height: 350px;
            width: 250px;
            background-color: #ececec;
            border: #545454 5px solid;
            border-radius: 10px;
            box-shadow: -1rem 0 3rem #9b9b9b;
            transition: 0.4s ease-out;
            position: relative;
            justify-content: center;
        }

        .function-card:not(:first-child) {
            margin-left: 3%;
            margin-bottom: 30px;
        }

        .function-card:hover {
            transform: translateY(-20px);
            transition: 0.4s ease-out;
        }

        .title {
            color: rgb(0, 0, 0);
            font-weight: bold;
            position: absolute;
            margin-top: 10px;
        }

        .description {
            color: rgb(0, 0, 0);
            font-weight: bold;
            position: absolute;
            align-self: flex-end;
        }

        .function-card:hover .stroke {
            stroke-dashoffset: 100;
            transition: 0.6s ease-out;
        }

        footer {
            position: relative;
            background-color: #545454;
            justify-items: center;
            padding-top: 1rem;
            padding-bottom: 1rem;
        }

        .profile img{
            border-radius: 50px;
        }
        .profile img:hover{
            background-color: #d3d3d3; /* Light gray */
            transition: background-color 0.3s ease;
        }
        .add-new-flashcard{
            width: 40px;
            height: 40px;
            border-radius: 50px;
        }
        .add-new-flashcard:hover{
            background-color: #d3d3d3; /* Light gray */
            transition: background-color 0.3s ease;
        }

        footer {
            background-color: #545454;
            color: white;
            padding: 7vh;
        }

        .footer-content {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 30px;
        }

        .footer-column h3 {
            margin-bottom: 20px;
            font-size: 18px;
            color: var(--secondary-color);
        }

        .footer-column ul {
            list-style: none;
        }

        .footer-column li {
            margin-bottom: 10px;
        }

        .footer-column a {
            color: var(--white);
            text-decoration: none;
        }

        .footer-column a:hover {
            text-decoration: underline;
        }

        .copyright {
            text-align: center;
            margin-top: 40px;
            padding-top: 20px;
            border-top: 1px solid rgba(255, 255, 255, 0.2);
        }

        .container {
            width: 90%;
            max-width: 1200px;
            margin: 0 auto;
        }



    </style>
</head>

<body>
    <div class="container-fluid">
        <div class="row vh-80">
            <!-- Offcanvas for Sliding Content -->
            <div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvas-menu">
                <div class="offcanvas-header" style="align-items: center">
                    <h3 class="offcanvas-description" style="font-weight: bold">MENU</h3>
                    <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                </div>
                <div class="offcanvas-body">
                    <div class="offcanvas-item" onclick="window.location.href = '#';">
                        <img src="icon_image/home.png" alt="Home Page">
                        <span>Home Page</span>
                    </div>
                    <div class="offcanvas-item" onclick="window.location.href = '#';">
                        <img src="icon_image/library.png" alt="Your Library">
                        <span>Your Library</span>
                    </div>
                    <div class="offcanvas-item" onclick="window.location.href = '#';">
                        <img src="icon_image/bell.png" alt="Notifications">
                        <span>Notifications</span>
                    </div>
                    <div class="offcanvas-item" onclick="window.location.href = 'chatBot.jsp';">
                        <img src="icon_image/bot.png" alt="Chat Bot">
                        <span>Chat Bot</span>
                    </div>
                </div>
            </div>

            <!-- Right Content -->
            <div class="col flex-grow-1" style="padding: 0px;">
                <div class="right-top-content-container">
                    <!-- Menu Button -->
                    <button class="image-button" data-bs-toggle="offcanvas" data-bs-target="#offcanvas-menu">
                        <img src="icon_image/menu.png" class="menu-icon my-3" alt="Menu Icon">
                    </button>
                    <!-- Logo -->
                    <div class="logo">
                        <a href="dashboard.jsp"><h1>CARDLORD</h1></a>
                    </div>

                    <!-- Search Box -->
                    <form action="action" class="search-box">
                        <input type="text" placeholder="&#128269; Find your flashcard set now..." style="color: #545454">
                    </form>

                    <!-- Add new flashcard set & profile -->
                    <div class="right-small-utility" style="align-items: center">
                        <div class="add-new-flashcard" onclick="window.location.href = '#';">+

                        </div>
                        <div class="profile">
                            <img src="icon_image/user.png" class="dropdown-toggle" role="button"
                                 data-bs-toggle="dropdown"
                                 style="width: 50px; height: 50px; cursor: pointer;">
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#">Notifications</a></li>
                                <li><a class="dropdown-item" href="#">Settings</a></li>
                                <li><a class="dropdown-item" href="premium.html">Become CARDLORD</a></li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li><a class="dropdown-item" href="contact.html">Customer Support</a></li>
                                <li><a class="dropdown-item" href="helper_center.html">Help</a></li>
                                <li><a class="dropdown-item" href="logout.html">Log out</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div style="height: 50px; background-color: #ececec;">
            Ambatukam ohhhhhh!
        </div>


    </div>
</body>
<footer>
    <div class="container">
        <div class="footer-content">
            <div class="footer-column">
                <h3>Help</h3>
                <ul>
                    <li><a href="#">Contact support</a></li>
                    <li><a href="#">Frequently Asked Questions</a></li>
                    <li><a href="#">Report a problem</a></li>
                </ul>
            </div>

            <div class="footer-column">
                <h3>Community</h3>
                <ul>
                    <li><a href="#">Blog</a></li>
                    <li><a href="#">Forum</a></li>
                    <li><a href="#">Contribute</a></li>
                </ul>
            </div>

            <div class="footer-column">
                <h3>Information</h3>
                <ul>
                    <li><a href="#">About us</a></li>
                    <li><a href="#">Clause</a></li>
                    <li><a href="#">Privacy</a></li>
                </ul>
            </div>

            <div class="footer-column">
                <h3>Account</h3>
                <ul>
                    <li><a href="#">Register</a></li>
                    <li><a href="#">Log in</a></li>
                    <li><a href="#">Upgrade account</a></li>
                </ul>
            </div>
        </div>

        <div class="copyright">
            <p>&copy; PRJ301_Project Group 1_FPT University</p>
        </div>
    </div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</html>

