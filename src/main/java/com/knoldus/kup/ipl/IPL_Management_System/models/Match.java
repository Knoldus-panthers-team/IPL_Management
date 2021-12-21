package com.knoldus.kup.ipl.IPL_Management_System.models;

import com.knoldus.kup.ipl.IPL_Management_System.validation.VenueValidate;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    private Long id;

//    @NotNull(message = "This filed is required")
    private String matchDate;

//    @DateTimeFormat(pattern = "dd/MM/yyyy")
//    private Date birtdate;

    private String team1Wickets;
    private String team2Wickets;

    //    private String tossDecision;
    private String matchWinner;
    private String result;
    private String team1Score;
    private String team2Score;

    @Transient
    private String team1FinalScore;

    @Transient
    private String team2FinalScore;

    private String tossChoice;

    @ManyToOne
    @JoinColumn(name = "tossWinner_id")
    private Team tossWinnerTeam;

    private String team1Over;
    private String team2Over;

//    private String playerOfMatch;

    @ManyToOne
    @JoinColumn(name = "venue_id")
//    @VenueValidate(message = "This slot is booked")
    private Venue venue;

    @ManyToOne
    @JoinColumn(name = "team_id1")
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "team_id2")
    private Team team2;

    public Match(Long id, String matchDate, Venue venue, Team team1, Team team2) {
        this.id = id;
        this.matchDate = matchDate;
        this.venue = venue;
        this.team1 = team1;
        this.team2 = team2;
    }
    public Match(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        if(!matchDate.contains("PM") && !matchDate.contains("AM")){
            Date parsedDate = null;
            try {
                parsedDate = inputFormat.parse(matchDate);
                String formattedDate = outputFormat.format(parsedDate);
                this.matchDate = formattedDate;
                System.out.println("hello---------------------------"+formattedDate+"  ----"+this.matchDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else {
            this.matchDate = matchDate;
        }
//        this.matchDate = matchDate;
    }

    public String getTeam1Wickets() {
        return team1Wickets;
    }

    public void setTeam1Wickets(String team1Wickets) {
        this.team1Wickets = team1Wickets;
    }

    public String getTeam2Wickets() {
        return team2Wickets;
    }

    public void setTeam2Wickets(String team2Wickets) {
        this.team2Wickets = team2Wickets;
    }

    public String getMatchWinner() {
        return matchWinner;
    }

    public void setMatchWinner(String matchWinner) {
        this.matchWinner = matchWinner;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTeam1Score() {
        return team1Score;
    }

    public void setTeam1Score(String team1Score) {
        this.team1Score = team1Score;
    }

    public String getTeam2Score() {
        return team2Score;
    }

    public void setTeam2Score(String team2Score) {
        this.team2Score = team2Score;
    }

    public String getTeam1Over() {
        String OverTeam1Over = team1Over;
        System.out.println("-------------------------------------"+ OverTeam1Over == null);
        if(OverTeam1Over == null){
            return "Yet to be played";
        }
        return team1Over;
    }

    public void setTeam1Over(String team1Over) {
        //        if (!team1Over.equals(null)){
//            this.team1Over = team1Over;
//        }
//        this.team1Over = "Yet to bat";
        this.team1Over = team1Over;
    }

//    public String getTeam1FinalScore(){
//        String score = getTeam1Score();
//        String wickets = getTeam1Wickets();
//        System.out.println("-------------------------------------"+ score +"--------"+wickets);
//        if(score != null){
//            return score+"/"+wickets;
//        }
//        return "empty";
//    }

    public String getTeam2Over() {
        String OverTeam2Over = team2Over;
        System.out.println("-------------------------------------"+ OverTeam2Over == null);
        if(OverTeam2Over == null){
            return "Yet to be played";
        }
        return team2Over;
    }

    public void setTeam2Over(String team2Over) {
        this.team2Over = team2Over;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public Team getTossWinnerTeam() {
        return tossWinnerTeam;
    }

    public void setTossWinnerTeam(Team tossWinnerTeam) {
        this.tossWinnerTeam = tossWinnerTeam;
    }

    public String getTossChoice() {
        return tossChoice;
    }

    public void setTossChoice(String tossChoice) {
        this.tossChoice = tossChoice;
    }

    public String getTeam1FinalScore() {
        String score = getTeam1Score();
        String wickets = getTeam1Wickets();
        System.out.println("-------------------------------------"+ score +"--------"+wickets);
        if(score != null){
            return this.team1FinalScore = score+"/"+wickets;
        }
        return this.team1FinalScore= " ";
    }
    public String getTeam2FinalScore() {
        String score = getTeam2Score();
        String wickets = getTeam2Wickets();
        System.out.println("-------------------------------------"+ score +"--------"+wickets);
        if(score != null){
            return this.team2FinalScore = score+"/"+wickets;
        }
        return this.team2FinalScore= " ";
    }
}
