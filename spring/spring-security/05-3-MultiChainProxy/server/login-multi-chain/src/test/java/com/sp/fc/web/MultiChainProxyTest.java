package com.sp.fc.web;

import com.sp.fc.web.student.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MultiChainProxyTest {

    @LocalServerPort
    int port;

    TestRestTemplate testClient = new TestRestTemplate("kim", "1");

    @DisplayName("1. kim:1 로 로그인 학생리스트 받기")
    @Test
    void test_1() {
        // kim:1 로 로그 학생리스트 받기
        ResponseEntity<List<Student>> resp = testClient.exchange("http://localhost:" + port + "/api/teacher/students", HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
        });
        assertNotNull(resp.getBody());
        assertEquals(3, resp.getBody().size());
    }
}
