package net.blogteamthreecoderhivebe.global.util.converter;

import net.blogteamthreecoderhivebe.domain.member.constant.MemberLevel;
import org.springframework.core.convert.converter.Converter;

public class LevelRequestConverter implements Converter<String, MemberLevel> {
    @Override
    public MemberLevel convert(String source) {
        return MemberLevel.of(source);
    }
}
