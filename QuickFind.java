class QuickFind 
{
    private int[]  id;

    public QuickFind(int n){
        id = new int[n];
        for (int i = 0; i < n; i++){
            id[i] = i;
        }
    }

    public void printArray(){
        for(int i=0; i<id.length ; i++){     
            System.out.println(id[i]);
        }
    }  

    public boolean find(int p, int q){
        if (p < 0 || p >= id.length || q < 0 || q >= id.length){
            throw new IllegalArgumentException("Indeces must be within range of id array.");
        }
        else{
            return id[p] == id[q];
        }
    }

    public void union(int p, int q){
        int x = id[p];
        int y = id[q];
        for(int i=0; i<id.length ; i++){     
            if (id[i] == x) id[i] = y;   
        }
    }

    public static void main(String[] args)  //static method  
    {  
    QuickFind x = new QuickFind(10);
    x.union(9, 4);
    x.union(9,2);
    if (x.find(4, 9) == true){
        System.out.println("true");
    }
    else {
        System.out.println("false");
    }
    x.printArray(); 
    }   
}


