
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%--<security:authorize access="isAuthenticated()">--%>
<%--    Logout <security:authentication property="principal.username" />--%>
<%--</security:authorize>--%>

<div style="background: #E0E0E0; height: 55px; padding: 5px;">
    <div style="float: left">
        <h1> OFD Test task </h1>
    </div>

    <security:authorize access="isAuthenticated()">
        Logout <security:authentication property="principal.username" />
    </security:authorize>
</div>