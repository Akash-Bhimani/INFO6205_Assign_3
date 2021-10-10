package edu.neu.coe.info6205.union_find;
import java.util.Random;

public class UF_Client {
    public static int count(int n) {

        int no_of_con = 0;
        UF_HWQUPC uf_client = new UF_HWQUPC(n, true);
        Random rand = new Random();

        while (uf_client.components()!=1)
        {
            int x = rand.nextInt(n);

            int y = rand.nextInt(n);

            if(!uf_client.connected(x,y)) {
                uf_client.union(x, y);
                no_of_con++;
            } else{
                no_of_con++;
            }
        }
        return no_of_con;
    }

    public static void main(String[] args) {

        Random rand = new Random();

        for(int i=0; i<10;i++) {

            int n = rand.nextInt(100000);
            System.out.println("Number of objects(n):" + n + " ------ Number of pairs(m):" + count(n));
        }
    }
}
