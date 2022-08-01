package com.ssafy.sai.global.util.validation;

import com.ssafy.sai.domain.member.exception.MemberException;
import com.ssafy.sai.domain.member.exception.MemberExceptionType;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Assert {

    private Assert() {
    }

    public static void memberNotNull(Object object, String msg) {
        if (validation(object))
            throw new MemberException(MemberExceptionType.NOT_FOUND_MEMBER);
    }

    public static void resultNotNull(Object object, String msg) {
        if (validation(object))
            throw new MemberException(MemberExceptionType.NOT_FOUND_MEMBER);
    }

    private static boolean validation(Object obj) {

        if (Objects.isNull(obj)) {
            return true;
        }

        if ("null".equals(obj)) {
            return true;
        }

        if (obj instanceof Long && (Long) obj == 0) {
            return true;
        }

        if (obj instanceof Integer && (Integer) obj == 0) {
            return true;
        }

        if ((obj instanceof String) && (((String) obj).trim().length() == 0)) {
            return true;
        }

        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }

        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }

        if (obj instanceof List) {
            return ((List<?>) obj).isEmpty();
        }

        if (obj instanceof Object[]) {
            return (((Object[]) obj).length == 0);
        }

        return false;
    }
}