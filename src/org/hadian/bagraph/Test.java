package org.hadian.bagraph;

import java.util.Random;

/**
 * Created by ali on 23/01/17.
 */
public class Test {
    protected static Random random = new Random();

    public static void main(String[] args) {

        for(int i=0; i<100; i++)
        System.out.println(random.nextInt(2));
    }

}
