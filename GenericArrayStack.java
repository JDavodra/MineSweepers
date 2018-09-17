//Jahesh Davodra
//300018359
//ITI 1121 -C
//Assingment 2
//@author Guy-Vincent Jourdan, University of Ottawa 
//https://github.com/gvjourdan/ITI1121-2018/commit/6c196a63579dc623c4d5c94b8b5b05f2c0c3d470
public class GenericArrayStack<E> implements Stack<E> {
   
   // ADD YOUR INSTANCE VARIABLES HERE
   private E[] elems; // Used to store the elements of this ArrayStack
   private int top; // Designates the first free cell

   // Constructor
    public GenericArrayStack( int capacity ) {
        
    // ADD YOU CODE HERE
		elems = (E[]) new Object[ capacity ];
		top = 0;

    }

    // Returns true if this ArrayStack is empty
    public boolean isEmpty() {
        
    // ADD YOU CODE HERE
		return top == 0;

    }

    public void push( E elem ) {
        
    // ADD YOU CODE HERE
		elems[ top ] = elem;
    	top++;

    }
    public E pop() {
        
    // ADD YOU CODE HERE
		E saved;

    	top--;
    	saved = elems[ top ];
    	elems[ top ] = null;

    	return saved;

    }

    public E peek() {
        
    // ADD YOU CODE HERE
		return elems[ top-1 ];

    }
}
