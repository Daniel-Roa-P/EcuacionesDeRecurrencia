
package ecuacionesrecurrencia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class EcuacionesRecurrencia extends JFrame implements ActionListener{

    JLabel texto1 = new JLabel(" Digite el grado de la ecuacion: ");
    JLabel texto2 = new JLabel(" Ecuacion resultante: ");
    
    JTextField fieldGrado = new JTextField("3");
    JTextField [] coeficientes;
    JTextField [][] valoresIniciales;
    
    JButton ingreso = new JButton("Crear campos");
    JButton calcular = new JButton("Calcular Ecuacion");
    
    JScrollPane scrollPane = new JScrollPane();
    JScrollPane scrollPane1 = new JScrollPane();
    
    JScrollPane scrollPane2 = new JScrollPane();
    JScrollPane scrollPane3 = new JScrollPane();
    
    double [] coef, coefDerivada, raices;
    
    DecimalFormat formato = new DecimalFormat("####.######");
    
    public static void main(String[] args) {
        
        EcuacionesRecurrencia ecuacion = new EcuacionesRecurrencia();
        ecuacion.add(new JScrollPane(), BorderLayout.CENTER);
        ecuacion.setBounds(500, 0, 500, 505);
        ecuacion.setTitle("Ecueciones en recurrencia");
        ecuacion.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ecuacion.setVisible(true); 
        
        
    }
    
    EcuacionesRecurrencia(){
        
        Container c = getContentPane();
        c.setLayout(null);
        
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        
        c.add(texto1);
        c.add(texto2);
        
        c.add(fieldGrado);
        
        c.add(ingreso);
        c.add(calcular);
        
        c.add(scrollPane1);
        c.add(scrollPane3);
        
        texto1.setBounds(130, 0, 200, 20);
        texto2.setBounds(0, 415, 200, 20);
        
        fieldGrado.setBounds(310, 0, 20, 20);
        
        ingreso.setBounds(130, 30, 200, 20);
        ingreso.setBackground(Color.ORANGE);
        ingreso.addActionListener(this);
        
        calcular.setBounds(0, 380, 483, 20);
        calcular.setBackground(Color.GREEN);
        calcular.addActionListener(this);
        
        scrollPane.setBounds(0, 70, 460, 1000);
        scrollPane.setPreferredSize(new Dimension(460, 1000));  
        
        scrollPane1.setBounds(0, 70, 480, 300);
        scrollPane1.setPreferredSize(new Dimension(480, 300)); 
        
        scrollPane2.setBounds(130, 410, 500, 30);
        scrollPane2.setPreferredSize(new Dimension(500, 30));  
        
        scrollPane3.setBounds(130, 410, 350, 50);
        scrollPane3.setPreferredSize(new Dimension(350, 50));
        
    }

    double funcionOriginal(double n, double coef[]){
        
        double y = 0;
        
        for(int i = 0; i<coef.length;i++){
            
            y = y + (coef[i] * Math.pow(n, (coef.length-1-i)));
            
        }
        
        return y; 
    
    }
    
    double funcionDerivada(double n, double coefDerivada[]){
        
        double y = 0;
        
        for(int i = 0; i<coefDerivada.length;i++){
            
            double exponente = coefDerivada.length-2-i;
            
            if(exponente != -1){
                
                y = y + (coefDerivada[i] * Math.pow(n, exponente));
                                
            }
            
        }
        
        return y;
        
    }
    
    double newtonRapson(double n){
        
//        System.out.println( "n" + formato.format(n) );
//        System.out.println( "f orig" + funcionOriginal(n, coef) );
//        System.out.println( "f der" + funcionDerivada(n, coefDerivada));
        
        double y = n - (funcionOriginal(n, coef))/(funcionDerivada(n, coefDerivada));
        
        if( formato.format(y).equals(formato.format(n)) ){
        
            return y;
            
        } else {
            
          y = newtonRapson(y);  
            
        }
        
        return y;
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == ingreso){
            
            scrollPane.removeAll();
            
            int grados = Integer.parseInt(fieldGrado.getText());
            
            valoresIniciales = new JTextField [grados][2];
            coeficientes = new JTextField [grados + 1];
            
            for(int i = 0; i< grados + 2 ; i++){
                
                if(i == 0){
                
                    JLabel label = new JLabel("Si n es:");                    
                    label.setBounds(50, 0, 100, 30);
                    
                    JLabel label2 = new JLabel("Valor de fn");                    
                    label2.setBounds(200, 0, 100, 30);
                    
                    JLabel label3 = new JLabel("Coeficientes:");
                    label3.setBounds(350, 0, 100, 30);
                    
                    scrollPane.add(label);
                    scrollPane.add(label2);
                    scrollPane.add(label3);
                    
                    
                } else if(i < grados + 1){
                    
                    valoresIniciales[i-1][0] = new JTextField();
                    valoresIniciales[i-1][0].setBounds(50, 30*i, 100, 20);
                    
                    valoresIniciales[i-1][1] = new JTextField();
                    valoresIniciales[i-1][1].setBounds(200, 30*i, 100, 20);
                    
                    coeficientes[i-1] = new JTextField();
                    coeficientes[i-1].setBounds(350, 30*i, 100, 20);
                    
                    scrollPane.add(valoresIniciales[i-1][0]);
                    scrollPane.add(valoresIniciales[i-1][1]);
                    scrollPane.add(coeficientes[i-1]);
                    
                } else {
                    
                    coeficientes[i-1] = new JTextField();
                    coeficientes[i-1].setBounds(350, 30*i, 100, 20);
                    scrollPane.add(coeficientes[i-1]);
                    
                }
                 
                scrollPane.repaint();
                
            }

            scrollPane1.setViewportView(scrollPane);
            scrollPane3.setViewportView(scrollPane2);
            
        } else if (e.getSource() == calcular) {
        
            coef = new double[coeficientes.length];
            coefDerivada = new double[coeficientes.length];
            raices = new double[coeficientes.length];
            
            for(int i=0; i < coeficientes.length; i++){
            
                coef[i] = Double.parseDouble(coeficientes[i].getText());                
                coefDerivada[i] = coef[i]*(coeficientes.length-1-i);
                
            }
            
//            System.out.println("Original: ");
//            
//            for(int i=0; i < coeficientes.length; i++){
//                
//                System.out.print(coef[i] + ", ");
//
//                
//            }
//            
//            System.out.println("");
//            System.out.println("Derivada: ");
//            
//            for(int i=0; i < coeficientes.length; i++){
//            
//                System.out.print(coefDerivada[i] + ", ");
//                
//            }
            
            double extremoIzq = -100;
            double extremoDer = -99;
            
            int j=0;
            
            while( extremoDer < 100 ){
            
                double a = funcionOriginal(extremoIzq, coef);
                double b = funcionOriginal(extremoDer, coef);
                
                int signo = 1;
                
                if((a*b)<0){
                
                    System.out.println( "Hay raiz entre: " + extremoIzq + " y " + extremoDer  );
                    
                    if(Math.abs(a) < Math.abs(b)){
                    
                        System.out.println( "Es: " + formato.format( newtonRapson(extremoIzq)));
                        
                        
                    } else {
                        
                        System.out.println( "Es: " + formato.format( newtonRapson(extremoDer)));
                        
                    }
                    
                    
                    
                }
                
                extremoIzq = extremoDer;
                extremoDer = extremoDer + 0.5;
                
            }

        }
    
    }
    
}