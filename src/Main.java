import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.regex.Matcher;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        String[][] movies;
        String[][] actors;
        String[][] directors;
        String[][] genres;
        String[][] tags;
        String[][] tagItems;
        String[][] userRatedMovies;
        String[][] countries;
        String[] queries = new String[8];
        boolean isWritingFile = false;

        System.out.println("Would you like to create Sql files? (yes/no)");
        String answer = scan.next();
        if(answer.equals("yes"))
        {
            isWritingFile = true;
        }

        System.out.println("Parsing movies.dat");
        Reader reader = new ReaderMovie("../dataset/movies.dat");
        movies = reader.getItemsToInsert();
        queries[0] = ((ReaderMovie) reader).QUERY;

        System.out.println("Parsing movie_actors.dat");
        reader = new ReaderActor("../dataset/movie_actors.dat");
        actors = reader.getItemsToInsert();
        queries[1] = ((ReaderActor) reader).QUERY;

        System.out.println("Parsing movie_directors.dat");
        reader = new ReaderDirector("../dataset/movie_directors.dat");
        directors = reader.getItemsToInsert();
        queries[2] = ((ReaderDirector) reader).QUERY;

        System.out.println("Parsing movie_genres.dat");
        reader = new ReaderGenre("../dataset/movie_genres.dat");
        genres = reader.getItemsToInsert();
        queries[3] = ((ReaderGenre) reader).QUERY;

        System.out.println("Parsing movie_tags.dat");
        reader = new ReaderTag("../dataset/movie_tags.dat");
        tags = reader.getItemsToInsert();
        queries[4] = ((ReaderTag) reader).QUERY;

        System.out.println("Parsing tags.dat");
        reader = new ReaderTagItem("../dataset/tags.dat");
        tagItems = reader.getItemsToInsert();
        queries[5] = ((ReaderTagItem) reader).QUERY;

        System.out.println("Parsing user_ratedmovies.dat");
        reader = new ReaderUserRatedMovies("../dataset/user_ratedmovies.dat");
        userRatedMovies = reader.getItemsToInsert();
        queries[6] = ((ReaderUserRatedMovies) reader).QUERY;

        System.out.println("Parsing movie_countries.dat");
        reader = new ReaderCountry("../dataset/movie_countries.dat");
        countries = reader.getItemsToInsert();
        queries[7] = ((ReaderCountry) reader).QUERY;

        // find unique actors
        System.out.println("Filtering unique actors");
        HashMap<String, String> map = new HashMap<String, String>();
        for(int i = 0; i < actors.length; i++)
        {
            if(map.containsKey(actors[i][1]))
            {
                continue;
            }
            map.put(actors[i][1], actors[i][2]);
        }

        // convert actors map to 2D array
        String[][] actors_single = new String[map.size()][2];
        int index = 0;
        for (String key : map.keySet())
        {
            actors_single[index][0] = key;
            actors_single[index][1] = map.get(key);
            index++;
        }

        // find unique directors
        System.out.println("Filtering unique directors");
        map = new HashMap<String,String>();
        for(int i = 0; i < directors.length; i++)
        {
            if(map.containsKey(directors[i][1]))
            {
                continue;
            }
            map.put(directors[i][1], directors[i][2]);
        }
        // convert directors map to 2D array
        String[][] directors_single = new String[map.size()][2];
        index = 0;
        for(String key : map.keySet())
        {
            directors_single[index][0] = key;
            directors_single[index][1] = map.get(key);
            index++;
        }

        if(isWritingFile)
        {
            createSqlFile(movies, "movie", queries[0]);
            createSqlFile(actors, "movie_actor", queries[1]);
            createSqlFile(directors, "movie_director", queries[2]);
            createSqlFile(genres, "movie_genre", queries[3]);
            createSqlFile(tags, "movie_tag", queries[4]);
            createSqlFile(tagItems, "tag", queries[5]);
            createSqlFile(userRatedMovies, "user_ratedmovies", queries[6]);
            createSqlFile(countries, "country", queries[6]);
            createSqlFile(actors_single, "actor", "insert into actor (id, actor_name) values (?,?)");
            createSqlFile(directors_single, "director", "insert into director (id, director_name) values (?,?)");
        }
        

        // get username and password
        System.out.println("Have you set a password for mysql? (yes/no)");
        String username = null;
        String password = null;
        String database_name = null;
        if(scan.next().equals("yes"))
        {
            System.out.println("What is your username?");
            username = scan.next();
            System.out.println("What is your password?");
            password = scan.next();
            System.out.println("What is the name of the database that you would like to connect to or create?");
            database_name = scan.next();
        }

        // create instance of database
        Database db;
        if(username != null && password != null && database_name != null)
        {
            db = new Database(username, password, database_name);
        }
        else
        {
            db = new Database();
        }

        System.out.println("Would you like to insert data files into database. This may take a while. (yes/no)");
        answer = scan.next();
        if(answer.equals("no"))
        {
            System.out.println("Ending program.");
            System.exit(0);
        }

        System.out.println("Inserting into movie table...");
        db.executeBatch(queries[0], movies, 2000);
        System.out.println("Inserting into actor table...");
        db.executeBatch("insert into actor (id, actor_name) values (?,?)", actors_single, 2000);
        System.out.println("Inserting into director table...");
        db.executeBatch("insert into director (id, director_name) values (?,?)", directors_single, 2000);
        System.out.println("Inserting into tag table...");
        db.executeBatch(queries[5], tagItems, 1000);
        System.out.println("Inserting into movie_actor table...");
        db.executeBatch(queries[1], actors, 2000);
        System.out.println("Inserting into movie_director table...");
        db.executeBatch(queries[2], directors, 2000);
        System.out.println("Inserting into movie_genres table...");
        db.executeBatch(queries[3], genres, 2000);
        System.out.println("Inserting into movie_tags table...");
        db.executeBatch(queries[4], tags, 2000);
        System.out.println("Inseting into user_ratedmovies table...");
        db.executeBatch(queries[6], userRatedMovies, 2000);
        System.out.println("Inseting into country table...");
        db.executeBatch(queries[7], countries, 2000);

        System.out.println("Database has been set up.");
    }

    private static boolean createSqlFile(String[][] table, String filename, String QUERY)
    {
        File sql_directory = new File("../sql");
        if(!sql_directory.exists())
        {
            if(sql_directory.mkdir())
            {
                System.out.println("Sql Directory Created.");
            }
            else
            {
                System.out.println("Could not create sql directory.");
                return false;
            }
        }

        File sql_file = new File("../sql/" + filename + ".sql");
        try
        {
            System.out.println("Creating " + filename + ".sql");
            sql_file.createNewFile();
        }
        catch(Exception e)
        {
            System.out.println("Could not create " + filename + ".sql");
            return false;
        }

        try
        {
            FileWriter fileWriter = new FileWriter(sql_file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            System.out.println("Writing to " + filename + ".sql");
            for(int i = 0; i < table.length; i++)
            {
                String temp_QUERY = QUERY;
                for(int j = 0; j < table[i].length; j++)
                {
                    temp_QUERY = temp_QUERY.replaceFirst("\\?", "'" + Matcher.quoteReplacement(table[i][j].replaceAll("'","\\\\'")) + "'").replaceAll("'\\\\N'", "null");
                }
                try
                {
                    writer.write(temp_QUERY);
                    writer.newLine();
                }
                catch(Exception e)
                {
                    System.out.println("Did not write:\t" + temp_QUERY);
                }
            }
            writer.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
            return false;
        }
        System.out.println("Finished writing to "  + filename + ".sql");
        return true;
    }
}
