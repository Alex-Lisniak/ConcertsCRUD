package com.kpi.spring_architecture_project;


import org.springframework.stereotype.Component;

@Component
public class AchievementsGenerator {
    public static String arrayOfStarters[] = {" clearest ", " best ", " most exciting ", " most popular "};
    public static String arrayOfMiddles[] = {" role ", " voice ", " eyes ", " arms "};
    public static String arrayOfEnds[] = {" in cinema ", " forever ", " humanity sees ", " of America "};

    public static String generateAnAchievement() {

        StringBuilder string = new StringBuilder("The ");

        int choice = (int) (Math.random() * arrayOfStarters.length);
        string.append(arrayOfStarters[choice]);

        choice = (int) (Math.random() * arrayOfMiddles.length);
        string.append(arrayOfMiddles[choice]);

        choice = (int) (Math.random() * arrayOfEnds.length);
        string.append(arrayOfEnds[choice]);
        return string.toString();
    }

}
