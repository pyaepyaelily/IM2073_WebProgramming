
<?php

/*
    session_start();
    include('config.php');
    if (isset($_POST['login'])) {
        $email = $_POST['email'];
        $password = $_POST['password'];
        $query = $connection->prepare("SELECT * FROM userbase WHERE email=:email");
        $query->bindParam("email", $email, PDO::PARAM_STR);
        $query->execute();
        $result = $query->fetch(PDO::FETCH_ASSOC);
        if (!$result) {
            echo '<p class="error">Email password combination is wrong!</p>';
        } else {
            if (password_verify($password, $result['password'])) {
                $_SESSION['userbase_id'] = $result['id'];
                echo '<p class="success">Congratulations, you are logged in!</p>';
            } else {
                echo '<p class="error">Username password combination is wrong!</p>';
            }
        }
    }

*/
?> 




<?php
    $email = $_POST['email'];
    $password = $_POST['password'];

    $con = new mysqli("localhost:3306","root","password","animedb");
    if($con->connect_error) {
        die("Fail to connect : ".$con->connect_error);
    } else {
        $stmt = $con ->prepare("select * from userbase where email = email");
        $stmt->bind_param("s",$email);
        $stmt->execute();
        $stmt_result = stmt ->get_result();
        if ($stmt_result->num_rows >0) {
            $data = $stmt_result ->fetch_assoc();
            if($data['password']===$password){
                echo "<h2>Login Success </h2>";
            } else {
                echo "<h2> no </h2>";
            }
        } else {
            echo "<h2> no </h2>";
        }

    }