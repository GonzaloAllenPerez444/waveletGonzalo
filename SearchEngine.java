
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;



class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;
    ArrayList<String> masterList = new ArrayList<String>();
    

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            
            
            return "Master List: " + String.join(",", masterList);
            
        } 
        
        else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    
                    masterList.add(parameters[1]);

                    return String.format("%s added to Master List",parameters[1]);
                }
            }

            if (url.getPath().contains("/search")) {
                ArrayList<String> temp = new ArrayList<String>();
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")){


                    for (String x: masterList)
                {
                    if (x.contains(parameters[1])) {temp.add(x);};
                };
                return "All of the items with " + parameters[1] + " are " + String.join(",", temp);
                };
                
            
            };
            return "404 Not Found!";
        }
    }
}






public class SearchEngine {
 
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
