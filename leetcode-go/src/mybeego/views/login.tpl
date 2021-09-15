<!DOCTYPE html>

<html lang="zh">

<head>
    <title>codingce-登陆</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="shortcut icon"
        href="https://cdn.jsdelivr.net/gh/xzMhehe/StaticFile_CDN@main/blogImages/site-img/favicon.png"
        type="image/x-icon" />
</head>

<body>
    <header>
        <h1 class="logo">欢迎登陆</h1>
    </header>
    <div>
        <form action="/tologin">
            <p>用户名</p>
            <input type="text" name="name" placeholder="用户名" />
            <p>密码</p>
            <input type="password" name="pass" placeholder="密码" />
            <input type="button" value="登陆" onclick="sub()" />
        </form>
    </div>
    <footer>

    </footer>
    <div class="backdrop"></div>

    <script src="/static/js/reload.min.js"></script>
    <script>
        function sub() {
            console.log("测试");
        }
    </script>
</body>

</html>