//Number of Islands 2 using Disjoint sets


import java.util.*;

public class dsu {

    class Point 
    {
        int x;
        int y;
        Point()
        {
            x = 0; y = 0;
        }
        Point(int a, int b)
        {
            x = a; y = b;
        }
    }

    int[] par;
    int[] rank;
    int sets;

    public int find(int a)
    {
        if(par[a]==a)
            return a;
        int temp = find(par[a]);
        par[a]=temp;
        return temp;
    }
    public void union(int a, int b)
    {
        int al = find(a);
        int bl = find(b);

        if(al==bl)
            return;
        else if(rank[al]<rank[bl])
            par[al]=bl;
        else if(rank[bl]<rank[al])
            par[bl]=al;
        else
        {
            par[bl]=al;
            rank[bl]++;
        }
        --sets;
        return;
    }
    public List<Integer> numIslands2(int n, int m, Point[] operators)
    {   
        if(operators==null || operators.length==0)
            return new ArrayList<>();
        par = new int[n*m];
        rank = new int[n*m];
        sets=0;
        List<Integer> ans = new ArrayList<>();
        
        Arrays.fill(par, -1);

        int[][] dirs = {{-1,0}, {0,1}, {1,0}, {0,-1}};
        for(int i=0; i<operators.length; ++i)
        {
            int cellNo = operators[i].x*m+operators[i].y;
            if(par[cellNo]!=-1)
            {
                ans.add(sets);
                continue;
            }
            par[cellNo] = cellNo;
            rank[cellNo] = 1;
            ++sets;

            for(int j=0; j<dirs.length; ++j)
            {
                int rc  = operators[i].x+dirs[j][0];
                int cc  = operators[i].y+dirs[j][1];
                int cellC = rc*m+cc;
                if(rc<0 || cc<0 || rc==n || cc==m || par[cellC]==-1)
                    continue;
                union(cellNo, cellC);
            }
            ans.add(sets);
        }
        return ans;
    }
}