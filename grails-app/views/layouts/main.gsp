<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
  		<asset:stylesheet src="application.css"/>
		<asset:javascript src="application.js"/>
		<g:layoutHead/>
        <!-- accommodate the navbar -->
        <style type="text/css">
            body {
                padding-top: 50px;
            }
        </style>
	</head>
	<body>

    <!-- navbar layout nicked from http://www.bootply.com/75225 (MIT license) -->
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button value="collapse" type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"></button>
                <a class="navbar-brand" href="#">web-wc</a>
            </div>
            <div class="collapse navbar-collapse">
            <!-- TODO: fix form css -->
            <!-- putting form outside preserves some the ul styling a little better-->
                <g:form name="scanForm" controller="document" action="save">
                    <ul class="nav navbar-nav">
                        <li id="list-tab">
                            <g:link action="index" params="[filter: params.filter]">List</g:link>
                        </li>
                        <li id="scan-tab">
                            <a href="#" onclick="$('#scanForm').submit()" >Scan</a>
                        </li>
                        <li>
                            <g:checkBox style="margin-top: 18px" name="filter" value="${params.filter}" />
                        </li>
                        <li>
                            <g:textField style="margin-top: 12px" name="url" value="${url}"/>
                        </li>
                    </ul>
                </g:form>
            </div>
        </div>
    </div>
    <!-- navbar -->

    <g:layoutBody/>

    </body>
</html>
