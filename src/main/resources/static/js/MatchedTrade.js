
layui.use('table', function(){
    var table = layui.table;
    var layer = layui.layer
    //var body = layer.getChildFrame('body', index);

    // loadData子页面方法
    var laydate;
    // document.getElementById("iframe").contentWindow.loadData($,laydate,data.id);
    // body.contents().find("#detailId").val(data.id);  // #detailId  子页面元素id
    table.render({
        elem: '#test'
        // ,url:'/getMatchedSalesRequest?traderRequestId='+$("[name='traderRequestId']").val()
        ,url:'/getMatchedSalesRequest?traderRequestId='+$("[name='traderRequestId']").val()
        ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
        ,cellMinWidth:60
        ,title: 'Trades'
        ,cols: [[
            {type:'radio'}
            ,{field:'cusipId', title:'cusipId',sort: true}
            ,{field:'price', title:'price',sort:true}
            ,{field:'amount', title:'amount',sort:true}
            ,{field:'targetId', title:'target id'}
            ,{field:'status', title:'status',templet: function (d) {return 'Pending';} }
            ,{field:'type', title:'type' ,templet: function (d){if(d==1) {return 'sale';} else {return 'buy';}}}
            ,{field:'issueDate', title:'date', sort:true}
        ]]
        ,page: true
        ,text: {
            none: 'no matched data' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
        }
    });

    //头工具栏事件
    table.on('toolbar(test)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id); //获取选中行状态
        switch(obj.event){
            case 'getCheckData':
                var data = checkStatus.data;  //获取选中行数据
                //layer.alert(JSON.stringify(data));
                //console.log(data)
                $.ajax({
                    url:"/matchRequest",
                    dataType:"text",
                    method:"post",
                    data:{
                        traderRequestId:$("[name='traderRequestId']").val(),
                        salesRequestId:data[0].salesRequestId
                    },
                    success:function (returnData) {
                        if (returnData=="success"){
                            layer.msg("add success");
                        }
                        else {
                            layer.msg(returnData)
                        }
                    },
                    error:function () {
                        $().alert("network failed")
                    }
                });
                break;
        };
    });
});