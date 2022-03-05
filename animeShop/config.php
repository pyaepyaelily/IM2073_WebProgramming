<?php
    define('PASSWORD', 'password');
    define('HOST', 'localhost:3306');
    define('DATABASE', 'animedb');
    try {
        $connection = new PDO("mysql:host=".HOST.";dbname=".DATABASE, PASSWORD);
    } catch (PDOException $e) {
        exit("Error: " . $e->getMessage());
    }
?>