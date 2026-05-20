package br.com.aplcurso.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {

    private static Connection conexao = null;

    private static String servidor =
            "jdbc:postgresql://localhost:5432/bdaplicurso";

    private static String usuario = "joao";
    private static String senha = "123456";

    static {

        try {

            conectar();

        } catch (Exception ex) {

            System.out.println(
                    "Erro ao conectar ao banco de dados"
            );

            ex.printStackTrace();
        }
    }

    public SingleConnection() throws Exception {

        conectar();
    }

    private static void conectar() throws Exception {

        if (conexao == null) {

            Class.forName("org.postgresql.Driver");

            conexao =
                    DriverManager.getConnection(
                            servidor,
                            usuario,
                            senha
                    );

            conexao.setAutoCommit(false);
        }
    }

    public static Connection getConnection() {

        return conexao;
    }
}