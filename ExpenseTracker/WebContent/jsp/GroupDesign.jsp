<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.gl.expencetracker.*"%>
	
<!DOCTYPE html>
<html>
<head>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/style.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../resources/content.js"></script>

<script type="text/javascript">
	//write here java script coding.

</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>


<div class="container">
<div class="col-xs-12">

    <div class="page-header">
         <h3>Group Details</h3>
    </div>
        
    <div class="carousel slide" id="myCarousel">
        <div class="carousel-inner">
            <div class="item active">
                    <ul class="thumbnails">
						<c:forEach var="item" items="${sessionScope.grpList}">
							<li class="col-sm-3">
							<div class="fff">
								<div class="thumbnail">
									<a href="#"><img src="../resources/logoExpenseTracker.png" alt=""></a>
								</div>
								<div class="caption">
									<h4 id="grpname">${item.grpName}</h4>
									<p>${item.number}</p>
									<a class="btn btn-mini" href="#">» SELECT</a>
								</div>
                            </div>
                        </li>
						</c:forEach>						
                    </ul>
              </div><!-- /Slide1 --> 
<!--             <div class="item"> -->
<!--                     <ul class="thumbnails"> -->
<!--                         <li class="col-sm-3"> -->
<!-- 							<div class="fff"> -->
<!-- 								<div class="thumbnail"> -->
<!-- 									<a href="#"><img src="http://placehold.it/360x240" alt=""></a> -->
<!-- 								</div> -->
<!-- 								<div class="caption"> -->
<!-- 									<h4>Praesent commodo</h4> -->
<!-- 									<p>Nullam Condimentum Nibh Etiam Sem</p> -->
<!-- 									<a class="btn btn-mini" href="#">» Read More</a> -->
<!-- 								</div> -->
<!--                             </div> -->
<!--                         </li> -->
<!--                     </ul> -->
<!--               </div>/Slide2  -->
        </div>
        
       
	   <nav>
			<ul class="control-box pager">
				<li><a data-slide="prev" href="#myCarousel" class=""><i class="glyphicon glyphicon-chevron-left"></i></a></li>
				<li><a data-slide="next" href="#myCarousel" class=""><i class="glyphicon glyphicon-chevron-right"></i></a></li>
			</ul>
		</nav>
	   <!-- /.control-box -->   
                              
    </div><!-- /#myCarousel -->
        
</div><!-- /.col-xs-12 -->          

</div><!-- /.container -->