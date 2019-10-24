layui.use('table', function(){
    var table = layui.table;

    table.render({
        elem: '#test'
        ,url:'/getProcessedTransaction'
        ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
        ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
            title: '提示'
            ,layEvent: 'LAYTABLE_TIPS'
            ,icon: 'layui-icon-tips'
        }]
        ,cols: [[
            {field:'cusipId', title:'cusipId',sort: true,fixed: 'left'}
            ,{field:'price', title:'price',sort:true}
            ,{field:'amount', title:'amount',sort:true}
            ,{field:'targetId', title:'target id'}
            ,{field:'status', title:'status',templet: function (d) {return 'Pending';} }
            ,{field:'type', title:'type' ,templet: function (d){if(d==1) {return 'sale';} else {return 'buy';}}}
            ,{field:'issueDate', title:'date', sort:true}
            ,{fixed: 'right', title:'action', toolbar: '#barDemo', width:150}
        ]]
        ,page:true
    });

    //头工具栏事件
    table.on('toolbar(test)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'getCheckData':
                var data = checkStatus.data;
                layer.alert(JSON.stringify(data));
                break;
            case 'getCheckLength':
                var data = checkStatus.data;
                layer.msg('选中了：'+ data.length + ' 个');
                break;
            case 'isAll':
                layer.msg(checkStatus.isAll ? '全选': '未全选');
                break;

            //自定义头工具栏右侧图标 - 提示
            case 'LAYTABLE_TIPS':
                layer.alert('这是工具栏右侧自定义的一个图标按钮');
                break;
        };
    });

    //监听行工具事件
    table.on('tool(test)', function(obj){
        var data = obj.data;
        //console.log(obj)
        if(obj.event === 'edit'){
            layer.confirm('conform accept?', function(index){
                obj.del();
                layer.close(index);

                $.ajax({
                    url:"/backOfficeAccept?traderRequestId="+data.traderRequestId,
                    dataType:"text",
                    method:"post",
                    data:$('#form1').serialize(),
                    success:function (returnData) {
                        if (returnData=="success"){
                            layer.msg("add success");
                            $("#reset").click();
                        }
                        else {
                            layer.msg(returnData)
                        }
                    },
                    error:function () {
                        $().alert("network failed")
                    }
                });

            });
        } else if(obj.event === 'del'){
            layer.prompt({
                formType: 2
                ,title:'拒绝理由'
                ,value: data.email
            }, function(value, index){

                    $.ajax({
                        url:"/backOfficeReject?traderRequestId="+data.traderRequestId+"&"+"rejectReason="+value,
                        dataType:"text",
                        method:"post",
                        data:$('#form1').serialize(),
                        success:function (returnData) {
                            if (returnData=="success"){
                                layer.msg("add success");
                                $("#reset").click();
                            }
                            else {
                                layer.msg(returnData)
                            }
                        },
                        error:function () {
                            $().alert("network failed")
                        }
                    });
                layer.close(index);
            });
        }
    });
});