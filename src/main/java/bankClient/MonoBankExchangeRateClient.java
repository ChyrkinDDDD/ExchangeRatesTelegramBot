package bankClient;

import bankModel.MonoBank;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


public class MonoBankExchangeRateClient {
    private final String MONO_URI = "https://api.monobank.ua/bank/currency";
    HttpClient client = HttpClient.newHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();
    public List<MonoBank> getMonoBankExchangeRates(){
        try{
            HttpRequest request = HttpRequest.newBuilder(new URI(MONO_URI))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return objectMapper.readValue(response.body(), new TypeReference<>() {
            });

        }catch (URISyntaxException e){
            System.out.println("MONO URI SYNTAX EXCEPTION!");
            e.printStackTrace();
        } catch (JsonMappingException e) {
            System.out.println("MONO Json Mapping Exception!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("MONO IOException!");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("MONO Interrupted Exception");
            e.printStackTrace();
        }
        return null;
    }
}
