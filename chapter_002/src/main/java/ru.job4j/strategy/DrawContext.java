package ru.job4j.strategy;

/**
 * Created by ephemeralin on 04.06.17.
 */
public class DrawContext {

    /**
     * Draw strategy.
     */
    private DrawStrategy drawStrategy;

    /**
     * Instantiates a new Draw context.
     *
     * @param drawStrategy the draw strategy
     */
    public DrawContext(DrawStrategy drawStrategy) {
        this.drawStrategy = drawStrategy;
    }

    /**
     * Sets draw strategy.
     *
     * @param drawStrategy the draw strategy
     */
    public void setDrawStrategy(DrawStrategy drawStrategy) {
        this.drawStrategy = drawStrategy;
    }

    /**
     * Gets picture.
     *
     * @return the picture
     */
    public String getPicture() {
        return drawStrategy.pic();
    }
}
