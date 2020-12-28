import java.io.*;
import java.util.*;

public class Main {
    
    public static class Person implements Comparable<Person>{
        String name;
        int age;
        int air;
        int marks;
        
        public Person(String name, int age, int air, int marks){
            this.name = name;
            this.age = age;
            this.air = air;
            this.marks = marks;
        }
        
        public int compareTo(Person o){
            return this.air - o.air;
        }
        
    }
    
    public static class personComp implements Comparator<Person>
    {
        public int compare(Person p1, Person p2)
        {
            return p1.age-p2.age;
        }
    }
    
    public static class MyPriorityQueue<T> {
        ArrayList < T > data;
        Comparator comp;

        public MyPriorityQueue() 
        {
            data = new ArrayList < > ();
        }
        
	public MyPriorityQueue(ArrayList<T> arr)
	{
	    data=arr;
	    for(int i=(arr.size()-2)/2; i>=0; --i)
	 	downHeapify(i);
	}

        public MyPriorityQueue(Comparator comp)
        {
            this.comp = comp;
            data = new ArrayList < > ();
        }

        public void add(T val) {
            // write your code here
            data.add(val);
            upHeapify(data.size()-1);
        }
        
        
        private boolean IscSmallerthanp(int ci, int pi)
        {
            if(comp == null)
            {
                Comparable c = (Comparable)data.get(ci);
                Comparable p = (Comparable)data.get(pi);
                if(c.compareTo(p) < 0)
                    return true;
                else
                    return false;
                
            }
            else
            {
                T c = data.get(ci);
                T p = data.get(pi);
                if(comp.compare(c,p) < 0)
                    return true;
                else
                    return false;
                
            }
        }
        
        private void swap(int i, int j)
        {
            T temp = data.get(i);
            data.set(i,data.get(j));
            data.set(j,temp);
        }
        private void upHeapify(int c)
        {   if(c==0)
                return;
            int p = (c-1)/2;
            if(IscSmallerthanp(c,p))
            {
                swap(p,c);
                upHeapify(p);
            }
            return;
        }
        
        private void downHeapify(int p)
        {   if(2*p+1>=data.size())
                return;
            else if(2*p+2>=data.size())
            {
                if(IscSmallerthanp(2*p+1,p))
                    swap(p,2*p+1);
            }
            else
            {   int midx;
                if(IscSmallerthanp(2*p+1,2*p+2))
                    midx=2*p+1;
                else
                    midx=2*p+2;
                
                if(IscSmallerthanp(p,midx))
                    midx=p;
                if(midx==p)
                    return;
                else if(IscSmallerthanp(2*p+2,2*p+1))
                {   
                    swap(p,2*p+2);
                    downHeapify(2*p+2);
                }
                else
                {
                    swap(p,2*p+1);
                    downHeapify(2*p+1);
                }
            }
            return;
        }
        
        public T remove() {
            // write your code here
            if(data.size()==0)
            {
                System.out.println("Underflow");
                return null;
            }
            T temp = data.get(0);
            swap(0,data.size()-1);
            data.remove(data.size()-1);
            downHeapify(0);
            return temp;
        }

        public T peek() {
            // write your code here
            if(data.size()==0)
            {
                System.out.println("Underflow");
                return null;
            }
            else return data.get(0);
        }

        public int size() {
            // write your code here
            return data.size();
        }
    }
    
    

    public static void main(String[] args) throws Exception {
        
        MyPriorityQueue<Person> mpq = new MyPriorityQueue<>(new personComp());
        Person p1 = new Person("A",10,112,90);
        Person p2 = new Person("B",20,12,98);
        Person p3 = new Person("C",15,200,80);
        Person p4 = new Person("D",18,160,85);
        
        mpq.add(p1);
        mpq.add(p2);
        mpq.add(p3);
        mpq.add(p4);
        
        while(mpq.size() != 0){
            Person rp = mpq.remove();
            System.out.println(rp.name + " " + rp.air+" "+rp.age);
        }
        

        
    }
}
