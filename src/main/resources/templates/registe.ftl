<!DOCTYPE html>
<#include "commons/common_header.ftl">
<body>
<div class="layui-row">
    <div class="layui-col-md8">our home</div>
    <div class="layui-form layui-col-md4">
        <div class="layui-form-item">
            <label class="layui-form-label">登录账号</label>
            <div class="layui-input-inline">
                <input type="text" name="loginNum" required lay-verify="required" placeholder="请输入账号" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">登录密码</label>
            <div class="layui-input-inline">
                <input type="password" name="loginPassword" required lay-verify="required" placeholder="请输入密码"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">确认密码</label>
            <div class="layui-input-inline">
                <input type="password" name="loginPassword2" required lay-verify="required|pwdValid" placeholder="请输入密码"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button id="submit" class="layui-btn" lay-submit lay-filter="formDemo">提交</button>
            </div>
        </div>
    </div>
</div>
<div id="alert-msg"></div>
</body>
</body>

<script>
    layui.use('form', function(){
        var form = layui.form;

        form.verify({
            pwdValid: function (value, item) { //value：表单的值、item：表单的DOM对象
                var pwd1 = $('input[name="loginPassword"]').val();
                if (value != pwd1) {
                    return "密码校验不一致";
                }
            }
        });

        submitFn();
    });

    function submitFn() {
        var data = {};
        data.loginNum = $('input[name="loginNum"]').val();
        data.loginPassword = $('input[name="loginPassword"]').val();
        $.ajax({
            type: "POST",
            url: "/doRegisteLogin",
            contentType: "application/json",
            data: JSON.stringify(data),
            dataType: "JSON",
            success: function (res) {
                if (res.success) {
                    $.get("/main");
                } else {
                    $('#alert-msg').modal(res.message);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {

            }
        })
    }

    function validateForm() {
        form.verify({
            pwdValid: function (value, item) { //value：表单的值、item：表单的DOM对象
                var pwd1 = $('input[name="loginPassword"]').val();
                if (value != pwd1) {
                    return "密码校验不一致";
                }
            }
        });
    }
</script>
</html>