// hashCodes are usually based on identity
// and they are not based on addresses

public static void main(String[] args) {
    for (i=0;i<10 ;i++ ) {
        String the = new String("the");
        System.out.print(the.hashCodes);
    }
}

// all output will be 114801.


// HashFucntions
// Convert values into
// some people acall it one way fucntion

public static int hash(int x){
    return (x*13)%17;
}
// not a good hashfucntion, only returns 17 possible ints

// password and Hashing
// The agent does not store actual password but hashcode obthe user number and the password
// So the same password won't appear for hundreds of times in the same database
