package pe.cmacica.labs.labs03.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pe.cmacica.labs.labs03.dominio.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteRespositoryImpl implements ClienteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    class ClienteMapper implements RowMapper<Cliente> {

        @Override
        public Cliente mapRow(ResultSet rs, int i) throws SQLException {
            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("id"));
            cliente.setNombres(rs.getString("nombres"));
            cliente.setApe_pat(rs.getString("ape_pat"));
            cliente.setApe_mat(rs.getString("ape_mat"));
            cliente.setEdad(rs.getInt("edad"));
            cliente.setEmail(rs.getString("email"));
            return cliente;
        }
    }

    @Override
    public List<Cliente> listar() {

        return jdbcTemplate.query("Select id,nombres, ape_pat, ape_mat, edad, email from cliente",new ClienteMapper());

        /*
        List<Cliente> list = new ArrayList<>();
        for (int i= 0; i<=10; i++){
            Cliente c = new Cliente();
            c.setId(i);
            c.setNombres("Nombre" + i);
            list.add(c);
        }

        return list;*/
    }


    @Override
    public Cliente getCliente(int id) {

        Cliente c = jdbcTemplate.queryForObject("Select * from cliente where id =?",
                                                new Object[]{id},
                                                new ClienteMapper());

        return c;
        /*
        Cliente c = new Cliente();
        c.setId(id);
        c.setNombres("Algun cliente");

        return c;*/
    }

    @Override
    public int eliminar(int id) {
        return jdbcTemplate.update("delete from cliente where id = ?", new Object[]{id});
    }

    @Override
    public int update(Cliente cliente) {
        return jdbcTemplate.update("update cliente set nombres=?, ape_pat =?, ape_mat =? where id =?",
                                         new Object[]{cliente.getNombres(),
                                                      cliente.getApe_mat(),
                                                      cliente.getApe_pat(),
                                                      cliente.getId()});
    }

    @Override
    public void insert(Cliente cliente) {

        String Sql_Insert = "insert cliente (nombres, ape_pat, ape_mat) values (?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(Sql_Insert, Statement.RETURN_GENERATED_KEYS);

                        ps.setString(1, cliente.getNombres());
                        ps.setString(2, cliente.getApe_pat());
                        ps.setString(3, cliente.getApe_mat());
                        return ps;
                    }
                }, keyHolder);


        int newUserId = keyHolder.getKey().intValue();

        cliente.setId(newUserId);

    }

    @Override
    public void abonarCuenta(String cuenta, double monto) {

    }

    @Override
    public void debitarCuenta(String cuenta, double monto) {

    }
}
