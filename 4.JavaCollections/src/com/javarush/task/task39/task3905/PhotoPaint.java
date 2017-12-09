package com.javarush.task.task39.task3905;

public class PhotoPaint {

    public boolean paintFill(Color[][] image, int r, int c, Color desiredColor) {
        if (r < 0 || c < 0 || image.length <= c || image[0].length <= r || image[c][r].equals(desiredColor))
            return false;
        else {
            paintFill2(image, r, c, desiredColor, image[c][r]);
            return true;
        }
    }

    public void paintFill2(Color[][] image, int r, int c, Color desiredColor, Color currentColor) {
        if (r < 0 || c < 0 || image.length <= c || image[0].length <= r || image[c][r].equals(desiredColor)) {
            return;
        }

        if (image[c][r] != currentColor) {
            return;
        }
        else {
            image[c][r] = desiredColor;
        }

        paintFill2(image, r - 1, c, desiredColor, currentColor);
        paintFill2(image, r + 1, c, desiredColor, currentColor);
        paintFill2(image, r, c - 1, desiredColor, currentColor);
        paintFill2(image, r, c + 1, desiredColor, currentColor);
    }
}
