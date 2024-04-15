<?php
  if(!isset($_SESSION)){
    session_start();
  }

    include "../database.php";
    $db = new database();
    if(isset($_POST['login'])){
      $tk = $_POST['hoten'];
      $mk = $_POST['masv'];
      $rs = $db->thucthi("SELECT * FROM `taikhoan` WHERE account = '$tk' AND `password` = '$mk' ");
      $num = mysqli_num_rows($rs);
      if($num > 0){
        $_SESSION['user'] = "ok";
        echo "<script>alert('đăng nhập thành công !')</script>";
        echo "<script>window.location.href = 'danhsach.php'</script>";
      }else{
        echo "<script>alert('đăng nhập thất bại !')</script>";          
      }
    }
    
  ?>
<link rel="stylesheet" href="menu.css">
<div id="bg">
    <div class="module">
      <ul>
        <li class="tab activeTab"><img src="https://i.imgur.com/Fk1Urva.png" alt="" class="icon"/></li>
        <li class="tab" ><img src="https://i.imgur.com/ZsRgIDD.png" alt="" class="icon"/></li>
        <li class="tab" ><img src="https://i.imgur.com/34Q50wo.png" alt="" class="icon"/></li>
        <li class="tab" ><img src="https://i.imgur.com/LCCJ06E.png" alt="" class="icon"/></li>
      </ul>
      
      <form class="form" method="post" action="dangnhap.php">
        <input type="text" placeholder="Tài khoản" class="textbox" name="hoten"/>
        <input type="password" placeholder="mật khẩu" class="textbox" name="masv"/>
        <input type="submit" value="đăng nhập" class="button" name="login"/>
      </form>
    </div>
  </div>
  
  <a href="http://dribbble.com/shots/1265587-Registration-Template-PSD?list=everyone" target="_blank">Design by: Asif Aleem</a>