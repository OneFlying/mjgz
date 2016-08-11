<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="materialGrid_window" class="easyui-window" title="添加新用户" style="width:400px;height:365px;"
     data-options="modal:true,closed:true,collapsible:false,minimizable:false,maximizable:false">
    <form id="materialGrid_form" class="easyui-form" method="post" data-options="validate:true" style="margin: 20px 0 0;">
        <input type="hidden" name="id">
        <table cellpadding="5">
            <tr>
                <td>物料编号：</td>
                <td colspan="2">
                    <input class="easyui-validatebox" missingmessage="必须填写物料编号" type="text" autocomplete="false"
                           name="num" style="width:300px;" data-options="required:true">
                </td>
            </tr>
            <tr>
                <td>物料名称：</td>
                <td colspan="2">
                    <input class="easyui-validatebox" missingmessage="必须填写物料名称" type="text" autocomplete="false"
                           name="name" style="width:300px;" data-options="required:true">
                </td>
            </tr>
            <tr>
                <td>物料规格：</td>
                <td colspan="2">
                    <input class="easyui-validatebox" missingmessage="必须填写物料规格" type="text" autocomplete="false"
                           name="stantard" style="width:300px;" data-options="required:true">
                </td>
            </tr>
            <tr>
                <td>物料材质：</td>
                <td colspan="2">
                    <input class="easyui-validatebox" missingmessage="必须填写物料材质" type="text" autocomplete="false"
                           name="material" style="width:300px;" data-options="required:true">
                </td>
            </tr>
            <tr>
                <td>使用寿命：</td>
                <td colspan="2">
                    <input class="easyui-validatebox" missingmessage="必须填写使用寿命" type="text" autocomplete="false"
                           name="life" style="width:300px;" data-options="required:true">
                </td>
            </tr>
            <tr>
                <td>图纸：</td>
                <td colspan="2">
                    <input class="easyui-validatebox" type="text" autocomplete="false" name="drawing" id="drawing" style="width:300px;" readonly>
                </td>
            </tr>
        </table>
    </form>
    <form id="fileupload" enctype="multipart/form-data" class="easyui-form" style="margin: 0 0 20px;">
        <table cellpadding="5">
            <tr>
                <td>上传图纸：</td>
                <td>
                    <input type="file" name="pic" id="file" style="width: 150px;" accept="image/*">
                </td>
                <td>
                    <button onclick="$.upload()">上传图纸</button>
                </td>
            </tr>
        </table>
    </form>
    <div style="text-align: center; padding: 5px 10px" class="controlbar">
        <a href="javascript:void(0)" icon="icon-ok" class="easyui-linkbutton" data-method="addMaterial">提交</a>
        <a href="javascript:void(0)" icon="icon-cancel" class="easyui-linkbutton" data-method="resetMaterial">重置</a>
    </div>
</div>