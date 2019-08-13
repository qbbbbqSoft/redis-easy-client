$(document).ready(function() {
   getDatas = function getDatas() {
       $("#tbody").children().remove();
       var param = {
           key: $("#key-input").val()
       }
        $.ajax({
            url:"/redis-datas",
            async:true,
            method: 'POST',
            data: JSON.stringify(param),
            contentType: 'application/json; charset=utf-8',
            success: function (res) {
                console.log(res);
                $("#info").html("<label style='font-size: 18px;'>redis缓存内容（" + res.info + ")</label>")
                var data = res.data;
                $('#treeview').treeview({
                    expandIcon: "glyphicon glyphicon-chevron-right",
                    collapseIcon: "glyphicon glyphicon-chevron-down",
                    nodeIcon: "glyphicon glyphicon-file",
                    color: "#000",
                    backColor: "#fff",
                    onhoverColor: "#FFA500",
                    borderColor: "red",
                    showIcon: false,
                    showBorder: false,
                    showTags: true,
                    highlightSelected: true,
                    selectedColor: "#FFA500",
                    selectedBackColor: "#D8EDCF",
                    data: data,
                    onNodeSelected: function(event, node) {
                        console.log(node);
                        if (node.nodes == null) {
                            $('#valueContent').removeAttr("hidden");
                            $('#key').val(node.href);
                            $('#nodeInfo').val(node);
                            getData(node.href);
                        }
                    }
                }).treeview('collapseAll');
            }
        })
    };
    getDatas();
    deleteByKey = function deleteByKey() {
        var param = {
          key: $('#key').val()
        };
        $.ajax({
            url:"/del-data-by-key",
            async:true,
            method: 'POST',
            data: JSON.stringify(param),
            contentType: 'application/json; charset=utf-8',
            success: function (res) {
                var timestamp=new Date().getTime();
                var alertId = "AL" + timestamp;
                if (res.code == 0) {
                    $('#myModal').modal('hide');
                    var html = "<div class='alert alert-success' id=" + alertId + ">\n" +
                        "    <a href='#' class='close' data-dismiss='alert'>\n" +
                        "        &times;\n" +
                        "    </a>\n" +
                        "    <strong>提示！</strong>" + res.msg + "\n" +
                        "</div>\n"
                    $('#alertDiv').append(html);
                } else {
                    $('#myModal').modal('hide');
                    var html = "<div class='alert alert-danger' id=" + alertId + ">\n" +
                        "    <a href='#' class='close' data-dismiss='alert'>\n" +
                        "        &times;\n" +
                        "    </a>\n" +
                        "    <strong>提示！</strong>" + res.msg + "\n" +
                        "</div>\n"
                    $('#alertDiv').append(html);
                }
                $('#alertDiv').oneTime('3s',function () {
                    $('#' + alertId).remove();
                })
            },
            complete: function(xhr,status) {
                var findDisabledNodes = function() {
                    return $('#treeview').treeview('search', [ $('#nodeInfo').val().href, { ignoreCase: false, exactMatch: false } ]);
                };
                var disabledNodes = findDisabledNodes();
                $('#treeview').treeview('disableNode', [ disabledNodes, { silent: $('#valueContent').attr("hidden","hidden") }]);
            }
        });
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
        var ttl = $($id).children()[2].innerHTML;
        $('#myModalLabel').text('修改');
        $('#newKeyInput').val(key);
        $('#newValueInput').val(value);
        $('#timeValueInput').val(ttl);
        $("#timeUnitSelect").val("SECONDS");
        $('#newKeyInput').attr('readonly','readonly');
        $('#myModal').modal('show');

    };
    addNew = function addNew() {
        $('#myModalLabel').text('新增');
        $('#newKeyInput').removeAttr("readonly");
        $('#newKeyInput').val('');
        $('#newValueInput').val('');
        $('#myModal').modal('show');
    };
    createNewRedisCache = function createNewRedisCache() {
        var checkText=$("#timeUnitSelect").find("option:selected").text();
        var param = {
            key: $("#newKeyInput").val(),
            value: $("#newValueInput").val(),
            timeout: $("#timeValueInput").val(),
            timeUnit: checkText
        };
        doAjaxAdd(param)

    };

    doAjaxAdd = function doAjaxAdd(param) {
        $.ajax({
            url:"/add-data",
            async:true,
            method: 'POST',
            data: JSON.stringify(param),
            contentType: 'application/json; charset=utf-8',
            success: function (res) {
                var timestamp=new Date().getTime();
                var alertId = "AL" + timestamp;
                if (res.code == 0) {
                    $('#myModal').modal('hide');
                    var html = "<div class='alert alert-success' id=" + alertId + ">\n" +
                        "    <a href='#' class='close' data-dismiss='alert'>\n" +
                        "        &times;\n" +
                        "    </a>\n" +
                        "    <strong>提示！</strong>" + res.msg + "\n" +
                        "</div>\n"
                    $('#alertDiv').append(html);
                } else {
                    $('#myModal').modal('hide');
                    var html = "<div class='alert alert-danger' id=" + alertId + ">\n" +
                        "    <a href='#' class='close' data-dismiss='alert'>\n" +
                        "        &times;\n" +
                        "    </a>\n" +
                        "    <strong>提示！</strong>" + res.msg + "\n" +
                        "</div>\n"
                    $('#alertDiv').append(html);
                }
                $('#alertDiv').oneTime('3s',function () {
                    $('#' + alertId).remove();
                })
            }
        });
    }

    getData = function getData(key) {
        var param = {
            key: key
        };
        $.ajax({
            url:"/get-data-by-key",
            async:true,
            method: 'POST',
            data: JSON.stringify(param),
            contentType: 'application/json; charset=utf-8',
            success: function (res) {
                $('#value').val(res.data.res);
                $('#ttl').val(res.data.ttl)
            }
        })
    };

    reLoad = function reLoad() {
        var key = $('#key').val();
        getData(key);
    }

    $('#save').click(function () {
        var param = {
            key: $('#key').val(),
            value: $('#value').val(),
            timeout: $("#ttl").val(),
            timeUnit: "SECONDS"
        }
        doAjaxAdd(param);
    })


});