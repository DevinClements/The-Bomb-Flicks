public class ReaderDirector extends Reader
{
    public static final String QUERY = "insert into movie_director (movie_id, director_id, director_name) values (?, ?, ?)";

    public ReaderDirector(String file)
    {
        super(file);
    }

    public String[][] getItemsToInsert()
    {
        String[][] insertItems = new String[this.lines.size()][3];
        for(int i = 0; i < this.lines.size(); i++)
        {
            String[] tokens = this.lines.get(i).split("\t");
            insertItems[i][0] = tokens[0];
            insertItems[i][1] = tokens[1];
            insertItems[i][2] = tokens[2];
        }
        return insertItems;
    }
}
