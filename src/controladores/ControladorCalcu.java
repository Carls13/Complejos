package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.*;
import vista.Calculos;
import java.text.DecimalFormat;

public class ControladorCalcu implements ActionListener {

    private Calculos calcu;
    private Complejos z1;
    private Complejos z2;

    /**
     * Constructor para hacer operaciones con 2 complejos
     */
    public ControladorCalcu(Calculos calcu) {
        this.calcu = calcu;
        this.iniciarListener();
    }

    public void iniciar() {
        this.calcu.setTitle("Calculadora de complejos");
        this.calcu.setLocationRelativeTo(null);
        this.calcu.setResizable(false);
        //this.calcu.setIconImage(new Image(''));
    }

    /**
     * Añade los listeners de los eventos
     */
    public void iniciarListener() {
        this.calcu.dividir.addActionListener(this);
        this.calcu.multiplicar.addActionListener(this);
        this.calcu.sumar.addActionListener(this);
        this.calcu.restar.addActionListener(this);
        this.calcu.btnz2.addActionListener(this);
        this.calcu.restaurar2.addActionListener(this);
        this.calcu.restaurar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.calcu.sumar) {
            Complejos z1 = new Complejos(0, 0);
            Complejos z2 = new Complejos(0, 0);
            this.setFasorialValues(z1, this.calcu.c1Arg, this.calcu.c1Modulo);
            this.setFasorialValues(z2, this.calcu.c2Arg, this.calcu.c2Modulo);
            Complejos c3 = this.suma(z1, z2);
            this.imprime(c3);
        }
        if (e.getSource() == this.calcu.restar) {
            Complejos z1 = new Complejos(0, 0);
            Complejos z2 = new Complejos(0, 0);
            this.setFasorialValues(z1, this.calcu.c1Arg, this.calcu.c1Modulo);
            this.setFasorialValues(z2, this.calcu.c2Arg, this.calcu.c2Modulo);

            Complejos c3 = this.resta(z1, z2);
            this.imprime(c3);
        }
        if (e.getSource() == this.calcu.multiplicar) {
            Complejos z1 = new Complejos(0, 0);
            Complejos z2 = new Complejos(0, 0);
            this.setFasorialValues(z1, this.calcu.c1Arg, this.calcu.c1Modulo);
            this.setFasorialValues(z2, this.calcu.c2Arg, this.calcu.c2Modulo);
            Complejos c3 = this.producto(z1, z2);
            this.imprime(c3);
        }

        if (e.getSource() == this.calcu.dividir) {
            Complejos z1 = new Complejos(0, 0);
            Complejos z2 = new Complejos(0, 0);
            this.setFasorialValues(z1, this.calcu.c1Arg, this.calcu.c1Modulo);
            this.setFasorialValues(z2, this.calcu.c2Arg, this.calcu.c2Modulo);
            Complejos c3 = this.division(z1, z2);
            this.imprime(c3);
        }
        if (e.getSource() == this.calcu.restaurar) {
            limpiar(this.calcu.c1Arg,this.calcu.c1Modulo);
            limpiar(this.calcu.c2Arg,this.calcu.c2Modulo);
        }
        if (e.getSource() == this.calcu.restaurar2) {
          limpiar(this.calcu.pReal,this.calcu.pImag);

        }
        if (e.getSource() == this.calcu.btnz2) {
          Complejos z1 = new Complejos(0, 0);
         this.setBinomicaValues(z1, this.calcu.pReal, this.calcu.pImag);
         this.imprimeFasorial(z1, this.calcu.resTrans);

        }
    }

    public void limpiar(JTextField tf1, JTextField tf2) {
        tf1.setText("");
        tf2.setText("");
        
    }
     public void setBinomicaValues(Complejos z, JTextField jtfReal,
            JTextField jtfImg) {
        z.setpReal((float) Float.parseFloat(jtfReal.getText()));
        z.setpImaginaria((float) Float.parseFloat(jtfImg.getText()));
    }

    public void setFasorialValues(Complejos z, JTextField jtfArg,
            JTextField jtfMod) {
        z.setArgumento((float) Float.parseFloat(jtfArg.getText()));
        z.setModulo((float) Float.parseFloat(jtfMod.getText()));
    }

    public Complejos suma(Complejos c1, Complejos c2) {
        c1.aBinomica();
        c2.aBinomica();
        Complejos c3 = new Complejos(0, 0);
        c3.pReal = c1.pReal + c2.pReal;
        
        c3.pImaginaria = c1.pImaginaria + c2.pImaginaria;
        
        return c3;

    }

    public Complejos resta(Complejos c1, Complejos c2) {
        c1.aBinomica();
        c2.aBinomica();
        Complejos c3 = new Complejos(0, 0);
        c3.pReal = c1.pReal - c2.pReal;
        
        c3.pImaginaria = c1.pImaginaria - c2.pImaginaria;
        

//        aFasorial(c3);
        return c3;
    }

    public Complejos producto(Complejos c1, Complejos c2) {
        c1.aBinomica();
        c2.aBinomica();
        Complejos c3 = new Complejos(0, 0);
        c3.pReal = (c1.pReal * c2.pReal)
                - (c1.pImaginaria * c2.pImaginaria);
        c3.pImaginaria = (c1.pReal * c2.pImaginaria)
                + (c1.pImaginaria * c2.pReal);
        return c3;
    }

    public Complejos division(Complejos c1, Complejos c2) {
        c1.aBinomica();
        c2.aBinomica();
        Complejos c3 = new Complejos(0, 0);
        c3.pReal = (float) (((c1.pReal * c2.pReal)
                + (c1.pImaginaria * c2.pImaginaria))
                / (Math.pow(c2.pReal, 2) + Math.pow(c2.pImaginaria, 2)));
        c3.pImaginaria = (float) ((-(c1.pReal * c2.pImaginaria)
                + (c1.pImaginaria * c2.pReal))
                / (Math.pow(c2.pReal, 2) + Math.pow(c2.pImaginaria, 2)));
        return c3;
    }

    public void imprime(Complejos z) {
        this.imprimeFasorial(z, this.calcu.resFas);
        this.imprimeBinomica(z, this.calcu.resBin);
    }

    public void imprimeFasorial(Complejos z, JLabel fas) {
        z.aFasorial();
        DecimalFormat df = new DecimalFormat("#,###.##");
        fas.setText(""
                + df.format(z.modulo)
                + "|" + df.format(z.argumento) + "°");
    }

    public void imprimeBinomica(Complejos z, JLabel bin) {
        
        DecimalFormat df = new DecimalFormat("#,###.##");
        bin.setText("" + df.format(z.pReal)
                + " + j(" + df.format(z.pImaginaria) + ")");
    }

}
