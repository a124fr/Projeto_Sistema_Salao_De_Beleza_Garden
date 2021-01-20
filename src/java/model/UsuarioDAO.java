package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioDAO extends DataBaseDAO {

    public ArrayList<Usuario> listar() throws Exception {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        String sql = "SELECT * FROM usuario";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        PerfilDAO pDAO = new PerfilDAO();
        
        while (rs.next()) {
            Usuario u = new Usuario();
            u.setId(rs.getInt("id"));
            u.setNome(rs.getString("nome"));
            u.setLogin(rs.getString("login"));
            u.setSenha(rs.getString("senha"));
            u.setSituacao(rs.getString("situacao"));
            u.setPerfil(pDAO.carregarPorId(rs.getInt("id_perfil")));
            
            usuarios.add(u);
        }
        
        this.desconectar();
        return usuarios;
    }

    public Usuario carregarPorId(int id) throws Exception {
        Usuario usuario = new Usuario();
        
        String sql = "SELECT * FROM usuario WHERE id = ?";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()) {
            usuario.setId(rs.getInt("id"));
            usuario.setNome(rs.getString("nome"));
            usuario.setLogin(rs.getString("login"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setSituacao(rs.getString("situacao"));
            
            PerfilDAO pDAO = new PerfilDAO();
            usuario.setPerfil(pDAO.carregarPorId(rs.getInt("id_perfil")));
        }
        
        this.desconectar();
        return usuario;
    }
    
    public Usuario logar(String login, String senha) throws Exception {
        Usuario usuario = new Usuario();
        
        String sql = "SELECT * FROM usuario WHERE situacao = 'Ok' AND login = ?";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, login);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()) {            
            if(senha.equals(rs.getString("senha"))){
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setSituacao(rs.getString("situacao"));

                PerfilDAO pDAO = new PerfilDAO();
                usuario.setPerfil(pDAO.carregarPorId(rs.getInt("id_perfil")));
            }            
        }
        
        this.desconectar();
        return usuario;
    }

    public void inserir(Usuario u) throws Exception {
        String sql = "INSERT INTO usuario (nome, login, senha, situacao, id_perfil) VALUES (?,?,?,?,?)";
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

    public void alterar(Usuario u) throws Exception {
        String sql = "UPDATE usuario SET nome = ?, login = ?, senha = ?, id_perfil = ? WHERE id = ?";
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
        String sql = "UPDATE usuario SET situacao = ? WHERE id = ?";
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
