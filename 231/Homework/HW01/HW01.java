
// What fields does the HW01 class have?
// What is printed to the screen if then run the code up to mark 1?
//  Will the output from the next two print statements (mark2 and mark3) will be identical? If so, why? if not, why not?
// What seems to be the purpose of update? (Summarize in English what the end goal is - don't just describe the algorithm the code is following).

import java.util.ArrayList;
public class HW01 {

    private int field1;
    private double field2;
    private ArrayList<Double> weights;
    
    public HW01( int f1, double f2 ) {
        this.field1 = f1;
        this.field2 = f2;
        this.weights = new ArrayList<Double>();
        for (int i = 0; i < this.field1; i++) {
            this.weights.add( f2 );
        }
    }
    
    public void update( int f1, double f2 ) {
        if (f1 > this.field1) {
            for (int i = this.field1; i < f1; i++) {
                System.out.println( "Adding at index " + i );
                this.weights.add( f2 );
            }
        }
        if (f1 < this.field1 ) { 
            for (int i = this.field1-1; i >= f1; i--) {
                System.out.println( "Removing at index " + i );
                this.weights.remove(i);
            }
        }
        this.field1 = f1;
        this.field2 = f2;
        
    }
    
    public ArrayList<Double> getList() { return (ArrayList<Double>)this.weights.clone(); }
    
    public static void main( String[] args ) {
        HW01 hw = new HW01( 3, 4.0 );
        System.out.println( hw.getList() );
        hw.update( 5, 3.0 );
        System.out.println( hw.getList() );
        hw.update( 2, 5.0 );
        System.out.println( hw.getList() );
        // Mark 1
        
        ArrayList<Double> list = hw.getList();
        list.add( 10.0 );
        System.out.println( list ); // mark 2
        System.out.println( hw.getList() ); // mark 3
    }

}