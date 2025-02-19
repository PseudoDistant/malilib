package fi.dy.masa.malilib.gui.icon;

import fi.dy.masa.malilib.render.RenderUtils;
import fi.dy.masa.malilib.render.ShapeRenderUtils;

public interface MultiIcon extends Icon
{
    /**
     * Get the U coordinate for the given icon variant
     */
    int getVariantU(int variantIndex);

    /**
     * Get the V coordinate for the given icon variant
     */
    int getVariantV(int variantIndex);

    /**
     * Returns the icon variant index to use for the given status of the icon.
     * By default a disabled icon is at index 0, an enabled, non-hovered icon
     * is at index 1 and an enabled, hovered icon is at index 2.
     * Thus the hover status has no effect for disabled icons.
     */
    default int getVariantIndex(boolean enabled, boolean hovered)
    {
        if (enabled == false)
        {
            return 0;
        }

        return hovered ? 2 : 1;
    }

    /**
     * Renders the icon at the given location, using an icon variant chosen
     * by the given enabled and hover status.
     */
    @Override
    default void renderAt(int x, int y, float z, boolean enabled, boolean hovered)
    {
        int variantIndex = this.getVariantIndex(enabled, hovered);
        this.renderAt(x, y, z, variantIndex);
    }

    /**
     * Renders a possibly scaled/stretched version of this icon at the given location,
     * using an icon variant chosen by the given enabled and hover status.
     */
    @Override
    default void renderScaledAt(int x, int y, float z, int renderWidth, int renderHeight,
                                boolean enabled, boolean hovered)
    {
        int variantIndex = this.getVariantIndex(enabled, hovered);
        this.renderScaledAt(x, y, z, variantIndex, renderWidth, renderHeight);
    }

    /**
     * Renders the icon at the given location, using the given icon variant index.
     * The variant index is basically an offset from the base UV location.
     * The implementation can define where and how the position is offset
     * from the base location.
     */
    default void renderAt(int x, int y, float z, int variantIndex)
    {
        this.renderScaledAt(x, y, z, variantIndex, this.getWidth(), this.getHeight());
    }

    /**
     * Renders a possibly scaled/stretched version of this icon, with the given
     * rendered width and height, using the given icon variant index.
     * The variant index is basically an offset from the base UV location.
     * The implementation can define where and how the position is offset
     * from the base location.
     */
    default void renderScaledAt(int x, int y, float z, int variantIndex, int renderWidth, int renderHeight)
    {
        int width = this.getWidth();
        int height = this.getHeight();

        if (width == 0 || height == 0)
        {
            return;
        }

        int u = this.getVariantU(variantIndex);
        int v = this.getVariantV(variantIndex);
        float pw = this.getTexturePixelWidth();
        float ph = this.getTexturePixelHeight();

        RenderUtils.color(1f, 1f, 1f, 1f);
        RenderUtils.bindTexture(this.getTexture());
        RenderUtils.setupBlend();

        ShapeRenderUtils.renderScaledTexturedRectangle(x, y, z, u, v, renderWidth, renderHeight,
                                                       width, height, pw, ph);
    }

    /**
     * Renders a composite (smaller) icon by using a rectangular area
     * of each of the 4 corners of the texture. The width and height
     * arguments define what size texture is going to be rendered.
     * @param width the width of the icon to render
     * @param height the height of the icon to render
     */
    default void renderFourSplicedAt(int x, int y, float z, int width, int height, boolean enabled, boolean hovered)
    {
        int variantIndex = this.getVariantIndex(enabled, hovered);
        int u = this.getVariantU(variantIndex);
        int v = this.getVariantV(variantIndex);

        this.renderFourSplicedAt(x, y, z, u, v, width, height);
    }
}
