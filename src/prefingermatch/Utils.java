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
        double angle = 0;
        double sum = 0;
        for (int x = (i-3); x < (i+3); x++) {
            for (int y = (j-3); y < (j+3); y++) {
                Color c00 = new Color(imagen.getRGB(x - 1, y - 1));
                Color c01 = new Color(imagen.getRGB(x, y - 1));
                Color c02 = new Color(imagen.getRGB(x + 1, y - 1));
                Color c10 = new Color(imagen.getRGB(x - 1, y));
                Color c11 = new Color(imagen.getRGB(x, y));
                Color c12 = new Color(imagen.getRGB(x + 1, y));
                Color c20 = new Color(imagen.getRGB(x - 1, y + 1));
                Color c21 = new Color(imagen.getRGB(x, y + 1));
                Color c22 = new Color(imagen.getRGB(x + 1, y + 1));
                int Gx = (c00.getRed() * MATRIZ_CONVU_SOBEL_X[0][0]) + (c01.getRed() * MATRIZ_CONVU_SOBEL_X[0][1]) + (c02.getRed() * MATRIZ_CONVU_SOBEL_X[0][2])
                        + (c10.getRed() * MATRIZ_CONVU_SOBEL_X[1][0]) + (c11.getRed() * MATRIZ_CONVU_SOBEL_X[1][1]) + (c12.getRed() * MATRIZ_CONVU_SOBEL_X[1][2])
                        + (c20.getRed() * MATRIZ_CONVU_SOBEL_X[2][0]) + (c21.getRed() * MATRIZ_CONVU_SOBEL_X[2][1]) + (c22.getRed() * MATRIZ_CONVU_SOBEL_X[2][2]);
                int Gy = (c00.getRed() * MATRIZ_CONVU_SOBEL_Y[0][0]) + (c01.getRed() * MATRIZ_CONVU_SOBEL_Y[0][1]) + (c02.getRed() * MATRIZ_CONVU_SOBEL_Y[0][2])
                        + (c10.getRed() * MATRIZ_CONVU_SOBEL_Y[1][0]) + (c11.getRed() * MATRIZ_CONVU_SOBEL_Y[1][1]) + (c12.getRed() * MATRIZ_CONVU_SOBEL_Y[1][2])
                        + (c20.getRed() * MATRIZ_CONVU_SOBEL_Y[2][0]) + (c21.getRed() * MATRIZ_CONVU_SOBEL_Y[2][1]) + (c22.getRed() * MATRIZ_CONVU_SOBEL_Y[2][2]);
                double num = 2 * Gx * Gy;
                double dem = (Gx * Gx) - (Gy * Gy);
                sum += (num/dem);
            }
        }
        
        angle = 0.5 * Math.atan(sum);
        return angle;
        
    }

    
}
