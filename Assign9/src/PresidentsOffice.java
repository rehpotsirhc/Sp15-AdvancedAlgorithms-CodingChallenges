import com.sun.prism.image.Coords;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Chris Johnson on 3/18/2015.
 */
public class PresidentsOffice {




    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");

        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        char c = line[2].charAt(0);
       List<Integer> presDeskRows = new ArrayList<Integer>();
        List<Integer> presDeskColumns = new ArrayList<Integer>();
        HashSet<Character> adjDesks = new HashSet<Character>();

        char empty = '.';

        char[][] office = new char[n][m];

        String row;
        char coord;
        for(int i = 0; i < n;i++)
        {
            row = br.readLine();
            for(int j = 0; j < m; j++)
            {
                coord = row.charAt(j);
                if(coord == c)
                {
                    presDeskRows.add(i);
                    presDeskColumns.add(j);
                }
                office[i][j] = coord;
            }
        }


        for(Integer r : presDeskRows)
        {
            for(int j = 0; j < office[0].length; j++)
            {
                if(office[r][j] == c)
                {
                    if(r > 0)
                    {
                        coord = office[r -1][j];
                        if(coord!=c && coord != empty && !adjDesks.contains(coord)) adjDesks.add(coord);
                    }
                    if(r < office.length - 1)
                    {
                        coord = office[r +1][j];
                        if(coord!=c && coord != empty && !adjDesks.contains(coord)) adjDesks.add(coord);
                    }
                }

            }
        }

        for(Integer col : presDeskColumns)
        {
            for(int i = 0; i < office.length; i++)
            {
                if(office[i][col] == c)
                {
                    if(col > 0)
                    {
                        coord = office[i][col-1];
                        if(coord!=c && coord != empty && !adjDesks.contains(coord)) adjDesks.add(coord);
                    }
                    if(col < office[0].length - 1)
                    {
                        coord = office[i][col+1];
                        if(coord!=c && coord != empty && !adjDesks.contains(coord)) adjDesks.add(coord);
                    }
                }

            }
        }

        System.out.println(adjDesks.size());



    }
}




