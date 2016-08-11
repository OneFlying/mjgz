<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
    <title>磨具跟踪</title>
    <%@ include file="/resource.jsp"%>
    <script src="${RESOUCE_STATIC_URL}/lib/modernizr.js"></script>
    <link rel="stylesheet" type="text/css" href="${RESOUCE_STATIC_URL}/lib/fontawesome/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${RESOUCE_STATIC_URL}/css/style.css">
</head>
<body>
    <button onclick="addMaterial()">添加</button>
    <button onclick="getInfo()">获取</button>
    <script src="${RESOUCE_STATIC_URL}/lib/jquery.min.js"></script>
    <script type="text/javascript">
    	function addMaterial(){
    		//添加订单
    		var url = RESOUCE_SYSTEM_URL_JS+"/order/add";
    		var arr = [];
    		var obj = {
    				"nodeName":"测试节点1",
					"overtime":"132"
					}
    		var obj2 = {
    				"nodeName":"测试节点2",
					"overtime":"132"
					}
    		var obj3 = {
    				"nodeName":"测试节点3",
					"overtime":"132"
					}
    		arr.push(obj);
    		arr.push(obj2);
    		arr.push(obj3);
    		var param = {
    				"materialId":"07c8c6bba00644e0b14ee94486bfa871",
    				"orderNodes": arr 				
    		}
    		
    		$.ajax({
    			url:url,
    			type:'POST',
    			dataType: "json",
    			contentType:"application/json",
    			data:JSON.stringify(param),
    			success : function(data){
    				console.log(data);
    			}
    		});
    		 /* var url = RESOUCE_SYSTEM_URL_JS+"/productionnode/add";
     	
     		var param = {
     				"name":"fasdf"				
     		}
     		
     		$.ajax({
     			url:url,
     			type:'POST',
     			data:param,
     			success : function(data){
     				console.log(data);
     			}
     		}); */
    		
    		
    		
    	}	
    	
    	function getInfo(){
    	
    		var url = RESOUCE_SYSTEM_URL_JS+"/productionnode/getnode";
         	   		
     		$.ajax({
     			url:url,
     			type:'GET',
     			success : function(data){
     				console.log(data);
     			}
     		});
    		
    	}
    
    </script>
</body>
</html>
