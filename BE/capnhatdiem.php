<?php
    include_once "database.php";
    $db = new database();
        $json = [];
        if(isset($_POST['diem']) && isset($_POST['tk'])){
            date_default_timezone_set('Asia/Ho_Chi_Minh');
            $currentDateTime = date('Y-m-d H:i:s');
            $diem = (int)$_POST['diem'];
            $taikhoan = (int)$_POST['tk'];
            $rs = $db->thucthi("SELECT * FROM `taikhoan` WHERE `id_tk` = $taikhoan");
            $row = mysqli_fetch_array($rs);
            $acc = $row['account'];
            $tongtg = $_POST['tong_tg'];
                $ok = $db->thucthi("UPDATE `taikhoan` SET `diem`= `diem`+$diem WHERE id_tk = $taikhoan");

                $db->thucthi("UPDATE `thongke` SET `LastTime` = '$currentDateTime',`Tong_tg` = '$tongtg' WHERE `thongke`.`account` = '$acc'");
                echo $ok;
        }else{
            echo "0";
        }   
?>