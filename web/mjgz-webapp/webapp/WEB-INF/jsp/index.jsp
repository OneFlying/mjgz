<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
    <title>磨具跟踪</title>
    <%@ include file="/resource.jsp"%>
    <script src="${RESOUCE_STATIC_URL}/lib/modernizr.js"></script>
    <link rel="stylesheet" type="text/css" href="${RESOUCE_STATIC_URL}/lib/fontawesome/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${RESOUCE_STATIC_URL}/css/style.css">
<body>
    <div class="overlay">
        <div class="header">
            <div class="logo">
                <a href="#">OneFlying Template V1.0</a>
            </div>
            <div class="navbar">
                <ul>
                    <li><a href="#">2016年07月01日 19:20:59</a> </li>
                    <li><a href="#"><i class="fa fa-envelope fa-fw"></i><i class="fa fa-caret-down"></i></a> </li>
                    <li><a href="#"><i class="fa fa-user fa-fw"></i><i class="fa fa-caret-down"></i> </a> </li>
                    <li><a href="#"><i class="fa fa-download fa-fw"></i><i class="fa fa-caret-down"></i> </a> </li>
                    <li><a href="#"><i class="fa fa-question-circle fa-fw"></i><i class="fa fa-caret-down"></i> </a> </li>
                </ul>
            </div>
        </div>
        <div class="content">
            <div class="sidebar">
                <div class="searchbar">
                    <input type="text" name="keywords" title="" placeholder="搜索..." />
                    <span>
                        <button><i class="fa fa-search fa-fw"></i> </button>
                    </span>
                </div>
                <div class="sidemenu">
                    <ul>
                        <li><a href="#"><i class="fa fa-dashboard fa-fw"></i>&nbsp;Dashboard</a></li>
                        <li><a href="#"><i class="fa fa-bar-chart-o fa-fw"></i>&nbsp;Charts</a> </li>
                        <li><a href="#"><i class="fa fa-table fa-fw"></i>&nbsp;Tables</a> </li>
                        <li><a href="#"><i class="fa fa-edit fa-fw"></i>&nbsp;Froms</a> </li>
                        <li>
                            <a href="#" class="active">
                                <i class="fa fa-wrench fa-fw"></i>&nbsp;Wrench
                                <span class="arrow">
                                    <i class="fa fa-angle-left fa-fw"></i>
                                </span>
                            </a>
                            <ul>
                                <li><a href="#">Dashboard</a></li>
                                <li><a href="#" class="active">Charts</a> </li>
                                <li><a href="#">Tables</a> </li>
                                <li><a href="#">Froms</a> </li>
                            </ul>
                        </li>
                        <li><a href="#"><i class="fa fa-paper-plane-o"></i>&nbsp;Papers </a> </li>
                    </ul>
                </div>
            </div>
            <div class="container">
                <!-- TODO page 存放地址 -->
                <div class="page" id="pageOne">
                    <div class="page-header">
                        <h1>在这里显示页面标题</h1>
                    </div>
                    <div class="page-content">
                        <div class="datagrid">
                            <div class="datagrid-header">
                                数据表格的标题
                            </div>
                            <div class="datagrid-content">
                                <div class="datagrid-searchbar">
                                    <span>条件一</span><input type="text" name="" title="">
                                    <span>条件二</span><input type="text" name="" title="">
                                    <span>条件三</span><input type="text" name="" title="">
                                    <button>搜索</button>
                                    <button>后退</button>
                                </div>
                                <div class="datagrid-items">
                                    <div class="datagrid-item datagrid-title">
                                        <span style="width: 15%">菜单一</span>
                                        <span style="width: 30%">菜单二</span>
                                        <span style="width: 15%">菜单三</span>
                                        <span style="width: 10%">菜单四</span>
                                        <span style="width: 20%">菜单五</span>
                                        <span style="width: 10%">菜单六</span>
                                    </div>
                                    <div class="datagrid-item">
                                        <span style="width: 15%">菜单一</span>
                                        <span style="width: 30%">菜单二</span>
                                        <span style="width: 15%">菜单三</span>
                                        <span style="width: 10%">菜单四</span>
                                        <span style="width: 20%">菜单五</span>
                                        <span style="width: 10%">菜单六</span>
                                    </div>
                                    <div class="datagrid-item double">
                                        <span style="width: 15%">菜单一</span>
                                        <span style="width: 30%">菜单二</span>
                                        <span style="width: 15%">菜单三</span>
                                        <span style="width: 10%">菜单四</span>
                                        <span style="width: 20%">菜单五</span>
                                        <span style="width: 10%">菜单六</span>
                                    </div>
                                    <div class="datagrid-item">
                                        <span style="width: 15%">菜单一</span>
                                        <span style="width: 30%">菜单二</span>
                                        <span style="width: 15%">菜单三</span>
                                        <span style="width: 10%">菜单四</span>
                                        <span style="width: 20%">菜单五</span>
                                        <span style="width: 10%">菜单六</span>
                                    </div>
                                    <div class="datagrid-item double">
                                        <span style="width: 15%">菜单一</span>
                                        <span style="width: 30%">菜单二</span>
                                        <span style="width: 15%">菜单三</span>
                                        <span style="width: 10%">菜单四</span>
                                        <span style="width: 20%">菜单五</span>
                                        <span style="width: 10%">菜单六</span>
                                    </div>
                                    <div class="datagrid-item">
                                        <span style="width: 15%">菜单一</span>
                                        <span style="width: 30%">菜单二</span>
                                        <span style="width: 15%">菜单三</span>
                                        <span style="width: 10%">菜单四</span>
                                        <span style="width: 20%">菜单五</span>
                                        <span style="width: 10%">菜单六</span>
                                    </div>
                                    <div class="datagrid-item double">
                                        <span style="width: 15%">菜单一</span>
                                        <span style="width: 30%">菜单二</span>
                                        <span style="width: 15%">菜单三</span>
                                        <span style="width: 10%">菜单四</span>
                                        <span style="width: 20%">菜单五</span>
                                        <span style="width: 10%">菜单六</span>
                                    </div>
                                    <div class="datagrid-item">
                                        <span style="width: 15%">菜单一</span>
                                        <span style="width: 30%">菜单二</span>
                                        <span style="width: 15%">菜单三</span>
                                        <span style="width: 10%">菜单四</span>
                                        <span style="width: 20%">菜单五</span>
                                        <span style="width: 10%">菜单六</span>
                                    </div>
                                    <div class="datagrid-item double">
                                        <span style="width: 15%">菜单一</span>
                                        <span style="width: 30%">菜单二</span>
                                        <span style="width: 15%">菜单三</span>
                                        <span style="width: 10%">菜单四</span>
                                        <span style="width: 20%">菜单五</span>
                                        <span style="width: 10%">菜单六</span>
                                    </div>
                                </div>
                            </div>
                            <div class="datagrid-footer">
                                <button name="lastPage">最后一页</button>
                                <button name="nextPage">后一页</button>
                                <button name="jumpPage">跳转</button>
                                <input type="text" title="" name="pagenum" value="1" />
                                <button name="prevPage">前一页</button>
                                <button name="firstPage">首页</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="footer">
                &copy;CopyRight 2016; 上海一飞科技工作室版权所有; All Rights Reserved.
            </div>
        </div>
    </div>
    <script src="${RESOUCE_STATIC_URL}/lib/jquery.min.js"></script>
</body>
</html>
