<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="RESOUCE_SYSTEM_URL" value="${pageContext.request.contextPath}"/>
<c:set var="RESOUCE_STATIC_URL" value="${pageContext.request.contextPath}/static"/>
<c:set var="RESOUCE_UPLOAD_URL" value="${pageContext.request.contextPath}/upload/" />
<c:set var="RESOUCE_BARCODE_URL" value="${pageContext.request.contextPath}/barcodeimg" />
<script type="text/javascript">
	var RESOUCE_STATIC_URL_JS = "${RESOUCE_STATIC_URL}";
	var RESOUCE_SYSTEM_URL_JS = "${RESOUCE_SYSTEM_URL}";
    var RESOUCE_UPLOAD_URL_JS = "${RESOUCE_UPLOAD_URL}";
    var RESOUCE_BARCODE_URl_JS = "${RESOUCE_BARCODE_URL}";
</script>
