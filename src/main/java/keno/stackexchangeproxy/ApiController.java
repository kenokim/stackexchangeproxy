package keno.stackexchangeproxy;

import keno.stackexchangeproxy.dto.ItemDto;
import keno.stackexchangeproxy.dto.QuestionListDto;
import keno.stackexchangeproxy.respository.ItemRepository;
import keno.stackexchangeproxy.stackData.Item;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Controller
public class ApiController {
    @Autowired ItemService itemService;
    @Autowired
    ItemRepository itemRepository;
    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
            HttpClientBuilder.create().build());
    RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);

    @GetMapping("/get")
    public ResponseEntity get() {
        String url = "http://localhost:8080/questions/mock";
        String url1 = "https://api.stackexchange.com/2.3/questions?page=1&pagesize=100&order=desc&sort=activity&site=stackoverflow&filter=!95kkh65WFZ)RhgpIx)CICUjWUcI0zc7mF5moTK(msTJOtjUZmFore2f2z5RGtW8a5o1fOtSuXjBXbXbnQWAoExQo_fU89xxwPx)Gi";
        QuestionListDto dto = restTemplate.getForObject(url1, QuestionListDto.class);
        itemService.store(dto.getItems().get(0));
        //for (Item item : dto.getItems()) {
        //    itemRepository.save(item);
        //}
        return new ResponseEntity("Ok", HttpStatus.OK);
    }

    @PostMapping("/additem")
    public ResponseEntity add(@RequestBody QuestionListDto dto) {
        List<ItemDto> items = dto.getItems();
        for (ItemDto i : items) {
            itemService.store(i);
        }
        return new ResponseEntity("Ok", HttpStatus.OK);
    }

    @GetMapping("/post")
    public ResponseEntity post() {
        String register = "http://localhost:8080/login/register";
        String logout = "http://localhost:8080/login/logout";
        String login = "http://localhost:8080/login";

        String password = "test!";

        String article = "http://localhost:8080/article/add";

        List<Item> items = itemRepository.findAll();
        for (Item item : items) {
            try {
                HttpEntity<LoginForm> request = new HttpEntity<>(new LoginForm(item.getOwner().getDisplay_name(), password));
                restTemplate.postForEntity(login, request, LoginForm.class);
                System.out.println("Login Ok");
            }
            catch (Exception e) {
                System.out.println("login not ok");
                try {
                    HttpEntity<RegisterForm> request = new HttpEntity<>(new RegisterForm(item.getOwner().getDisplay_name(), password));
                    restTemplate.postForEntity(register, request, RegisterForm.class);
                    System.out.println("oh so good");
                    HttpEntity<LoginForm> request2 = new HttpEntity<>(new LoginForm(item.getOwner().getDisplay_name(), password));
                    restTemplate.postForEntity(login, request2, LoginForm.class);
                }
                catch(Exception e2) {
                    System.out.println("fuck");
                }
            }
            try {
                String tags = listToTag(item.getTags());
                Long epochTime = item.getCreation_date();
                LocalDateTime dateTime = LocalDateTime.ofEpochSecond(epochTime, 0, ZoneOffset.UTC);
                HttpEntity<ArticleForm> request = new HttpEntity<>(new ArticleForm(item.getTitle(), item.getTitle() + " test article ", tags, dateTime));
                restTemplate.postForEntity(article, request, ArticleForm.class);
                System.out.println("oh so good");
            }
            catch (Exception e) {
                System.out.println("article not included");
            }
            try {
                restTemplate.postForEntity(logout, null, null);
                System.out.println("logout ed");
            }
            catch (Exception e) {
                System.out.println("failed to logout");
            }
        }
        return new ResponseEntity("Ok", HttpStatus.OK);
    }

    private String listToTag(List<String> tags) {
        if (tags.isEmpty()) return "";
        String tagString = "";
        for (String t : tags) {
            tagString += t + ",";
        }
        return tagString.substring(0, tagString.length() - 1);
    }
}

