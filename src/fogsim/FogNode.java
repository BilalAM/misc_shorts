package physical;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import logical.Tuple;
import strategies.TupleProcessingStrategy;

import java.util.ArrayList;
import java.util.List;

public class FogNode {

    private List<Tuple> tuple;
    private List<FogNode> connectedFogNodes;
    private String name;
    private double MIPS;
    private double RAM;
    private int uplinkBW;
    private int downloadBW;
    private int Id;
    private TupleProcessingStrategy tupleProcessingStrategy;


    public FogNode(int id ,String name,  List<Tuple> tuple,double MIPS, double RAM , int uplinkBW , int downloadBW, TupleProcessingStrategy tupleProcessingStrategy) {
        this.tuple = tuple;
        this.name = name;
        this.MIPS = MIPS;
        this.RAM = RAM;
        Id = id;
        this.downloadBW = downloadBW;
        this.uplinkBW  = uplinkBW;
        this.tupleProcessingStrategy = tupleProcessingStrategy;
        tuple = new ArrayList<>();
        connectedFogNodes = new ArrayList<>();
    }

    public FogNode(){
        tuple = new ArrayList<>();
        connectedFogNodes = new ArrayList<>();
    }

    /**
     * Sends tuple to another fognode .
     * @param tuple : Tuple to send .
     * @param destinationNode : The destination fognode to be sent to .
     */
    public void sendTuple(Tuple tuple, FogNode destinationNode){
        // set that tuple to the destination fognode list of tuples .
        destinationNode.getTuple().add(tuple);
        // remove the tuple from 'this' fognode list since it has been sent ahead.
        this.tuple.remove(tuple);
    }



    // Accessors and mutators below :

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMIPS() {
        return MIPS;
    }

    public void setMIPS(double MIPS) {
        this.MIPS = MIPS;
    }

    public double getRAM() {
        return RAM;
    }

    public void setRAM(double RAM) {
        this.RAM = RAM;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getUplinkBW() {
        return uplinkBW;
    }

    public void setUplinkBW(int uplinkBW) {
        this.uplinkBW = uplinkBW;
    }

    public int getDownloadBW() {
        return downloadBW;
    }

    public void setDownloadBW(int downloadBW) {
        this.downloadBW = downloadBW;
    }


    public TupleProcessingStrategy getTupleProcessingStrategy() {
        return tupleProcessingStrategy;
    }

    public void setTupleProcessingStrategy(TupleProcessingStrategy tupleProcessingStrategy) {
        this.tupleProcessingStrategy = tupleProcessingStrategy;
    }

    public List<Tuple> getTuple() {
        return tuple;
    }

    public void setTuple(Tuple... tuples) {
        for(Tuple tuple : tuples){
            getTuple().add(tuple);
        }
    }

    public List<FogNode> getConnectedFogNodes() {
        return connectedFogNodes;
    }

    public void setConnectedFogNodes(List<FogNode> connectedFogNodes) {
        this.connectedFogNodes = connectedFogNodes;
    }


    public boolean isHighLevel(){
        return (RAM >= 4000 && MIPS >= 5600);
    }

    public boolean isLowelLevel(){
        return (RAM >= 1024 && MIPS >= 800);
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id",Id)
                .append("name",name)
                .append("tuples",tuple)
                .append("MIPS",MIPS)
                .append("RAM",RAM)
                .append("Downlink BW" , downloadBW)
                .append("Uplink BW" , uplinkBW)
                .toString();
    }
}
