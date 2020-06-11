public class ReaderUserRatedMovies extends Reader
{

    public static final String QUERY = "insert into user_ratedmovies (user_id, movie_id, rating, date_day, date_month, date_year, date_hour, date_minute, date_second) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public ReaderUserRatedMovies(String file)
    {
        super(file);
    }

    public String[][] getItemsToInsert()
    {
        String[][] insertItems = new String[this.lines.size()][9];
        for(int i = 0; i < this.lines.size(); i++)
        {
            String[] tokens = this.lines.get(i).split("\t");
            insertItems[i][0] = tokens[0];
            insertItems[i][1] = tokens[1];
            insertItems[i][2] = tokens[2];
            insertItems[i][3] = tokens[3];
            insertItems[i][4] = tokens[4];
            insertItems[i][5] = tokens[5];
            insertItems[i][6] = tokens[6];
            insertItems[i][7] = tokens[7];
            insertItems[i][8] = tokens[8];
        }
        return insertItems;
    }
}
