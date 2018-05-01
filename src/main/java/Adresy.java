import model.AddressResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Adresy {
    private static final Logger log = LoggerFactory.getLogger(Adresy.class);
    private static ArrayList<String> latList = new ArrayList<>();
    private static ArrayList<String> loanList = new ArrayList<>();
    private static ArrayList<AddressResponse> addressResponseList = new ArrayList<>();


    public static void main(String[] args) {
        readCSV();
        requestForAddress(latList, loanList);
        insertData();
    }


    private static void insertData() {
        InsertDataInToDB insert = new InsertDataInToDB();
        for (int i = 0; i < addressResponseList.size(); i++) {
            insert.insertAdres(addressResponseList.get(i).getAddress().getCounty(), addressResponseList.get(i).getAddress().getRoad(), addressResponseList.get(i).getAddress().getPostalcode(), addressResponseList.get(i).getAddress().getState());
        }
        insert.closeConnection();
    }

    private static void requestForAddress(ArrayList<String> latList, ArrayList<String> loanList) {
        for (int i = 0; i < latList.size(); i++) {
            RestTemplate restTemplate = new RestTemplate();
            addressResponseList.add(restTemplate.getForObject("http://geo2.osm.gpsserwer.pl/reverse.php?format=json&lat=" + latList.get(i) + "&lon=" + loanList.get(i), AddressResponse.class));
            log.info(addressResponseList.get(i).toString());
        }
    }

    private static void readCSV() {
        String csvFile = "adresy.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] addrresFromCSV = line.split(cvsSplitBy);
                System.out.println("lat= " + addrresFromCSV[0] + " , lon=" + addrresFromCSV[1]);
                latList.add(addrresFromCSV[0]);
                loanList.add(addrresFromCSV[1]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}