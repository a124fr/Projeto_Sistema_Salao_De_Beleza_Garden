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
            f.setNome(rs.getString("nome_completo"));
            f.setLogin(rs.getString("email"));
            f.setSenha(rs.getString("senha"));
            f.setSituacao(rs.getString("situacao"));
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
            funcionario.setNome(rs.getString("nome_completo"));
            funcionario.setLogin(rs.getString("email"));
            funcionario.setSenha(rs.getString("senha"));
            funcionario.setSituacao(rs.getString("situacao"));
            
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
                funcionario.setNome(rs.getString("nome_completo"));
                funcionario.setLogin(rs.getString("email"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setSituacao(rs.getString("situacao"));

                PerfilDAO pDAO = new PerfilDAO();
                funcionario.setPerfil(pDAO.carregarPorId(rs.getInt("id_perfil")));
            }            
        }
        
        this.desconectar();
        return funcionario;
    }

    public void inserir(Funcionario u) throws Exception {
        String sql = "INSERT INTO funcionario (nome, login, senha, situacao, id_perfil) VALUES (?,?,?,?,?)";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, u.getNome());
        ps.setString(2, u.getLogin());
        ps.setString(3, u.getSenha());
        ps.setString(4, "Ok");
        ps.setInt(5, u.getPerfil().getId());
        ps.execute();
        this.desconectar();
    }

    public void alterar(Funcionario u) throws Exception {
        String sql = "UPDATE funcionario SET nome = ?, login = ?, senha = ?, id_perfil = ? WHERE id = ?";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);        
        ps.setString(1, u.getNome());
        ps.setString(2, u.getLogin());
        ps.setString(3, u.getSenha());        
        ps.setInt(4, u.getPerfil().getId());
        ps.setInt(5, u.getId());
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
        String sql = "DELETE FROM usuario WHERE id = ?";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
        this.desconectar();
    }

}
