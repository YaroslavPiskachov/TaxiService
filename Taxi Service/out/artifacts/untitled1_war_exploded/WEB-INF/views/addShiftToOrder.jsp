<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<meta charset="utf-8">
<title>Bootstrap Admin</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/lib/bootstrap/css/bootstrap.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/lib/font-awesome/css/font-awesome.css"/>"/>

<script src="<c:url value="/resources/lib/jquery-1.11.1.min.js"/>" type="text/javascript"></script>

<script src="<c:url value="/resources/lib/jQuery-Knob/js/jquery.knob.js"/>" type="text/javascript"></script>
<script type="text/javascript">
    $(function() {
        $(".knob").knob();
    });
</script>


<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/theme.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/premium.css"/>"/>

</head>
<body class=" theme-blue">
<script type="text/javascript">
    $(function() {
        var match = document.cookie.match(new RegExp('color=([^;]+)'));
        if(match) var color = match[1];
        if(color) {
            $('body').removeClass(function (index, css) {
                return (css.match (/\btheme-\S+/g) || []).join(' ')
            })
            $('body').addClass('theme-' + color);
        }

        $('[data-popover="true"]').popover({html: true});

    });
</script>
<style type="text/css">
    #line-chart {
        height:300px;
        width:800px;
        margin: 0px auto;
        margin-top: 1em;
    }
    .navbar-default .navbar-brand, .navbar-default .navbar-brand:hover {
        color: #fff;
    }
</style>

<script type="text/javascript">
    $(function() {
        var uls = $('.sidebar-nav > ul > *').clone();
        uls.addClass('visible-xs');
        $('#main-menu').append(uls.clone());
    });
</script>

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<!-- Le fav and touch icons -->
<link rel="shortcut icon" href="../assets/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">


<!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
<!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
<!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->

<!--<![endif]-->

<div class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="" href="/"><span class="navbar-brand"><span class="fa fa-paper-plane"></span> Taxi </span></a></div>

    <div class="navbar-collapse collapse" style="height: 1px;">
        <ul id="main-menu" class="nav navbar-nav navbar-right">
            <li class="dropdown hidden-xs">
                <a href="/logout" class="dropdown-toggle">
                    Выйти
                </a>
            </li>
        </ul>
    </div>
</div>
</div>


<div class="sidebar-nav">
    <ul>
        <li><a href="/" class="nav-header"><i class="fa fa-fw fa-question-circle"></i> Актуальные данные </a></li>
        <li><a href="#" data-target=".dashboard-menu" class="nav-header" data-toggle="collapse"><i class="fa fa-fw fa-dashboard"></i>Управление персоналом<i class="fa fa-collapse"></i></a></li>
        <li><ul class="dashboard-menu nav nav-list collapse in">
            <li ><a href="/drivers"><span class="fa fa-caret-right"></span> Список Водителей </a></li>
            <li ><a href="/dispatchers"><span class="fa fa-caret-right"></span> Список Диспетчеров </a></li>
            <li ><a href="/shifts"><span class="fa fa-caret-right"></span> Рабочие смены </a></li>

        </ul></li>
        <li><a href="/orders" class="nav-header"><i class="fa fa-fw fa-question-circle"></i> Заказы </a></li>
        <li><a href="/cars" class="nav-header"><i class="fa fa-fw fa-question-circle"></i> Машини </a></li>
    </ul>
</div>


<div class="content">
    <div class="main-content">
        <div class="btn-toolbar list-toolbar">
            <h3> Список водителей которые работают в данный момент</h3>
            <div class="btn-group"></div>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th> Фамилия Имя Отчество</th>
                <th> Марка и модель машини </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${Shifts}" var="shift">
                <tr>
                    <td><a href="/addOrder?Shift1=${shift.idShift}&addressDeparture=${Booking.addressDeparture}&addressArrive=${Booking.addressArrive}&distance=${Booking.distance}&countPassangers=${Booking.countPassangers}">
                    ${shift.driverEntity.fullName}</a></td>
                    <td>${shift.carEntity.model}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="modal small fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="myModalLabel">Delete Confirmation</h3>
                    </div>
                    <div class="modal-body">
                        <p class="error-text"><i class="fa fa-warning modal-icon"></i>Are you sure you want to delete
                            the user?<br>This cannot be undone.</p>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">Cancel</button>
                        <button class="btn btn-danger" data-dismiss="modal">Delete</button>
                    </div>
                </div>
            </div>
        </div>

        <footer>
            <hr>
            <p class="pull-right"> Курсовая работа студента группы КН 34-в <a href="https://vk.com/yarikkomarik" target="_blank">Пискачева Ярослава Максимовича</a></p>
            <p>© 2016 </p>
        </footer>
    </div>
</div>


<script src="<c:url value="/resources/lib/bootstrap/js/bootstrap.js"/>"></script>
<script type="text/javascript">
    $("[rel=tooltip]").tooltip();
    $(function() {
        $('.demo-cancel-click').click(function(){return false;});
    });
</script>


</body>
</html>
