package bankModel;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NBU {
    private int r030;
    private String txt;
    private float rate;
    private String cc;
    private String exchangedate;
}

