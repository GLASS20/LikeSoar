package me.liycxc.modules.impl.utilty.irc.utils;

import static me.liycxc.modules.impl.utilty.irc.utils.ServerUtils.getTime;

public final class MSTimer {

    public long time = -1L;

    public boolean hasTimePassed(final long MS) {
        return getTime() >= time + MS;
    }

    public long hasTimeLeft(final long MS) {
        return (MS + time) - getTime();
    }

    public void reset() {
        time = getTime();
    }
}
