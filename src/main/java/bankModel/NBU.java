package bankModel;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NBU {
    int r030;
    String txt;
    float rate;
    String cc;
    String exchangedate;
}

