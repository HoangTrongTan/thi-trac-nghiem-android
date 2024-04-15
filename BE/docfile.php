<?php
$file_path = 'file.txt';
$file_handle = fopen($file_path, 'r');

if ($file_handle) {
    while (($line = fgets($file_handle)) !== false) {
        echo $line;
    }

    fclose($file_handle);
} else {
    echo 'Không thể mở file.';
}
?>