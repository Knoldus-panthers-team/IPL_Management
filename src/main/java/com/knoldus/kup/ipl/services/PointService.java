package com.knoldus.kup.ipl.services;

import com.knoldus.kup.ipl.models.Match;
import com.knoldus.kup.ipl.models.PointTable;
import com.knoldus.kup.ipl.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class PointService {

    @Autowired
    private PointRepository pointRepository;

    private static final DecimalFormat df = new DecimalFormat("0.000");

    public PointTable createNewTable(PointTable pointTableTeam){
        PointTable pointTableNew = new PointTable();
        pointTableNew.setTotalMatch(0);
        pointTableNew.setWin(0);
        pointTableNew.setLose(0);
        pointTableNew.setPoints(0);
        pointTableNew.setNetRunRate(0);
        return pointTableNew;
    }

    public void addPointTable(Match match){

        double team1Over = Double.parseDouble(match.getTeam1Over());
        double team2Over = Double.parseDouble(match.getTeam2Over());;

        long team1Id = match.getTeam1().getId();
        long team2Id = match.getTeam2().getId();

        PointTable pointTableTeam1 = pointRepository.findByTeamId(team1Id);
        PointTable pointTableTeam2 = pointRepository.findByTeamId(team2Id);

        int team1Runs= Integer.parseInt(match.getTeam1Score());
        int team2Runs = Integer.parseInt(match.getTeam2Score());

        double runRate1 = team1Runs/team1Over;
        double runRate2 = team2Runs/team2Over;

        if (pointTableTeam1==null) {
            pointTableTeam1 = this.createNewTable(pointTableTeam1);
            pointTableTeam1.setTeam(match.getTeam1());
            pointRepository.save(pointTableTeam1);
        }

        if (pointTableTeam2==null){
            pointTableTeam2 = this.createNewTable(pointTableTeam2);
            pointTableTeam2.setTeam(match.getTeam2());
        }

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
        }else {
            pointTableTeam2.setTotalMatch(pointTableTeam2.getTotalMatch()+1);
            pointTableTeam2.setLose(pointTableTeam2.getLose()+1);
        }

        double netRunRateTeam1 = (runRate1 - runRate2) + pointTableTeam1.getNetRunRate();
        pointTableTeam1.setNetRunRate(Double.valueOf(df.format(netRunRateTeam1)));

        double netRunRateTeam2 = (runRate2 - runRate1) + pointTableTeam2.getNetRunRate();
        pointTableTeam2.setNetRunRate(Double.valueOf(df.format(netRunRateTeam2)));

        pointRepository.save(pointTableTeam1);
        pointRepository.save(pointTableTeam2);
    }

    public List<PointTable> getAllTables(){
        return (List<PointTable>) pointRepository.findAll();
    }
    public PointTable getById(Long id){
        return pointRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Id"+id));
    }
}
