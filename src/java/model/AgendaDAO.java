package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AgendaDAO extends DataBaseDAO {
    
    public ArrayList<Agenda> listar() throws Exception {
        ArrayList<Agenda> lista = new ArrayList<Agenda>();
        String sql = 
            "SELECT " +
                "id, " +
                "data_agendamento, " +
                "data_atendimento, " +
                "data_pagamento, " +
                "data_cancelamento, " +
                "id_funcionario, " +
                "id_procedimento, " +
                "id_cliente, " +
                "status_agenda, " +
                "desconto, " +
                "valor_pago " +
            "FROM agenda "+ 
            "WHERE  date(data_agendamento) = CURDATE()";        
        this.conectar();
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
        
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        DateFormat outputFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        
        while(rs.next()) {
            Agenda a = new Agenda();
            a.setId(rs.getInt("id"));
            a.setDataAgendamento( rs.getString("data_agendamento") );            
            a.setDataAtendimento( rs.getString("data_atendimento") );
            a.setDataCancelamento( rs.getString("data_cancelamento") );
            a.setDataPagamento( rs.getString("data_pagamento") );                        
            a.setFuncionario( funcionarioDAO.carregarPorId( rs.getInt("id_funcionario") ) );
            a.setCliente( clienteDAO.carregarPorId( rs.getInt("id_cliente")) );
            a.setProcedimento( procedimentoDAO.carregarPorId( rs.getInt("id_procedimento") ) );            
            a.setStatus( rs.getString("status_agenda") );
            a.setDesconto(rs.getDouble("desconto"));
            a.setValorPago(rs.getDouble("valor_pago"));
            
            lista.add(a);
        }        
        
        this.desconectar();        
        return lista;
    }
    
    public ArrayList<Agenda> consultarAgendaPorCpfCliente(String cpfCliente) throws Exception {
        ArrayList<Agenda> lista = new ArrayList<Agenda>();
        String sql = "SELECT a.id, a.data_agendamento, a.data_atendimento, a.data_pagamento, a.data_cancelamento, a.id_funcionario, a.id_procedimento, a.id_cliente, a.status_agenda, a.desconto, a.valor_pago FROM agenda a inner join cliente c on a.id_cliente = c.id WHERE  c.cpf = ?";        
        this.conectar();
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setString(1, cpfCliente);
        ResultSet rs = ps.executeQuery();
        
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
        
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        DateFormat outputFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        
        while(rs.next()) {
            Agenda a = new Agenda();
            a.setId(rs.getInt("id"));
            a.setDataAgendamento( rs.getString("data_agendamento") );            
            a.setDataAtendimento( rs.getString("data_atendimento") );
            a.setDataCancelamento( rs.getString("data_cancelamento") );
            a.setDataPagamento( rs.getString("data_pagamento") );                        
            a.setFuncionario( funcionarioDAO.carregarPorId( rs.getInt("id_funcionario") ) );
            a.setCliente( clienteDAO.carregarPorId( rs.getInt("id_cliente")) );
            a.setProcedimento( procedimentoDAO.carregarPorId( rs.getInt("id_procedimento") ) );            
            a.setStatus( rs.getString("status_agenda") );
            a.setDesconto(rs.getDouble("desconto"));
            a.setValorPago(rs.getDouble("valor_pago"));
            
            lista.add(a);
        }        
        
        this.desconectar();        
        return lista;
    }
    
    public Agenda carregarPorId(int id) throws Exception {
        Agenda agenda = new Agenda();
        String sql = 
            "SELECT " +
                "id, " +
                "data_agendamento, " +
                "data_atendimento, " +
                "data_pagamento, " +
                "data_cancelamento, " +
                "id_funcionario, " +
                "id_procedimento, " +
                "id_cliente, " +
                "status_agenda, " +
                "desconto, " +
                "valor_pago " +
            "FROM agenda "+ 
            "WHERE id = ?";        
        this.conectar();
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
        
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        DateFormat outputFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        
        if(rs.next()) {            
            agenda.setId(rs.getInt("id"));
            agenda.setDataAgendamento( rs.getString("data_agendamento") );            
            agenda.setDataAtendimento( rs.getString("data_atendimento") );
            agenda.setDataCancelamento( rs.getString("data_cancelamento") );
            agenda.setDataPagamento( rs.getString("data_pagamento") );                        
            agenda.setFuncionario( funcionarioDAO.carregarPorId( rs.getInt("id_funcionario") ) );
            agenda.setCliente( clienteDAO.carregarPorId( rs.getInt("id_cliente")) );
            agenda.setProcedimento( procedimentoDAO.carregarPorId( rs.getInt("id_procedimento") ) );            
            agenda.setStatus( rs.getString("status_agenda") );
            agenda.setDesconto(rs.getDouble("desconto"));
            agenda.setValorPago(rs.getDouble("valor_pago"));
        }
        
        this.desconectar();        
        return agenda;
    }    
    
    public void atender(Agenda a) throws Exception {
        String sql = "UPDATE agenda SET data_atendimento = ?, status_agenda = ? WHERE id = ?";
        this.conectar();
        PreparedStatement ps = this.conn.prepareStatement(sql);        
        ps.setString(1, a.getDataAtendimento());        
        ps.setString(2, "EmAtendimento");
        ps.setInt(3, a.getId());
        ps.execute();
        this.desconectar();
    }
    
    public void pagar(Agenda a) throws Exception {
        String sql = "UPDATE agenda SET desconto = ?, valor_pago = ?, data_pagamento = ?, status_agenda = ? WHERE id = ?";
        this.conectar();
        PreparedStatement ps = this.conn.prepareStatement(sql);        
        ps.setDouble(1, a.getDesconto());
        ps.setDouble(2, a.getValorPago());
        ps.setString(3, a.getDataPagamento());        
        ps.setString(4, "Pago");
        ps.setInt(5, a.getId());
        ps.execute();
        this.desconectar();
    }
    
   public void cancelar(Agenda a) throws Exception {
        String sql = "UPDATE agenda SET data_cancelamento = ?, status_agenda = ? WHERE id = ?";
        this.conectar();
        PreparedStatement ps = this.conn.prepareStatement(sql);        
        ps.setString(1, a.getDataCancelamento());        
        ps.setString(2, "Cancelado");
        ps.setInt(3, a.getId());
        ps.execute();
        this.desconectar();
    }
    
}
