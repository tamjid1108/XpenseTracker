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

    <link rel="stylesheet" href="/styles/index.css">

    <title>Xpense Tracker</title>
</head>

<body>

    <div class="main col-10 col-md-9 col-lg-7 rounded mx-auto">
    
        <div class="row align-items-center">

            <div class="col-7 text-center me-1">
                <img src="/images/login.jpg" alt="login" class="login" width="100%">
                <p class="text-muted"><a href="/signup" class="text-reset">Create an account</a></p>
            </div>

            <div class="col ms-1">
                <h2 class="heading">Login</h2>
                <form action="/login" method="post">
                    <input type="text" id="username" name="username" class="register-input icon" placeholder="&#xf007    Your Name" required>
                    <input type="password" id="password" name="password" class="register-input icon" placeholder="&#xf023    Password" required>
                    <p id="message" class="pt-3 text-danger message">${errorMessage}</p>
                    <input type="submit" value="Log In" class="mt-5 btn btn-primary btncolor">
                </form>
                
            </div>

        </div>
        
    </div>

</body>    