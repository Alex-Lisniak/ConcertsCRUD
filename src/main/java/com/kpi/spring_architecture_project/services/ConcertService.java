package com.kpi.spring_architecture_project.services;

import com.kpi.spring_architecture_project.AchievementsGenerator;
import com.kpi.spring_architecture_project.models.*;
import com.kpi.spring_architecture_project.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.regex.Pattern;


@Service
public class ConcertService {

    @Autowired
    private ActorAchievementRepository actorAchievementRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ConcertRepository concertRepository;

    @Autowired
    private DistributionOfSeatsRepository distributionOfSeatsRepository;

    @Autowired
    private MaxSizeOfConcertRepository maxSizeOfConcertRepository;

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private PopularityVariantsRepository popularityVariantsRepository;

    @Autowired
    private TicketsCostRepository ticketsCostRepository;

    public String index(Model model){
        Iterable<Concert> concerts = concertRepository.findAll();
        System.out.println("balooo");
        model.addAttribute("concerts" , concerts);

        return "index";
    }

    public String addConcert(String place, String designation, String performanceName,
                             String distributionOfSeats, Long countOfSeats,  Long costOfTicket,
                             Model model){

        if(!validation(place, designation ,performanceName , distributionOfSeats, countOfSeats, costOfTicket)) return "dataException";
        Concert concert = new Concert(place , designation , distributionOfSeats, costOfTicket , countOfSeats);

        Iterable<Performance> performances = performanceRepository.findAll();
        Iterable<Concert> concerts = concertRepository.findAll();

        Performance performance = null;
        Iterator performanceIterator = performances.iterator();

        while(performanceIterator.hasNext()){
            Performance performanceForIterations = (Performance) performanceIterator.next();
            System.out.println(performanceForIterations.getDesignation());
            if(performanceName.equals(performanceForIterations.getDesignation())){
                performance = performanceForIterations;

                break;

            } else return "concertAddWithNewPerformance";
        }

        concert = concertRepository.save(concert);

        Performance performance1 = new Performance(performance.getId_actors(), concert.getId() , performance.getDesignation());
        performanceRepository.save(performance1);

        DistributionOfSeats distribution = new DistributionOfSeats(concert.getId() , distributionOfSeats );

        MaxSizeOfConcert maxSizeOfConcert = new MaxSizeOfConcert(concert.getId() , countOfSeats);

        TicketsCost ticketsCost = new TicketsCost(concert.getId() , costOfTicket);

        String futurePopularity = null;
        if(concert.getPopularity() < 300) futurePopularity = "low";
        if(concert.getPopularity() > 300 && concert.getPopularity() < 600) futurePopularity = "middle";
        if(concert.getPopularity() > 600 && concert.getPopularity() < 900) futurePopularity = "high";
        PopularityVariants popularityVariants = new PopularityVariants(performance1.getId() , futurePopularity);


        popularityVariantsRepository.save(popularityVariants);
        distributionOfSeatsRepository.save(distribution);
        ticketsCostRepository.save(ticketsCost);
        maxSizeOfConcertRepository.save(maxSizeOfConcert);

        return "redirect:/concerts";
    }

    public String concertPostAddWithNewPerformance(String place, String designation, String distributionOfSeats, Long countOfSeats,
                                                   Long costOfTicket,  String performanceName,
                                                   String actorName, Model model) {
        if(!validation(place, designation , distributionOfSeats, countOfSeats, costOfTicket , performanceName , actorName)) return "dataException";
        Concert concert = new Concert(place , designation , distributionOfSeats, costOfTicket , countOfSeats);
        Actor actor = new Actor(actorName);

        concert = concertRepository.save(concert);
        actor = actorRepository.save(actor);

        Performance performance = new Performance(actor.getId(), concert.getId() , performanceName);
        performanceRepository.save(performance);

        ActorAchievement actorAchievement = new ActorAchievement(actor.getId());
        actorAchievement.setAchievement(AchievementsGenerator.generateAnAchievement());

        DistributionOfSeats distribution = new DistributionOfSeats(concert.getId() , distributionOfSeats );

        MaxSizeOfConcert maxSizeOfConcert = new MaxSizeOfConcert(concert.getId() , countOfSeats);

        TicketsCost ticketsCost = new TicketsCost(concert.getId() , costOfTicket);

        String futurePopularity = null;
        if(concert.getPopularity() < 300) futurePopularity = "low";
        if(concert.getPopularity() > 300 && concert.getPopularity() < 600) futurePopularity = "middle";
        if(concert.getPopularity() > 600 && concert.getPopularity() < 900) futurePopularity = "high";
        PopularityVariants popularityVariants = new PopularityVariants(performance.getId() , futurePopularity);


        popularityVariantsRepository.save(popularityVariants);
        actorAchievementRepository.save(actorAchievement);
        distributionOfSeatsRepository.save(distribution);
        maxSizeOfConcertRepository.save(maxSizeOfConcert);
        ticketsCostRepository.save(ticketsCost);

        return "redirect:/concerts";

    }

    public String cocnertDetails(long id , Model model){
        if(!concertRepository.existsById(id)){
            return "redirect:/concerts";
        }
        Optional<Concert> concert = concertRepository.findById(id);

        ArrayList<Concert> listOfConcerts = new ArrayList<>();
        concert.ifPresent(listOfConcerts::add);


        model.addAttribute("concert" , listOfConcerts.get(0));
        Concert concertToIncrement = listOfConcerts.get(0);
        concertToIncrement.incrementViews();
        concertToIncrement.calculatePopularity();
        concertRepository.save(concertToIncrement);
        return "concertDetails";
    }

    public String concertEdit(long id , Model model){
        if(!concertRepository.existsById(id)){
            return "redirect:/concerts";
        }

        Optional<Concert> concert = concertRepository.findById(id);
        System.out.println(concert.getClass().getName());
        ArrayList<Concert> listOfConcerts = new ArrayList<>();
        concert.ifPresent(listOfConcerts::add);
        model.addAttribute("concert" , listOfConcerts);

        return "concertEdit";
    }

    public String concertPostEdit(long id, String place,  String designation, String distributionOfSeats,
                                  Long countOfSeats, Long costOfTicket, Model model){

        if(!validation(place, designation , distributionOfSeats, countOfSeats , costOfTicket)) return "dataException";

        Concert concert = concertRepository.findById(id).orElseThrow();
        concert.setPlace(place);
        concert.setDesignation(designation);
        concert.setDistributionOfSeats(distributionOfSeats);
        concert.setCountOfSeats(countOfSeats);
        concert.setCostOfTicket(costOfTicket);

        concertRepository.save(concert);

        return "redirect:/concerts";
    }


    public String concertPostDelete(long id , Model model){

        Iterable<DistributionOfSeats> distributionOfSeats = distributionOfSeatsRepository.findAll();
        Iterator distributionOfSeatsIterator = distributionOfSeats.iterator();
        DistributionOfSeats distribution = null;
        while(distributionOfSeatsIterator.hasNext()){
            DistributionOfSeats distributionOfSeatsForIterations = (DistributionOfSeats) distributionOfSeatsIterator.next();
            if(distributionOfSeatsForIterations.getId_concert() == id) {
                distribution = distributionOfSeatsForIterations;
                break;
            }
        }

        Iterable<MaxSizeOfConcert> maxSizeOfConcerts = maxSizeOfConcertRepository.findAll();
        Iterator maxSizeIterator = maxSizeOfConcerts.iterator();
        MaxSizeOfConcert maxSizeOfConcert = null;
        while(maxSizeIterator.hasNext()){
            MaxSizeOfConcert maxSizeOfConcertForIterations = (MaxSizeOfConcert) maxSizeIterator.next();
            if(maxSizeOfConcertForIterations.getId_concert() == id) {
                maxSizeOfConcert = maxSizeOfConcertForIterations;
                break;
            }
        }

        Iterable<TicketsCost> ticketsCosts = ticketsCostRepository.findAll();
        Iterator ticketsCostIterator = ticketsCosts.iterator();
        TicketsCost ticketsCost = null;
        while(ticketsCostIterator.hasNext()){
            TicketsCost ticketsCostForIterations = (TicketsCost) ticketsCostIterator.next();
            if(ticketsCostForIterations.getId_concert() == id) {
                ticketsCost = ticketsCostForIterations;
                break;
            }
        }

        Iterable<Performance>  performances = performanceRepository.findAll();
        Iterator performanceIterator = performances.iterator();
        Performance performance = null;
        while(performanceIterator.hasNext()){
            Performance performanceForIterations = (Performance) performanceIterator.next();
            System.out.println(performanceForIterations.getDesignation());
            if(performanceForIterations.getId_concert() == id) {
                performance = performanceForIterations;
                System.out.println("Find it! ");
                break;
            }
        }

        Iterable<PopularityVariants>  popularityVariants = popularityVariantsRepository.findAll();
        Iterator popularityIterator = popularityVariants.iterator();
        PopularityVariants popularityVariant = null;
        while(popularityIterator.hasNext()){
            PopularityVariants popularityVariantForIterations = (PopularityVariants) popularityIterator.next();

            if(popularityVariantForIterations.getId_performance() == performance.getId()) {
                popularityVariant = popularityVariantForIterations;
                System.out.println("Find it! ");
                break;
            }
        }


        ticketsCostRepository.delete(ticketsCost);
        maxSizeOfConcertRepository.delete(maxSizeOfConcert);
        distributionOfSeatsRepository.delete(distribution);

        popularityVariantsRepository.delete(popularityVariant);
        Concert concert = concertRepository.findById(id).orElseThrow();
        performanceRepository.delete(performance);
        concertRepository.delete(concert);

        return "redirect:/concerts";
    }



    public boolean validation( String place,  String designation, String performanceName,
                               String distributionOfSeats, Long countOfSeats,  Long costOfTicket){

        System.out.println(Pattern.matches("[a-zA-Z\\s]+" , place) + " " +
                Pattern.matches("[a-zA-Z\\s]+" , designation) + " " +
                Pattern.matches("[A-Za-z]{0,30}"  , performanceName) + " " +
                Pattern.matches("[1-9][0-9]*" , countOfSeats.toString()) + " " +
                Pattern.matches("[1-9][0-9]*" , costOfTicket.toString()) + " " +
                ((distributionOfSeats.equals("normal") | distributionOfSeats.equals("unnormal"))));
        if(Pattern.matches("[a-zA-Z\\s]+" , place) &&
                Pattern.matches("[a-zA-Z\\s]+" , designation) &&
                Pattern.matches("[A-Za-z]{0,30}"  , performanceName) &&
                Pattern.matches("[1-9][0-9]*" , countOfSeats.toString()) &&
                Pattern.matches("[1-9][0-9]*" , costOfTicket.toString()) &&
                (distributionOfSeats.equals("normal") | distributionOfSeats.equals("unnormal"))) return true;

        return false;
    }

    public boolean validation( String place, String designation , String distributionOfSeats,
                               Long countOfSeats, Long costOfTicket ,
                               String performanceName ,  String actorName){

        if(Pattern.matches("[a-zA-Z\\s]+" , place) &&
                Pattern.matches("[a-zA-Z\\s]+" , designation) &&
                Pattern.matches("[A-Za-z]{0,30}"  , performanceName) &&
                Pattern.matches("[A-Za-z]{0,30}"  , actorName) &&
                Pattern.matches("[1-9][0-9]*" , countOfSeats.toString()) &&
                Pattern.matches("[1-9][0-9]*" , costOfTicket.toString()) &&
                (distributionOfSeats.equals("normal") | distributionOfSeats.equals("unnormal"))) return true;

        return false;


    }
    public boolean validation(String place, String designation , String distributionOfSeats,
                              Long countOfSeats, Long costOfTicket ){

        if(Pattern.matches("[a-zA-Z\\s]+" , place) &&
                Pattern.matches("[a-zA-Z\\s]+" , designation) &&
                Pattern.matches("[1-9][0-9]*" , countOfSeats.toString()) &&
                Pattern.matches("[1-9][0-9]*" , costOfTicket.toString()) &&
                (distributionOfSeats.equals("normal") | distributionOfSeats.equals("unnormal"))) return true;

        return false;
    }
}
