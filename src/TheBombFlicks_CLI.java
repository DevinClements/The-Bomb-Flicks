import java.util.Scanner;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

public class TheBombFlicks_CLI
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        String username = null;
        String password = null;
        String databaseName = null;

        System.out.println("What is the name of the database you would like to connect to?");
        databaseName = scan.next();
        System.out.println("Did you set a username and password for " + databaseName + "? (yes/no)");
        String answer = scan.next();
        if(answer.equals("yes"))
        {
            System.out.println("What is your username?");
            username = scan.next();
            System.out.println("What is your password?");
            password = scan.next();
        }
        Database db;
        if(username == null || password == null)
        {
            db = new Database();
        }
        else
        {
            db = new Database(username, password, databaseName, true);
        }

        System.out.println("\n\nWelcome to TheBombFlicks.");
        while(true)
        {
            System.out.println("[1] Top Popular Movies");
            System.out.println("[2] Search by Title");
            System.out.println("[3] Search by Genre");
            System.out.println("[4] Search by Director");
            System.out.println("[5] Search by Actor");
            System.out.println("[6] Search by Tag");
            System.out.println("[7] Top 20 Directors");
            System.out.println("[8] Top 20 Actors");
            System.out.println("[9]");
            System.out.println("[10]");
            System.out.println("[0] Exit");

            int command = 99;
            command = scan.nextInt();
            scan.nextLine();

            LinkedList<HashMap<String,String>> result;
            switch(command)
            {
                case 1:     
                            System.out.println("Enter a Number of Movie Here.");
                            int movieNum = Integer.parseInt( scan.nextLine() );
                            result = db.getTopMovies(movieNum);
                            for(HashMap<String,String> map : result)
                            {
                                for(String key : map.keySet())
                                {
                                    System.out.printf("%s\t\t", map.get(key));
                                }
                                System.out.println();
                            }
                            break;
                case 2:     
                            System.out.println("Enter a movie title.");
                            String title = scan.nextLine();
                            result = db.getMovie(title, 1);
                            for(HashMap<String,String> map : result)
                            {
                                for(String key : map.keySet())
                                {
                                    System.out.printf("%s\t\t", map.get(key));   
                                }
                                System.out.println();
                            }
                            break;
                case 3:     
                            System.out.println("Enter a genre.");
                            String genre = scan.nextLine();
                            System.out.println("Enter Number of Movie Here.");
                            int genreNum = Integer.parseInt ( scan.nextLine() );
                            result = db.getGenre(genre, genreNum);
                            for(HashMap<String,String> map : result)
                            {
                                for(String key : map.keySet())
                                {
                                    System.out.printf("%s\t\t", map.get(key));   
                                }
                                System.out.println();
                            }
                            break;
                case 4:     
                            System.out.println("Enter a director name.");
                            String directorName = scan.nextLine();
                            result = db.getDirector(directorName, command);
                            for(HashMap<String,String> map : result)
                            {
                                for(String key : map.keySet())
                                {
                                    System.out.printf("%s\t\t", map.get(key));   
                                }
                                System.out.println();
                            }
                            break;
                case 5:     
                            System.out.println("Enter an actor name.");
                            String actorName = scan.nextLine();
                            result = db.getActor(actorName, command);
                            for(HashMap<String,String> map : result)
                            {
                                for(String key : map.keySet())
                                {
                                    System.out.printf("%s\t\t", map.get(key));   
                                }
                                System.out.println();
                            }
                            break;
                case 6:     
                            System.out.println("Enter a tag.");
                            String tagName = scan.nextLine();
                            result = db.getTag(tagName, command);
                            for(HashMap<String,String> map : result)
                            {
                                for(String key : map.keySet())
                                {
                                    System.out.printf("%s\t\t", map.get(key));   
                                }
                                System.out.println();
                            }
                            break;
                case 7:     
                            result = db.getTopDirectors(20);
                            for(HashMap<String,String> map : result)
                            {
                                for(String key : map.keySet())
                                {
                                    System.out.printf("%s\t\t", map.get(key));
                                }
                                System.out.println();
                            }
                            break;
                case 8:     
                            result = db.getTopActors(20);
                            for(HashMap<String,String> map : result)
                            {
                                for(String key : map.keySet())
                                {
                                    System.out.printf("%s\t\t", map.get(key));
                                }
                                System.out.println();
                            }
                            break;
                case 9:
                            System.out.println("Enter User Name Here.");
                            String userName = scan.nextLine();
                            result = db.getTag(userName, command);
                            for(HashMap<String, String> map : result)
                            {
                                for(String key : map.keySet())
                                {
                                    System.out.printf("%s\t\t", map.get(key));
                                }
                                System.out.println();
                            }
                            break;

                case 10:
                            System.out.println("Enter Name of Movie");
                            String movieName = scan.nextLine();
                            result = db.getTag(movieName, command);
                            for(HashMap<String, String> map : result)
                            {
                                for(String key : map.keySet())
                                {
                                    System.out.printf("%s\t\t", map.get(key));
                                }
                                System.out.println();
                            }
                            break;
                            
                case 11:
                            System.out.println("Enter Country");
                            String countryName = scan.nextLine();
                            result = db.getTag(countryName, command);
                            for(HashMap<String, String> map : result)
                            {
                                for(String key : map.keySet())
                                {
                                    System.out.printf("%s\t\t", map.get(key));  
                                }
                                System.out.println();
                            }
                            break;

                case 12:
                            System.out.println("Enter username");
                            userName = scan.nextLine();
                            result = db.getTag(userName, command);
                            for(HashMap<String, String> map: result)
                            {
                                for(String key : map.keySet())
                                {
                                    System.out.printf("%s\t\t", map.get(key));
                                }
                                System.out.println();
                            }
                            break;
                case 13:
                			System.out.println("Enter username");
                    		userName = scan.nextLine();
                            result = db.getActor(userName, command);
                            for(HashMap<String,String> map : result)
                            {
                                for(String key : map.keySet())
                                {
                                    System.out.printf("%s\t\t", map.get(key));
                                }
                                System.out.println();
                            }
                            break;


                case 0:     System.out.println("Exiting...");
                            System.exit(0);
                default:    System.out.println("Not a valid number.");
                            continue;
            }
            System.out.printf("\n\n\n");
        }
    }
}