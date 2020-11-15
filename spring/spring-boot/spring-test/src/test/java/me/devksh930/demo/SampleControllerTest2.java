package me.devksh930.demo;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.system.OutputCaptureRule;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@WebMvcTest(SampleController.class)
public class SampleControllerTest2 {

    @Rule
    public OutputCaptureRule outputCaptureRule = new OutputCaptureRule();

    @MockBean
    SampleService sampleService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void hello() throws Exception {
        when(sampleService.getName()).thenReturn("sungho");

        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello sungho"));

        assertThat(outputCaptureRule.toString())
                .contains("holoman")
                .contains("skip");
    }
}