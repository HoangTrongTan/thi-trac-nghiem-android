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
            return $this->conn = mysqli_connect($this->host,$this->user,$this->pass,$this->db);
        }
        
        function thucthi($sql){
            $result = mysqli_query($this->conn,$sql);
            return $result;
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