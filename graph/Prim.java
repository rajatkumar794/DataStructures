import java.io.*;
import java.util.*;


public class Prim {

    static class Pair implements Comparable<Pair>
    {
        int src;
        int wt;
        public Pair(int a, int b)
        {
            src=a;
            wt=b;
        }
        
        public int compareTo(Pair o)
        {
            return this.wt-o.wt;    
        }
    }

    private static int minSpanning(ArrayList<ArrayList<Pair>> graph)
    {
        int minWt=0;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(1,0));
        boolean[] visited = new boolean[graph.size()+1];

        while(pq.size()!=0)
        {
            Pair p = pq.remove();
            if(visited[p.src])
                continue;
            visited[p.src]=true;
            minWt+=p.wt;

            ArrayList<Pair> nbrs = graph.get(p.src);
            for(Pair nbr: nbrs)
            {
                if(!visited[nbr.src])
                    pq.add(nbr);
            }
        }
        return minWt;
    }
    public static void main(String[] args) throws IOException, NoSuchElementException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inp = br.readLine().split(" ");
        int n = Integer.parseInt(inp[0]);
        int m = Integer.parseInt(inp[1]);

        ArrayList<ArrayList<Pair>> graph = new ArrayList<>();
        for(int i=0; i<=n; ++i)
            graph.add(new ArrayList<>());

        for(int i = 0; i < m; i++)
        {
            String[] parts = br.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);
            int wt = Integer.parseInt(parts[2]);
            graph.get(v1).add(new Pair(v2, wt));
            graph.get(v2).add(new Pair(v1, wt));
         }
         System.out.println(minSpanning(graph));
    }
}
