<?php 
    include_once "connect.class.php";
    $db = new database();
    if(isset($_POST['tk']) && isset($_POST['mk'] )){
        $tk = $_POST['tk'];
        $mk = $_POST['mk'];

        $sql = "SELECT ten,anh,diem,id_tk FROM `taikhoan` WHERE `account` = '$tk' AND `password` = '$mk'";
        $stmt = $db->all($sql);
        $row = $stmt->fetch(PDO::FETCH_ASSOC);

        $json = [];
        $json = ($row);
        if(empty($json)){
            echo "0";
        }else{
            print_r(json_encode($json));
        }

    }else{
        echo "0";
    }
?>