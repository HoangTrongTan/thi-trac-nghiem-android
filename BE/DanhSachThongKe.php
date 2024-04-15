<?php
    include_once "connect.class.php";
    $db = new database();
        $json = [];
        $stmt = $db->all("SELECT ten,diem,anh FROM `taikhoan` ORDER BY diem DESC");
        while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
            $json[] = $row;
        }
        if(empty($json)){
            echo "0";
        }else{
            print_r(json_encode($json));
        }
?>