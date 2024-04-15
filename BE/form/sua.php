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
    $id = isset($_GET['id'])?$_GET['id']:-1;
    
    $rs = $db->thucthi("SELECT * FROM `cauhoi` WHERE id = $id");
    $row = mysqli_fetch_array($rs);
    if(isset($_POST['sua'])){
        $CauHoi = $_POST['CauHoi'];
        $Cau1 = $_POST['Cau1'];
        $Cau2 = $_POST['Cau2'];
        $Cau3 = $_POST['Cau3'];
        $Cau4 = $_POST['Cau4'];
        $KetQua = $_POST['KetQua'];
        $MucCauhoi = $_POST['MucCauhoi'];
        $Mon = $_POST['Mon'];
        $id = isset($_GET['id'])?$_GET['id']:-1;
        // echo "<br><br><br><br>".$id."<br><br><br><br><br><br>";
        $rs = $db->thucthi("UPDATE `cauhoi` SET `Ten_Cauhoi`='$CauHoi',`da_a`='$Cau1',`da_b`='$Cau2',`da_c`='$Cau3',`da_d`='$Cau4',`kq`='$KetQua',`Muc_CauHoi`='$MucCauhoi',`mon`='$Mon' WHERE id = $id");
        if($rs){
            echo "<script>alert('Sửa thành công !')</script>";
            echo "<script>window.location.href = 'danhsach.php'</script>";
        }
        else{
            echo "<script>alert('Sửa thất bại !')</script>";
            echo "<script>window.location.href = 'danhsach.php'</script>";
        }
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
    <form action="sua.php?id=<?php echo $_GET['id'] ?>" method="post">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Nhập câu hỏi"  name="CauHoi" value="<?php echo $row['Ten_Cauhoi'] ?>">
        </div>
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Nhập câu thứ 1" name="Cau1" value="<?php echo $row['da_a'] ?>" >
        </div>
        <div class="form-group">

            <input type="text" class="form-control" placeholder="Nhập câu thứ 2" name="Cau2" value="<?php echo $row['da_b'] ?>" >
          </div>
          <div class="form-group">

            <input type="text" class="form-control" placeholder="Nhập câu thứ 3" name="Cau3" value="<?php echo $row['da_c'] ?>">
          </div>
          <div class="form-group">

            <input type="text" class="form-control" placeholder="Nhập câu thứ 4" name="Cau4" value="<?php echo $row['da_d'] ?>">
          </div>
          <div class="form-group">
          <?php $kq =  $row['kq'] ?> 
              <select class="form-control"  name="KetQua">
                      <option value="1" <?php echo $kq == 1? 'selected': ''?> >Câu 1</option>
                      <option value="2" <?php echo $kq == 2? 'selected': ''?> >Câu 2</option>
                      <option value="3" <?php echo $kq == 3? 'selected': ''?> >Câu 3</option>
                      <option value="4" <?php echo $kq == 4? 'selected': ''?> >Câu 4</option>
              </select>
          </div>
          <div class="form-group">
          <?php $muc =  $row['Muc_CauHoi'] ?> 
              <select class="form-control"  name="MucCauhoi">
                      <option value="0" <?php echo $muc == 0? 'selected': ''?>>Dễ</option>
                      <option value="1" <?php echo $muc == 1? 'selected': ''?>>Trung bình</option>
                      <option value="2" <?php echo $muc == 2? 'selected': ''?>>Khó</option>
              </select>
          </div>
          <div class="form-group">
          <?php $mon = $row['mon'] ?>
              <select class="form-control"  name="Mon">
                      <option value="CTDL&GT" <?php echo $mon == 'CTDL&GT'? 'selected': ''?>>Cấu trúc dữ liệu và giải thuật</option>
                      <option value="KTPM" <?php echo $mon == 'KTPM'? 'selected': ''?>>Kiểm thử phần mềm</option>
              </select>
          </div>
        <button type="submit" class="btn btn-primary" name="sua">Sửa</button>
      </form>
</div>
</body>
</html>
