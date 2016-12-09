package apitesting;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.*;


public class RestExampleTest {

    private static final String GITHUB_HOST = "https://api.github.com";

    /** GET
     * Example from http://www.baeldung.com/integration-testing-a-rest-api
     *
     */
    @Test
    public void searchNonexistentUser() throws IOException {
        // Given
        String userName = RandomStringUtils.randomAlphabetic(8);
        HttpUriRequest request = new HttpGet(GITHUB_HOST + "/users/" + userName);

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        // Then
        assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);
    }

    /**
     * GET
     */
    @Test
    public void searchExistentUser() throws IOException {
        String userName = "spodgorov";
        //отправляем HttpGet запрос
        HttpUriRequest request = new HttpGet(GITHUB_HOST + "/users/" + userName);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        //у httpResponse есть HttpEntity, из него делаем строку, это уже json response
        HttpEntity responseEntity = httpResponse.getEntity();
        String result = EntityUtils.toString(responseEntity);

        //из строки в формате json, сделаем JSONObject, из него удобнее доставать конкретные параметры
        //для работы с этими классами, в pom.xml была подключена зависимость com.googlecode.json-simple
        JSONObject json = new JSONObject();  //экземпляр JSONObject, в который мы положим json response
        JSONParser parser = new JSONParser(); //экземпляр JSONParser, с помощью его метода parse сделаем из строки json
        try {
            //парсим строку в JSONObject, это происходит в блоке try/catch, чтобы в случае, если строка окажется не в правильном формате, вывести в консоль понятную ошибку
            json = (JSONObject) parser.parse(result);
        } catch (ParseException e) {
            System.out.println("Can't parse response to JSON");
            e.printStackTrace();
        }
        System.out.println("Json response: " + json.toString());

        assertEquals(json.get("login"), userName, "Login response parameter doesn't match with expected value");
        String userId = String.valueOf(json.get("id")); // json.get returns abstract Object, so here we say that we use String.valueOf to cast Object to String
        assertNotNull(userId);
        assertTrue(String.valueOf(json.get("avatar_url")).contains(userId), "avatar_url must contain userId"); //String.valueOf( что-то, что ты хочешь сделать строкой)
        assertTrue(String.valueOf(json.get("url")).contains(userName));
    }

    /**
     * ссылка с примером https://www.mkyong.com/java/apache-httpclient-examples/
     * Пример: отправка пост запроса. Строку в формате json вывести в консоль
     * IOException не обрабатывается в try-catch блоке, поэтому указано в заголовке тестового метода
     */
    @Test
    public void postExample() throws IOException {
        String url = "https://jsonplaceholder.typicode.com/posts";
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);

        //Если в запросе нужны параметры(для данного урла не нужны), их можно так добавить:
        //подготовили список параметров
        //List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        //urlParameters.add(new BasicNameValuePair("someParameter", "someValue"));
        //urlParameters.add(new BasicNameValuePair("someParameter2", "someValue2"));
        //добавили параметры запросе в HttpPost
        //post.setEntity(new UrlEncodedFormEntity(urlParameters));

        //отправляем
        HttpResponse response = client.execute(post);
        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

        //строку из ответа доставать уже умеем
        HttpEntity responseEntity = response.getEntity();
        String result = EntityUtils.toString(responseEntity);
        System.out.println("String from http response: " + result);
    }

}