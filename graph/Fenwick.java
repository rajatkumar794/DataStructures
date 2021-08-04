import java.io.*;
import java.util.*;


public class Fenwick {
    static StringBuilder sb;
    static long[] fenwick;
    
    private static void update(int idx, int newEle)
    {
        while(idx<fenwick.length)
        {
            fenwick[idx]+=newEle;
            int mask = idx&(~(idx-1));
            idx+=mask;
        }
    }

    private static long prefixSum(int i)
    {
        long ans = 0;
        while(i>0)
        {
            ans+=fenwick[i];
            i&=i-1;
        }
        return ans;
    }

    private static long query(int l, int r)
    {
        return prefixSum(r)-prefixSum(l-1);
    }

    private static void construct(int[] arr)
    {
        fenwick = new long[arr.length+1];
        for(int i=1; i<fenwick.length; ++i)
            update(i, arr[i-1]);
    }
    public static void main(String[] args) throws IOException, NoSuchElementException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        String inp = br.readLine();
        int n = Integer.parseInt(inp);
        int[] arr = new int[n];
        inp = br.readLine();
        
        StringTokenizer st = new StringTokenizer(inp, " ");
        for(int i=0; i<n; ++i)
            arr[i] = Integer.parseInt(st.nextToken());
        
        construct(arr);
        
        inp = br.readLine();
        int q = Integer.parseInt(inp);
        
        for(int i=0; i<q; ++i)
        {
            st = new StringTokenizer(br.readLine(), " ");
            if(st.nextToken().equals("q"))
            {   
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                long sum = query(l,r);
                sb.append(sum+"\n");
            }
            else
            {
                update(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
        }
        System.out.println(sb);
        return;
    }
}
