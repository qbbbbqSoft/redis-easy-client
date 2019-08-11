$(document).ready(function() {
   getDatas = function getDatas() {
       $("#tbody").children().remove();
       var param = {
           key: $("#key-input").val(),
           start: 0,
           end: 5,
           timeUnit: "NANOSECONDS"
       }
        $.ajax({
            url:"/redis-datas",
            async:true,
            method: 'POST',
            data: JSON.stringify(param),
            contentType: 'application/json; charset=utf-8',
            success: function (res) {
                console.log(res);
                for (var i = 0; i < res.data.length; i++) {
                    var id = "tr" + i;
                    var html = "<tr class='active' id=" + id +">\n" +
                        "            <td>" + res.data[i].key + "</td>\n" +
                        "            <td style='max-width:500px;min-width:200px;'>" + res.data[i].res + "</td>\n" +
                        "            <td style='max-width:500px;min-width:200px;'>" + res.data[i].ttl + "</td>\n" +
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
        // var $id = "#" + id + "> td > div > input";
        // var $bid = "#" + id + "> td > div > span";
        // $($id).removeAttr("readonly");
        // $($id).focus();
        // $($bid).removeClass("hide");
        var $id = "#" + id;
        var key = $($id).children()[0].innerHTML;
        var value = $($id).children()[1].innerHTML;
        $('#myModalLabel').text('修改');
        $('#newKeyInput').val(key);
        $('#newValueInput').val(value);
        $('#newKeyInput').attr('readonly','readonly');
        $('#myModal').modal('show');

    };
    addNew = function addNew() {
        $('#myModalLabel').text('新增');
        $('#newKeyInput').removeAttr("readonly");
        $('#newKeyInput').val('');
        $('#newValueInput').val('');
        $('#myModal').modal('show');
    }


});