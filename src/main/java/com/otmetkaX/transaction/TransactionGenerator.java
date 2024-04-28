package com.otmetkaX.transaction;

import com.otmetkaX.exception.CustomException;
import com.otmetkaX.model.Product;
import com.otmetkaX.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class TransactionGenerator {
    private final Logger logger = LoggerFactory.getLogger(TransactionGenerator.class);
    @Autowired
    private ProductService productService;
    
    private final Random random = new Random();
    
    @Scheduled(fixedDelay = 60000) // Выполнение каждую минуту
    public void generateTransaction() {
        long randomProductId = random.nextInt(390) + 1;
        try {
            Product product = productService.findById(randomProductId);
            productService.incrementCountToDay(product);
        } catch (CustomException e) {
            logger.debug(e.getMessage() + " id product: " + randomProductId);
        }
    }

    @Scheduled(cron = "0 0 0 * * *") // Выполнение в 00.00 раз в сутки
    public void runAtMidnight() {
        productService.resetCountToDay();
    }

    public void saveAllProducts() throws CustomException {
        ProductLukoil[] products = ProductLukoil.values();
        Region[] regions = Region.values();

        for (ProductLukoil product : products) {
            for (Region region : regions) {
                Product newProduct = new Product(product.name(), 0, 0, region.name(), product.getPrice());
                productService.save(newProduct);
            }
        }
    }

    public static enum ProductLukoil {
        GASOLINE_92(790.00),
        GASOLINE_95(920.00),
        DIESEL_FUEL(760.00),
        PROPANE(800.00),
        MOTOR_OIL(900.00),
        CAR_ANTIFREEZE(800.00),
        CAR_ENGINE_OIL(790.00),
        CAR_SHAMPOO(1220.00),
        FUEL_ADDITIVES(920.00),
        CAR_PARTS(14400.00),
        BATTERIES(12000.00),
        TIRES(18000.00),
        CAR_ACCESSORIES(3600.00),
        GAS_CANS(1800.00),
        CAR_PAINT(3450.00),
        GLASS_CLEANERS(660.00),
        CAR_DEODORANTS(300.00),
        MAGAZINES_AND_NEWSPAPERS(240.00),
        CHIPS(240.00),
        CHOCOLATE(100.00),
        CHEWING_GUM(120.00),
        ENERGY_DRINKS(540.00),
        CARBONATED_DRINKS(240.00),
        BOTTLED_WATER(180.00),
        MINERAL_VITAMINS(450.00),
        PROTEIN_BARS(80.00),
        FRUITS_AND_VEGETABLES(450.00),
        SANDWICHES(300.00),
        HOT_PIES(200.00);

        private double price;

        ProductLukoil(double price) {
            this.price = price;
        }
        
        public double getPrice() {
            return price;
        }
    }

    public static enum Region {
        MOSCOW,
        SAINT_PETERSBURG,
        NOVOSIBIRSK,
        EKATERINBURG,
        ROSTOV_ON_DON,
        UFA,
        VOLGOGRAD,
        KEMEROVO,
        ARKHANGELSK,
        CHELYABINSK,
        ORENBURG,
        VLADIVOSTOK,
        KALININGRAD,

    }

}
