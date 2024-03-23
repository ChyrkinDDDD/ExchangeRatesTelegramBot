package bankModel;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrivatBank {
    private String ccy;
    private String base_ccy;
    private String buy;
    private String sale;
}
