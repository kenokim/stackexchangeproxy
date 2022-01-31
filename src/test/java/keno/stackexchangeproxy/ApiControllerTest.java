package keno.stackexchangeproxy;

import keno.stackexchangeproxy.dto.QuestionListDto;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.zip.GZIPInputStream;


class ApiControllerTest {

    String BASE_URL = "https://api.stackexchange.com/2.3";
    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
            HttpClientBuilder.create().build());
    RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);

    String url1 = "https://api.stackexchange.com/2.3/questions?order=desc&sort=activity&site=stackoverflow";
    String url2 = "http://localhost:8080/article/articles";

    public static String decompress(byte[] compressed) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(compressed);
        GZIPInputStream gis = new GZIPInputStream(bis);
        BufferedReader br = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        gis.close();
        bis.close();
        return sb.toString();
    }

    @Test
    public void getRestTemplate() throws IOException {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                HttpClientBuilder.create().build());
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);

        String sourceUrl = BASE_URL + "/questions";

        ResponseEntity<String> response
                = restTemplate.getForEntity(sourceUrl + "?order=desc&sort=activity&site=stackoverflow", String.class);
        System.out.println(response.getBody());



        //InputStream content = response.getEntity().getContent();

    }

    @Test
    public void getRestEntity() {
        System.out.println(restTemplate.getForEntity(url1, String.class).getBody());

    }

    @Test
    public void getRestObject() {
        System.out.println(restTemplate.getForObject(url2, ArticleListDto.class));

    }

    @Test
    public void getRestObject2() {
        System.out.println(restTemplate.getForObject(url1, QuestionListDto.class));
    }

    @Test
    public void getRestObject3() {
        String url = "http://localhost:8080/questions/mock";
        System.out.println(restTemplate.getForObject(url, QuestionListDto.class));
    }



    @Test
    public void epochTimeToLocalDateTime() {
        Long epochTime = 1643005858L;
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(epochTime, 0, ZoneOffset.UTC);
        System.out.println(dateTime);
    }

}