
layui.use('table', function(){
    var table = layui.table;

    table.render({
        elem: '#test'
        ,url:'/getConfirmList'
        ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        ,cols: [[
            {field:'cusipId', title:'cusipId',sort: true,fixed: 'left'}
            ,{field:'price', title:'price',sort:true}
            ,{field:'amount', title:'amount',sort:true}
            ,{field:'targetId', title:'target id'}
            ,{field:'status', title:'status',templet: function (d) {return 'Pending';} }
            ,{field:'type', title:'type' ,templet: function (d){if(d==1) {return 'sale';} else {return 'buy';}}}
            ,{field:'issueDate', title:'date', sort:true}
            ,{fixed:'right', title:'action', toolbar: '#barDemo'}
        ]]
    });
});