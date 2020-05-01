/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author gabrielferreira
 */
public class Conexao {
    public static Connection fabricar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        return DriverManager.getConnection("jdbc:derby://localhost:1527/BDMapas");
    }
}
