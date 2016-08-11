<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="nodeGrid_window" class="easyui-window" title="添加新用户" style="width:400px;height:180px;"
     data-options="modal:true,closed:true,collapsible:false,minimizable:false,maximizable:false">
    <form id="nodeGrid_form" class="easyui-form" method="post" data-options="validate:true" style="margin: 20px 0;">
        <input type="hidden" name="id">
        <table cellpadding="5">
            <tr>
                <td>节点名称：</td>
                <td>
                    <input class="easyui-validatebox" missingmessage="必须填写节点名称" type="text" autocomplete="false"
                           name="name" style="width:300px;" data-options="required:true">
                </td>
            </tr>
        </table>
    </form>
    <div style="text-align: center; padding: 5px" class="controlbar">
        <a href="javascript:void(0)" icon="icon-ok" class="easyui-linkbutton" data-method="addNode">提交</a>
        <a href="javascript:void(0)" icon="icon-cancel" class="easyui-linkbutton" data-method="resetNode">重置</a>
    </div>
</div>