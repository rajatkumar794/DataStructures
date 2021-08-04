import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class articulation
{
    static int[] par;
    static int[] disc;
    static int[] low;
    static boolean[] vis;
    static int time;
    static boolean[] ap;
    static int count;
    
    public static void articulationPoint(int u, ArrayList<ArrayList<Integer>> graph)
    {
        disc[u]=low[u]=time++;
        vis[u]=true;
        
        ArrayList<Integer> nbrs = graph.get(u);
        for(int v: nbrs)
        {   
            if(par[u]==v)
                continue;
            else if(vis[v])
                low[u] = Math.min(low[u], disc[v]);
            else
            {
                par[v]=u;
                articulationPoint(v,graph);
                if(par[u]==0)
                {
                    ++count;
                    if(count>=2)
                        ap[u]=true;
                }
                else
                {
                    if(low[u]>=disc[u])
                        ap[u]=true;
                }
                low[u] = Math.min(low[u],low[v]);
            }
        }
    }
    
    public static int doctorStrange (int n, int k, int g[][])
    {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i=0; i<=k; ++i)
            graph.add(new ArrayList<>());
        
        for(int i=0; i<g.length; ++i)
        {
            int u = g[i][0];
            int v = g[i][1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        par = new int[k+1];
        disc = new int[k+1];
        low = new int[k+1];
        vis = new boolean[k+1];
        ap = new boolean[k+1];
        time=0;
        articulationPoint(1, graph);
        count=0;
        
        int ans=0;
        for(int i=0; i<=k; ++i)
        {
            if(ap[i])
                ++ans;
        }
        return ans;
    }
    
    public static void main(String[] args) throws IOException, NoSuchElementException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true)
        {
            String inp = br.readLine();
            if(inp.length()==0)
                break;
            int n = Integer.parseInt(inp.split(" ")[0]);
            int m = Integer.parseInt(inp.split(" ")[1]);
            int[][] edges = new int[m][2];
            for(int i=0; i<m; ++i)
            {
                inp = br.readLine();
                int u = Integer.parseInt(inp.split(" ")[0]);
                int v = Integer.parseInt(inp.split(" ")[1]);
                edges[i][0]=u;
                edges[i][1]=v;
            }
            System.out.println(doctorStrange (n, m, edges));
        }
    }
}
