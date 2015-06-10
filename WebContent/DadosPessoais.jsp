<%@page import="java.util.List"%>
<%@page import="dao.KnowledgeArea"%>
<%@page import="dao.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>DaisyKLR</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/3-col-portfolio.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="books.jsp">DaisyKLR</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="books.jsp">Sobre</a>
                    </li>
                    <li>
                        <a href="historico.jsp">Historico</a>
                    </li>
                    <li>
                        <a href="DadosPessoais.jsp">Dados Pessoais</a>
                    </li>
                     <li>
                        <a href="Logout">Logout</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Content -->
    
    <div class="container">
    
    	<!-- Page Header -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Dados Pessoais
                    <small>
                    <% 
						User us = new User((long) request.getSession().getAttribute("nusp"));
            				out.print(us.getName());
            		
            		%>
					</small>
                </h1>
                 <h4 style="color:#990000"><%=(request.getParameter("msg") != null ? request.getParameter("msg") : "") %></h4>
            </div>
        </div>
        
        <!-- NUSP -->
        <div class="row" style="margin-left:5%">
            <div class="col-lg-12">
                <h3>NUSP:
                    <small><%=us.getNusp() %></small>
                </h3>
            </div>
        </div>
        
        <!-- Areas Conhecimento -->
        <div class="row" style="margin-left:5%">
            <div class="col-lg-12">
                <h3>Áreas de Conhecimento:</h3>
                <h4>
                	<ul>
                		<% List<KnowledgeArea> areas = us.getKnowledgeAreas(); %>
                		<% for (KnowledgeArea a : areas) { %>
                    	<li><small><%=a.getName() %></small></li>
                    	<% } %>
                    </ul>
                </h4>
            </div>
        </div>
        <br>
        <br>
        
        <!-- Alteração Senha -->
        <div class="row" style="margin-left:5%;border-style:solid">
            <div class="col-lg-12">
                <h3>
                	Alterar Senha
                </h3>
            </div>
            <form method="post" action="ChangePass">
					<div class="col-lg-12">
					<label for="old_pass">Senha Antiga:</label>
					<input type="password" name="old_pass" id="old_pass" autofocus required>
					</div>
					<div class="col-lg-12" style="margin:1%">
			       	<label for="new_pass1">Senha Nova:</label>
			       	<input type="password" name="new_pass1" id="new_pass1" required/>
			       	<label for="new_pass1">Confirmar Senha:</label>
			       	<input type="password" name="new_pass2" id="new_pass2" required/>
			       	</div>
					<div class="col-lg-12" style="margin:1%">
			       	<input type="submit" />
			       	</div>
			</form>	
            
        </div>
    
    
    <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; DaisyKLR 2015</p>
                </div>
            </div>
            <!-- /.row -->
        </footer>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>