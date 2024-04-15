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
if(!isset($_SESSION)){
        session_start();
    }
    if(!isset($_SESSION['user'])){        
       header('Location: dangnhap.php');
    }else{
        echo $_SESSION['user'];
    }
  include "../database.php";
  $db = new database();
  if(isset($_GET['id'])){
    $id = $_GET['id'];
    $rs = $db->thucthi("DELETE FROM `cauhoi` WHERE id = $id");
    if($rs == 1){
          echo "<script>alert('Xóa thành công !')</script>";
          echo "<script>window.location.href = 'danhsach.php'</script>";
    }
  }
  $rs = $db->thucthi("SELECT * FROM `cauhoi`");
  
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
    
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Câu hỏi</th>
        <th>đáp án 1</th>
        <th>đáp án 2</th>
        <th>đáp án 3</th>
        <th>đáp án 4</th>
        <th>Kết quả</th>
        <th>Mức câu hỏi</th>
        <th>Môn</th>
        <th>Thao tác</th>
      </tr>
    </thead>
    <tbody>
     <?php while($r = mysqli_fetch_assoc($rs)){ ?>
      <tr>
        <td><?php echo $r['Ten_Cauhoi'] ?></td>
        <td><?php echo $r['da_a'] ?></td>
        <td><?php echo $r['da_b'] ?></td>
        <td><?php echo $r['da_c'] ?></td>
        <td><?php echo $r['da_d'] ?></td>
        <td><?php echo $r['kq'] ?></td>
        <td><?php echo $r['Muc_CauHoi'] ?></td>
        <td><?php echo $r['mon'] ?></td>
        <td> <pre> <a class="btn btn-primary" href="sua.php?id=<?php echo $r['id'] ?>">Sửa</a> <a onclick="return confirm('Bạn có chắc muốn xóa không')" class="btn btn-danger" href="danhsach.php?id=<?php echo $r['id'] ?>">xóa</a> </pre></td>
      </tr>
      <?php } ?>
    </tbody>
  </table>
</div>

</body>
</html>
