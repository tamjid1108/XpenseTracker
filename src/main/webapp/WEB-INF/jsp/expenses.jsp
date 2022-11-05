<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
        href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
        rel="stylesheet">


    <link rel="stylesheet" href="styles/index.css">
    <title>Xpense Tracker</title>
    
    <style>
    
	
    </style>

</head>

<body>

    <div class="header d-flex justify-content-between py-3">
        
            <a href="/" class="text-decoration-none text-dark d-flex align-items-center ms-5">
                <img src="images/logo.gif" alt="" width="40px" height="40px">
                <h4 class="fw-bolder px-2">XpenseTracker</h4>
            </a>
        <div class="userinfo">
            <div class="d-flex align-items-center">

                <div class="text-end">
					<span class="fw-bold fs-6">${name}</span><br> <span
						class="fw-normal text-muted">@${username}</span>
				</div>
                <a href="/logout" class="text-decoration-none">
                    <button type="button" class="btn btn-sm btn-outline-dark px-3 py-2 mx-5 ">Sign out</button>
                </a>
            </div>
        </div>

    </div>

    <div class="main">
        <div class="row py-5">
            <div class="col-8 mx-auto">
                <div class="d-flex justify-content-between mb-5">
                    <h4 class="">All expenses</h4>
                    <a href="/" class="">
                        <img src="images/left-arrow-circle-regular-48.png" alt="" width="30px">
                    </a>
                </div>
                
				<div class="col-10 mx-auto">
				
				<c:forEach var="list" items="${allExpensesChron}">
				<span class="fs-6 text-muted ms-3">${list.key}</span>
				
					<c:forEach var="entry" items="${list.value}">
						
						
						
						<div class='expense-card ${entry.getExpenseType().replaceAll(" ", "")} position-relative d-flex justify-content-between align-items-center mx-5'>
		                    <div>
		                        <h6 class="">${entry.getDescription()}</h6>
		                        <span class="text-muted">${entry.getCategory().getCategoryName()}</span>
		                    </div>
		                    <div class="text-end">
		                    
		                        <h4>&#8377; <span class="fw-bold fs-4">
		                        <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${entry.getExpenseAmount()}" /></span>
		                        </h4>
		                        <span class="text-muted">by ${entry.getExpenseType()}</span>
		                    </div>
                		</div>
                
                
                
					</c:forEach>
				</c:forEach>
			</div>
            </div>
        </div>
    </div>
    
</body>

</html>