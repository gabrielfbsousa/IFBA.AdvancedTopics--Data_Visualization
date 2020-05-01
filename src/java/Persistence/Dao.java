/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistence;

import Model.Pais;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gabrielferreira
 */
public class Dao {
    public Connection con;
    
    public Dao(Conexao conexao) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException{
        this.con = conexao.fabricar();
    }
    
    public ArrayList<Pais> list() throws SQLException{
        ArrayList<Pais> paises = new ArrayList<Pais>();
        PreparedStatement pstm = con.prepareStatement("select * from pais");
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            Pais pais = new Pais();
            pais.setDensidade_demografica(rs.getDouble("densidade_demografica"));
            pais.setIdh(rs.getDouble("idh"));
            pais.setPib(rs.getDouble("pib"));
            pais.setPopulacao(rs.getDouble("populacao"));
            pais.setArea(rs.getDouble("area"));
            pais.setNome(rs.getString("nome"));
            paises.add(pais);
        }
        
        return paises;
    }
    
}
