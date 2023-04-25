package net.blogteamthreecoderhivebe.global.util.converter;

import net.blogteamthreecoderhivebe.domain.member.constant.MemberCareer;
import org.springframework.core.convert.converter.Converter;

public class CareerRequestConverter implements Converter<String, MemberCareer> {
    @Override
    public MemberCareer convert(String source) {
        return MemberCareer.of(source);
    }
}
