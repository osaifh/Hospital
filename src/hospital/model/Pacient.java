package hospital.model;

import java.sql.Date;

public class Pacient extends Persona {
    private java.sql.Date dataAlta, dataBaixa;
    private Habitacio habitacio;

    public Pacient() {
    }

    public Pacient(Date dataAlta, Date dataBaixa, Habitacio habitacio) {
        this.dataAlta = dataAlta;
        this.dataBaixa = dataBaixa;
        this.habitacio = habitacio;
    }

    public Date getDataAlta() {
        return dataAlta;
    }

    public void setDataAlta(Date dataAlta) {
        this.dataAlta = dataAlta;
    }

    public Date getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(Date dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    public Habitacio getHabitacio() {
        return habitacio;
    }

    public void setHabitacio(Habitacio habitacio) {
        this.habitacio = habitacio;
    }
    
    
    
}
