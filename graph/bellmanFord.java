import java.io.*;
import java.util.*;


public class bellmanFord {

    static class Pair implements Comparable<Pair>
    {
        int vtx;
        int wt;
        public Pair(int a, int b)
        {
            vtx=a;
            wt=b;
        }
        
        public int compareTo(Pair o)
        {
            return this.wt-o.wt;    
        }
    }

    private static void shortestPath(int src, ArrayList<ArrayList<Pair>> graph)
    {
        int minDist[] = new int[graph.size()];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[src]=0;
        
        for(int i=0; i<graph.size()-1; ++i)
        {
            for(int j=0; j<graph.size(); ++j)
            {   
                if(minDist[j]==Integer.MAX_VALUE)
                    continue;
                ArrayList<Pair> nd = graph.get(j);
                for(int k=0; k<nd.size(); ++k)
                {
                    Pair p = nd.get(k);
                    if(minDist[j]+p.wt<minDist[p.vtx])
                        minDist[p.vtx]=minDist[j]+p.wt;
                }
            }
            
        }
        
        for(int i=0; i<graph.size(); ++i)
        System.out.println(i+": "+minDist[i]);

    }
    public static void main(String[] args) throws IOException, NoSuchElementException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inp = br.readLine().split(" ");
        int n = Integer.parseInt(inp[0]);
        int m = Integer.parseInt(inp[1]);

        ArrayList<ArrayList<Pair>> graph = new ArrayList<>();
        for(int i=0; i<n; ++i)
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
         int src = Integer.parseInt(br.readLine());
         shortestPath(src, graph);
    }
}
