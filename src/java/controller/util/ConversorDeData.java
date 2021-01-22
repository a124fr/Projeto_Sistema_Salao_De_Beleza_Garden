package controller.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConversorDeData {
    
    private DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
    DateFormat outputFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");    
    private DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private Date data;
    
    public String converterParaTela(String dataParaConverter) throws ParseException {        
        return formatador.format(dataParaConverter);
    }
    
    public String converterParaBanco(String dataParaConverter) throws ParseException {        
        return inputFormat.format(this.data);
    }
    
}
