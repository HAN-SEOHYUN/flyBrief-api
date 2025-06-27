package com.example.FlyBrief.api.service.country.request;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

class CountryAccidentClientTest {

    @DisplayName("외교부 사건·사고 API 요청 URL로부터 news 태그 내용을 추출한다")
    @Test
    void getAccidentNewsByIso3() throws NoSuchFieldException, IllegalAccessException {
        // given
        Dotenv dotenv = Dotenv.load();

        String baseUrl = "https://apis.data.go.kr/1262000/AccidentService/getAccidentList";
        String apiKey = dotenv.get("ACCIDENT_API_KEY");
        if (apiKey == null) throw new IllegalStateException("환경변수 ACCIDENT_API_KEY 가 설정되지 않았습니다.");

        CountryAccidentClient client = new CountryAccidentClient(new RestTemplate());

        // reflection으로 baseUrl 주입
        Field urlField = CountryAccidentClient.class.getDeclaredField("accidentApiUrl");
        urlField.setAccessible(true);
        urlField.set(client, baseUrl);

        // reflection으로 apiKey 주입
        Field keyField = CountryAccidentClient.class.getDeclaredField("serviceKey");
        keyField.setAccessible(true);
        keyField.set(client, apiKey);

        // when
        String news = client.getAccidentNewsByIso3("USA");

        // then
        assertThat(news).isNotBlank();
        assertThat(news).contains("사건").contains("지진").contains("범죄").contains("테러");
        System.out.println("=======");
        System.out.println(news);
        System.out.println("=======");
    }
}