package br.com.aplcurso.dao;

import br.com.aplcurso.model.Usuario;
import br.com.aplcurso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements GenericDAO {

    private Connection conexao;

    public UsuarioDAO() throws Exception {

        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {

        Usuario oUsuario = (Usuario) objeto;

        if (oUsuario.getId() == 0) {

            return this.inserir(oUsuario);

        } else {

            return this.alterar(oUsuario);
        }
    }

    @Override
    public Boolean inserir(Object objeto) {

        Usuario oUsuario = (Usuario) objeto;

        String sql = "insert into usuario "
                + "(nome, datanascimento, cpf, email, senha, salario) "
                + "values (?,?,?,?,?,?)";

        try (
                PreparedStatement stmt =
                        conexao.prepareStatement(sql)
        ) {

            stmt.setString(1, oUsuario.getNome());

            stmt.setDate(
                    2,
                    new java.sql.Date(
                            oUsuario.getDataNascimento().getTime()
                    )
            );

            stmt.setString(3, oUsuario.getCpf());
            stmt.setString(4, oUsuario.getEmail());
            stmt.setString(5, oUsuario.getSenha());
            stmt.setDouble(6, oUsuario.getSalario());

            int linhas = stmt.executeUpdate();

            conexao.commit();

            System.out.println(
                    "Usuário cadastrado com sucesso!"
            );

            return linhas > 0;

        } catch (Exception ex) {

            System.out.println(
                    "Problemas ao cadastrar usuário!"
            );

            ex.printStackTrace();

            try {

                conexao.rollback();

            } catch (SQLException e) {

                e.printStackTrace();
            }

            return false;
        }
    }

    @Override
    public Boolean alterar(Object objeto) {

        Usuario oUsuario = (Usuario) objeto;

        String sql = "update usuario set "
                + "nome=?, "
                + "datanascimento=?, "
                + "cpf=?, "
                + "email=?, "
                + "senha=?, "
                + "salario=? "
                + "where id=?";

        try (
                PreparedStatement stmt =
                        conexao.prepareStatement(sql)
        ) {

            stmt.setString(1, oUsuario.getNome());

            stmt.setDate(
                    2,
                    new java.sql.Date(
                            oUsuario.getDataNascimento().getTime()
                    )
            );

            stmt.setString(3, oUsuario.getCpf());
            stmt.setString(4, oUsuario.getEmail());
            stmt.setString(5, oUsuario.getSenha());
            stmt.setDouble(6, oUsuario.getSalario());
            stmt.setInt(7, oUsuario.getId());

            stmt.executeUpdate();

            conexao.commit();

            return true;

        } catch (Exception ex) {

            ex.printStackTrace();

            try {

                conexao.rollback();

            } catch (SQLException e) {

                e.printStackTrace();
            }

            return false;
        }
    }

    @Override
    public Boolean excluir(int numero) {

        String sql = "delete from usuario where id=?";

        try (
                PreparedStatement stmt =
                        conexao.prepareStatement(sql)
        ) {

            stmt.setInt(1, numero);

            stmt.executeUpdate();

            conexao.commit();

            return true;

        } catch (Exception ex) {

            ex.printStackTrace();

            try {

                conexao.rollback();

            } catch (SQLException e) {

                e.printStackTrace();
            }

            return false;
        }
    }

    @Override
    public Object carregar(int numero) {

        Usuario oUsuario = null;

        String sql = "select * from usuario where id=?";

        try (
                PreparedStatement stmt =
                        conexao.prepareStatement(sql)
        ) {

            stmt.setInt(1, numero);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                oUsuario = new Usuario();

                oUsuario.setId(rs.getInt("id"));
                oUsuario.setNome(rs.getString("nome"));
                oUsuario.setCpf(rs.getString("cpf"));
                oUsuario.setEmail(rs.getString("email"));
                oUsuario.setSenha(rs.getString("senha"));
                oUsuario.setSalario(rs.getDouble("salario"));
                oUsuario.setDataNascimento(
                        rs.getDate("datanascimento")
                );
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return oUsuario;
    }

    @Override
    public List<Object> listar() {

        List<Object> lista = new ArrayList<>();

        String sql = "select * from usuario order by id";

        try (
                PreparedStatement stmt =
                        conexao.prepareStatement(sql);

                ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {

                Usuario oUsuario = new Usuario();

                oUsuario.setId(rs.getInt("id"));
                oUsuario.setNome(rs.getString("nome"));
                oUsuario.setCpf(rs.getString("cpf"));
                oUsuario.setEmail(rs.getString("email"));
                oUsuario.setSenha(rs.getString("senha"));
                oUsuario.setSalario(rs.getDouble("salario"));
                oUsuario.setDataNascimento(
                        rs.getDate("datanascimento")
                );

                lista.add(oUsuario);
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return lista;
    }

    public boolean cpfExiste(String cpf) {

        String sql =
                "select count(*) as quantidade "
                + "from usuario where cpf=?";

        try (
                PreparedStatement stmt =
                        conexao.prepareStatement(sql)
        ) {

            stmt.setString(1, cpf);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                if (rs.getInt("quantidade") > 0) {

                    return true;
                }
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return false;
    }
    
    public boolean emailExiste(String email) {

    String sql =
            "select count(*) as quantidade "
            + "from usuario "
            + "where lower(email)=lower(?)";

    try (
            PreparedStatement stmt =
                    conexao.prepareStatement(sql)
    ) {

        stmt.setString(1, email);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            return rs.getInt("quantidade") > 0;
        }

    } catch (Exception ex) {

        ex.printStackTrace();
    }

    return false;
}
}