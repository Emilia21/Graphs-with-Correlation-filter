/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphtheory;

/**
 *
 * @author epetkova2
 */
public class GraphTheory {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // directory of the source code 
        String currentDirectory = System.getProperty("user.dir");
        // directory of the csv files; in the current directory in subfolder Input
        String ewm_vol_file = currentDirectory + "/Input/mtx_correl_ewm_vol.csv";
        String log_ret_file = currentDirectory + "/Input/mtx_correl_log_ret.csv";

        // create graphs from a matrix in csv file 
        Graph ewm_vol_graph = new Graph(ewm_vol_file);
        Graph ret_file_graph = new Graph(log_ret_file);

        // for every vertex let only 3 edges (these with stronger correlation)
        ewm_vol_graph.correlation_filter();
        ret_file_graph.correlation_filter();
        
        //create xml files
        ewm_vol_graph.create_xml("ewm_vol_graph.xml");
        ret_file_graph.create_xml("ret_file_graph.xml");
    }

}
