<!DOCTYPE html>
<#include "commons/common_header.ftl">
<body>
<form class="layui-form">
    <div class="layui-form-item">
        <label class="layui-form-label">登录账号</label>
        <div class="layui-input-inline">
            <input type="text" name="loginNum" required  lay-verify="required" placeholder="请输入账号" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">登录密码</label>
        <div class="layui-input-inline">
            <input type="password" name="loginPassword" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-inline">
            <button id="formSubmit" class="layui-btn" lay-submit lay-filter="formDemo">提交</button>
            <a href="/registe" class="layui-btn">注册</a>
        </div>
    </div>
</form>
</body>

<script>
    $("#formSubmit").click(function () {
        var loginNum = $('input[name="loginNum"]').val();
        var loginPassword = $('input[name="loginPassword"]').val();
        $.ajax({
            url: "/doLogin",
            type: "POST",
            data: {
                loginNum: loginNum,
                loginPassword: loginPassword
            },
            success: function (res) {
                debugger;
                if (res.success) {
                    $.get("/main");
                } else {
                    $.get("/main");
                }
            }
        })
    })
</script>
</html>