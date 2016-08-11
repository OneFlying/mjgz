<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="userGrid_window" class="easyui-window" title="添加新用户" style="width:400px;height:280px;"
     data-options="modal:true,closed:true,collapsible:false,minimizable:false,maximizable:false">
    <form id="userGrid_form" class="easyui-form" method="post" data-options="validate:true" style="margin: 20px 0;">
        <input type="hidden" name="id">
        <table cellpadding="5">
            <tr>
                <td>人员姓名：</td>
                <td>
                    <input class="easyui-validatebox" missingmessage="必须填写人员姓名" type="text" autocomplete="false"
                           name="name" style="width:300px;" data-options="required:true">
                </td>
            </tr>
            <tr>
                <td>电话号码:</td>
                <td>
                    <input class="easyui-validatebox" missingmessage="必须填写电话号码" type="text" autocomplete="false"
                           name="phone" style="width:300px;" data-options="required:true">
                </td>
            </tr>
            <tr>
                <td>人员职务:</td>
                <td>
                    <input class="easyui-validatebox"  missingmessage="人员职务必须填写" type="text" autocomplete="false"
                           name="duty" data-options="required:true" style="width:300px;">
                </td>
            </tr>
            <tr>
                <td>登录密码:</td>
                <td>
                    <input class="easyui-validatebox"  missingmessage="登录密码必须填写" type="text" autocomplete="false"
                           name="passwd" data-options="required:true" style="width:300px;">
                </td>
            </tr>
        </table>
    </form>
    <div style="text-align: center; padding: 5px" class="controlbar">
        <a href="javascript:void(0)" icon="icon-ok" class="easyui-linkbutton" data-method="addUser">提交</a>
        <a href="javascript:void(0)" icon="icon-cancel" class="easyui-linkbutton" data-method="resetUser">重置</a>
    </div>
</div>