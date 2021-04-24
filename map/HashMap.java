import java.io.*;
import java.util.*;
  
public static class HashMap <K, V>
{
    private class Node
    {
        K key;
        V value;
        Node(K key, V value)
        {
            this.key=key;
            this.value=value;
        }
    }
    private int size;
    private LinkedList<Node>[] buckets;

    public HashMap()
    {
        initBuckets(4);
        size=0;
    }

    private void initBuckets(int n)
    {
        buckets = new LinkedList[n];
        for(int i=0; i<n; ++i)
        buckets[i] = new LinkedList<>();
    }

    private void reHash() 
    {
        LinkedList<Node>[] oldBuckets = buckets;
        initBuckets(oldBuckets.length*2);
        size=0;
        for(int i=0; i<oldBuckets.length; ++i)
        {
            for(int j=0; j<oldBuckets[i].size(); ++j)
              put(oldBuckets[i].get(j).key, oldBuckets[i].get(j).value);
        }
    }

    private int hashFunction(K key)
    {
        return Math.abs(key.hashCode())%buckets.length;
    }

    public int findInBucket(K key, int bi)
    {
        for(int i=0; i<buckets[bi].size(); ++i)
        {
            if(buckets[bi].get(i).key.equals(key))
              return i;
        }
        return -1;
    }

    public void put(K key, V value)
    {
        int bi = hashFunction(key);
        int di = findInBucket(key, bi);
        if(di==-1)
        {
            buckets[bi].add(new Node(key, value));
            ++size;
        }
        else
        {
            Node n = buckets[bi].get(di);
            n.value=value;
        }

        double lambda = (size*1.0)/(buckets.length*1.0);
        if(lambda>2)
          reHash();
    }

    public V get(K key)
    {
        int bi =  hashFunction(key);
        int di = findInBucket(key, bi);
        if(di==-1)
          return null;
        else
          return buckets[bi].get(di).value;
    }

    public boolean containsKey(K key)
    {
        int bi = hashFunction(key);
        int di = findInBucket(key,bi);
        if(di==-1)
          return false;
        else
          return true;
    }

    public V remove(K key)
    {
          int bi = hashFunction(key);
          int di = findInBucket(key,bi);
          if(di==-1)
              return null;
          else
          {   --size;
              return buckets[bi].remove(di).value;
          }
    }

     public ArrayList < K > keyset()
      {
          ArrayList<K> set = new ArrayList<>();
          for(int i=0; i<buckets.length; ++i)
          {
              for(int j=0; j<buckets[i].size(); ++j)
              set.add(buckets[i].get(j).key);
          }
          return set;
      }

      public int size() {
          return size;
      }

      public String toString()
      {
          String str="[{";
          {
              for(int j=0; j<buckets[i].size(); ++j)
              str+=buckets[i].get(j).key+"="+buckets[i].get(j).value;
          }
          return str;
      }
      public String toString()
      {
          String str="[{";
          for(int i=0; i<buckets.length; ++i)
          {
              for(int j=0; j<buckets[i].size(); ++j)
              str+=buckets[i].get(j).key+"="+buckets[i].get(j).value+", ";
          }
          return str.substring(0,str.length()-2)+"}]";
      }
}
