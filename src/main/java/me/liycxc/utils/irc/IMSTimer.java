package me.liycxc.utils.irc;

import static me.liycxc.utils.irc.ServerUtils.getTime;

public final class IMSTimer {

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
