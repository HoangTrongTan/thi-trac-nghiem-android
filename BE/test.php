<?php
    include_once "connect.class.php";
    $db = new database();
    date_default_timezone_set('Asia/Ho_Chi_Minh'); // Đặt múi giờ cho Việt Nam
    $currentDateTime = date('Y-m-d H:i:s');
    $stmt = $db->thucthi("SELECT * FROM `taikhoan` WHERE `id_tk` = ")
?>