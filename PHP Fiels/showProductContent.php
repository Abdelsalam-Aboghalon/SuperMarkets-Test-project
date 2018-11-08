<?php
//$Supermarkets_Name = $_POST['Supermarkets_Name'];
//$Prod_name = $_POST['Prod_name'];

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


$sql = "SELECT Prod_name ,Cat_name, Prod_price,Supermarkets.Supermarkets_Name FROM `AllProducts`
INNER JOIN categories
ON categories.Cat_id=AllProducts.Prod_category
INNER JOIN SupermarketProducts
ON SupermarketProducts.Prod_id=AllProducts.Prod_id
INNER JOIN Supermarkets
ON Supermarkets.Supermarkets_id=SupermarketProducts.Supermarkets_id
WHERE  Supermarkets_Name='awladragab'AND Prod_name='KitKat' ";


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
