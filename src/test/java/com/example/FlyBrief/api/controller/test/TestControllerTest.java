package com.example.FlyBrief.api.controller.test;

import com.example.FlyBrief.ControllerTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

class TestControllerTest extends ControllerTestSupport {

    @DisplayName("testController 정상동작 여부를 확인한다")
    @Test
    void test() throws Exception{
        //given
        //when
        //then
        mockMvc.perform(get("/api/test"))
            .andDo(print())
            .andExpect(content().string("test"));
    }
}