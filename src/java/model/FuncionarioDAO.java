package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FuncionarioDAO extends DataBaseDAO {

    public ArrayList<Funcionario> listar() throws Exception {
        ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();

        String sql = "SELECT * FROM funcionario";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        PerfilDAO pDAO = new PerfilDAO();
        
        while (rs.next()) {
            Funcionario f = new Funcionario();
            f.setId(rs.getInt("id"));
            f.setNomeCompleto(rs.getString("nome_completo"));
            f.setEmail(rs.getString("email"));
            f.setSenha(rs.getString("senha"));
            f.setSituacao(rs.getString("situacao"));            
            f.setTelefoneContato(rs.getString("telefone_contato"));
            f.setTelefoneWhatsapp(rs.getString("telefone_whatszapp"));            
            f.setCpf(rs.getString("cpf"));
            f.setCep(rs.getString("cep"));
            f.setEndereco(rs.getString("endereco"));
            
            f.setPerfil(pDAO.carregarPorId(rs.getInt("id_perfil")));
            
            funcionarios.add(f);
        }
        
        this.desconectar();
        return funcionarios;
    }

    public Funcionario carregarPorId(int id) throws Exception {
        Funcionario funcionario = new Funcionario();
        
        String sql = "SELECT * FROM funcionario WHERE id = ?";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()) {
            funcionario.setId(rs.getInt("id"));
            funcionario.setNomeCompleto(rs.getString("nome_completo"));
            funcionario.setEmail(rs.getString("email"));
            funcionario.setSenha(rs.getString("senha"));
            funcionario.setSituacao(rs.getString("situacao"));
            funcionario.setTelefoneContato(rs.getString("telefone_contato"));
            funcionario.setTelefoneWhatsapp(rs.getString("telefone_whatszapp"));            
            funcionario.setCpf(rs.getString("cpf"));
            funcionario.setCep(rs.getString("cep"));
            funcionario.setEndereco(rs.getString("endereco"));
            
            PerfilDAO pDAO = new PerfilDAO();
            funcionario.setPerfil(pDAO.carregarPorId(rs.getInt("id_perfil")));
        }
        
        this.desconectar();
        return funcionario;
    }
    
    public Funcionario logar(String login, String senha) throws Exception {
        Funcionario funcionario = new Funcionario();
        
        String sql = "SELECT * FROM funcionario WHERE situacao = 'Ok' AND email = ?";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, login);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()) {            
            if(senha.equals(rs.getString("senha"))){
                funcionario.setId(rs.getInt("id"));
                funcionario.setNomeCompleto(rs.getString("nome_completo"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setSituacao(rs.getString("situacao"));

                PerfilDAO pDAO = new PerfilDAO();
                funcionario.setPerfil(pDAO.carregarPorId(rs.getInt("id_perfil")));
            }            
        }
        
        this.desconectar();
        return funcionario;
    }

    public void inserir(Funcionario f) throws Exception {
        String sql = "INSERT INTO funcionario (nome_completo, cpf, email, telefone_contato, "
                + "telefone_whatszapp, senha, situacao, endereco, cep, id_perfil) VALUES (?,?,?,?,?,?,?,?,?,?)";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, f.getNomeCompleto());
        ps.setString(2, f.getCpf());
        ps.setString(3, f.getEmail());
        ps.setString(4, f.getTelefoneContato());
        ps.setString(5, f.getTelefoneWhatsapp());
        ps.setString(6, f.getSenha());
        ps.setString(7, "Ok");
        ps.setString(8, f.getEndereco());
        ps.setString(9, f.getCep());
        ps.setInt(10, f.getPerfil().getId());
        ps.execute();
        this.desconectar();
    }

    public void alterar(Funcionario f) throws Exception {
        String sql = "UPDATE funcionario SET nome_completo = ?, cpf = ?, email = ?, telefone_contato = ?, "
                + "telefone_whatszapp = ?, senha = ?, endereco = ?, cep = ?, id_perfil = ? WHERE id = ?";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);        
        ps.setString(1, f.getNomeCompleto());
        ps.setString(2, f.getCpf());
        ps.setString(3, f.getEmail());
        ps.setString(4, f.getTelefoneContato());
        ps.setString(5, f.getTelefoneWhatsapp());
        ps.setString(6, f.getSenha());        
        ps.setString(7, f.getEndereco());
        ps.setString(8, f.getCep());
        ps.setInt(9, f.getPerfil().getId());
        ps.setInt(10, f.getId());
        ps.execute();
        this.desconectar();
    }
    
    public void alterarSituacao(int id, int op) throws Exception {
        String sql = "UPDATE funcionario SET situacao = ? WHERE id = ?";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);        
        
        String situacao = "Ok";        
        if(op == 0) {
            situacao = "nOk";
        }
        
        ps.setString(1, situacao);
        ps.setInt(2, id);
        ps.execute();
        this.desconectar();
    }

    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM funcionario WHERE id = ?";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
        this.desconectar();
    }

}
