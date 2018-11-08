<?php

$Clients_username = $_POST['Clients_username'];
$Clients_password = $_POST['Clients_password'];


$servername = "127.0.0.1";
$username = "root";
$password = "";
$dbname = "supermarket";



$conn = new mysqli($servername, $username, $password, $dbname);
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
else
{

}


$sql = 
"SELECT Clients_username,Clients_password FROM Clients WHERE Clients_username='$Clients_username'AND Clients_password='$Clients_password'";
;

$result = $conn->query($sql);
$check=mysqli_fetch_array($result);
if(isset($check))
{
echo 'success';
}
else{
echo 'Failure';
}



$conn->close();
?>



