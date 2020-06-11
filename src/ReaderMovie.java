public class ReaderMovie extends Reader
{
    public static final String QUERY = "INSERT INTO movie (id, title_english, title_spanish, image_url_imdb, year, rt_critic_rating, rt_critic_num_reviews, rt_critic_num_fresh, rt_critic_num_rotten, rt_critic_score, rt_critic_top_rating, rt_critic_top_num_reviews, rt_critic_top_num_fresh, rt_critic_top_num_rotten, rt_critic_top_score, rt_audience_rating, rt_audience_num_rating, rt_audience_score, image_url_rt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public ReaderMovie(String file)
    {
        super(file);
    }

    public String[][] getItemsToInsert()
    {
        String[][] insertItems = new String[this.lines.size()][19];
        for(int i = 0; i < this.lines.size(); i++)
        {
            String[] tokens = this.lines.get(i).split("\t");
            insertItems[i][0] = tokens[0];
            insertItems[i][1] = tokens[1];
            insertItems[i][2] = tokens[3];
            insertItems[i][3] = tokens[4];
            insertItems[i][4] = tokens[5];
            insertItems[i][5] = tokens[7];
            insertItems[i][6] = tokens[8];
            insertItems[i][7] = tokens[9];
            insertItems[i][8] = tokens[10];
            insertItems[i][9] = tokens[11];
            insertItems[i][10] = tokens[12];
            insertItems[i][11] = tokens[13];
            insertItems[i][12] = tokens[14];
            insertItems[i][13] = tokens[15];
            insertItems[i][14] = tokens[16];
            insertItems[i][15] = tokens[17];
            insertItems[i][16] = tokens[18];
            insertItems[i][17] = tokens[19];
            insertItems[i][18] = tokens[20];
        }
        return insertItems;
    }
}
