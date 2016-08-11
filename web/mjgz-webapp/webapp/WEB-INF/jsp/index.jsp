<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
    <title>明皓模具生产跟踪系统</title>
    <%@ include file="/resource.jsp"%>
    <script src="${RESOUCE_STATIC_URL}/lib/modernizr.custom.js"></script>
    <link rel="stylesheet" href="${RESOUCE_STATIC_URL}/lib/fontawesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${RESOUCE_STATIC_URL}/lib/jquery-easyui-1.4.3/themes/bootstrap/easyui.css">
    <link rel="stylesheet" href="${RESOUCE_STATIC_URL}/lib/jquery-easyui-1.4.3/themes/icon.css">
    <link rel="stylesheet" href="${RESOUCE_STATIC_URL}/css/style.css">
    <link rel="stylesheet" href="${RESOUCE_STATIC_URL}/css/jquery.steps.css">
</head>
<body>
    <div class="overlay">
        <div class="header">
            <div class="logo">
                <a href="javascript:;">明皓模具生产跟踪系统</a>
            </div>
            <div class="navbar">
                <ul>
                    <li><a href="javascript:;" id="time">2016年07月01日 19:20:59</a> </li>
                    <li><a href="javascript:;"><i class="fa fa-envelope fa-fw"></i><i class="fa fa-caret-down"></i></a> </li>
                    <li><a href="javascript:;"><i class="fa fa-user fa-fw"></i><i class="fa fa-caret-down"></i> </a> </li>
                    <li><a href="javascript:;"><i class="fa fa-download fa-fw"></i><i class="fa fa-caret-down"></i> </a> </li>
                    <li><a href="javascript:;"><i class="fa fa-question-circle fa-fw"></i><i class="fa fa-caret-down"></i> </a> </li>
                </ul>
            </div>
        </div>
        <div class="content">
            <div class="sidebar">
                <div class="searchbar">
                    <input type="text" name="keywords" title="" placeholder="搜索..." />
                    <span><button><i class="fa fa-search fa-fw"></i> </button></span>
                </div>
                <div class="sidemenu">
                    <ul>
                        <li><a href="javascript:;" data-href="#pageOne" data-method="orderGrid_load" class="active"><i class="fa fa-calendar fa-fw"></i>&nbsp;订单管理</a> </li>
                        <li><a href="javascript:;" data-href="#pageTwo" data-method="materialGrid_load"><i class="fa fa-archive fa-fw"></i>&nbsp;物料管理</a></li>
                        <li><a href="javascript:;" data-href="#pageFour" data-method="userGrid_load"><i class="fa fa-user fa-fw"></i>&nbsp;用户管理</a> </li>
                        <li><a href="javascript:;" data-href="#pageFive" data-method="nodeGrid_load"><i class="fa fa-diamond fa-fw"></i>&nbsp;流程节点 </a> </li>
                    </ul>
                </div>
            </div>
            <div class="container">
                <div class="page" id="pageOne">
                    <div class="page-header"><h1>订单管理</h1></div>
                    <div class="page-content">
                        <table id="orderGrid"></table>
                    </div>
                    <div id="orderGrid_toolbar" style="padding:5px;height:auto;display: none">
                        <div style="margin-bottom:5px">
                            <a href="javascript:;" class="btn btn-success" data-method="orderGrid_add"><i class="fa fa-plus fa-fw"></i>添加 </a>
                            <a href="javascript:;" class="btn btn-warning" data-method="orderGrid_edit"><i class="fa fa-edit fa-fw"></i>修改 </a>
                            <a href="javascript:;" class="btn btn-danger" data-method="orderGrid_del"><i class="fa fa-remove fa-fw"></i>删除 </a>
                        </div>
                    </div>
                </div>
                <div class="page" id="pageTwo">
                    <div class="page-header"><h1>物料管理</h1></div>
                    <div class="page-content">
                        <table id="materialGrid"></table>
                    </div>
                    <div id="materialGrid_toolbar" style="padding: 5px;height:auto">
                        <div style="margin-bottom:5px">
                            <a href="javascript:;" class="btn btn-success" data-method="materialGrid_add"><i class="fa fa-plus fa-fw"></i>添加 </a>
                            <a href="javascript:;" class="btn btn-warning" data-method="materialGrid_edit"><i class="fa fa-edit fa-fw"></i>修改 </a>
                            <a href="javascript:;" class="btn btn-danger" data-method="materialGrid_del"><i class="fa fa-remove fa-fw"></i>删除 </a>
                        </div>
                    </div>
                </div>
                <div class="page" id="pageFour">
                    <div class="page-header"><h1>用户管理</h1></div>
                    <div class="page-content">
                        <table id="userGrid"></table>
                    </div>
                    <div id="userGrid_toolbar" style="padding: 5px;height:auto">
                        <div style="margin-bottom:5px">
                            <a href="javascript:;" class="btn btn-success" data-method="userGrid_add"><i class="fa fa-plus fa-fw"></i>添加 </a>
                            <a href="javascript:;" class="btn btn-warning" data-method="userGrid_edit"><i class="fa fa-edit fa-fw"></i>修改 </a>
                            <a href="javascript:;" class="btn btn-danger" data-method="userGrid_del"><i class="fa fa-remove fa-fw"></i>删除 </a>
                        </div>
                    </div>
                </div>
                <div class="page" id="pageFive">
                    <div class="page-header"><h1>流程节点</h1></div>
                    <div class="page-content">
                        <table id="nodeGrid"></table>
                    </div>
                    <div id="nodeGrid_toolbar" style="padding: 5px;height:auto">
                        <div style="margin-bottom:5px">
                            <a href="javascript:;" class="btn btn-success" data-method="nodeGrid_add"><i class="fa fa-plus fa-fw"></i>添加 </a>
                            <a href="javascript:;" class="btn btn-warning" data-method="nodeGrid_edit"><i class="fa fa-edit fa-fw"></i>修改 </a>
                            <a href="javascript:;" class="btn btn-danger" data-method="nodeGrid_del"><i class="fa fa-remove fa-fw"></i>删除 </a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="footer">
                &copy;CopyRight 2016; 上海一飞科技工作室版权所有; All Rights Reserved.
            </div>
        </div>
    </div>
    <div id="bigImg_window" class="easyui-window" title="图纸预览" style="width: 900px; height: 600px;" data-options="closed:true">
        <div>
            <img src="" alt="1" id="bigImg" />
        </div>
    </div>
    <div id="bigCode_window" class="easyui-window" title="条形码预览" style="width: 600px; height: 400px;" data-options="closed:true">
        <div style="text-align: center; padding-top: 100px;">
            <img src="" alt="2" id="bigCode" style="border: 1px solid #f5f5f5;" />
        </div>
    </div>
    <%@include file="/popbox/user.jsp" %>
    <%@include file="/popbox/node.jsp" %>
    <%@include file="/popbox/material.jsp" %>
    <%@include file="/popbox/order.jsp" %>
    <script src="${RESOUCE_STATIC_URL}/lib/jquery.min.js"></script>
    <script src="${RESOUCE_STATIC_URL}/lib/jquery.steps.min.js"></script>
    <script src="${RESOUCE_STATIC_URL}/lib/moment.min.js"></script>
    <script src="${RESOUCE_STATIC_URL}/lib/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
    <script src="${RESOUCE_STATIC_URL}/lib/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js"></script>
    <script src="${RESOUCE_STATIC_URL}/lib/datagrid-detailview.js"></script>
    <script src="${RESOUCE_STATIC_URL}/js/plugins.js"></script>
</body>
</html>
