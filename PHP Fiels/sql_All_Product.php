
<?php

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
//echo "success";
}

$sql = "SELECT Prod_name ,Cat_name FROM `AllProducts`
INNER JOIN categories
ON categories.Cat_id=AllProducts.Prod_category";
$result = $conn->query($sql);
// output data of each row
$test = array ();
while($row = $result->fetch_assoc())
{
	array_push($test, $row);
}
echo json_encode($test);
$conn->close();
?>
