<?php 
    class database{
        private $host = "localhost";
        private $user = "root";
        private $pass = "";
        private $db = "tracnghiem";
        private $conn = "";
        
        function __construct()
        {
            return $this->ketnoi();
        }

        function ketnoi(){
            try{
                $this->conn = new PDO("mysql:host=$this->host;dbname=$this->db",$this->user,$this->pass);
                $this->conn->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);
                return $this->conn;

            }catch(PDOException $ex){
                echo "Connect failed: " . $ex->getMessage();
            }
        }
        function dem_cot($sql){
            $stmt = $this->conn->prepare($sql);
            $stmt->execute();
            return $num_rows = $stmt->rowCount();
        }
        function all($sql){
            $stmt = $this->conn->prepare($sql);
            $stmt->execute();
            return $stmt;
        }
        function thucthi($sql){
            try {
                $result = $this->conn->query($sql);
                return $result;
            } catch(PDOException $e) {
                echo "Query failed: " . $e->getMessage();
            }
        }
        function test($arg){
            foreach($arg as $item){
                foreach($item as $i){
                    echo $i."<br>";
                }
                echo "<br><br>";
            }
        }
    }
?>