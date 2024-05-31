package src;

import java.util.List;

class Rectangle {
    String name;
    int width;
    int height;

    boolean root=false;

    public Rectangle(int width, int height, String  name) {
        this.width = width;
        this.height = height;
        this.name = name;
    }

    Rectangle(String name, int width, int height,boolean root) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.root=root;
    }
    public static void generateCombinations(List<Rectangle> combinations, List<Rectangle> currentCombination, int index, int[] maxRectangles) {
        if (index == combinations.size()) {
            if (RectangleStitcher.canFormRectangle(currentCombination)) {
                maxRectangles[0] ++;
            }
            return;
        }

        generateCombinations(combinations, currentCombination, index + 1, maxRectangles);
        currentCombination.add(combinations.get(index));

        generateCombinations(combinations, currentCombination, index + 1, maxRectangles);
        currentCombination.remove(currentCombination.size() - 1);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "name='" + name + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}