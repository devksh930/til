package me.devksh930.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    @GetMapping("/hello")
    public String hello() {
        throw new SampleExecption();
    }

    @ExceptionHandler(SampleExecption.class)
    public @ResponseBody
    AppError sampleError(SampleExecption e) {
        AppError appError = new AppError();
        appError.setMsg("error.app.key");
        appError.setReason("IDK IDK IDK");
        return appError;
    }
}
