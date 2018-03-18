<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users database</title>
</head>

<body>
<p>Please, fill all fields and submit...</p>

<form action='/users/createj' method='post'>
    E-mail:<br>
    <input type='text' name='email' value=''><br>
    Name:<br>
    <input type='text' name='name' value=''><br>
    Login:<br>
    <input type='text' name='login' value=''><br><br>
    <input type='submit' value='Create'>
</form>

<form action='/users/mainj' method = 'get'>
    <input type='submit' value='Cancel'>
</form>