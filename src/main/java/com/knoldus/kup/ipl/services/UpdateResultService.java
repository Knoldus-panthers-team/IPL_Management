package com.knoldus.kup.ipl.services;

import com.knoldus.kup.ipl.models.Match;
import com.knoldus.kup.ipl.models.PointTable;
import com.knoldus.kup.ipl.models.Team;
import com.knoldus.kup.ipl.repository.MatchRepository;
import com.knoldus.kup.ipl.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class UpdateResultService {
    @Autowired
    PointRepository pointRepository;

    @Autowired
    MatchRepository matchRepository;

    private static final DecimalFormat df = new DecimalFormat("0.000");

    public void updatePointTable(Match match){

        long team1Id = match.getTeam1().getId();
        long team2Id = match.getTeam2().getId();

        Match preMatch = matchRepository.findById(match.getId())
                .orElseThrow(()->new IllegalArgumentException("Invalid match id: "+match.getId()));

        PointTable pointTableTeam1 = pointRepository.findByTeamId(team1Id);
        PointTable pointTableTeam2 = pointRepository.findByTeamId(team2Id);

//     --------------------------   pre run rate-------------------------------------------

        double team1PreOver = Double.parseDouble(match.getTeam1Over());
        double team2PreOver = Double.parseDouble(match.getTeam2Over());;

        int team1PreRuns = Integer.parseInt(preMatch.getTeam1Score());
        int team2PreRuns = Integer.parseInt(preMatch.getTeam2Score());

        double preRunRate1 = team1PreRuns/team1PreOver;
        double preRunRate2 = team2PreRuns/team2PreOver;

//  ---------------------------------------------------------------------
        double team1Over = Double.parseDouble(match.getTeam1Over());
        double team2Over = Double.parseDouble(match.getTeam2Over());;

        int team1Runs= Integer.parseInt(match.getTeam1Score());
        int team2Runs= Integer.parseInt(match.getTeam2Score());

        double runRate1 = team1Runs/team1Over;
        double runRate2 = team2Runs/team2Over;

        int team1Wickets= Integer.parseInt(match.getTeam1Wickets());
        int team2Wickets= Integer.parseInt(match.getTeam2Wickets());

        String result;
        String teamWinner;
        Team tossWinner = match.getTossWinnerTeam();
        String tossChoice = match.getTossChoice();

        if(team1Runs>team2Runs){
            teamWinner = match.getTeam1().getName();
            if(tossChoice.equals("batting")){         //won by runs
                result = teamWinner+" won by "+(team1Runs-team2Runs)+" runs";
            }else { //won by wickets
                result = teamWinner+" won by "+(10-team1Wickets)+" wickets";
            }
        }
        else {
            System.out.println("team2"+tossChoice);
            teamWinner = match.getTeam2().getName();
            if(tossChoice.equals("batting")){       //won by runs
                result = teamWinner+" won by "+(team2Runs-team1Runs)+" runs";
            }else { //won by wickets
                result=teamWinner+" won by "+(10-team2Wickets)+" wickets";
            }
        }

        match.setMatchWinner(teamWinner);
        match.setResult(result);
        matchRepository.save(match);
//----------------------- If Team-1 Wins pre match-------------------------

        if(preMatch.getTeam1().getName().equals(match.getMatchWinner())){
            pointTableTeam1.setWin(pointTableTeam1.getWin()-1);
            pointTableTeam1.setTotalMatch(pointTableTeam1.getTotalMatch()-1);
            pointTableTeam1.setPoints(pointTableTeam1.getPoints()-2);
        }else {
            pointTableTeam1.setTotalMatch(pointTableTeam1.getTotalMatch()-1);
            pointTableTeam1.setLose(pointTableTeam1.getLose()-1);
        }

        if(preMatch.getTeam2().getName().equals(match.getMatchWinner())){
            pointTableTeam2.setWin(pointTableTeam2.getWin()-1);
            pointTableTeam2.setTotalMatch(pointTableTeam2.getTotalMatch()-1);
            pointTableTeam2.setPoints(pointTableTeam2.getPoints()-2);
        }else {
            pointTableTeam2.setTotalMatch(pointTableTeam2.getTotalMatch()-1);
            pointTableTeam2.setLose(pointTableTeam2.getLose()-1);
        }
//   -------------------------------------------------------------------------
        if(match.getTeam1().getName().equals(match.getMatchWinner())){
            pointTableTeam1.setWin(pointTableTeam1.getWin()+1);
            pointTableTeam1.setTotalMatch(pointTableTeam1.getTotalMatch()+1);
            pointTableTeam1.setPoints(pointTableTeam1.getPoints()+2);
        }else {
            pointTableTeam1.setTotalMatch(pointTableTeam1.getTotalMatch()+1);
            pointTableTeam1.setLose(pointTableTeam1.getLose()+1);
        }

        if(match.getTeam2().getName().equals(match.getMatchWinner())){
            pointTableTeam2.setWin(pointTableTeam2.getWin()+1);
            pointTableTeam2.setTotalMatch(pointTableTeam2.getTotalMatch()+1);
            pointTableTeam2.setPoints(pointTableTeam2.getPoints()+2);
            pointRepository.save(pointTableTeam2);
        }else {
            pointTableTeam2.setTotalMatch(pointTableTeam2.getTotalMatch()+1);
            pointTableTeam2.setLose(pointTableTeam2.getLose()+1);
        }

        double preNetRunRateTeam1 =(preRunRate1-preRunRate2) - pointTableTeam1.getNetRunRate();
        pointTableTeam1.setNetRunRate(Double.valueOf(df.format(preNetRunRateTeam1)));

        double preNetRunRateTeam2 = (preRunRate2-preRunRate1) - pointTableTeam2.getNetRunRate();
        pointTableTeam2.setNetRunRate(Double.valueOf(df.format(preNetRunRateTeam2)));

        double netRunRateTeam1 = (runRate1 - runRate2) + pointTableTeam1.getNetRunRate() - preNetRunRateTeam1;
        pointTableTeam1.setNetRunRate(Double.valueOf(df.format(netRunRateTeam1)));

        double netRunRateTeam2 = (runRate2 - runRate1) + pointTableTeam2.getNetRunRate() -preNetRunRateTeam2;
        pointTableTeam2.setNetRunRate(Double.valueOf(df.format(netRunRateTeam2)));

        pointRepository.save(pointTableTeam1);
        pointRepository.save(pointTableTeam2);
    }
}
