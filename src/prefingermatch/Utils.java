/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prefingermatch;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author USUARIO
 */
public class Utils {

    public static final int MATRIZ_CONVU_SOBEL_X[][] = new int[][]{{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
    public static final int MATRIZ_CONVU_SOBEL_Y[][] = new int[][]{{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}};
    
    //Calcular angulo
    public static double angle (BufferedImage imagen, int i, int j) {
        double phi = 0;
        double sum = 0;
        double num = 0;
        double dem = 0;
        for (int x = (i-3); x < (i+3); x++) {
            for (int y = (j-3); y < (j+3); y++) {
                int c00 = new Color(imagen.getRGB(x - 1, y - 1)).getRGB() == Color.BLACK.getRGB() ? 0 : 1;
                int c01 = new Color(imagen.getRGB(x, y - 1)).getRGB() == Color.BLACK.getRGB() ? 0 : 1;
                int c02 = new Color(imagen.getRGB(x + 1, y - 1)).getRGB() == Color.BLACK.getRGB() ? 0 : 1;
                int c10 = new Color(imagen.getRGB(x - 1, y)).getRGB() == Color.BLACK.getRGB() ? 0 : 1;
                int c11 = new Color(imagen.getRGB(x, y)).getRGB() == Color.BLACK.getRGB() ? 0 : 1;
                int c12 = new Color(imagen.getRGB(x + 1, y)).getRGB() == Color.BLACK.getRGB() ? 0 : 1;
                int c20 = new Color(imagen.getRGB(x - 1, y + 1)).getRGB() == Color.BLACK.getRGB() ? 0 : 1;
                int c21 = new Color(imagen.getRGB(x, y + 1)).getRGB() == Color.BLACK.getRGB() ? 0 : 1;
                int c22 = new Color(imagen.getRGB(x + 1, y + 1)).getRGB() == Color.BLACK.getRGB() ? 0 : 1;
                
                int Gx = (c00 * MATRIZ_CONVU_SOBEL_X[0][0]) + (c01 * MATRIZ_CONVU_SOBEL_X[0][1]) + (c02 * MATRIZ_CONVU_SOBEL_X[0][2])
                        + (c10 * MATRIZ_CONVU_SOBEL_X[1][0]) + (c11 * MATRIZ_CONVU_SOBEL_X[1][1]) + (c12 * MATRIZ_CONVU_SOBEL_X[1][2])
                        + (c20 * MATRIZ_CONVU_SOBEL_X[2][0]) + (c21 * MATRIZ_CONVU_SOBEL_X[2][1]) + (c22 * MATRIZ_CONVU_SOBEL_X[2][2]);
                int Gy = (c00 * MATRIZ_CONVU_SOBEL_Y[0][0]) + (c01 * MATRIZ_CONVU_SOBEL_Y[0][1]) + (c02 * MATRIZ_CONVU_SOBEL_Y[0][2])
                        + (c10 * MATRIZ_CONVU_SOBEL_Y[1][0]) + (c11 * MATRIZ_CONVU_SOBEL_Y[1][1]) + (c12 * MATRIZ_CONVU_SOBEL_Y[1][2])
                        + (c20 * MATRIZ_CONVU_SOBEL_Y[2][0]) + (c21 * MATRIZ_CONVU_SOBEL_Y[2][1]) + (c22 * MATRIZ_CONVU_SOBEL_Y[2][2]);
                
                num += 2 * Gx * Gy;
                dem += (Gx * Gx) - (Gy * Gy);
                //sum += (num/dem);
            }
        }
        //System.out.println("num " + num);
        //System.out.println("dem " + dem);
        phi = 0.5 * Math.atan(num/dem);
        double k;
        if ((phi < 0 && num < 0) || (phi >= 0 && num > 0)) {
            k = 0.5;
        } else if (phi < 0 && num >= 0) {
            k = 1;
        } else /*if (phi >= 0 && num <= 0)*/ {
            k = 0;
        }
        System.out.println(Math.toDegrees(phi + (k*Math.PI)));
        //System.out.println((phi + (k*Math.PI)));
        return Math.toDegrees(phi + (k*Math.PI));
        
    }

    
}
