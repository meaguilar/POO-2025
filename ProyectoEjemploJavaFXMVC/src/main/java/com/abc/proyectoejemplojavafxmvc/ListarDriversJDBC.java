package com.abc.proyectoejemplojavafxmvc;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

public class ListarDriversJDBC {
  public static void imprimirDriverJDBC (){
      Enumeration<Driver> drivers = DriverManager.getDrivers();
          while (drivers.hasMoreElements()) {
              Driver driver = drivers.nextElement();
              System.out.println("Driver: " + driver.getClass().getName());
              System.out.println("Versión: " + driver.getMajorVersion() + "." + driver.getMinorVersion());
              System.out.println("Cumple con JDBC: " + driver.jdbcCompliant());
              System.out.println("------------------------------");
          }
  }
}
