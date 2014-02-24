import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class FraudPrevention {
    

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int cases = Integer.parseInt(sc.nextLine());
        ArrayList<Order> orders = new ArrayList<Order>();
        TreeSet<Integer> frauds = new TreeSet<Integer>();
        for(int i = 0; i < cases; i++)
        {
            String line = sc.nextLine();
            String[] tokens = line.split(",");
            Solution.Order or = new Solution. Order(tokens[0],tokens[1],tokens[2],tokens[3],tokens[4],tokens[5],tokens[6],tokens[7]);
            //System.out.println(or);
            for(Order prev : orders)
            {
                if(or.detect(prev))
                {
                    frauds.add(or.oid);
                    frauds.add(prev.oid);
                }

            }
            orders.add(or);
        }
        Iterator<Integer> it = frauds.iterator();
        boolean first = true;
        while(it.hasNext())
        {
            if(first)
            {
                System.out.print(it.next());
                first = false;
            }
            else
                System.out.print(","+it.next());
        }
    }
    
    private static class Order
    {
        private int oid;
        private int did;
        private String email;
        private String fullAddress;
        private String street;
        private String city;
        private String state;
        private String zip;
        private String cc;
    
        public Order(String oid, String did, String email, String street, String city, String state, String zip,String cc)
        {
            this.oid = Integer.parseInt(oid);
            this.did = Integer.parseInt(did);
            StringBuilder fullAddress = new StringBuilder(street);
            StringBuilder userName = new StringBuilder(email.substring(0,email.indexOf("@")));
            String domain = email.substring(email.indexOf("@"));
            if(userName.indexOf(".") != -1)
                userName.deleteCharAt(userName.indexOf("."));
            if(userName.indexOf("+") != -1)
                userName.delete(userName.indexOf("+"),userName.length());
            this.email = (userName.append(domain)).toString();
            if(street.indexOf("St.") != -1)
                fullAddress.replace(street.indexOf("St."),street.indexOf("St.")+6,"Street");
            else if(street.indexOf("Rd.") != -1)
                fullAddress.replace(street.indexOf("Rd."),street.indexOf("Rd.")+4,"Road");
            fullAddress.append(" "+city);  
            if(state.equalsIgnoreCase("NY"))
                fullAddress.append(" New York");
            else if(state.equalsIgnoreCase("IL"))
                fullAddress.append(" Illinois");
            else if(state.equalsIgnoreCase("CA"))
                fullAddress.append(" California");
            else 
                fullAddress.append(" "+state);
            if(zip.indexOf("-")!= -1)
                fullAddress.append(" "+zip.replace("-",""));
            else
                fullAddress.append(" "+zip);
            this.fullAddress = fullAddress.toString();
            this.cc = cc;

        }
        
        public boolean detect(Order other)
        {
            if(this.did == other.did)
            {
                if (this.email.equalsIgnoreCase(other.email))
                    return true;
                else if(this.fullAddress.equalsIgnoreCase(other.fullAddress))
                    return true;
            }
            return false;
        }
    
        public String toString()
        {
            return "Order id: "+oid+"\nDeal id: "+did+"\nEmail: "+email+"\nFull Address: "+fullAddress;
        }
    }
}
