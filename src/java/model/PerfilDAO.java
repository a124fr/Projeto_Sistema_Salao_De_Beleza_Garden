package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PerfilDAO extends DataBaseDAO {

    public ArrayList<Perfil> listar() throws Exception {
        ArrayList<Perfil> perfis = new ArrayList<Perfil>();

        String sql = "SELECT * FROM perfil";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Perfil p = new Perfil();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setDescricao(rs.getString("descricao"));

            perfis.add(p);
        }
        this.desconectar();
        return perfis;
    }
    
    public ArrayList<Menu> carregarMenusPerfil(int id_perfil) throws Exception {
        ArrayList<Menu> menus = new ArrayList<Menu>();

        String sql = "SELECT m.* FROM menu as m, perfil_menu as pm  "
                     + " WHERE m.id = pm.id_menu AND pm.id_perfil = ?";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id_perfil);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Menu m = new Menu();
            m.setId(rs.getInt("id"));
            m.setTitulo(rs.getString("titulo"));            
            m.setLink(rs.getString("link"));
            m.setIcone(rs.getString("icone"));

            menus.add(m);
        }
        this.desconectar();
        return menus;
    }
    public ArrayList<Menu> carregarMenusNaoPerfil(int id_perfil) throws Exception {
        ArrayList<Menu> menus = new ArrayList<Menu>();

        String sql = "SELECT m.* FROM menu as m "
                   + "WHERE m.id NOT IN (SELECT id_menu FROM perfil_menu WHERE id_perfil = ?)";
                     
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id_perfil);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Menu m = new Menu();
            m.setId(rs.getInt("id"));
            m.setTitulo(rs.getString("titulo"));            
            m.setLink(rs.getString("link"));
            m.setIcone(rs.getString("icone"));

            menus.add(m);
        }
        this.desconectar();
        return menus;
    }

    public Perfil carregarPorId(int id) throws Exception {
        Perfil perfil = new Perfil();
        
        String sql = "SELECT * FROM perfil WHERE id = ?";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()) {
            perfil.setId(rs.getInt("id"));
            perfil.setNome(rs.getString("nome"));
            perfil.setDescricao(rs.getString("descricao"));
            perfil.setMenus(this.carregarMenusPerfil(id));
        }
        this.desconectar();
        return perfil;
    }

    public void inserir(Perfil p) throws Exception {
        String sql = "INSERT INTO perfil (nome, descricao) VALUES (?,?)";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, p.getNome());
        ps.setString(2, p.getDescricao());
        ps.execute();
        this.desconectar();
    }

    public void alterar(Perfil p) throws Exception {
        String sql = "UPDATE perfil SET nome = ?, descricao = ? WHERE id = ?";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, p.getNome());
        ps.setString(2, p.getDescricao());
        ps.setInt(3, p.getId());
        ps.execute();
        this.desconectar();
    }

    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM perfil WHERE id = ?";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
        this.desconectar();
    }
    
    public void vincularMenu(int id_perfil, int id_menu) throws Exception {
        String sql = "INSERT INTO perfil_menu (id_perfil, id_menu) VALUES (?,?)";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id_perfil);
        ps.setInt(2, id_menu);
        ps.execute();
        this.desconectar();
    }
    
    public void desvincularMenu(int id_perfil, int id_menu) throws Exception {
        String sql = "DELETE FROM perfil_menu WHERE id_perfil = ? AND id_menu = ?";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id_perfil);
        ps.setInt(2, id_menu);
        ps.execute();
        this.desconectar();
    }

}
