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
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css?<?php echo time() ?>">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <style>
    .bg-dark{
      border-radius: 0.5rem;
    }
  </style>
</head>
<body>
<?php
  include "../database.php";
  $db = new database();
  $rs = $db->thucthi("SELECT * FROM `taikhoan` INNER JOIN `thongke` ON `taikhoan`.`account` = `thongke`.`account`");
  
?>
<div class="container">
 
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
    
  <table class="table table-striped" id="table">
    <thead>
      <tr>
        <th>Tên người chơi</th>
        <th>Điểm</th>
        <th>Thời gian chơi lần cuối</th>
        <th>Tổng thời gian</th>
      </tr>
    </thead>
    <tbody>
     <?php while($r = mysqli_fetch_assoc($rs)){ ?>
      <tr>
        <td><?php echo $r['ten'] ?></td>
        <td><?php echo $r['diem']." Điểm" ?></td>
        <td><?php echo $r['LastTime'] ?></td>
        <td><?php echo $r['Tong_tg']. " miniutes" ?></td>
      </tr>
      <?php } ?>
    </tbody>
  </table>
    <button onclick="tableToExcels()" type="submit" id="btn_xuatexcels" class="btn btn-primary" name="sua">Xuất file excels</button>
  
</div>
<script type="text/javascript" src="table2excel.js"></script>
<script type="text/javascript">
    function tableToExcels(){
      var table2excel = new Table2Excel();
      table2excel.export(document.getElementById("table"));
    }
</script>
</body>
</html>
