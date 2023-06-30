package me.liycxc.api.value;

import com.google.gson.JsonElement;
import lombok.Getter;
import lombok.Setter;
import me.liycxc.utils.Logger;

public abstract class Value<T> {

    private final String name;
    @Setter
    @Getter
    private T value;
    @Getter
    @Setter
    private Function0<Boolean> displayable;
    private final T defaultVal;

    public Value(String name, T value, Function0<Boolean> displayable) {
        this.name = name;
        this.value = value;
        this.displayable = displayable;
        this.defaultVal = value;
    }

    public abstract JsonElement toJson();

    public abstract void fromJson(JsonElement element);

    public T get() {
        return value;
    }

    public void set(T newValue) {
        if (newValue.equals(value)) {
            return;
        }

        T oldValue = get();

        try {
            onChange(oldValue, newValue);
            changeValue(newValue);
            onChanged(oldValue, newValue);
            onChanging();
            // Save
        } catch (Exception e) {
            Logger.error(String.format("[ValueSystem (%s)]: %s (%s) [%s >> %s]", name, e.getClass().getName(), e.getMessage(), oldValue, newValue));
        }
    }

    public void setDefault() {
        setValue(defaultVal);
    }

    public void onChange(T oldValue, T newValue) {
    }

    public void onChanged(T oldValue, T newValue) {
    }

    public void onChanging() {
    }

    protected void changeValue(T value) {
        this.value = value;
    }

    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (value instanceof String && other instanceof String) {
            return ((String) value).equalsIgnoreCase((String) other);
        }
        return value != null && value.equals(other);
    }

    public boolean contains(String text) {
        if (value instanceof String) {
            return ((String) value).contains(text);
        } else {
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public boolean isDisplayable() {
        return displayable.invoke();
    }

//    @Nullable
//    public Boolean displayable(@NotNull Function0<Boolean> function) {
//        displayable = function;
//        return null;
//    }
}
