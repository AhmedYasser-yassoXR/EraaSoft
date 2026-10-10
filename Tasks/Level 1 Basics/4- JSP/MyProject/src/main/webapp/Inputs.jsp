
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Form</title>
</head>
<body>

    <h2>User Information</h2>

    <form action="result.jsp" method="post">
<% // hna inputs 3ady lel name wa password wa age %>
        Full Name:
        <input type="text" name="fullName">
        <br><br>

        Password:
        <input type="text" name="password">
        <br><br>

        Age:
        <input type="text" name="age">
        <br><br>

        Address (Radio):
        <input type="radio" name="radioAddress" value="Cairo"> Cairo
        <input type="radio" name="radioAddress" value="Alex"> Alex
        <input type="radio" name="radioAddress" value="Menofia"> Menofia

        <br><br>

        Address (Select):
        <select name="selectAddress">
            <option value="Cairo">Cairo</option>
            <option value="Alex">Alex</option>
            <option value="Menofia">Menofia</option>
        </select>

        <br><br>

        <input type="submit" value="Submit">

    </form>

</body>
</html>
