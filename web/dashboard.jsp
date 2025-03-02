<%-- 
    Document   : dashboard
    Created on : Feb 28, 2025, 4:50:35 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Cardlord Quiz</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .menu-icon,
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
                padding: 0;
                cursor: pointer;
            }

            .left-content {
                background-color: #545454;
                height: 100vh;
                position: fixed;
                z-index: 3;
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
                background-color: #545454;
                color: white;
            }

            .offcanvas.show {
                width: 20%;
            }

            .offcanvas-item {
                display: flex;
                align-items: center;
                color: white;
                margin: 1rem 0;
                padding-left: 1rem;
                cursor: pointer;
            }

            .offcanvas-item img {
                width: 2rem;
                margin-right: 1rem;
                filter: invert(100%);
            }

            .offcanvas-item span {
                display: none;
                white-space: nowrap;
            }

            .offcanvas.show .offcanvas-item span {
                display: inline;
            }

            .right-content{
                background-color: white;
            }

            .search-box {
                width: 100%;
                display: flex;
                justify-content: center;
            }

            .search-box input {
                width: 50%;
                border-width: 2px;
                border-style: solid;
                border-color: #545454;
                border-radius: 50px;
                padding: 0.5rem 1rem;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                outline: none;
                transform: translateX(25%);
            }

            .right-top-content-container {
                display: grid;
                grid-template-columns: 80% 20%;
                padding: 10px;
                position: fixed;
                width: 100%;
                z-index: 2;
                background-color: white;
            }

            .right-small-utility {
                display: flex;
                flex-direction: row;
                gap: 1em;
                justify-content: flex-end;
                align-items: center;
                transform: translateX(-10%);
            }

            .add-new-flashcard {
                font-size: 2em;
                width: 40px;
                height: 42px;
                margin-bottom: 10px;
                border-radius: 50px;
                display: flex;
                justify-content: center;
                cursor: pointer;
            }

            .right-lower-content{
                margin-left: 5rem;
                padding: 20px;
                background-color: white;
                min-height: 100vh;
                margin-top: 6em;
            }

        </style>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row vh-100">
                <!-- Left Sidebar -->
                <div class="left-content d-flex flex-column align-items-center" style="width: 5rem;">
                    <!-- Menu Icon with Toggle -->
                    <button class="image-button" data-bs-toggle="offcanvas" data-bs-target="#offcanvas-menu">
                        <img src="icon_image/menu.png" class="menu-icon my-3" alt="Menu Icon">
                    </button>
                    <button class="image-button" onclick="window.location.href = 'https://www.example.com';">
                        <img src="icon_image/home.png" class="home-icon my-3" alt="Home Icon">
                    </button>
                    <button class="image-button" onclick="window.location.href = 'https://www.example.com';">
                        <img src="icon_image/folder.png" class="folder-icon my-3" alt="Folder Icon">
                    </button>
                    <button class="image-button" onclick="window.location.href = 'https://www.example.com';">
                        <img src="icon_image/bell.png" class="bell-icon my-3" alt="Bell Icon">
                    </button>

                    <!-- Lower Section -->
                    <div class="lower-left-inner mt-auto mb-4">
                        <button class="image-button" onclick="window.location.href = 'https://www.example.com';">
                            <img src="icon_image/robot.png" class="robot-icon my-3" alt="Robot Icon">
                        </button>
                        <p class="logo-name">CARDLORD</p>
                    </div>
                </div>

                <!-- Offcanvas for Sliding Content -->
                <div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvas-menu">
                    <div class="offcanvas-header">
                        <h5 class="offcanvas-title">Menu</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                    </div>
                    <div class="offcanvas-body">
                        <div class="offcanvas-item" onclick="window.location.href = 'index.html';">
                            <img src="icon_image/home.png" alt="Home">
                            <span>Home</span>
                        </div>
                        <div class="offcanvas-item" onclick="window.location.href = 'index.html';">
                            <img src="icon_image/folder.png" alt="Folder">
                            <span>Folder</span>
                        </div>
                        <div class="offcanvas-item" onclick="window.location.href = 'index.html';">
                            <img src="icon_image/bell.png" alt="Notifications">
                            <span>Notifications</span>
                        </div>
                        <div class="offcanvas-item" onclick="window.location.href = 'index.html';">
                            <img src="icon_image/robot.png" alt="Bot">
                            <span>Bot</span>
                        </div>
                    </div>
                </div>

                <!-- Right Content -->
                <div class="right-content flex-grow-1">
                    <div class="right-top-content-container">
                        <!-- Search Box -->
                        <form action="action" class="search-box">
                            <input type="text" placeholder="Finding flashcard set...">
                        </form>

                        <!-- Add new flashcard set & profile -->
                        <div class="right-small-utility">
                            <div class="add-new-flashcard" onclick="window.location.href = 'https://www.example.com';">+</div>
                            <div class="profile">
                                <img src="icon_image/user.png" 
                                     class="dropdown-toggle" 
                                     role="button" 
                                     data-bs-toggle="dropdown" 
                                     style="width: 40px; height: 40px; cursor: pointer;">
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="#">Thông báo</a></li>
                                    <li><a class="dropdown-item" href="#">Cài đặt</a></li>
                                    <li><a class="dropdown-item" href="#">Nâng cấp</a></li>
                                    <li><hr class="dropdown-divider"></li>
                                    <li><a class="dropdown-item" href="#">Giúp đỡ và phản hồi</a></li>
                                    <li><a class="dropdown-item" href="#">Đăng xuất</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="right-lower-content">
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                        <p>content here...</p>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</html>

