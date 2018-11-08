<?php

$servername = "127.0.0.1";
$username = "root";
$password = "";
$dbname = "Center";



$conn = new mysqli($servername, $username, $password, $dbname);
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
else
{
//echo "success";
}


$sql = "SELECT Stu_ID , Stu_Name , Stu_Address FROM Student";
$result = $conn->query($sql);
// output data of each row
$test = array ();
while($row = $result->fetch_assoc())
{
	//echo "Stu ID : ". $row["Stu_ID"]. " - Name : ". $row["Stu_Name"]. " ". $row["Stu_Address"]."<br>";
	array_push($test, $row);
}
echo json_encode($test);
$conn->close();
?>
