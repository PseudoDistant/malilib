package fi.dy.masa.malilib.input;

import com.google.gson.JsonObject;
import fi.dy.masa.malilib.config.value.BaseOptionListConfigValue;
import fi.dy.masa.malilib.overlay.message.MessageType;
import fi.dy.masa.malilib.util.JsonUtils;

public class KeyBindSettings
{
    public static final KeyBindSettings INGAME_DEFAULT              = new KeyBindSettings(Context.INGAME, KeyAction.PRESS, true, true, false, CancelCondition.ALWAYS);
    public static final KeyBindSettings INGAME_SUCCESS              = new KeyBindSettings(Context.INGAME, KeyAction.PRESS, true, true, false, CancelCondition.ON_SUCCESS);
    public static final KeyBindSettings INGAME_BOTH                 = new KeyBindSettings(Context.INGAME, KeyAction.BOTH, true, true, false, CancelCondition.ALWAYS);
    public static final KeyBindSettings INGAME_MODIFIER             = new KeyBindSettings(Context.INGAME, KeyAction.PRESS, true, false, false, CancelCondition.NEVER);
    public static final KeyBindSettings INGAME_MODIFIER_EMPTY       = new KeyBindSettings(Context.INGAME, KeyAction.PRESS, true, false, false, CancelCondition.NEVER, true);
    public static final KeyBindSettings INGAME_MODIFIER_BOTH        = new KeyBindSettings(Context.INGAME, KeyAction.BOTH, true, false, false, CancelCondition.NEVER);
    public static final KeyBindSettings INGAME_RELEASE              = new KeyBindSettings(Context.INGAME, KeyAction.RELEASE, true, true, false, CancelCondition.NEVER);
    public static final KeyBindSettings INGAME_RELEASE_EXCLUSIVE    = new KeyBindSettings(Context.INGAME, KeyAction.RELEASE, true, true, true, CancelCondition.NEVER);
    public static final KeyBindSettings GUI_DEFAULT                 = new KeyBindSettings(Context.GUI, KeyAction.PRESS, true, true, false, CancelCondition.ALWAYS);
    public static final KeyBindSettings GUI_MODIFIER                = new KeyBindSettings(Context.GUI, KeyAction.PRESS, true, false, false, CancelCondition.NEVER);

    protected final KeyAction activateOn;
    protected final Context context;
    protected final CancelCondition cancel;
    protected final MessageType messageType;
    protected final boolean allowEmpty;
    protected final boolean allowExtraKeys;
    protected final boolean exclusive;
    protected final boolean firstOnly;
    protected final boolean orderSensitive;
    protected final boolean showToast;
    protected final int priority;

    protected KeyBindSettings(Context context, KeyAction activateOn, boolean allowExtraKeys, boolean orderSensitive,
                            boolean exclusive, CancelCondition cancel)
    {
        this(context, activateOn, allowExtraKeys, orderSensitive, exclusive, cancel, false);
    }

    protected KeyBindSettings(Context context, KeyAction activateOn, boolean allowExtraKeys, boolean orderSensitive,
                            boolean exclusive, CancelCondition cancel, boolean allowEmpty)
    {
        this(context, activateOn, allowExtraKeys, orderSensitive, exclusive, cancel, allowEmpty, 50, false);
    }

    protected KeyBindSettings(Context context, KeyAction activateOn, boolean allowExtraKeys, boolean orderSensitive,
                            boolean exclusive, CancelCondition cancel, boolean allowEmpty, int priority, boolean firstOnly)
    {
        this(context, activateOn, allowExtraKeys, orderSensitive, exclusive, cancel, allowEmpty, priority, firstOnly, true);
    }

    protected KeyBindSettings(Context context, KeyAction activateOn, boolean allowExtraKeys, boolean orderSensitive,
                              boolean exclusive, CancelCondition cancel, boolean allowEmpty, int priority,
                              boolean firstOnly, boolean showToast)
    {
        this(context, activateOn, allowExtraKeys, orderSensitive, exclusive, cancel, allowEmpty,
             priority, firstOnly, showToast, MessageType.CUSTOM_HOTBAR);
    }

    protected KeyBindSettings(Context context, KeyAction activateOn, boolean allowExtraKeys, boolean orderSensitive,
                              boolean exclusive, CancelCondition cancel, boolean allowEmpty, int priority,
                              boolean firstOnly, boolean showToast, MessageType messageType)
    {
        this.context = context;
        this.activateOn = activateOn;
        this.allowExtraKeys = allowExtraKeys;
        this.orderSensitive = orderSensitive;
        this.exclusive = exclusive;
        this.cancel = cancel;
        this.allowEmpty = allowEmpty;
        this.priority = priority;
        this.firstOnly = firstOnly;
        this.showToast = showToast;
        this.messageType = messageType;
    }

    public static KeyBindSettings create(Context context, KeyAction activateOn, boolean allowExtraKeys,
                                         boolean orderSensitive, boolean exclusive, CancelCondition cancel)
    {
        return create(context, activateOn, allowExtraKeys, orderSensitive, exclusive, cancel, false);
    }

    public static KeyBindSettings create(Context context, KeyAction activateOn, boolean allowExtraKeys,
                                         boolean orderSensitive, boolean exclusive, CancelCondition cancel, boolean allowEmpty)
    {
        return new KeyBindSettings(context, activateOn, allowExtraKeys, orderSensitive, exclusive, cancel, allowEmpty);
    }

    public static KeyBindSettings create(Context context, KeyAction activateOn, boolean allowExtraKeys,
                                         boolean orderSensitive, boolean exclusive, CancelCondition cancel,
                                         boolean allowEmpty, int priority, boolean firstOnly)
    {
        return new KeyBindSettings(context, activateOn, allowExtraKeys, orderSensitive, exclusive, cancel,
                                   allowEmpty, priority, firstOnly);
    }

    public static KeyBindSettings create(Context context, KeyAction activateOn, boolean allowExtraKeys,
                                         boolean orderSensitive, boolean exclusive, CancelCondition cancel,
                                         boolean allowEmpty, int priority, boolean firstOnly, boolean showToast)
    {
        return new KeyBindSettings(context, activateOn, allowExtraKeys, orderSensitive, exclusive, cancel,
                                   allowEmpty, priority, firstOnly, showToast);
    }

    public static KeyBindSettings create(Context context, KeyAction activateOn, boolean allowExtraKeys,
                                         boolean orderSensitive, boolean exclusive, CancelCondition cancel,
                                         boolean allowEmpty, int priority, boolean firstOnly,
                                         boolean showToast, MessageType messageType)
    {
        return new KeyBindSettings(context, activateOn, allowExtraKeys, orderSensitive, exclusive, cancel,
                                   allowEmpty, priority, firstOnly, showToast, messageType);
    }

    public Context getContext()
    {
        return this.context;
    }

    public KeyAction getActivateOn()
    {
        return this.activateOn;
    }

    public CancelCondition getCancelCondition()
    {
        return this.cancel;
    }

    public boolean getAllowEmpty()
    {
        return this.allowEmpty;
    }

    public boolean getAllowExtraKeys()
    {
        return this.allowExtraKeys;
    }

    public boolean isOrderSensitive()
    {
        return this.orderSensitive;
    }

    public boolean isExclusive()
    {
        return this.exclusive;
    }

    public boolean getFirstOnly()
    {
        return this.firstOnly;
    }

    public int getPriority()
    {
        return this.priority;
    }

    public boolean getShowToast()
    {
        return this.showToast;
    }

    public MessageType getMessageType()
    {
        return this.messageType;
    }

    public JsonObject toJson()
    {
        JsonObject obj = new JsonObject();

        obj.addProperty("activate_on", this.activateOn.getName());
        obj.addProperty("allow_empty", this.allowEmpty);
        obj.addProperty("allow_extra_keys", this.allowExtraKeys);
        obj.addProperty("cancel", this.cancel.getName());
        obj.addProperty("context", this.context.getName());
        obj.addProperty("exclusive", this.exclusive);
        obj.addProperty("first_only", this.firstOnly);
        obj.addProperty("order_sensitive", this.orderSensitive);
        obj.addProperty("priority", this.priority);
        obj.addProperty("show_toast", this.showToast);
        obj.addProperty("message_type", this.messageType.getName());

        return obj;
    }

    public static KeyBindSettings fromJson(JsonObject obj)
    {
        Context context = Context.INGAME;
        KeyAction activateOn = KeyAction.PRESS;
        MessageType messageType = MessageType.CUSTOM_HOTBAR;
        String contextStr = JsonUtils.getString(obj, "context");
        String activateStr = JsonUtils.getString(obj, "activate_on");
        String messageTypeStr = JsonUtils.getString(obj, "message_type");

        if (contextStr != null)
        {
            context = BaseOptionListConfigValue.findValueByName(contextStr, Context.VALUES);
        }

        if (activateStr != null)
        {
            activateOn = BaseOptionListConfigValue.findValueByName(activateStr, KeyAction.VALUES);
        }

        if (messageTypeStr != null)
        {
            messageType = BaseOptionListConfigValue.findValueByName(messageTypeStr, MessageType.VALUES);
        }

        boolean allowEmpty = JsonUtils.getBoolean(obj, "allow_empty");
        boolean allowExtraKeys = JsonUtils.getBoolean(obj, "allow_extra_keys");
        boolean orderSensitive = JsonUtils.getBooleanOrDefault(obj, "order_sensitive", true);
        boolean exclusive = JsonUtils.getBooleanOrDefault(obj, "exclusive", true);
        boolean firstOnly = JsonUtils.getBooleanOrDefault(obj, "first_only", false);
        boolean showToast = JsonUtils.getBooleanOrDefault(obj, "show_toast", true);
        int priority = JsonUtils.getIntegerOrDefault(obj, "priority", 50);
        String cancelName = JsonUtils.getStringOrDefault(obj, "cancel", "false");
        CancelCondition cancel;

        // Backwards compatibility with the old boolean value
        if (cancelName.equalsIgnoreCase("true"))
        {
            cancel = CancelCondition.ALWAYS;
        }
        else if (cancelName.equalsIgnoreCase("false"))
        {
            cancel = CancelCondition.NEVER;
        }
        else
        {
            cancel = BaseOptionListConfigValue.findValueByName(cancelName, CancelCondition.VALUES);
        }

        return create(context, activateOn, allowExtraKeys, orderSensitive, exclusive, cancel, allowEmpty,
                      priority, firstOnly, showToast, messageType);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        KeyBindSettings other = (KeyBindSettings) obj;
        if (this.activateOn != other.activateOn)
            return false;
        if (this.context != other.context)
            return false;
        if (this.allowEmpty != other.allowEmpty)
            return false;
        if (this.allowExtraKeys != other.allowExtraKeys)
            return false;
        if (this.cancel != other.cancel)
            return false;
        if (this.exclusive != other.exclusive)
            return false;
        if (this.firstOnly != other.firstOnly)
            return false;
        if (this.priority != other.priority)
            return false;
        if (this.showToast != other.showToast)
            return false;
        if (this.messageType != other.messageType)
            return false;
        return this.orderSensitive == other.orderSensitive;
    }
}
