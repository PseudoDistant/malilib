package fi.dy.masa.malilib;

import fi.dy.masa.malilib.action.Action;
import fi.dy.masa.malilib.action.ActionUtils;
import fi.dy.masa.malilib.action.NamedAction;
import fi.dy.masa.malilib.action.NamedParameterizedAction;
import fi.dy.masa.malilib.action.ParameterizedAction;
import fi.dy.masa.malilib.config.util.ConfigUtils;
import fi.dy.masa.malilib.gui.action.ActionPromptScreen;
import fi.dy.masa.malilib.gui.action.ActionWidgetScreen;
import fi.dy.masa.malilib.gui.config.CustomIconListScreen;
import fi.dy.masa.malilib.input.callback.AdjustableValueHotkeyCallback;
import fi.dy.masa.malilib.listener.EventListener;
import fi.dy.masa.malilib.overlay.message.MessageUtils;

public class MaLiLibActions
{
    public static final NamedAction OPEN_ACTION_PROMPT_SCREEN       = register("openActionPromptScreen", ActionPromptScreen::openActionPromptScreen);
    public static final NamedAction OPEN_CONFIG_SCREEN              = register("openConfigScreen", MaLiLibConfigScreen::open);
    public static final NamedAction SCROLL_VALUE_ADJUST_DECREASE    = register("scrollValueAdjustDecrease", (ctx) -> AdjustableValueHotkeyCallback.onScrollAdjust(-1));
    public static final NamedAction SCROLL_VALUE_ADJUST_INCREASE    = register("scrollValueAdjustIncrease", (ctx) -> AdjustableValueHotkeyCallback.onScrollAdjust(1));

    public static void init()
    {
        register("addMessage", MessageUtils::addMessageAction);
        register("addToast", MessageUtils::addToastAction);
        register("createActionWidgetScreen", ActionWidgetScreen::openCreateActionWidgetScreen);
        register("openActionWidgetScreen", ActionWidgetScreen::openActionWidgetScreen);
        register("openCustomIconsListScreen", CustomIconListScreen::openCustomIconListScreenAction);
        register("openPreviousActionWidgetScreen", ActionWidgetScreen::openPreviousActionWidgetScreen);
        register("runCommand", ActionUtils::runVanillaCommand);
        register("switchConfigProfile", ConfigUtils::switchConfigProfile);
    }

    private static NamedAction register(String name, EventListener action)
    {
        return NamedAction.register(MaLiLibReference.MOD_INFO, name, action);
    }

    private static NamedAction register(String name, Action action)
    {
        return NamedAction.register(MaLiLibReference.MOD_INFO, name, action);
    }

    private static NamedAction register(String name, ParameterizedAction action)
    {
        return NamedParameterizedAction.register(MaLiLibReference.MOD_INFO, name, action);
    }
}
