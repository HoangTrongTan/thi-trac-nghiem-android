<?php
    include_once "connect.class.php";
    $db = new database();
    if(isset($_POST['Muc_CauHoi']) && isset($_POST['loai'])){
        $Muc_CauHoi	 = $_POST['Muc_CauHoi'];
        $loai = $_POST['loai'];
        $json = [];
        $stmt = $db->all("SELECT * FROM `cauhoi` WHERE Muc_CauHoi = $Muc_CauHoi and mon LIKE '%$loai%' ");
        while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
            $json[] = $row;
        }
        if(empty($json)){
            echo "0";
        }else{
            print_r(json_encode($json));
        }
    }
    else{
        echo "0";
    }
?>