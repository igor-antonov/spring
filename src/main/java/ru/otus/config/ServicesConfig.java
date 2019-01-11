package ru.otus.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.QuestionScanner;
import ru.otus.service.*;

import java.util.Locale;

@Configuration
public class ServicesConfig {

    @Bean
    public Locale locale(){
        return Locale.getDefault();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms
                = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/bun");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    @Bean(name = "scanner")
    public QuestionScanner questionScanner(){
        return new QuestionScanner(System.in);
    }

    @Bean
    public ExamService examService(MessageSource ms, Locale locale){
        return new ExamServiceCSV(ms.getMessage("csv.path",null, locale));
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }
}
