package us.bojie.luckymoney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
public class LuckyMoneyService {

    private final LuckyMoneyRepository repository;

    @Autowired
    public LuckyMoneyService(LuckyMoneyRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void createTwo() {
        LuckyMoney money1 = new LuckyMoney();
        money1.setConsumer("Bojie");
        money1.setMoney(new BigDecimal("520"));
        repository.save(money1);

        LuckyMoney money2 = new LuckyMoney();
        money2.setConsumer("Bojie");
        money2.setMoney(new BigDecimal("1314"));
        repository.save(money2);
    }
}
