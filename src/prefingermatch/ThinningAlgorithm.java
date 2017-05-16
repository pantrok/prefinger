/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prefingermatch;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class ThinningAlgorithm {

    private final int[][] nbrs = {{0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1},{-1, 1}, {-1, 0}, {-1, -1}, {0, -1}};
    private final int[][][] nbrGroups = {{{0, 2, 4}, {2, 4, 6}}, {{0, 2, 6},{0, 4, 6}}};
    private List<Point> toWhite = new ArrayList<>();
    private BufferedImage imagen;

    public ThinningAlgorithm(BufferedImage imagen) {
        this.imagen = imagen;
    }

    public BufferedImage thinImage() {
        boolean firstStep = false;
        boolean hasChanged;

        do {
            hasChanged = false;
            firstStep = !firstStep;

            for (int x = 1; x < imagen.getWidth() - 1; x++) {
                for (int y = 1; y < imagen.getHeight() - 1; y++) {
                    Color color = new Color(imagen.getRGB(x, y));
                    if (color.getRGB() != Color.WHITE.getRGB()) {
                        continue;
                    }

                    int nn = numNeighbors(x, y);
                    if (nn < 2 || nn > 6) {
                        continue;
                    }

                    if (numTransitions(x, y) != 1) {
                        continue;
                    }

                    if (!atLeastOneIsWhite(x, y, firstStep ? 0 : 1)) {
                        continue;
                    }

                    toWhite.add(new Point(y, x));
                    hasChanged = true;
                }
            }

            for (Point p : toWhite) {
                imagen.setRGB(p.y, p.x, Color.WHITE.getRGB());
            }
            toWhite.clear();

        } while (firstStep || hasChanged);

        return imagen;

    }

    private int numNeighbors(int x, int y) {
        int count = 0;
        for (int i = 0; i < nbrs.length - 1; i++) {
            if (imagen.getRGB(x + nbrs[i][1], y + nbrs[i][0]) == Color.BLACK.getRGB()) {
                count++;
            }
        }
        return count;
    }

    private int numTransitions(int x, int y) {
        int count = 0;
        for (int i = 0; i < nbrs.length - 1; i++) {
            if (imagen.getRGB(x + nbrs[i][1], y + nbrs[i][0]) == Color.WHITE.getRGB()) {
                if (imagen.getRGB(x + nbrs[i + 1][1], y + nbrs[i + 1][0]) == Color.BLACK.getRGB()) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean atLeastOneIsWhite(int x, int y, int step) {
        int count = 0;
        int[][] group = nbrGroups[step];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < group[i].length; j++) {
                int[] nbr = nbrs[group[i][j]];
                if (imagen.getRGB(x + nbr[1], y + nbr[0]) == Color.WHITE.getRGB()) {
                    count++;
                    break;
                }
            }
        }
        return count > 1;
    }

}
