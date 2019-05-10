$(function () {
$("#loginsubmit").click(
    function () {
        $.ajax({
            url:"/logincontro/jug",
            type:"POST",
            dataType:"json",
            data:$("#am-form").serializeArray(),
            //获取成功数据后刷新页面
            success:function (data) {
                console.log(data);
                var user = data;
                if (user.uuuid === null)
                    alert("账号密码不正确");
                if (user.uuuid != null)
                    window.location.href="/zeusblog/index"

            }
        })
    }
)
});