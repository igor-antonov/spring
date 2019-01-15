package ru.otus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.service.InputServiceImpl;
import ru.otus.service.*;

@Configuration
@PropertySource({"classpath:application.properties"})
public class ServicesConfig {
    @Value("${csv.path}")
    private String csvPath;

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms
                = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/bun");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    @Bean
    public LocalizedService messageService(MessageSource ms){
        return new LocalizedServiceImpl(ms);
    }

    @Bean
    public InputService inputService(){
        return new InputServiceImpl(System.in);
    }

    @Bean
    public StudentService studentService(LocalizedService localizedService, InputService inputService){
        return new StudentServiceImpl(localizedService, inputService);
    }

    @Bean
    public ExamService examService(LocalizedService localizedService, InputService inputService) {
        return new ExamServiceCSV(this.csvPath, inputService, localizedService.getLocale());
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }
}
