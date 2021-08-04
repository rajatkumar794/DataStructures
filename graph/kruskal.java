import java.util.PriorityQueue;

public class kruskal
{
    static int[] par;
    static int[] rank;

    static public int find(int a)
    {
        if(par[a]==a)
            return a;
        int temp = find(par[a]);
        par[a]=temp;
        return temp;
    }
    static public boolean union(int a, int b)
    {
        int al = find(a);
        int bl = find(b);

        if(al==bl)
            return true;
        else if(rank[al]<rank[bl])
            par[al]=bl;
        else if(rank[bl]<rank[al])
            par[bl]=al;
        else
        {
            par[bl]=al;
            rank[bl]++;
        }
        return false;
    }

    static class Pair implements Comparable<Pair>
    {
        int src;
        int nbr;
        int wt;

        Pair(int a, int b, int c)
        {
            src=a;
            nbr=b;
            wt=c;
        }
        public int compareTo(Pair o)
        {
            return this.wt-o.wt;
        }
    }
    static int minSpanning(int[][] edges, int n)
    {   
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for(int i=0; i<edges.length; ++i)
            pq.add(new Pair(edges[i][0], edges[i][1], edges[i][2]));
        
        
        int minWt=0;
        while(pq.size()!=0)
        {
            Pair p = pq.remove();
            boolean temp = union(p.src,p.nbr);
            if(!temp)
                minWt+=p.wt;
        }
        return minWt;
    }
    public static void main(String[] args)
    {
        
    }
}
