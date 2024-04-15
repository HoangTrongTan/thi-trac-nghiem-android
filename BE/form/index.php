<?php
    if(!isset($_SESSION)){
        session_start();
    }
    if(!isset($_SESSION['user'])){        
       header('Location: dangnhap.php');
    }else{
        echo $_SESSION['user'];
    }
?>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Luyện tập</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="css.css">
  <style>
  .fakeimg {
    height: 200px;
    background: #aaa;
  }
  .bg-dark{
      border-radius: 0.5rem;
    }
  </style>
</head>
<body>
<?php 
    include "../database.php";
    $db = new database();
    if(isset($_POST['themCauhoi'])){
        $CauHoi = $_POST['CauHoi'];
        $Cau1 = $_POST['Cau1'];
        $Cau2 = $_POST['Cau2'];
        $Cau3 = $_POST['Cau3'];
        $Cau4 = $_POST['Cau4'];
        $KetQua = $_POST['KetQua'];
        $MucCauhoi = $_POST['MucCauhoi'];
        $Mon = $_POST['Mon'];

        $rs = $db->thucthi("INSERT INTO `cauhoi`( `Ten_Cauhoi`, `da_a`, `da_b`, `da_c`, `da_d`, `kq`, `Muc_CauHoi`, `mon`) VALUES ('$CauHoi','$Cau1','$Cau2','$Cau3','$Cau4','$KetQua','$MucCauhoi','$Mon')");
        // if($rs){
        //     echo "<script>alert('Thêm thành công !')</script>";
        // }
        // else{
        //     echo "<script>alert('Thêm thất bại !')</script>";
        // }
    }
?>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="index.php">Thêm câu hỏi</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="danhsachplayer.php">Danh sách người chơi</a>
      </li> 
      <li class="nav-item">
        <a class="nav-link" href="danhsach.php">Danh sách Câu hỏi</a>
      </li> 
    </ul>
  </div>  
</nav>
<div class="container" style="margin-top:30px">

    <p style="color: #FFF;">Form thêm câu hỏi</p>
    <form action="index.php" method="post">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Nhập câu hỏi"  name="CauHoi">
        </div>
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Nhập câu thứ 1" name="Cau1">
        </div>
        <div class="form-group">

            <input type="text" class="form-control" placeholder="Nhập câu thứ 2" name="Cau2">
          </div>
          <div class="form-group">

            <input type="text" class="form-control" placeholder="Nhập câu thứ 3" name="Cau3">
          </div>
          <div class="form-group">

            <input type="text" class="form-control" placeholder="Nhập câu thứ 4" name="Cau4">
          </div>
          <div class="form-group">
              <select class="form-control"  name="KetQua">
                      <option value="1" >Câu 1</option>
                      <option value="2" >Câu 2</option>
                      <option value="3" >Câu 3</option>
                      <option value="4" >Câu 4</option>
              </select>
          </div>
          <div class="form-group">
              <select class="form-control"  name="MucCauhoi">
                      <option value="0" >Dễ</option>
                      <option value="1" >Trung bình</option>
                      <option value="2" selected>Khó</option>
              </select>
          </div>
          <div class="form-group">
              <select class="form-control"  name="Mon">
                      <option value="CTDL&GT" >Cấu trúc dữ liệu và giải thuật</option>
                      <option value="KTPM" selected>Kiểm thử phần mềm</option>
              </select>
          </div>
        <button type="submit" class="btn btn-primary" name="themCauhoi">Thêm</button>
      </form>
</div>
</body>
</html>
