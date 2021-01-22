package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProcedimentoDAO extends DataBaseDAO {
    
    public ArrayList<Procedimento> listar() throws Exception {
        ArrayList<Procedimento> procedimentos = new ArrayList<Procedimento>();
        String sql = "SELECT * FROM procedimento";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()) {
            Procedimento p = new Procedimento();
            p.setId(rs.getInt("id"));
            p.setTitulo(rs.getString("titulo"));
            p.setDescricao(rs.getString("descricao"));
            p.setValor(rs.getDouble("valor"));
            p.setComissaoPorcentual(rs.getInt("comissao_porcentual"));
            
            procedimentos.add(p);
        }

        return procedimentos;
    }

    public Procedimento carregarPorId(int id) throws Exception {
        Procedimento procedimento = new Procedimento();
        String sql = "SELECT * FROM procedimento WHERE id = ?";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()) {            
            procedimento.setId(rs.getInt("id"));
            procedimento.setTitulo(rs.getString("titulo"));
            procedimento.setDescricao(rs.getString("descricao"));
            procedimento.setValor(rs.getDouble("valor"));
            procedimento.setComissaoPorcentual(rs.getInt("comissao_porcentual"));            
        }

        return procedimento;
    }
    
    public void inserir(Procedimento p) throws Exception {
        String sql = "INSERT INTO procedimento (titulo, descricao, valor, comissao_porcentual) VALUES (?,?,?,?)";        
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, p.getTitulo());
        ps.setString(2, p.getDescricao());
        ps.setDouble(3, p.getValor());
        ps.setInt(4, p.getComissaoPorcentual());
        ps.execute();
        this.desconectar();        
    }
    
    public void alterar(Procedimento p) throws Exception {
        String sql = "UPDATE procedimento SET titulo = ?, descricao = ?, valor = ?, comissao_porcentual = ? WHERE id = ?";        
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, p.getTitulo());
        ps.setString(2, p.getDescricao());
        ps.setDouble(3, p.getValor());
        ps.setInt(4, p.getComissaoPorcentual());
        ps.setInt(5, p.getId());
        ps.execute();
        this.desconectar();        
    }
    
    
    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM procedimento WHERE id = ?";
        this.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
        this.desconectar();
    }
}
