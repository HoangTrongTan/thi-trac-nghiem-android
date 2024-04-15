<?php 
    include_once "connect.class.php";
    $db = new database();
    if(isset($_POST['ten']) && isset($_POST['tk']) && isset($_POST['anh']) && isset($_POST['mk'] )){
        $ten = $_POST['ten'];
        $tk = $_POST['tk'];
        $mk = $_POST['mk'];
        $anh = $_POST['anh'];
        $anh_binary = base64_decode($anh);

        $sql = "INSERT INTO `taikhoan`(`account`, `password`, `ten`, `anh`) VALUES ('$tk','$mk','$ten','$anh')";
        $sql2 = "INSERT INTO `thongke`( `account`, `LastTime`) VALUES ('$tk','01/01/2022 00:00')";
        
        if($db->thucthi($sql) && $db->thucthi($sql2)){
            echo "1";
        }else{
            echo "0";
        }

    }else{
        echo "0";
    }
?>