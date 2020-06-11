import org.json.simple.JSONValue;
import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Arrays;

public class TheBombFlicks
{
	public static void main(String[] args)
	{
		Database db = new Database();
		if(args[0].equals("top_movies"))
		{
			List retval = db.getTopMovies(Integer.parseInt(args[1]));
			String jsonString = JSONValue.toJSONString(retval);
			System.out.print(jsonString);
		}
		if(args[0].equals("get_movie"))
		{
			List retval = db.getMovie(args[1].replaceAll("_"," "), Integer.parseInt(args[2]));
			String jsonString = JSONValue.toJSONString(retval);
			System.out.print(jsonString);
		}
		if(args[0].equals("get_genre"))
		{
			List retval = db.getGenre(args[1].replaceAll("_"," "), Integer.parseInt(args[2]));
			String jsonString = JSONValue.toJSONString(retval);
			System.out.print(jsonString);
		}
		if(args[0].equals("get_director"))
		{
			List retval = db.getDirector(args[1].replaceAll("_"," "), Integer.parseInt(args[2]));
			String jsonString = JSONValue.toJSONString(retval);
			System.out.print(jsonString);
		}
		if(args[0].equals("get_actor"))
		{
			List retval = db.getActor(args[1].replaceAll("_"," "), Integer.parseInt(args[2]));
			String jsonString = JSONValue.toJSONString(retval);
			System.out.print(jsonString);
		}
		if(args[0].equals("get_tag"))
		{
			List retval = db.getTag(args[1].replaceAll("_"," "), Integer.parseInt(args[2]));
			String jsonString = JSONValue.toJSONString(retval);
			System.out.print(jsonString);
		}
		if(args[0].equals("top_directors"))
		{
			List retval = db.getTopDirectors(Integer.parseInt(args[1]));
			String jsonString = JSONValue.toJSONString(retval);
			System.out.print(jsonString);
		}
		if(args[0].equals("top_actors"))
		{
			List retval = db.getTopActors(Integer.parseInt(args[1]));
			String jsonString = JSONValue.toJSONString(retval);
			System.out.print(jsonString);
		}
		if(args[0].equals("getTagsFromMovie"))
		{
			List retval = db.getTagsFromMovie(args[1].replaceAll("_"," "), Integer.parseInt(args[2]));
			String jsonString = JSONValue.toJSONString(retval);
			System.out.print(jsonString);
		}
		if(args[0].equals("getMoviesByCountry"))
		{
			List retval = db.getMoviesByCountry(args[1].replaceAll("_"," "), Integer.parseInt(args[2]));
			String jsonString = JSONValue.toJSONString(retval);
			System.out.print(jsonString);
		}
		if(args[0].equals("getRatingsFromMovie"))
		{
			List retval = db.getRatingsFromMovie(args[1].replaceAll("_"," "), Integer.parseInt(args[2]));
			String jsonString = JSONValue.toJSONString(retval);
			System.out.print(jsonString);
		}
		if(args[0].equals("getUserRatedMovies"))
		{
			List retval = db.getUserRatedMovies(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
			String jsonString = JSONValue.toJSONString(retval);
			System.out.print(jsonString);
		}
	}
}
