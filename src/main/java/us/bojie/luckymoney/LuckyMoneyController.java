package us.bojie.luckymoney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
public class LuckyMoneyController {

    private final LuckyMoneyRepository repository;
    private final LuckyMoneyService service;

    @Autowired
    public LuckyMoneyController(LuckyMoneyRepository repository, LuckyMoneyService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping("/luckymoneys")
    public List<LuckyMoney> list() {
        return repository.findAll();
    }

    @PostMapping("/luckymoneys")
    public LuckyMoney create(@RequestParam("producer") String producer,
                             @RequestParam("money")BigDecimal money) {
        LuckyMoney luckyMoney = new LuckyMoney();
        luckyMoney.setProducer(producer);
        luckyMoney.setMoney(money);
        return repository.save(luckyMoney);
    }

    @GetMapping("/luckymoneys/{id}")
    public LuckyMoney findById(@PathVariable("id") Integer id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/luckymoneys/{id}")
    public LuckyMoney update(@PathVariable("id") Integer id,
                             @RequestParam("consumer") String consumer) {
        Optional<LuckyMoney> optionalLuckyMoney = repository.findById(id);
        if (optionalLuckyMoney.isPresent()) {
            LuckyMoney money = optionalLuckyMoney.get();
            money.setConsumer(consumer);
            return repository.save(money);
        }
        return null;
    }

    @PostMapping("/luckymoneys/two")
    public void createTwo() {
        service.createTwo();
    }
}
