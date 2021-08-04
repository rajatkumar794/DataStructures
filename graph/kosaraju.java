import java.util.*;


class kosaraju
{
    static LinkedList<Integer> st;
    static int ans;
    static void dfs1(boolean[] visited, ArrayList<ArrayList<Integer>> adj, int s)
    {
        if(visited[s])
            return;
        
        visited[s]=true;
        ArrayList<Integer> nbrs = adj.get(s);
        for(int nbr: nbrs)
        dfs1(visited, adj, nbr);
        st.addFirst(s);
    }
    static void dfs2(boolean[] visited, ArrayList<ArrayList<Integer>> adj, int s)
    {
        if(visited[s])
            return;
        
        visited[s]=true;
        ArrayList<Integer> nbrs = adj.get(s);
        for(int nbr: nbrs)
            dfs2(visited, adj, nbr);
    }
    static public int kosarajuFunc(int V, ArrayList<ArrayList<Integer>> adj)
    {   
        st = new LinkedList<>();
        boolean[] visited = new boolean[V];
        
        for(int i=0; i<V; ++i)
        {
            if(!visited[i])
                dfs1(visited, adj, i);
        }
        
        ArrayList<ArrayList<Integer>> revAdj = new ArrayList<>();
        for(int i=0; i<V; ++i)
            revAdj.add(new ArrayList<Integer>());
        
        for(int i=0; i<adj.size(); ++i)
        {
            ArrayList<Integer> nbrs = adj.get(i);
            for(int j=0; j<nbrs.size(); ++j)
                revAdj.get(nbrs.get(j)).add(i);
        }
        
        ans=0;
        visited = new boolean[V];
        while(st.size()!=0)
        {
            int v = st.removeFirst();
            if(!visited[v])
            {
                dfs2(visited, revAdj, v);
                ++ans;
            }
        }
        return ans;
    }

    public static void main (String[] args)
    {
        Scanner sc = new Scanner(System. in) ;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>( );
        int V = Integer . parseInt(sc. next() );
        int E = Integer . parseInt(sc. next( ) ) ;
        for (int i =0; i < V; i++)
        adj.add(i, new ArrayList<Integer> ());

        for (int i=1; i<=E; i++)
        {
            int u = Integer . parseInt (sc. next());
            int v = Integer . parseInt(sc. next()) ;
            adj.get(u).add(v);
        }
        System.out.println(kosarajuFunc(V, adj));
        sc.close();
    }

}