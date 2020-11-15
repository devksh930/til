package databindingconverterformatter.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {
    @Autowired
    ConversionService conversionService;// class org.springframework.boot.autoconfigure.web.format.WebConversionService DefaultFormattingConversionService를 상속하여 만든 WebConversionService


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(conversionService); //등록된 컨번터
        System.out.println(conversionService.getClass().toString());
    }
}
