<!DOCTYPE html>

<html lang="zh">

<head>
    <title>codingce-登陆</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript"
        src="https://cdn.jsdelivr.net/gh/xzMhehe/StaticFile_CDN@main/tjise/static/js/jquery.min.js"></script>
    <link rel="shortcut icon"
        href="https://cdn.jsdelivr.net/gh/xzMhehe/StaticFile_CDN@main/blogImages/site-img/favicon.png"
        type="image/x-icon" />
    <style>
        #canvas {}

        h1 {
            color: #4c1caa;
            text-align: t;
        }

        body {
            margin: 0;
            padding: 0;
            font-family: "montserrat";
            background-image: linear-gradient(125deg, #2c3e50, #4366da, #2980b9, #9ae91b, #8e44ad);
            background-size: 400%;
            animation: bganimation 15s infinite;
            text-shadow: 2.5px 5px 0.3125rem royalblue;
            color: #8E44AD;
            font-size: sans-serif;
        }

        .box {
            width: 300px;
            padding: 40px;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            text-align: center;
        }

        .box h1 {
            color: rgb(255, 255, 255);
            text-transform: uppercase;
            font-weight: 500;
        }

        .box input[type="text"],
        .box input[type="password"] {
            border: 0;
            background: none;
            display: block;
            margin: 20px auto;
            text-align: center;
            border: 2px solid #3498db;
            padding: 14px 10px;
            width: 200px;
            outline: none;
            color: white;
            border-radius: 24px;
            transition: 0.25s;
        }

        .box input[type="text"]:focus,
        .box input[type="password"]:focus {
            width: 280px;
            border-color: #2ecc71;
        }

        -webkit-input-placeholder {
            color: rgb(240, 191, 58);
            font-size: 20px;
            line-height: 50px;
        }

        .box input[type="submit"] {
            border: 0;
            background: none;
            display: block;
            margin: 20px auto;
            text-align: center;
            border: 2px solid #ffffff;
            padding: 14px 40px;
            outline: none;
            color: white;
            border-radius: 24px;
            transition: 0.25s;
            cursor: pointer;
        }

        .box input[type="submit"]:hover {
            background: #d6e737;
        }

        .text {
            color: white;
            text-align: center;
            text-transform: uppercase;
            margin: 0px 0;
            //控制主背景高度
            font-size: 22px;
        }

        @keyframes bganimation {
            0% {
                background-position: 0% 50%;
            }

            50% {
                background-position: 100% 50%;
            }

            100% {
                background-position: 0% 50%;
            }
        }
    </style>
</head>


<body>
    <canvas id="canvas"></canvas>

    <div class="text">
        <div class="box">
            <h1>登 录</h1>
            <input type="text" id="username" name="username" placeholder="用户名">
            <input type="password" id="pass" name="pass" placeholder="密码">
            <input type="submit" id="btn-ok" name="" value="登陆">
        </div>
    </div>


    <script>
        $("#btn-ok").on("click", function() {
            if ($("#username").val() == "" || $("#pass").val() == "") {
                console.log("账号密码不能为空");
                return;
            }

            console.log("未进判空");
            $.ajax({
                type: "POST",
                url: "/tologin",
                data: {
                    username: $("#username").val(),
                    pass: $("#pass").val(),
                },
                success: function(data) {
                    console.log("请求成功");
                    console.log(data);
                    if(data.code == 200) {
                        location.href = "/";
                    } else {
                        console.log("账号或密码错误");
                    }
                },
                error: function(e) {
                    //请求超时回调
                    if (e.statusText == "timeout") {
                        console.log("访问超时");
                    }
                },
            });
        });
    </script>
</body>

</html>