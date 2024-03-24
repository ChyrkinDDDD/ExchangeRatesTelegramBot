package settings;

import bankUtil.BankUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSettings {
    BankUtil bankUtil;
    int numberAfterComa;
    String currency;
    String bank;
    int time;
}
