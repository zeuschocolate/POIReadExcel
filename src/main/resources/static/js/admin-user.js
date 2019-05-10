$(function () {
    //更改用户信息表单提交
    $("#changebtn").click(
        function () {
            $.ajax({
                url:"/admin/edituser",
                type:"POST",
                dataType:"json",
                data:$("#editform").serializeArray(),
                //获取成功数据后刷新页面
                success:function (data) {
                    console.log(data);
                    var user = data;
                    if (user.uuuid != null)
                        window.location.reload();//刷新当前页面

                }
            })
        }
    );

    //升级积分进度条样式改变
     var uDay = $("#uDay").val();
    var uDdayAll = $("#uDayAll").val();
    var x = GetPercent((uDdayAll-uDay)/uDdayAll,100);
    $("#progressbar1")[0].style.width = x;
//---------------------------------------------------------------------------------------------------------

});

//---------------------------------------------------------------------------------------------------------
// 求百分比
function GetPercent(num, total) {
    num = parseFloat(num);
    total = parseFloat(total);
    if (isNaN(num) || isNaN(total)) {
        return "-";
    }
    return total <= 0 ? "0%" : (Math.round(num / total * 1000000) / 100) + "%";

}