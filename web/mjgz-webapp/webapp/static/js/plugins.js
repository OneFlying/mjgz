/**
 * Created by Administrator on 2016/8/9.
 * js插件
 */
;(function ($,window) {

    "use strict";
    var arr = []; //缓存 流程节点
    //刷新时间
    var time = setInterval(function () {
        var date = moment().format("YYYY年MM月DD日 HH:mm:ss");
        $('#time').html(date);
    },1000);

    //物料管理
    window.materialGrid_load = function() {

        $('#materialGrid').datagrid({
            url: RESOUCE_SYSTEM_URL_JS +'/material/getall',
            method: 'get',
            idField: 'id',
            columns: [[
                {
                    title: "编号",
                    field: "id",
                    checkbox: true
                }, {
                    title: "物料编号",
                    field: "num",
                    width: 100
                }, {
                    title: "物料名称",
                    field: "name",
                    width: 100
                }, {
                    title: "物料规格",
                    field: "stantard",
                    width: 100
                }, {
                    title: "物料材质",
                    field: "material",
                    width: 100
                }, {
                    title: "使用寿命",
                    field: "life",
                    width: 100
                }, {
                    title: "图纸",
                    field: "drawing",
                    width: 100,
                    formatter: function (value, row, index) {
                        if(row.drawing == null || row.drawing == '') {
                            return '';
                        } else {
                            return '<a href="#" onclick="$(this).showBigImg();">' +
                                    '<img style="margin-top:4px; height: 18px;" alt="1" src="'+RESOUCE_UPLOAD_URL_JS+row.drawing+'"/></a>';
                        }
                    }
                }
            ]],
            singleSelect:false,
            selectOnCheck:true,
            fitColumns:true,
            pageSize:10,
            pageList:[5,10,15,20],
            autoRowHeight:true,
            striped:true,
            pageNumber: 1,
            pagination: true,
            toolbar: '#materialGrid_toolbar'
        });
        $(window).on('resize', debounce(function (event) {
            $('#materialGrid').datagrid('resize');
        },300));
    };
    window.orderGrid_load = function() {
        $('#orderGrid').datagrid({
            url: RESOUCE_SYSTEM_URL_JS +'/order/getall',
            method: 'get',
            idField: 'id',
            columns: [[
                {
                    field: "id",
                    title: "id",
                    checkbox: true
                }, {
                    field: "materialId",
                    title: "物料编号",
                    width: 100
                }, {
                    field: "node",
                    title: "节点名称",
                    width: 100
                }, {
                    field: "code",
                    title: "二维码",
                    width: 100
                }, {
                    field: "status",
                    title: "状态",
                    width: 100,
                    formatter: function (value, row, index) {
                        if(value == 0) {
                            return '<font><i class="fa fa-clock-o fa-fw"></i>&nbsp;未执行</font>';
                        } else if (value == 1) {
                            return '<font color="orange"><i class="fa fa-spinner fa-spin fa-fw"></i>&nbsp;正在执行</font>'
                        } else if (value == 2) {
                            return '<font color="red"><i class="fa fa-warning fa-fw"></i>&nbsp;执行超时</font>';
                        } else if (value == 3) {
                            return '<font color="green"><i class="fa fa-check-circle-o"></i>&nbsp;执行结束</font>';
                        } else {
                            return '';
                        }
                    }
                }
            ]],
            singleSelect:false,
            selectOnCheck:true,
            fitColumns:true,
            pageSize:10,
            pageList:[5,10,15,20],
            autoRowHeight:false,
            striped:true,
            pageNumber: 1,
            pagination: true,
            toolbar: '#orderGrid_toolbar',
            rowStyler: function () {

            },
            view: detailview,
            detailFormatter:function(index,row){
                return '<div style="margin: 5px;border: 1px solid #48a4ff"><table id="ddv-' + index + '"></table></div>';
            },
            onExpandRow:function(index,row){
                $('#ddv-'+index).datagrid({
                    url: RESOUCE_SYSTEM_URL_JS + '/track/getall/byorderId?orderId=' +(row.id),
                    method: 'get',
                    fitColumns:true,
                    singleSelect:true,
                    height:'auto',
                    striped:true,
                    columns:[[
                        {
                            field: "id",
                            title: "编号",
                            width: 150
                        }, {
                            field: "node",
                            title: "流程节点",
                            width: 100
                        }, {
                            field: "workName",
                            title: "操作人员",
                            width: 100
                        }, {
                            field: "time",
                            title: "操作时间",
                            width: 100
                        }, {
                            field: "code",
                            title: "条形码",
                            width: 100,
                            formatter: function (value, row, index) {
                                if(value != null || value != '') {
                                    return '<a href="javascript:;" onclick="$(this).showBigCode()"><img style="height:18px;padding-top:4px;" src="'+RESOUCE_BARCODE_URl_JS+'/'+value+'.png'+'" /></a>';
                                } else {
                                    return '';
                                }
                            }
                        }
                    ]],
                    onResize:function(){
                        $('#orderGrid').datagrid('fixDetailRowHeight',index);
                    },
                    onLoadSuccess:function(){
                        setTimeout(function(){
                            $('#orderGrid').datagrid('fixDetailRowHeight',index);
                        },0);
                    },
                    render: function (target, container, frozen) {
                        console.log(target);
                    }
                });
                $(window).on('resize', debounce(function (event) {
                    $('#ddv-'+index).datagrid('resize');
                },300));
                $('#orderGrid').datagrid('fixDetailRowHeight',index);
            }
        });
        $(window).on('resize', debounce(function (event) {
            $('#orderGrid').datagrid('resize');
        },300));
    };
    window.userGrid_load = function () {
        $('#userGrid').datagrid({
            url: RESOUCE_SYSTEM_URL_JS + '/user/getall',
            method: 'get',
            idField: 'id',
            columns: [[
                {
                    field: "id",
                    title: "id",
                    checkbox: true
                }, {
                    field: "name",
                    title: "用户名称",
                    width: 100
                }, {
                    field: "phone",
                    title: "电话号码",
                    width: 100
                }, {
                    field: "duty",
                    title: "用户职务",
                    width: 100
                }
            ]],
            singleSelect:false,
            selectOnCheck:true,
            fitColumns:true,
            pageSize:10,
            pageList:[5,10,15,20],
            autoRowHeight:false,
            striped:true,
            pageNumber: 1,
            pagination: true,
            toolbar: '#userGrid_toolbar'
        });
        $(window).on('resize', debounce(function (event) {
            $('#userGrid').datagrid('resize');
        },300));
    };
    window.nodeGrid_load = function () {
        $('#nodeGrid').datagrid({
            url: RESOUCE_SYSTEM_URL_JS + '/productionnode/getall',
            method: 'get',
            idField: 'id',
            columns: [[
                {
                    field: "id",
                    title: "id",
                    checkbox: "true"
                }, {
                    field: "name",
                    title: "节点名称",
                    width: 100
                }
            ]],
            singleSelect:false,
            selectOnCheck:true,
            fitColumns:true,
            pageSize:10,
            pageList:[5,10,15,20],
            autoRowHeight:false,
            striped:true,
            pageNumber: 1,
            pagination: true,
            toolbar: '#nodeGrid_toolbar'
        });
        $(window).on('resize', debounce(function (event) {
            $('#nodeGrid').datagrid('resize');
        },300));
    };

    //防止频繁操作
    var clicktag = 0;
    $(document).on('click', '[data-href]', function (e) {
        var page = $(this).data('href'),
            method = $(this).data('method');
        if(clicktag === 0) {
            clicktag = 1;
            $('.page').fadeOut();
            $(page).fadeIn();
            $('[data-href]').removeClass('active');
            $(this).addClass('active');
            var fn = window[method];
            if(typeof fn == 'function') {
                fn.apply(null,arguments);
            }
            setTimeout(function () {
                clicktag = 0;
            }, 500);
        } else {
            console.warn('操作过于频繁');
        }
    });

    //页面防抖
    function debounce(func, wait, immediate) {
        var timeout;
        return function() {
            var context = this, args = arguments;
            var later = function() {
                timeout = null;
                if (!immediate) func.apply(context, args);
            };
            var callNow = immediate && !timeout;
            clearTimeout(timeout);
            timeout = setTimeout(later, wait);
            if (callNow) func.apply(context, args);
        };
    }

    //初始化执行
    orderGrid_load();

    $("#steps").steps({
        headerTag: "h3",
        bodyTag: "section",
        transitionEffect: "slideLeft",
        autoFocus: true,
        labels: {
            cancel: "取消",
            current: "当前进度：",
            pagination: "分页",
            finish: "完成",
            next: "下一步",
            previous: "上一步",
            loading: "正在加载..."
        },
        onStepChanging: function (event, currentIndex, newIndex) {
            if(newIndex == 1 && $('#isFirstLoad').val() == "true") {
                $('#isFirstLoad').val("false");
                setTimeout(function () {
                    $('#nodeSelectGrid').datagrid({
                        url: RESOUCE_SYSTEM_URL_JS + '/productionnode/getall',
                        method: 'get',
                        idField: 'id',
                        onClickRow: onClickRow,
                        columns: [[
                            {
                                title: "编号",
                                field: "id",
                                checkbox: true
                            }, {
                                title: "流程节点",
                                field: "nodeName",
                                width: 100
                            }, {
                                title: "运行时间",
                                field: "overtime",
                                width: 100,
                                editor: {
                                    type: 'numberbox',
                                    options: {precision: 1}
                                }
                            }
                        ]],
                        singleSelect:false,
                        selectOnCheck:true,
                        fitColumns:true,
                        pageSize:10,
                        pageList:[10],
                        autoRowHeight:true,
                        striped:true,
                        pageNumber: 1,
                        pagination: true,
                        loadFilter: function (data) {
                            $(data.rows).each(function (index, item) {
                                item.overtime = 2;
                                item.nodeName = item.name;
                            });
                            return data;
                        }
                    });
                },500);
            }
            if(newIndex == 1) {
                var rows = $('#materialSelectGrid').datagrid('getChecked'),materialId= '';
                $(rows).each(function (index, item) {
                    materialId += item.id +',';
                });
                $('input[name="materialId"]').val(materialId.substring(0,materialId.length-1));
            }
            if(newIndex == 2) {
                var items = $('#nodeSelectGrid').datagrid('getChecked');
                $(items).each(function (index, item) {
                    var obj = {};
                    obj.overtime = item.overtime;
                    obj.nodeName = item.nodeName;
                    arr.push(obj);
                });
            }
            return true;
        },
        onFinishing: function (event, currentIndex) {
            var param = {};
            param.materialId = $('input[name="materialId"]').val();
            param.orderNodes = arr;
            console.log(param);
            $.ajax({
                url: RESOUCE_SYSTEM_URL_JS+"/order/add",
                type:'POST',
                dataType: "json",
                contentType:"application/json",
                data:JSON.stringify(param),
                success: function (data) {
                    if(data.success) {
                        $.messager.alert('提示信息','提交订单完成','info');
                        $('#orderGrid_window').window('close');
                        return true;
                    } else {
                        $.messager.alert('错误','提交订单失败','error');
                        return false;
                    }
                }
            });
        }
    });

    var editIndex = undefined;
    function endEditing(){
        if (editIndex == undefined){return true}
        if ($('#nodeSelectGrid').datagrid('validateRow', editIndex)){
            var ed = $('#nodeSelectGrid').datagrid('getEditor', {index:editIndex,field:'id'});
            /*var productname = $(ed.target).combobox('getText');
            $('#nodeSelectGrid').datagrid('getRows')[editIndex]['productname'] = productname;*/
            $('#nodeSelectGrid').datagrid('endEdit', editIndex);
            editIndex = undefined;
            return true;
        } else {
            return false;
        }
    }
    function onClickRow(index){
        if (editIndex != index){
            if (endEditing()){
                $('#nodeSelectGrid').datagrid('selectRow', index)
                    .datagrid('beginEdit', index);
                editIndex = index;
            } else {
                $('#nodeSelectGrid').datagrid('selectRow', editIndex);
            }
        }
    }

})(jQuery,window);

+function ($) {

    "use strict";

    $.fn.showBigImg = function() {
        var imgSrc = $(this).find('img').attr('src');
        $('#bigImg').attr('src',imgSrc);
        $('#bigImg_window').window('open');
    };

    $.fn.showBigCode = function () {
        var imgSrc = $(this).find('img').attr('src');
        $('#bigCode').attr('src',imgSrc);
        $('#bigCode_window').window('open');
    };

    var userGrid_type = 'add',
        nodeGrid_type = 'add',
        materialGrid_type = 'add',
        orderGrid_type = 'add';

    (function () {
        $(document).on('click', '[data-method]', function (e) {
            var method = $(this).data('method');
            switch (method) {
                case 'userGrid_add':
                    userGrid_add();
                    break;
                case 'userGrid_edit':
                    userGrid_edit();
                    break;
                case 'userGrid_del':
                    userGrid_del();
                    break;
                case 'addUser':
                    addUser();
                    break;
                case 'resetUser':
                    $('#userGrid_form').form('reset');
                    break;
                case 'nodeGrid_add':
                    nodeGrid_add();
                    break;
                case 'nodeGrid_edit':
                    nodeGrid_edit();
                    break;
                case 'nodeGrid_del':
                    nodeGrid_del();
                    break;
                case 'addNode':
                    addNode();
                    break;
                case 'resetNode':
                    $('#nodeGrid_form').form('reset');
                    break;
                case 'materialGrid_add':
                    materialGrid_add();
                    break;
                case 'materialGrid_edit':
                    materialGrid_edit();
                    break;
                case 'materialGrid_del':
                    materialGrid_del();
                    break;
                case 'addMaterial':
                    addMaterial();
                    break;
                case 'resetMaterial':
                    $('#materialGrid_form').form('reset');
                    break;
                case 'orderGrid_add':
                    orderGrid_add();
                    break;
                case 'orderGrid_edit':
                    orderGrid_edit();
                    break;
                case 'orderGrid_del':
                    orderGrid_del();
                    break;
                case 'addOrder':
                    addOrder();
                    break;
                case 'resetOrder':
                    $('#orderGrid_form').form('reset');
                    break;
                default:
                    break;
            }
        });
    })();
    //订单管理
    function orderGrid_add() {
        $('#orderGrid_form').form('reset');
        $('#orderGrid_window').window('open').panel({
            title: "添加新订单"
        });
        init();
        orderGrid_type = 'add';
    }
    function init(type) {
        $('#materialSelectGrid').datagrid({
            url: RESOUCE_SYSTEM_URL_JS + '/material/getall',
            method: 'get',
            idField: 'id',
            columns: [[
                {
                    title: "编号",
                    field: "id",
                    checkbox: true
                }, {
                    title: "物料编号",
                    field: "num",
                    width: 100
                }, {
                    title: "物料名称",
                    field: "name",
                    width: 100
                }, {
                    title: "物料规格",
                    field: "stantard",
                    width: 100
                }, {
                    title: "物料材质",
                    field: "material",
                    width: 100
                }
            ]],
            singleSelect:false,
            selectOnCheck:true,
            fitColumns:true,
            pageSize:10,
            pageList:[10],
            autoRowHeight:true,
            striped:true,
            pageNumber: 1,
            pagination: true
        });
    }
    function orderGrid_edit() {
        var rows = $('#orderGrid').datagrid('getChecked');
        if(rows.length == 1) {
            if(rows[0].status !== 0) {
                $.messager.alert('提示','只有未执行的订单才可以修改','info');
            } else {
                $('#orderGrid_form').form('reset').form('load', rows[0]);
                $('#orderGrid_window').window('open').panel({
                    title:'更新订单信息'
                });
                orderGrid_type = 'edit';
            }
        } else {
            $.messager.alert('提示','请选择一个订单进行编辑！','info');
        }
    }
    function orderGrid_del() {
        var rows = $('#orderGrid').datagrid('getChecked'),
            isEnableDel = true;
        if(rows.length <= 0 ){
            $.messager.alert('提示','请选择您要删除的订单！','info');
        }else{
            var idArray = [];
            $(rows).each(function(index,item){
                idArray.push(item.id);
                if(item.status === 1) {
                    isEnableDel = false;
                    $.messager.alert('提示','正在执行的订单不能删除','info');
                }
            });
            if(isEnableDel) {
                $.messager.confirm('温馨提示', '是否要删除该信息？', function(r){
                    if (r){
                        $.post(RESOUCE_SYSTEM_URL_JS+'/order/del',{ids:idArray},function(data){
                            if(data.success){
                                $('#orderGrid').datagrid('reload').datagrid('clearChecked');
                                $.messager.alert('提示','删除成功','info');
                            }else{
                                $.messager.alert('警告','删除失败','warning');
                            }
                        });
                    }
                });
            }
        }
    }
    function addOrder() {
        if(orderGrid_type === 'add') {

        }
    }

    //物料管理
    function materialGrid_add() {
        $('#materialGrid_form').form('reset');
        $('#materialGrid_window').window('open').panel({
            title: "添加新物料"
        });
        materialGrid_type = 'add';
    }
    function materialGrid_edit() {
        var rows = $('#materialGrid').datagrid('getChecked');
        if(rows.length <= 0 || rows.length > 1){
            $.messager.alert('提示','请选择一个物料进行编辑！','info');
            return;
        }
        $('#materialGrid_form').form('reset').form('load', rows[0]);
        $('#materialGrid_window').window('open').panel({
            title:'更新物料信息'
        });
        materialGrid_type = 'edit';
    }
    function materialGrid_del() {
        var rows = $('#materialGrid').datagrid('getChecked');
        if(rows.length<=0 ){
            $.messager.alert('提示','请选择您要删除的物料！','info');
        }else{
            var idArray = [];
            $(rows).each(function(index,item){
                idArray.push(item.id);
            });
            $.messager.confirm('温馨提示', '是否要删除该信息？', function(r){
                if (r){
                    $.post(RESOUCE_SYSTEM_URL_JS+'/material/del',{ids:idArray},function(data){
                        if(data.success){
                            $('#materialGrid').datagrid('reload').datagrid('clearChecked');
                            $.messager.alert('提示','删除成功','info');
                        }else{
                            $.messager.alert('警告','删除失败','warning');
                        }
                    });
                }
            });
        }
    }
    function addMaterial() {
        var $form = $('#materialGrid_form');
        if(materialGrid_type === 'add') {
            $form.form('submit', {
                url: RESOUCE_SYSTEM_URL_JS + '/material/add',
                onSubmit: function () {
                    return $form.form('validate');
                },
                success: function (d) {
                    var data = eval('(' + d + ')');
                    if(data.success) {
                        $('#materialGrid_window').window('close');
                        $('#materialGrid').datagrid('reload');
                        $.messager.alert('消息', data.msg, 'info');
                    } else {
                        $.messager.alert('错误', data.msg, 'error');
                    }
                },
                error: function (msg) {
                    $.messager.alert('错误',msg, 'error');
                }
            });
        } else if (materialGrid_type === 'edit') {
            $form.form('submit', {
                url: RESOUCE_SYSTEM_URL_JS + '/material/update',
                onSubmit: function () {
                    return $form.form('validate');
                },
                success: function (d) {
                    var data = eval('(' + d + ')');
                    if(data.success) {
                        $('#materialGrid_window').window('close');
                        $('#materialGrid').datagrid('reload');
                        $.messager.alert('消息', data.msg, 'info');
                    } else {
                        $.messager.alert('错误', data.msg, 'error');
                    }
                },
                error: function (msg) {
                    $.messager.alert('错误',msg, 'error');
                }
            });
        }
    }
    $.upload = function() {
        var formData = new FormData($("#fileupload")[0]);
        $.ajax({
            url: RESOUCE_SYSTEM_URL_JS + '/material/upload',
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                $("#drawing").val(data.logourl);
            }
        });
    };
    //流程节点管理
    function nodeGrid_add() {
        $('#nodeGrid_form').form('reset');
        $('#nodeGrid_window').window('open').panel({
            title: "添加生产节点"
        });
        nodeGrid_type = 'add';
    }
    function nodeGrid_edit() {
        var rows = $('#nodeGrid').datagrid('getChecked');
        if(rows.length <= 0 || rows.length > 1){
            $.messager.alert('提示','请选择一个生产节点进行编辑！','info');
            return;
        }
        $('#nodeGrid_form').form('reset').form('load', rows[0]);
        $('#nodeGrid_window').window('open').panel({
            title:'更新生产节点'
        });
        nodeGrid_type = 'edit';
    }
    function nodeGrid_del() {
        var rows = $('#nodeGrid').datagrid('getChecked');
        if(rows.length<=0 ){
            $.messager.alert('提示','请选择您要删除的生产节点！','info');
        }else{
            var idArray = [];
            $(rows).each(function(index,item){
                idArray.push(item.id);
            });
            $.messager.confirm('温馨提示', '是否要删除该信息？', function(r){
                if (r){
                    $.post(RESOUCE_SYSTEM_URL_JS+'/productionnode/del',{ids:idArray},function(data){
                        if(data.success){
                            $('#nodeGrid').datagrid('reload').datagrid('clearChecked');
                            $.messager.alert('提示','删除成功','info');
                        }else{
                            $.messager.alert('警告','删除失败','warning');
                        }
                    });
                }
            });
        }
    }
    function addNode() {
        var $form = $('#nodeGrid_form');
        if(nodeGrid_type === 'add') {
            $form.form('submit', {
                url: RESOUCE_SYSTEM_URL_JS + '/productionnode/add',
                onSubmit: function () {
                    return $form.form('validate');
                },
                success: function (d) {
                    var data = eval('(' + d + ')');
                    if(data.success) {
                        $('#nodeGrid_window').window('close');
                        $('#nodeGrid').datagrid('reload');
                        $.messager.alert('消息', data.msg, 'info');
                    } else {
                        $.messager.alert('错误', data.msg, 'error');
                    }
                },
                error: function (msg) {
                    $.messager.alert('错误',msg, 'error');
                }
            });
        } else if (nodeGrid_type === 'edit') {
            $form.form('submit', {
                url: RESOUCE_SYSTEM_URL_JS + '/productionnode/update',
                onSubmit: function () {
                    return $form.form('validate');
                },
                success: function (d) {
                    var data = eval('(' + d + ')');
                    if(data.success) {
                        $('#nodeGrid_window').window('close');
                        $('#nodeGrid').datagrid('reload');
                        $.messager.alert('消息', data.msg, 'info');
                    } else {
                        $.messager.alert('错误', data.msg, 'error');
                    }
                },
                error: function (msg) {
                    $.messager.alert('错误',msg, 'error');
                }
            });
        }
    }

    // 用户管理
    function userGrid_add() {
        $('#userGrid_form').form('reset');
        $('#userGrid_window').window('open').panel({
            title: "添加新用户"
        });
        userGrid_type = 'add';
    }
    function userGrid_edit() {
        var rows = $('#userGrid').datagrid('getChecked');
        if(rows.length <= 0 || rows.length > 1){
            $.messager.alert('提示','请选择一个人员进行编辑！','info');
            return;
        }
        $('#userGrid_form').form('reset').form('load', rows[0]);
        $('#userGrid_window').window('open').panel({
            title:'更新人员信息'
        });
        userGrid_type = 'edit';
    }
    function userGrid_del() {
        var rows = $('#userGrid').datagrid('getChecked');
        if(rows.length<=0 ){
            $.messager.alert('提示','请选择您要删除的人员！','info');
        }else{
            var idArray = [];
            $(rows).each(function(index,item){
                idArray.push(item.id);
            });
            $.messager.confirm('温馨提示', '是否要删除该信息？', function(r){
                if (r){
                    $.post(RESOUCE_SYSTEM_URL_JS+'/user/del',{ids:idArray},function(data){
                        if(data.success){
                            $('#userGrid').datagrid('reload').datagrid('clearChecked');
                            $.messager.alert('提示','删除成功','info');
                        }else{
                            $.messager.alert('警告','删除失败','warning');
                        }
                    });
                }
            });
        }
    }
    function addUser() {
        var $form = $('#userGrid_form');
        if(userGrid_type === 'add') {
            $form.form('submit', {
                url: RESOUCE_SYSTEM_URL_JS + '/user/add',
                onSubmit: function () {
                    return $form.form('validate');
                },
                success: function (d) {
                    var data = eval('(' + d + ')');
                    if(data.success) {
                        $('#userGrid_window').window('close');
                        $('#userGrid').datagrid('reload');
                        $.messager.alert('消息', data.msg, 'info');
                    } else {
                        $.messager.alert('错误', data.msg, 'error');
                    }
                },
                error: function (msg) {
                    $.messager.alert('错误',msg, 'error');
                }
            });
        } else if (userGrid_type === 'edit') {
            $form.form('submit', {
                url: RESOUCE_SYSTEM_URL_JS + '/user/update',
                onSubmit: function () {
                    return $form.form('validate');
                },
                success: function (d) {
                    var data = eval('(' + d + ')');
                    if(data.success) {
                        $('#userGrid_window').window('close');
                        $('#userGrid').datagrid('reload');
                        $.messager.alert('消息', data.msg, 'info');
                    } else {
                        $.messager.alert('错误', data.msg, 'error');
                    }
                },
                error: function (msg) {
                    $.messager.alert('错误',msg, 'error');
                }
            });
        }
    }

}(jQuery);



