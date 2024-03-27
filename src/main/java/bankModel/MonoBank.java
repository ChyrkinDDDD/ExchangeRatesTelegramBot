package bankModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MonoBank{
    private int currencyCodeA;
    private int currencyCodeB;
    private long date;
    private float rateSell;
    private float rateBuy;
    private float rateCross;
}
