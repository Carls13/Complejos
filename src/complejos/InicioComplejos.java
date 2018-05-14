

package complejos;

import vista.*;
import controladores.*;

public class InicioComplejos {

    
    public static void main(String[] args) {
     
        Calculos calcu = new Calculos();        
        ControladorCalcu ctrl = new ControladorCalcu(calcu);
        ctrl.iniciar();
        calcu.setVisible(true);
        
    }

}
