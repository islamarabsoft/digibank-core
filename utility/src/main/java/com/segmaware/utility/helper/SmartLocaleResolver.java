package com.segmaware.utility.helper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @author mohamed.hanafy
 *
 **/
@Component
@RequiredArgsConstructor
public class SmartLocaleResolver extends AcceptHeaderLocaleResolver {

    private final HttpServletRequest request;

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        if (request.getHeader("Accept-Language") == null) {
            return Locale.getDefault();
        }
        List<Locale.LanguageRange> list =
                Locale.LanguageRange.parse(request.getHeader("Accept-Language"));

        Locale locale = Locale.lookup(list, ApplicationConstants.LOCALES);
        if (locale == null || !Arrays.asList("ar", "en", "fr").contains(locale.getLanguage()))
            return Locale.FRENCH;

        return locale;
    }

    public Locale resolveLocale(){
        return resolveLocale(request);
    }

    private static class ApplicationConstants{
        protected static final List<Locale> LOCALES = Arrays.asList(new Locale("en"),
                new Locale("es"),
                new Locale("fr"),
                new Locale("es", "MX"),
                new Locale("zh"),
                new Locale("ja"),
                new Locale("ar"));
    }
}
