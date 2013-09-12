package runkeeperclient.model;

/**
 * {"strength_training_activities":"/strengthTrainingActivities","weight":"/weight",
 * "settings":"/settings","diabetes":"/diabetes","team":"/team","sleep":"/sleep",
 * "fitness_activities":"/fitnessActivities","change_log":"/changeLog",
 * "userID":8146463,"nutrition":"/nutrition","general_measurements":"/generalMeasurements","background_activities":"/backgroundActivities","records":"/records","profile":"/profile"}
 */
public class User {

    private String strength_training_activities;
    private String weight;
    private String settings;
    private String diabetes;
    private String team;
    private String sleep;
    private String fitness_activities;
    private String change_log;
    private Long userID;
    private String nutrition;
    private String general_measurements;
    private String background_activities;
    private String records;
    private String profile;

    public String getStrengthTrainingActivities() {
        return strength_training_activities;
    }

    public String getWeight() {
        return weight;
    }

    public String getSettings() {
        return settings;
    }

    public String getDiabetes() {
        return diabetes;
    }

    public String getTeam() {
        return team;
    }

    public String getSleep() {
        return sleep;
    }

    public String getFitnessActivities() {
        return fitness_activities;
    }

    public String getChangeLog() {
        return change_log;
    }

    public Long getUserID() {
        return userID;
    }

    public String getNutrition() {
        return nutrition;
    }

    public String getGeneralMeasurements() {
        return general_measurements;
    }

    public String getBackgroundActivities() {
        return background_activities;
    }

    public String getRecords() {
        return records;
    }

    public String getProfile() {
        return profile;
    }

    @Override
    public String toString() {
        return "User{" +
                "strength_training_activities='" + strength_training_activities + '\'' +
                ", weight='" + weight + '\'' +
                ", settings='" + settings + '\'' +
                ", diabetes='" + diabetes + '\'' +
                ", team='" + team + '\'' +
                ", sleep='" + sleep + '\'' +
                ", fitness_activities='" + fitness_activities + '\'' +
                ", change_log='" + change_log + '\'' +
                ", userID=" + userID +
                ", nutrition='" + nutrition + '\'' +
                ", general_measurements='" + general_measurements + '\'' +
                ", background_activities='" + background_activities + '\'' +
                ", records='" + records + '\'' +
                ", profile='" + profile + '\'' +
                '}';
    }
}
