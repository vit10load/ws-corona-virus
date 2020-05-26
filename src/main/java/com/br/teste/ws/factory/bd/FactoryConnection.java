package com.br.teste.ws.factory.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author vitcl
 */
public class FactoryConnection {
    
    private static final String url = "jdbc:mysql://localhost:3306/exemplo_ws_aluno?useTimezone=true&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "";

    public FactoryConnection() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
        }catch(ClassNotFoundException e){
            System.out.println("err.."+e.getMessage());
        }
        
    }
    
    public static Connection getConnection(){
        
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.out.println("err..."+ex.getMessage());
        }
        
        return null;
    }
    
    
    
    
}
