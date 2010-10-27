package org.arti01.abstrakt;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;

public class ResizeJpg {

   Logger logger = Logger.getLogger(ResizeJpg.class);


   public void zrob(int var1, int var2, String var3, String var4) {
	   int dlugosc=0;
	   int wysokosc=0;
      try {
         BufferedImage var5 = ImageIO.read(new File(var3));
         BufferedImage var10 = new BufferedImage(var1, var2, 1);
         Graphics2D var11 = var10.createGraphics();
         
         if(var5.getWidth()/var5.getHeight()>var1/var2) {
            dlugosc =var1;
            wysokosc=var5.getHeight()*dlugosc/var5.getWidth();
            //logger.info("szerokie"+dlugosc+"--"  + var5.getWidth()+"--" + wysokosc+"--"+var5.getHeight());
            AffineTransform var13=AffineTransform.getTranslateInstance(0, (var2-wysokosc)/2);
            var11.setTransform(var13);
         } else {
        	wysokosc=var2;
            dlugosc = var5.getWidth()*wysokosc/var5.getHeight();
            //logger.info("waskie"+dlugosc+"--"  + var5.getWidth()+"--" + wysokosc+"--"+var5.getHeight());
            AffineTransform var14=AffineTransform.getTranslateInstance((var1-dlugosc)/2,0);
            var11.setTransform(var14);
         }
 
         AffineTransform var12=AffineTransform.getScaleInstance((double)dlugosc/(double)var5.getWidth(),(double)dlugosc/(double)var5.getWidth());
         var11.drawRenderedImage(var5, var12);
         ImageIO.write(var10, "JPG", new File(var4));
         var11.dispose();
      } catch (Throwable var13) {
         this.logger.error("Wystapil blad przy skalowaniu obrazka. Plik wejsciowy: " + var3 + ", plik docelowy: " + var4 + ", skala: " + var1 + ":" + var2 + ". Przyczyna bledu (wyjatek) dolaczona", var13);
      }

   }

   public static void main(String[] var0) {
      (new ResizeJpg()).zrob(240, 160, "8681.jpg", "result2.jpg");
   }
}
