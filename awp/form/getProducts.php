<?php
// echo "Hello getProducts";
// create connection
$conn = new mysqli("localhost", "root", "actscdac", "mydb");
// checking connection
if ($conn->connect_error) {
    die("Connection Failed! : " . $conn->connect_error);
}
// echo "Connected Successfully!";
// create database if not exists (for insert products page)
// create statement to get all products
$sql = "select * from products";
$result = $conn->query($sql);
// echo print_r($result);
$displayString = "";
$myArray = array();
if ($result->num_rows > 0) {
    // echo print_r($result);
    // output data of each row
    while ($row = $result->fetch_assoc()) {
        $myArray[] = $row;
    }
    echo json_encode($myArray);
}