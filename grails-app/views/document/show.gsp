<%@ page import="web.wc.Document" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'document.label', default: 'Document')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
    <script>
        $(document).ready(function() {
            $('#scan-tab').addClass('active')
        })
    </script>
</head>

<body>

<div class="container">

    <div class="text-center">
        <div style="opacity: .9; background-color: lightgray">
            <h1> <a href="http://${url}">${url}</a> </h1>
            <h3>Top ten words</h3>
            <p class="lead">
                <g:each in="${topTen}" var="word">
                    <strong>${word}</strong><br>
                </g:each>
                <br>
            </p>
        </div>
    </div>

    <span style="z-index: -1; position: absolute; top: 0; left: 0" id="wordcloud"></span>

</div>

<!-- d3 word cloud -->
<script>
    var width = $(window).width();
    var height= $(window).height();
    var scale = (width * height) / 400;
    var fill  = d3.scale.category20c();
    var total = ${total};
    var words = $.map(<g:applyCodec encodeAs="none">${words}</g:applyCodec>,
            function(count, word) {
                return {text: word, size: 20 + count/total * scale};
            }
    );

    d3.layout.cloud().size([width, height])
            .words(words)
            .padding(5)
            .rotate(function() { return ~~(Math.random() * 6) * 30; })
            .font("Impact")
            .fontSize(function(d) { return d.size; })
            .on("end", draw)
            .start();
    function draw(words) {
        d3.select("#wordcloud").append("svg")
                .attr("width", width)
                .attr("height", height)
                .append("g")
                .attr("transform", "translate(" + width/2 + "," + height/2 + ")")
                .selectAll("text")
                .data(words)
                .enter().append("text")
                .style("font-size", function(d) { return d.size + "px"; })
                .style("font-family", "Impact")
                .style("fill", function(d, i) { return fill(i); })
                .attr("text-anchor", "middle")
                .attr("transform", function(d) {
                    return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
                })
                .text(function(d) { return d.text; });
    }
</script>

</body>
</html>
