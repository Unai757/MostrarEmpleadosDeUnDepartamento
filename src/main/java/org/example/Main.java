package org.example;

import java.sql.*;


public class Main {

    public static void main(String[] args) {
        //Creamos la sentencia sql donde mostramos los datos del empledo y el id y nombre del departamento con el id 10
        String sql = "SELECT EMPLEADO.ID AS empleado_id,EMPLEADO.NOMBRE as empleado_nombre, EMPLEADO.SALARIO AS salario, EMPLEADO.DEPARTAMENTO_ID AS idDepartamento, DEPARTAMENTO.NOMBRE as departamento_nombre " +
                "FROM EMPLEADO JOIN DEPARTAMENTO ON EMPLEADO.DEPARTAMENTO_ID=DEPARTAMENTO.ID " +
                "WHERE DEPARTAMENTO.ID=10";

        try (Connection conn = DriverManager.getConnection(
                DBConfig.getUrl(),
                DBConfig.getUser(),
                DBConfig.getPassword());
             //Ponemos aqui los stament y resultset en el try with resources para que se cierren automaticamente
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {
            System.out.println("Conectado con suceso!");

//Ahora en el resultSet lo ponemos en un bucle para que nos muestre toda la información de todos los empleados
            while (resultSet.next()) {
                int id = resultSet.getInt("empleado_id");

                String empleado = resultSet.getString("empleado_nombre");
                double salario = resultSet.getDouble("salario");
                String idDepartamento = resultSet.getString("idDepartamento");
                String departamento = resultSet.getString("departamento_nombre");

                System.out.println("El id del empleado es " + id + " su nombre es " + empleado + " su salario es " +
                        salario + " su id de departamento es " + idDepartamento + " su departamento es " + departamento);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

