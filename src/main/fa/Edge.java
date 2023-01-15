package main.fa;

public class Edge {
    private final String from;
    private final String with;

    public Edge(String from, String with) {
        this.from = from;
        this.with = with;
    }

    @Override
    public int hashCode(){
        return from.hashCode()+with.hashCode();
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null || obj.getClass()!= this.getClass())
            return false;
        Edge other = (Edge) obj;
        return other.from.equals(this.from) && other.with.equals(this.with);
    }

    @Override
    public String toString(){
        return "(" + from + "," + with + ")";
    }
}
