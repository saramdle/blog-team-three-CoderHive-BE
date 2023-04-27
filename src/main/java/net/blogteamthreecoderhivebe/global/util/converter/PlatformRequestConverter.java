package net.blogteamthreecoderhivebe.global.util.converter;

import net.blogteamthreecoderhivebe.domain.info.constant.Platform;
import org.springframework.core.convert.converter.Converter;

public class PlatformRequestConverter implements Converter<String, Platform> {
    @Override
    public Platform convert(String source) {
        return Platform.of(source);
    }
}
