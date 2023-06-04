package com.demo.travelcardsystem.config;

import com.demo.travelcardsystem.businessrule.RuleCollection;
import com.demo.travelcardsystem.businessrule.TravelStrategy;
import com.demo.travelcardsystem.constant.Zone;
import com.demo.travelcardsystem.entity.Station;
import com.demo.travelcardsystem.entity.TravelCard;
import com.demo.travelcardsystem.entity.TravelCardObserver;
import com.demo.travelcardsystem.repository.InMemoryCardTransactionRepository;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/*
 * This is the main class for the application.
 * This class is annotated with @SpringBootApplication which is a convenience annotation that adds all of the following:
 * @Configuration: Tags the class as a source of bean definitions for the application context.
 * @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
 * @ComponentScan: Tells Spring to look for other components, configurations, and services in the com/example package, letting it find the controllers.
 * 
 */
@SpringBootApplication(scanBasePackages = {"com.demo.travelcardsystem"})
public class TravelcardsystemApplication{

    public static Set<Station> stations;

    
    public static void main(String[] args) {
        SpringApplication.run(TravelcardsystemApplication.class, args);
    }

    @Bean
    public TravelCard getTravelCard(TravelCardObserver travelCardObserver) {
        TravelCard travelCard = new TravelCard();
        travelCard.registerObserver(travelCardObserver);
        return travelCard;
    }

    @Bean
    public RuleCollection loadAllTravelStrategy(TravelStrategy travelStrategy) {
       return travelStrategy.loadAllBusinessRules();
    }

/*
 * This method is used to load all the stations in the system.
 * This method is annotated with @Bean which tells Spring that a method annotated with 
 * @Bean will return an object that should be registered as a bean in the Spring application context.
 */
    @Bean
    public Boolean loadAllStation(InMemoryCardTransactionRepository inMemoryCardTransactionRepository) {
        stations = new HashSet<>();

        //ADD Algubaiba
        stations.add(new Station("Algubaiba", new HashSet<>(Arrays.asList(Zone.ONE))));
        //ADD Jumeirah
        stations.add(new Station("Jumeirah", new HashSet<>(Arrays.asList(Zone.ONE, Zone.TWO))));
        //ADD Bur Dubai
        stations.add(new Station("Bur Dubai", new HashSet<>(Arrays.asList(Zone.THREE))));
        //ADD Deirah
        stations.add(new Station("Deirah", new HashSet<>(Arrays.asList(Zone.TWO))));

        return inMemoryCardTransactionRepository.addAllStationsToStationStore(stations);
    }

    /*
     * This method is used to load all the cards in the system.
     */
    @Bean
    public Boolean loadInitialCards(InMemoryCardTransactionRepository inMemoryCardTransactionRepository) {
        TravelCard firstTravelCard = new TravelCard();
        firstTravelCard.setCardNumber("A101");
        firstTravelCard.setBalance(30);

        TravelCard secondTravelCard = new TravelCard();
        secondTravelCard.setCardNumber("B201");
        secondTravelCard.setBalance(50);

        inMemoryCardTransactionRepository.registerNewCard(firstTravelCard);
        inMemoryCardTransactionRepository.registerNewCard(secondTravelCard);

        return true;
    }


}
