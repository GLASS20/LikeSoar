package me.liycxc.verify;

import lombok.experimental.UtilityClass;
import me.liycxc.utils.Logger;
import org.apache.commons.codec.digest.DigestUtils;

@UtilityClass
public class Verify {
    public void init() {
        String md5 = DigestUtils.md5Hex(VerifyUtils.getSerialNumber());
        Logger.log("MD5: " + md5);
    }
}
