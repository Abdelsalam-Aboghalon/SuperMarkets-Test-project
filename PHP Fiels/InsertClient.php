<?php

$Clients_firstname = $_POST['Clients_firstName'];
$Clients_lastname = $_POST['Clients_lastName'];
$Clients_phone = $_POST['Clients_phone'];
$Clients_email = $_POST['Clients_email'];
$Clients_username = $_POST['Clients_username'];
$Clients_password = $_POST['Clients_password'];
$Clients_Address = $_POST['Clients_Address'];


$servername = "127.0.0.1";
$username = "root";
$password = "";
$dbname = "supermarket";

$conn = new mysqli($servername, $username, $password, $dbname);

$sql = "INSERT INTO `Clients` (`Clients_id`, `Clients_firstname`, `Clients_lastname`, `Clients_email`, `Clients_username`, `Clients_password`, `Clients_Address`,`Clients_phone`) VALUES (NULL, '$Clients_firstname', '$Clients_lastname',  '$Clients_email', '$Clients_username', '$Clients_password', '$Clients_Address','$Clients_phone');
";

if(mysqli_query($conn,$sql))
{
	echo "Date insertion success";
}
else
{
	echo "error while insertion";
}
mysqli_close($conn);
?>
