package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClienteDAO extends DataBaseDAO {
    
    public ArrayList<Cliente> listar() throws Exception {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
                
        String sql = "SELECT * FROM cliente";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()) {
            Cliente c = new Cliente();
            c.setId(rs.getInt("id"));
            c.setNomeCompleto(rs.getString("nome_completo"));
            c.setCpf(rs.getString("cpf"));
            c.setEmail(rs.getString("email"));
            c.setTelefoneContato(rs.getString("telefone_contato"));
            c.setTelefoneWhatsapp(rs.getString("telefone_whatszapp").isEmpty() ? "":rs.getString("telefone_whatszapp"));
            c.setEndereco(rs.getString("endereco").isEmpty() ? "":rs.getString("endereco"));
            c.setCep(rs.getString("cep").isEmpty() ? "": rs.getString("cep"));
            
            clientes.add(c);
        }
        this.desconectar();
        
        return clientes;
    }
    
    public Cliente carregarPorId(int id) throws Exception {
        Cliente cliente = new Cliente();
                
        String sql = "SELECT * FROM cliente WHERE id = ?";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()) {            
            cliente.setId(rs.getInt("id"));
            cliente.setNomeCompleto(rs.getString("nome_completo"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setEmail(rs.getString("email"));
            cliente.setTelefoneContato(rs.getString("telefone_contato"));
            cliente.setTelefoneWhatsapp(rs.getString("telefone_whatszapp").isEmpty() ? "":rs.getString("telefone_whatszapp"));
            cliente.setEndereco(rs.getString("endereco").isEmpty() ? "":rs.getString("endereco"));
            cliente.setCep(rs.getString("cep").isEmpty() ? "": rs.getString("cep"));
        }
        this.desconectar();
        
        return cliente;
    }
    
    public void inserir(Cliente c) throws Exception {
        String sql = "INSERT INTO cliente (nome_completo, cpf, email, telefone_contato, telefone_whatszapp, endereco, cep) VALUES (?,?,?,?,?,?,?)";
        
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, c.getNomeCompleto());
        ps.setString(2, c.getCpf());
        ps.setString(3, c.getEmail());
        ps.setString(4, c.getTelefoneContato());
        ps.setString(5, c.getTelefoneWhatsapp());
        ps.setString(6, c.getEndereco());
        ps.setString(7, c.getCep());
        ps.execute();
        this.desconectar();
    }
    
    public void alterar(Cliente c) throws Exception {
        String sql = "UPDATE cliente SET nome_completo = ?, cpf = ?, email = ?, telefone_contato = ?, "
                + "telefone_whatszapp = ?, endereco = ?, cep = ? WHERE id = ?";
        
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, c.getNomeCompleto());
        ps.setString(2, c.getCpf());
        ps.setString(3, c.getEmail());
        ps.setString(4, c.getTelefoneContato());
        ps.setString(5, c.getTelefoneWhatsapp());
        ps.setString(6, c.getEndereco());
        ps.setString(7, c.getCep());
        ps.setInt(8, c.getId());
        ps.execute();
        this.desconectar();
    }
    
    
    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM cliente WHERE id = ?";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
        this.desconectar();
    }
}
