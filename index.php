<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>PHP</title>

    <?php
      $servername = "localhost";
      $username = "root";
      $password = "root";
    ?>
</head>
<body>
    <h1>Hello</h1>
    
    <?php
        // create connection
        $conn = new mysqli($servername, $username, $password);

        // check connection
        if($conn -> connect_error){
            die("Connection failed: ". $conn->connect_error);
        }
        echo "Connected successfully".print_r($conn);
        echo "<br/>";

        // create database
        $sql = "DROP DATABASE IF EXISTS myDB;";
        $sql .= "CREATE DATABASE myDB;";

        if ($conn->multi_query($sql) === TRUE) {
            echo "Database created successfully";
        } else {
            echo "Error creating database: " . $conn->error;
        }
        
        // Closing the connection 
        $conn->close();
    ?>



</body>
</html>