package tasks.third;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class YTranslator {

    private static final String KEY = "trnsl.1.1.20181129T101813Z.9c51a94b3c072c82.e1063156a83af57bcb353c2d4480f4b0cfb0420d";
    private static final String PATH = "https://translate.yandex.net/api/v1.5/tr.json/translate";

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in, "UTF-8");
        String input = scanner.nextLine();

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(PATH);
        Header header = new BasicHeader("User-Agent", "Mozilla/5.0");
        httpPost.setHeader(header);

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("key", KEY));
        params.add(new BasicNameValuePair("lang", "en-ru"));
        params.add(new BasicNameValuePair("text", input));

        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        HttpResponse httpResponse = client.execute(httpPost);
        BufferedReader br = new BufferedReader(new InputStreamReader(httpResponse
                .getEntity().getContent(), "UTF-8"));
        System.out.println(br.readLine());
    }

}