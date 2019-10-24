
layui.use('table', function(){
    var table = layui.table;

    table.render({
        elem: '#test'
        ,url:'/getTraderHistory'
        //,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        ,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
            //,curr: 5 //设定初始在第 5 页
            ,groups: 1 //只显示 1 个连续页码
            ,first: false //不显示首页
            ,last: false //不显示尾页
        }
        ,cols: [[
            {field:'cusipId', title:'cusipId',sort: true,fixed: 'left'}
            ,{field:'price', title:'price',sort:true}
            ,{field:'amount', title:'amount',sort:true}
            ,{field:'targetId', title:'target id'}
            ,{field:'status', title:'status',templet: function (d) {if (d.status == 1)
                    return 'Pending';
                else if (d.status == 2) return 'processed';
                else if (d.status == 3) return 'Accept';
                else if (d.status == 4) {
                    return "Rejected";
                }
                    return "Unrecognized";}}
            ,{field:'type', title:'type' ,templet: function (d){
                if(d.type==1) {return 'buy';}
                else if (d.type==2) {return 'sale';}
                else return 'unrecognized'}}
            ,{field:'issueDate', title:'date', sort:true}
            ,{field: 'rejectReason',title:'Reject Reason'}
        ]]
    });
});