package src;

import java.util.ArrayList;
import java.util.List;

public class RectangleStitcher {
    public static boolean canFormRectangle(List<Rectangle> rectangles) {
        if (rectangles.size()==1)
            return false;
        if (rectangles == null || rectangles.size() == 0) {
            return false;
        }

        int totalArea = 0;
        for (Rectangle rect : rectangles) {
            totalArea += rect.width * rect.height;
        }

        for (int width = 1; width <= totalArea; width++) {
            if (totalArea % width == 0) {
                int height = totalArea / width;
                boolean[][] used = new boolean[width][height];
                if (backtrack(rectangles, width, height, 0, 0, new ArrayList<>(rectangles), used)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean backtrack(List<Rectangle> rectangles, int targetWidth, int targetHeight, int startX, int startY, List<Rectangle> remainingRectangles, boolean[][] used) {
        if (remainingRectangles.isEmpty()) {
            return true;
        }

        if (startX == targetWidth) {
            startX = 0;
            startY++;
            if (startY == targetHeight) {
                return false;
            }
        }

        if (used[startX][startY]) {
            return backtrack(rectangles, targetWidth, targetHeight, startX + 1, startY, remainingRectangles, used);
        }

        for (Rectangle rect : new ArrayList<>(remainingRectangles)) {
            if (canPlaceRectangle(rect, targetWidth, targetHeight, startX, startY, used)) {
                placeRectangle(rect, startX, startY, used, true);
                remainingRectangles.remove(rect);
                if (backtrack(rectangles, targetWidth, targetHeight, startX + rect.width, startY, remainingRectangles, used)) {
                    return true;
                }
                remainingRectangles.add(rect);
                placeRectangle(rect, startX, startY, used, false);
            }
            if (canPlaceRectangle(new Rectangle(rect.height, rect.width, rect.name), targetWidth, targetHeight, startX, startY, used)) {
                placeRectangle(new Rectangle(rect.height, rect.width, rect.name), startX, startY, used, true);
                remainingRectangles.remove(rect);
                if (backtrack(rectangles, targetWidth, targetHeight, startX + rect.height, startY, remainingRectangles, used)) {
                    return true;
                }
                remainingRectangles.add(rect);
                placeRectangle(new Rectangle(rect.height, rect.width, rect.name), startX, startY, used, false);
            }
        }

        return false;
    }

    private static boolean canPlaceRectangle(Rectangle rect, int targetWidth, int targetHeight, int startX, int startY, boolean[][] used) {
        if (startX + rect.width > targetWidth || startY + rect.height > targetHeight) {
            return false;
        }

        for (int i = startX; i < startX + rect.width; i++) {
            for (int j = startY; j < startY + rect.height; j++) {
                if (used[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void placeRectangle(Rectangle rect, int startX, int startY, boolean[][] used, boolean set) {
        for (int i = startX; i < startX + rect.width; i++) {
            for (int j = startY; j < startY + rect.height; j++) {
                used[i][j] = set;
            }
        }
    }
}