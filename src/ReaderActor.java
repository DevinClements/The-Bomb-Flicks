public class ReaderActor extends Reader
{
    public static final String QUERY = "insert into movie_actor (movie_id, actor_id, actor_name, ranking) values (?, ?, ?, ?)";

    public ReaderActor(String file)
    {
        super(file);
    }

    public String[][] getItemsToInsert()
    {
        String[][] insertItems = new String[this.lines.size()][4];
        for(int i = 0; i < this.lines.size(); i++)
        {
            String[] tokens = this.lines.get(i).split("\t");
            insertItems[i][0] = tokens[0];
            insertItems[i][1] = tokens[1];
            insertItems[i][2] = tokens[2];
            insertItems[i][3] = tokens[3];
        }
        return insertItems;
    }
}
