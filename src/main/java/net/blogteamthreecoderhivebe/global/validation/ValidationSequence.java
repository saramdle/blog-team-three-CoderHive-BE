package net.blogteamthreecoderhivebe.global.validation;

import jakarta.validation.GroupSequence;
import jakarta.validation.groups.Default;
import net.blogteamthreecoderhivebe.global.validation.ValidationGroups.NotEmptyGroup;
import net.blogteamthreecoderhivebe.global.validation.ValidationGroups.PatternCheckGroup;

@GroupSequence({Default.class, NotEmptyGroup.class, PatternCheckGroup.class})
public interface ValidationSequence {
}
