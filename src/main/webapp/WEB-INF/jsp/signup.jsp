<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;300;400;500;600;700;900&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="/styles/auth.css">

    <title>Xpense Tracker</title>
</head>
<body>

    <div class="main col col-lg-7 rounded mx-auto">
        <h2 class="heading">Sign up</h2>

        <div class="row align-items-center">

            <div class="col-lg-5 me-1">

                <form action="/signup" method="post">
                    <input type="text" id="name" name="name" class="register-input icon" placeholder="&#xf007    Your Name" required>
                    <input type="text" id="username" name="username" class="register-input icon" placeholder="&#xf007    Username" required>
                    <input type="email" id="email" name="email" class="register-input icon" placeholder="&#xf0e0    Your Email" required>
                    <input type="password" id="password" name="password" class="register-input icon" placeholder="&#xf023    Password"  required onkeyup="validate()" autocomplete="new-password">
                    <input type="password" id="repeatpw" name="repeatpw" class="register-input icon" placeholder="&#xf13e    Repeat your password" required onkeyup="validate()">
                    <p id="message" class="pt-3 text-danger"></p>
                    <p id="message" class="pt-3 text-danger message">${message}</p>
                    <input type="submit" id="register" value="Register" class="btn btn-primary btncolor">
                </form>

            </div>

            <div class="col ms-1 text-center">
                <img src="/images/signup.jpg" alt="signup" class="signup">
                <p class="text-muted"><a href="/login" class="text-reset">Already registered?</a></p>
            </div>

        </div>
        
    </div>
    
    <script>
    	console.log("hello");
    	function validate() {
    		console.log("hello");
	        var password = document.getElementById("password").value;
	        var confirmPassword = document.getElementById("repeatpw").value;
	        if (confirmPassword !="" && password!="" && password != confirmPassword) {
	        	document.getElementById("message").innerHTML = "Passwords do not match";
	            document.getElementById("register").setAttribute("disabled", "");
	        }
	        else{
	        	document.getElementById("message").innerHTML = "";
	        	document.getElementById("register").removeAttribute("disabled");
	        }
	    }
    </script>

</body>
</html>

