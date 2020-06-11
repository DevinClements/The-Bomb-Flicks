public class ReaderGenre extends Reader
{
    public static final String QUERY = "insert into movie_genre (movie_id, genre) values (?, ?)";

    public ReaderGenre(String file)
    {
        super(file);
    }

    public String[][] getItemsToInsert()
    {
        String[][] insertItems = new String[this.lines.size()][2];
        for(int i = 0; i < this.lines.size(); i++)
        {
            String[] tokens = this.lines.get(i).split("\t");
            insertItems[i][0] = tokens[0];
            insertItems[i][1] = tokens[1];
        }
        return insertItems;
    }
}
