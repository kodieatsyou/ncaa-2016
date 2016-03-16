import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class BracketRunner {
	public static void main(String[] args) {
		
		ArrayList<Team> teams= new ArrayList<Team>();
		
		try {
			File file = new File("ncaa_data_2016.csv");
			Scanner input = new Scanner(file);
			input.nextLine();
			
			while (input.hasNextLine()) {
				String[]line = input.nextLine().split(",");
				line[2].replaceAll("\\s","");
				teams.add(new Team(line[0], Double.parseDouble(line[1]), line[2].toLowerCase(), Integer.parseInt(line[3])));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		ArrayList<Team> east = new ArrayList<Team>();
		ArrayList<Team> west = new ArrayList<Team>();
		ArrayList<Team> south = new ArrayList<Team>();
		ArrayList<Team> midwest = new ArrayList<Team>();
		
		for (Team t : teams) {
			if(t.region.equals("east")) {
				east.add(t);
			} else if (t.region.equals("west")) {
				west.add(t);
			}else if (t.region.equals("midwest")) {
				midwest.add(t);
			}else if (t.region.equals("south")) {
				south.add(t);
			}
		}
		Team eastWinner = regionWinner(east);
		Team westWinner = regionWinner(west);
		Team midwestWinner = regionWinner(midwest);
		Team southWinner = regionWinner(south);
		Team semi1 = compareTeams(eastWinner, midwestWinner);
		Team semi2 = compareTeams(southWinner, westWinner);
		Team champion = compareTeams(semi1, semi2);
		System.out.println("The Winner Is: " + champion.name);
	}
	
	public static Team regionWinner(ArrayList<Team> region) {
		ArrayList<Team> winners = new ArrayList<Team>();
		ArrayList<Team> secondRound = new ArrayList<Team>();
		ArrayList<Team> sweetSixteen = new ArrayList<Team>();
		winners.add(compareTeams(region.get(0), region.get(15)));
		winners.add(compareTeams(region.get(7), region.get(8)));
		winners.add(compareTeams(region.get(4), region.get(13)));
		winners.add(compareTeams(region.get(3), region.get(12)));
		winners.add(compareTeams(region.get(5), region.get(10)));
		winners.add(compareTeams(region.get(2), region.get(13)));
		winners.add(compareTeams(region.get(6), region.get(9)));
		winners.add(compareTeams(region.get(1), region.get(14)));
		for(int t = 0; t < winners.size(); t += 2) {
			secondRound.add(compareTeams(winners.get(t), winners.get(t + 1)));
		}
		for(int t = 0; t < secondRound.size(); t += 2) {
			sweetSixteen.add(compareTeams(secondRound.get(t), secondRound.get(t + 1)));
		}
		return compareTeams(sweetSixteen.get(0), sweetSixteen.get(1));
	}
	
	public static Team compareTeams(Team team1, Team team2) {
		double pa = team1.winPercent;
		double pb = team2.winPercent;
		double sum = ((pa * pb) - pa) / (2 *(pa * pb) - (pa + pb));
		double randomCheck = Math.random();
		if (sum <= randomCheck) {
			return team1;
		} else {
			return team2;
		}
	}
}
