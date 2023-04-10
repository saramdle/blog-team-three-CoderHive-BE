package net.blogteamthreecoderhivebe.converter;

import net.blogteamthreecoderhivebe.entity.constant.MemberCareer;
import org.springframework.core.convert.converter.Converter;

public class CareerRequestConverter implements Converter<String, MemberCareer> {

    @Override
    public MemberCareer convert(String source) {
        return MemberCareer.of(source);
    }
}
