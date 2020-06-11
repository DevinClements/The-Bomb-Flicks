import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public abstract class Reader
{
    BufferedReader reader;
    ArrayList<String> lines;

    public Reader(String file)
    {
        //Initialize reader and list for lines
        this.lines = new ArrayList<String>();

        try
        {
            //Load file into reader
            this.reader = new BufferedReader(new FileReader(file));
        }
        catch(Exception e)
        {
            //Error Handling
            this.reader = null;
            System.err.println(e);
            System.exit(1);
        }

        try
        {
            boolean isFirstLine = true; //Check if we are at the first line

            //Load each line into array list
            String line;
            while((line = this.reader.readLine()) != null)
            {
                //Skip first line with irrelevant information
                if(isFirstLine)
                {
                    isFirstLine = false;
                    continue;
                }

                this.lines.add(line);
            }
        }
        catch(Exception e)
        {
            //Error Handling
            System.err.println(e);
            System.exit(1);
        }
    }
    
    public abstract String[][] getItemsToInsert();
}
