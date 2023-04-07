package net.blogteamthreecoderhivebe.converter;

import net.blogteamthreecoderhivebe.entity.constant.MemberLevel;
import org.springframework.core.convert.converter.Converter;

public class LevelRequestConverter implements Converter<String, MemberLevel> {

    @Override
    public MemberLevel convert(String source) {
        return MemberLevel.of(source);
    }
}
