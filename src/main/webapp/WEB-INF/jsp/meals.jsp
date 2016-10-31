<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>


    <div class="jumbotron">
        <div class="container">
            <div class="shadow">
                <h3><fmt:message key="meals.title"/></h3>
                <form method="post" action="meals/filter">
                    <dl>
                        <dt><fmt:message key="meals.startDate"/>:</dt>
                        <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
                    </dl>
                    <dl>
                        <dt><fmt:message key="meals.endDate"/>:</dt>
                        <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
                    </dl>
                    <dl>
                        <dt><fmt:message key="meals.startTime"/>:</dt>
                        <dd><input type="time" name="startTime" value="${param.startTime}"></dd>
                    </dl>
                    <dl>
                        <dt><fmt:message key="meals.endTime"/>:</dt>
                        <dd><input type="time" name="endTime" value="${param.endTime}"></dd>
                    </dl>
                    <button class="btn btn-primary" type="submit"><fmt:message key="meals.filter"/></button>
                </form>
                <hr>
                <a class="btn btn-sm btn-info" onclick="addMeal()"><fmt:message key="meals.add"/></a>
                <hr>
                <table class="table table-striped display" id="mealsdata">
                    <thead>
                    <tr>
                        <th><fmt:message key="meals.dateTime"/></th>
                        <th><fmt:message key="meals.description"/></th>
                        <th><fmt:message key="meals.calories"/></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <c:forEach items="${meals}" var="meal">
                        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
                        <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                            <td>
                                    <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                                    <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                                    ${fn:formatDateTime(meal.dateTime)}
                            </td>
                            <td>${meal.description}</td>
                            <td>${meal.calories}</td>
                            <td><a class="btn btn-xs btn-primary edit" id="${meal.id}"><fmt:message key="common.update"/></a></td>
                            <td><a class="btn btn-xs btn-danger delete" id="${meal.id}"><fmt:message key="common.delete"/></a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</section>
<jsp:include page="fragments/footer.jsp"/>

<div class="modal fade" id="addMeal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"><fmt:message key="meals.add"/></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" id="detailsForm">
                    <input type="text" hidden="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="${meal.datetime}" class="control-label col-xs-3"><fmt:message key="meals.dateTime"/></label>

                        <div class="col-xs-9">
                            <input type="datetime" class="form-control" id="${meal.dateTime}" name="dateTime" placeholder="Date">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3"><fmt:message key="meals.description"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="description" name="description" placeholder="Завтрак">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="calories" class="control-label col-xs-3"><fmt:message key="meals.calories"/></label>

                        <div class="col-xs-9">
                            <input type="number" class="form-control" id="calories" name="calories" placeholder="500">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="submit" class="btn btn-primary">Add</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.3.8/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="resources/js/datatablesUtil.js"></script>
<script type="text/javascript">

    var ajaxUrl = 'ajax/meals/';
    var datatableApi;

    // $(document).ready(function () {
    $(function () {
        datatableApi = $('#mealsdata').dataTable({
            "bPaginate": false,
            "bInfo": false,
            "aoColumns": [
                {
                    "mData": "dateTime"
                },
                {
                    "mData": "description"
                },
                {
                    "mData": "calories"
                },
                {
                    "sDefaultContent": "Edit",
                    "bSortable": false
                },
                {
                    "sDefaultContent": "Delete",
                    "bSortable": false
                }
            ],
            "aaSorting": [
                [
                    0,
                    "asc"
                ]
            ]
        });
        makeEditable();
    });
    </script>
</html>
