public class ReaderCountry extends Reader
{
    public static final String QUERY  = "insert into country (movie_id, country) values (?, ?)";

    public ReaderCountry(String file)
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
        }
        return insertItems;
    }
}
