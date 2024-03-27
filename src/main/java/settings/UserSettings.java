package settings;

import bankUtil.BankUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSettings {
    private BankUtil bankUtil;
    private int numberAfterComa;
    private  String currency;
    private String bank;
    private int time;
}
