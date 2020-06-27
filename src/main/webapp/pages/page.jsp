<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<!-- 分页-->
<div class="box-footer">
    <div class="pull-left">
        <div class="form-group form-inline">
            总共${pageInfo.pages} 页，共${pageInfo.total} 条数据。 每页
            <select class="form-control" id="changePageSize" onclick="changePageSize()">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
            </select> 条
        </div>
    </div>

    <div class="box-tools pull-right">
        <ul class="pagination">
            <li>
                <a href="${pageContext.request.contextPath}/orders/findAll?page=1&pageSize=${pageInfo.pageSize}" aria-label="Previous">首页</a>
            </li>
            <li><a href="${pageContext.request.contextPath}/orders/findAll?page=${pageInfo.prePage}&pageSize=${pageInfo.pageSize}">上一页</a></li>
            <c:forEach begin="1" end="${pageInfo.pages}" var="pageNum">
                <li><a href="${pageContext.request.contextPath}/orders/findAll?page=${pageNum}&pageSize=${pageInfo.pageSize}">${pageNum}</a></li>
            </c:forEach>
            <li><a href="${pageContext.request.contextPath}/orders/findAll?page=${pageInfo.nextPage}&pageSize=${pageInfo.pageSize}">下一页</a></li>
            <li>
                <a href="${pageContext.request.contextPath}/orders/findAll?page=${pageInfo.pages}&pageSize=${pageInfo.pageSize}" aria-label="Next">尾页</a>
            </li>
        </ul>
    </div>
</div>
<!-- /分页-->