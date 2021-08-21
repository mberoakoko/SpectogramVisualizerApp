package org.specVisualizer.ViewModel;

public enum WindowDimensions {
    FULLSCREEN(1080, 720), MEDIUM(100, 100), SMALL(100, 100);
    int width ,  height;

    WindowDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
