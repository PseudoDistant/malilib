package fi.dy.masa.malilib.gui.widget;

import fi.dy.masa.malilib.gui.icon.MultiIcon;
import fi.dy.masa.malilib.gui.util.ScreenContext;

public class InfoIconWidget extends HoverInfoWidget
{
    protected final MultiIcon icon;

    public InfoIconWidget(MultiIcon icon, String key, Object... args)
    {
        super(icon.getWidth(), icon.getHeight(), key, args);

        this.icon = icon;
    }

    @Override
    public void renderAt(int x, int y, float z, ScreenContext ctx)
    {
        this.icon.renderAt(x, y, z + 0.1f, true, this.isHoveredForRender(ctx));
    }
}
