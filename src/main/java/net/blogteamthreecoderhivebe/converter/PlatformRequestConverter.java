package net.blogteamthreecoderhivebe.converter;

import net.blogteamthreecoderhivebe.entity.constant.Platform;
import org.springframework.core.convert.converter.Converter;

public class PlatformRequestConverter implements Converter<String, Platform> {

    @Override
    public Platform convert(String source) {
        return Platform.of(source);
    }
}
