/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colortest;
import java.awt.*;
import java.applet.Applet;

/**
 *
 * @author DARLINGTON
 */
public class Colortest extends java.applet.Applet {
    Canvas swatch = new Canvas();
 ColorControls RGBcontrols, HSBcontrols;
  public void init(){
  Color theColor = new Color(0,0,0);
   float HSB[] = Color.RGBtoHSB(theColor.getRed(), theColor.getGreen(), theColor.getBlue(), new float[3]);
   setLayout(new GridLayout(1,3,10,10));
   //setting swatch
   swatch.setBackground(theColor);
   // the controlpanels
   
   //rgb panel
   RGBcontrols= new ColorControls(this, "Red", "Green","Blue", theColor.getRed(),theColor.getGreen(), theColor.getBlue());
   //HSB panel
   HSBcontrols= new ColorControls(this,"HUE","SATURATION","BRIGHTNESS", (int)(HSB[0]*360), (int)(HSB[1]*100),(int)(HSB[2]*100));
   
   add(swatch);
   add(RGBcontrols);
   add(HSBcontrols);
  }
 
   public Insets insets(){
       return new Insets(10,10,10,10);
   }
    void update(ColorControls in){
        String V1 = in.f1.getText();
        String V2 = in.f2.getText();
        String V3 = in.f3.getText();
        Color c;
        
      if (in== RGBcontrols){
          c = new Color(Integer.parseInt(V1),Integer.parseInt(V2),Integer.parseInt(V3) );
          swatch.setBackground(c);
          
          float [] HSB = Color.RGBtoHSB(c.getRed(),c.getGreen(),c.getBlue(),new float[3]);
          
          HSB[0]= HSB[0]*360;
          HSB[1]*= 100;
          HSB[2]*=100;
          
          HSBcontrols.f1.setText(String.valueOf((int)(HSB[0])));
          HSBcontrols.f2.setText(String.valueOf((int)(HSB[1])));
          HSBcontrols.f3.setText(String.valueOf((int)(HSB[2])));
      }
          else{
               int f1= Integer.parseInt(V1);
               int f2= Integer.parseInt(V2);
               int f3= Integer.parseInt(V3);
               
               c = Color.getHSBColor((float)(f1/360), (float)(f2/100),(float)(f3/100) );
               swatch.setBackground(c);
               RGBcontrols.f1.setText(String.valueOf(c.getRed()));
               RGBcontrols.f2.setText(String.valueOf(c.getGreen()));
               RGBcontrols.f3.setText(String.valueOf(c.getBlue()));
               
                  }
           
          
          
    }
        
  public static void main(String[] args){
        Frame FR = new Frame("Colortest-DARLINGTON'S FIRST");
       Colortest MAC = new Colortest();
        MAC.init();
        
       FR.add( MAC);
       FR.resize(300,300);
       FR.show();
        
  }
   }




class ColorControls extends Panel{
TextField f1, f2, f3;
Colortest outparent;

ColorControls(Colortest target,String l1, String l2, String l3, int v1, int v2, int v3){

    this.outparent = target;
    setLayout(new GridLayout(3,4,10,10));
    
    f1 = new TextField(String.valueOf(v1),4);
    f2 = new TextField(String.valueOf(v2), 4);
    f3 = new TextField(String.valueOf(v3), 4);
    
    add(new Label(l1, Label.RIGHT) );
    add(f1);
    add(new Label(l2, Label.RIGHT));
    add(f2);
    add(new Label (l3, Label.RIGHT));
    add(f3);
}
@Override
public Insets insets(){
return new Insets(10,10,10,10);
        }
 
@Override
    public boolean action(Event evt, Object arg){
        if(evt.target instanceof TextField){
            this.outparent.update(this);
            return true;
        }
          else
            return false;
    }
}
            
    
    
  
    
    
  

    
    
    
    
   