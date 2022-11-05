<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
	rel="stylesheet">


<link rel="stylesheet" href="/styles/index.css">

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', { 'packages': ['corechart'] });
        google.charts.setOnLoadCallback(drawPieChart);
        google.charts.setOnLoadCallback(drawCurveChart);
        google.charts.setOnLoadCallback(drawBarChart);
        function drawPieChart() {

            var data = google.visualization.arrayToDataTable([
                ['Category', 'Expense'],
                <c:forEach var="entry" items="${expenses}">
					['${entry.key}', ${entry.value}],
        		</c:forEach>
            ]);
            
            

            var options = {
                legend: {
                    position: 'bottom',
                    alignment: 'center',
                }
            };

            var chart = new google.visualization.PieChart(document.getElementById('piechart'));

            chart.draw(data, options);
        }
        

        function drawCurveChart() {
        	var data = new google.visualization.DataTable();
            data.addColumn('date', 'Day');
            data.addColumn('number', 'Expenses');
            data.addRows([
                <c:forEach var="entry" items="${expensesByDate}">
				[new Date('${entry.key}'), ${entry.value}],
    			</c:forEach>
              ]);
          var options = {
            legend: { position: 'bottom' }
          };

          var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

          chart.draw(data, options);
        }
        
  
        
        function drawBarChart() {
          var data = google.visualization.arrayToDataTable([
            ["Category", "Expenses", { role: "style" }],
            <c:forEach var="entry" items="${expenses}">
			['${entry.key}', ${entry.value}, "red"],
			</c:forEach>
          ]);

          var view = new google.visualization.DataView(data);
          view.setColumns([0, 1,
              { calc: "stringify",
                sourceColumn: 1,
                type: "string",
                role: "annotation" },
              2]);

          var options = {
            bar: {groupWidth: "95%"},
            legend: { position: "none" },
          };
          var chart = new google.visualization.BarChart(document.getElementById("barchart_values"));
          chart.draw(view, options);
      }
    </script>

<title>Xpense Tracker</title>

</head>
<body>
	
	<div class="header d-flex justify-content-between py-3">

		<a href="/"
			class="text-decoration-none text-dark d-flex align-items-center ms-5">
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
					<button type="button"
						class="btn btn-sm btn-outline-dark px-3 py-2 mx-5 ">Sign
						out</button>
				</a>
			</div>
		</div>

	</div>

	<div class="main">
		<div class="row mx-5">
			<div class="col widget">

				<h4>Add expense</h4>
			
				<form action="/expense/add" method="post">

					<label for="desc" class="col-form-label mt-4">Description</label>
					<div class="form-floating" >
						<textarea class="form-control"
							placeholder="Enter your description" id="description"
							name="description" required></textarea>
					</div>


					<label for="category" class="col-form-label mt-2">Category</label>
					<select class="form-select" name="category" id="category" required>
						<option value=""></option>
						<c:forEach var="cat" items="${catOptions}">
							<option value="${cat}">${cat}</option>
        				</c:forEach>
						
					</select> <label for="date" class="col-form-label mt-2">Date</label> <input
						type="date" name="expenseDate" class="form-control" required> <label
						for="type" class="col-form-label mt-2">Type</label>

					<div class="d-flex justify-content-between">

						<div class="form-check">
							<input class="form-check-input" type="radio" name="expenseType"
								id="cash" value="Cash" required> <label class="form-check-label"
								for="cash">Cash</label>
						</div>

						<div class="form-check">
							<input class="form-check-input" type="radio" name="expenseType"
								id="" value="Credit card" required> <label
								class="form-check-label" for="">Credit card</label>
						</div>

						<div class="form-check">
							<input class="form-check-input" type="radio" name="expenseType"
								id="" value="Cheque" required> <label class="form-check-label"
								for="">Cheque</label>
						</div>

						<div class="form-check">
							<input class="form-check-input" type="radio" name="expenseType"
								id="" value="Transfer" required> <label class="form-check-label"
								for="">Transfer</label>
						</div>

					</div>


					<div class="d-flex align-items-end justify-content-between pt-3">
						<div class="">
							<label for="amt" class="fw-bold col-form-label mt-2">Amount</label>
							<input type="number" class="form-control w-75"
								name="expenseAmount" required>
						</div>
						<div class="">
							<button type="submit" class="btn btn-sm btn-primary px-4">Add</button>
						</div>
					</div>
				</form>
			</div>

			<div class="col widget">
				<div class="d-flex justify-content-between">
					<h4>Expenses overview</h4>
					
					<a href="/expenses" class="text-decoration-none">
						<button type="button" class="btn btn-sm btn-secondary px-3">View
							all</button>
					</a>
				</div>
				<div id="piechart" style="height:480px;"></div>
			</div>
			
		</div>
		
		<div class="row mx-5">
			<div class="col widget">
				<h4>Recent expenses</h4>
				<h6 class="text-muted">Last 30 days</h6>
				<div id="curve_chart" class="mx-auto" style="height: 300px"></div>
			</div>
			
			<div class="col widget">
			<h4>Expenses</h4>
			<h6 class="text-muted">Bar chart</h6>
			<div id="barchart_values" style="height: 300px"></div>
			</div>
			
		</div>
	</div>
	

</body>
</html>