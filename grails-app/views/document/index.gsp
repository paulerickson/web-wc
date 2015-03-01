<%@ page import="web.wc.Document" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'document.label', default: 'Document')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
    <script>
        $(document).ready(function() {
            $('#list-tab').addClass('active')
        })
    </script>
</head>

<body>

<div class="container">

    <div id="list-document" class="text-center" role="main">
        <h1><g:message code="default.list.label" args="[entityName]"/></h1>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <g:each in="${documentInstanceList}" status="i" var="doc">
            <div class="row" >
                <div>
                    <g:link class="segment1" action="show" params="[id: doc.id, filter: params.filter]" >${doc.getShortUrl()}</g:link>
                </div>
                <div>
                    <span class="badge">
                        ${doc.getWordCount()} words
                    </span>
                    <span>
                        Top word: <span>${doc.getTopWord(true)}</span>
                    </span>
                </div>
                <br>
            </div>
        </g:each>

        <div class="pagination">
            <g:paginate total="${documentInstanceCount ?: 0}"/>
        </div>
    </div>

</div>

</body>
</html>
