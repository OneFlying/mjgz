<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="orderGrid_window" class="easyui-window" title="添加新用户" style="width:960px;height:500px;"
     data-options="modal:true,closed:true,collapsible:false,minimizable:false,maximizable:false">
    <form id="orderGrid_form" class="easyui-form" method="post" data-options="validate:true" style="padding: 5px">
        <input type="hidden" name="id">
        <input type="hidden" name="materialId">
        <input type="hidden" name="orderNodes">
        <input type="hidden" id="isFirstLoad" value="true" />
        <div id="steps">
            <h3>选择物料</h3>
            <section>
                <table id="materialSelectGrid" style="width:924px;height:308px;"></table>
            </section>
            <h3>选择流程</h3>
            <section>
                <table id="nodeSelectGrid" style="width:924px;height:308px;"></table>
            </section>
            <h3>完成</h3>
            <section>
                <p>正在提交订单....</p>
            </section>
        </div>
    </form>
</div>