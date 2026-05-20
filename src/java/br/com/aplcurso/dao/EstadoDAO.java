package br.com.aplcurso.dao;

import br.com.aplcurso.model.Estado;
import br.com.aplcurso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstadoDAO implements GenericDAO {

    private Connection conexao;

    public EstadoDAO() throws Exception {

        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {

        Estado estado = (Estado) objeto;

        if (estado.getId() == 0) {

            return inserir(estado);

        } else {

            return alterar(estado);
        }
    }

    @Override
    public Boolean inserir(Object objeto) {

        Estado estado = (Estado) objeto;


        if (nomeExiste(estado.getNomeEstado())) {

            System.out.println("Nome do estado já cadastrado!");
            return false;
        }


        if (siglaExiste(estado.getSiglaEstado())) {

            System.out.println("Sigla já cadastrada!");
            return false;
        }

        String sql =
                "insert into estado "
                + "(nomeestado, siglaestado) "
                + "values (?, ?)";

        try (
                PreparedStatement stmt =
                        conexao.prepareStatement(sql)
        ) {

            stmt.setString(
                    1,
                    estado.getNomeEstado()
            );

            stmt.setString(
                    2,
                    estado.getSiglaEstado()
            );

            stmt.executeUpdate();

            conexao.commit();

            return true;

        } catch (Exception ex) {

            ex.printStackTrace();

            return false;
        }
    }

    @Override
    public Boolean alterar(Object objeto) {

        Estado estado = (Estado) objeto;


        if (nomeExisteOutro(
                estado.getNomeEstado(),
                estado.getId())) {

            System.out.println("Nome já cadastrado em outro estado!");
            return false;
        }


        if (siglaExisteOutro(
                estado.getSiglaEstado(),
                estado.getId())) {

            System.out.println("Sigla já cadastrada em outro estado!");
            return false;
        }

        String sql =
                "update estado set "
                + "nomeestado=?, "
                + "siglaestado=? "
                + "where id=?";

        try (
                PreparedStatement stmt =
                        conexao.prepareStatement(sql)
        ) {

            stmt.setString(
                    1,
                    estado.getNomeEstado()
            );

            stmt.setString(
                    2,
                    estado.getSiglaEstado()
            );

            stmt.setInt(
                    3,
                    estado.getId()
            );

            stmt.executeUpdate();

            conexao.commit();

            return true;

        } catch (Exception ex) {

            ex.printStackTrace();

            return false;
        }
    }

    @Override
    public Boolean excluir(int numero) {

        String sql =
                "delete from estado where id=?";

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

            return false;
        }
    }

    @Override
    public Object carregar(int numero) {

        Estado estado = null;

        String sql =
                "select * from estado where id=?";

        try (
                PreparedStatement stmt =
                        conexao.prepareStatement(sql)
        ) {

            stmt.setInt(1, numero);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                estado = new Estado();

                estado.setId(
                        rs.getInt("id")
                );

                estado.setNomeEstado(
                        rs.getString("nomeestado")
                );

                estado.setSiglaEstado(
                        rs.getString("siglaestado")
                );
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return estado;
    }

    @Override
    public List<Object> listar() {

        List<Object> lista =
                new ArrayList<>();

        String sql =
                "select * from estado order by id";

        try (
                PreparedStatement stmt =
                        conexao.prepareStatement(sql);

                ResultSet rs =
                        stmt.executeQuery()
        ) {

            while (rs.next()) {

                Estado estado = new Estado();

                estado.setId(
                        rs.getInt("id")
                );

                estado.setNomeEstado(
                        rs.getString("nomeestado")
                );

                estado.setSiglaEstado(
                        rs.getString("siglaestado")
                );

                lista.add(estado);
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return lista;
    }

    // VERIFICA SE A SIGLA JÁ EXISTE
    public boolean siglaExiste(String sigla) {

        String sql =
                "select count(*) as quantidade "
                + "from estado "
                + "where lower(siglaestado)=lower(?)";

        try (
                PreparedStatement stmt =
                        conexao.prepareStatement(sql)
        ) {

            stmt.setString(1, sigla);

            ResultSet rs =
                    stmt.executeQuery();

            while (rs.next()) {

                return rs.getInt(
                        "quantidade"
                ) > 0;
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return false;
    }

    public boolean siglaExisteOutro(String sigla, int id) {

        String sql =
                "select count(*) as quantidade "
                + "from estado "
                + "where lower(siglaestado)=lower(?) "
                + "and id<>?";

        try (
                PreparedStatement stmt =
                        conexao.prepareStatement(sql)
        ) {

            stmt.setString(1, sigla);
            stmt.setInt(2, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                return rs.getInt("quantidade") > 0;
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return false;
    }

    public boolean nomeExiste(String nome) {

        String sql =
                "select count(*) as quantidade "
                + "from estado "
                + "where lower(nomeestado)=lower(?)";

        try (
                PreparedStatement stmt =
                        conexao.prepareStatement(sql)
        ) {

            stmt.setString(1, nome);

            ResultSet rs =
                    stmt.executeQuery();

            while (rs.next()) {

                return rs.getInt(
                        "quantidade"
                ) > 0;
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return false;
    }

    public boolean nomeExisteOutro(
            String nome,
            int id) {

        String sql =
                "select count(*) as quantidade "
                + "from estado "
                + "where lower(nomeestado)=lower(?) "
                + "and id<>?";

        try (
                PreparedStatement stmt =
                        conexao.prepareStatement(sql)
        ) {

            stmt.setString(1, nome);
            stmt.setInt(2, id);

            ResultSet rs =
                    stmt.executeQuery();

            while (rs.next()) {

                return rs.getInt(
                        "quantidade"
                ) > 0;
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return false;
    }
}