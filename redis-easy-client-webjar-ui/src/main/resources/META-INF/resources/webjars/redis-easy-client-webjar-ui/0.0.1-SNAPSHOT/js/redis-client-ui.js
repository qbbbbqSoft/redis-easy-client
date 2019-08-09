$(document).ready(function() {
   getDatas = function getDatas() {
       $("#tbody").children().remove();
        $.ajax({
            url:"/redis-datas",
            async:true,
            success: function (res) {
                console.log(res);
                for (var i = 0; i < res.length; i++) {
                    var id = "tr" + i;
                    var html = "<tr class='active' id=" + id +">\n" +
                        "            <td>" + res[i].key + "</td>\n" +
                        "            <td><div class='input-group' width='300px;'>\n" +
                        "                            <input type='text' oldVal=" +res[i].res+ " onblur='onBlur($(this))' readonly class='form-control' value=" +res[i].res+ ">\n" +
                        "                            <span class='input-group-btn hide'>\n" +
                        "<button class='btn btn-info' type='button' onclick='save($(this))'>\n" +
                        "确认\n" +
                        "</button>\n" +
                        "</span>\n" +
                        "                        </div></td>\n" +
                        "            <td><button type='button' class='btn btn-primary' onclick='editByKey(" + '\"' + id + '\"' + ")'>修改</button>" +
                        "             <button type='button' class='btn btn-danger' onclick='deleteByKey(" + '\"' + id + '\"' + ")'>删除</button></td>\n" +
                        "        </tr>";
                    $("#tbody").append(html)
                }
            }
        })
    };
    deleteByKey = function deleteByKey(id) {
        var $id = "#" + id;
        var key = $($id).children()[0].innerHTML;
        //TODO ajax 删除
        $($id).remove();
    };
    editByKey = function editByKey(id) {
        // $("#tr0 > td > input").removeAttr("readonly");
        var $id = "#" + id + "> td > div > input";
        var $bid = "#" + id + "> td > div > span";
        $($id).removeAttr("readonly");
        $($id).focus();
        $($bid).removeClass("hide");
    };
    save = function save(val) {
        val.parent().addClass("hide");
        val.parent().prev().attr("readonly","readonly")
        // console.log(val.parent());
    };
    onBlur = function onBlur(val) {
        val.attr("readonly","readonly");
        val.val(val.attr("oldVal"));
        val.next().addClass("hide");
    }

});