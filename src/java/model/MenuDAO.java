package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MenuDAO extends DataBaseDAO {

    public ArrayList<Menu> listar() throws Exception {
        ArrayList<Menu> menus = new ArrayList<Menu>();

        String sql = "SELECT * FROM menu";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
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

    public Menu carregarPorId(int id) throws Exception {
        Menu menu = new Menu();
        
        String sql = "SELECT * FROM menu WHERE id = ?";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()) {
            menu.setId(rs.getInt("id"));
            menu.setTitulo(rs.getString("titulo"));            
            menu.setLink(rs.getString("link"));
            menu.setIcone(rs.getString("icone"));
        }
        this.desconectar();
        return menu;
    }

    public void inserir(Menu m) throws Exception {
        String sql = "INSERT INTO menu (titulo, link, icone) VALUES (?,?,?)";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, m.getTitulo());
        ps.setString(2, m.getLink());
        ps.setString(3, m.getIcone());
        ps.execute();
        this.desconectar();
    }

    public void alterar(Menu m) throws Exception {
        String sql = "UPDATE menu SET titulo = ?, link = ?, icone = ? WHERE id = ?";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, m.getTitulo());
        ps.setString(2, m.getLink());
        ps.setString(3, m.getIcone());        
        ps.setInt(4, m.getId());
        ps.execute();
        this.desconectar();
    }

    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM menu WHERE id = ?";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
        this.desconectar();
    }

}
