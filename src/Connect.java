import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Connect {
    public static void main(String[] args) throws IOException {

        //REQUEST
        ConnectURI koneksiSaya = new ConnectURI();
        URL myAddress = koneksiSaya.buildURL("https://farmasi.mimoapps.xyz/mimoqss2auyqD1EAlkgZCOhiffSsFl6QqAEIGtM");

        //RESPONSE
        String response = koneksiSaya.getResponseFromHttpUrl(myAddress);
        System.out.println(response);

        //DECODINGJSON
        assert response != null;
        JSONArray responsedJSON = new JSONArray(response);
        ArrayList<FarmasiData> models = new ArrayList<>();

        for (int i=0;i<responsedJSON.length(); i++){
            FarmasiData farData = new FarmasiData();
            JSONObject myJSONObject = responsedJSON.getJSONObject(i);
            farData.setI_qty(myJSONObject.getString("i_qty"));
            farData.setI_name(myJSONObject.getString("i_name"));
            farData.setI_cogs(myJSONObject.getString("i_cogs"));
            farData.setI_kdsell(myJSONObject.getString("i_kdsell"));
            farData.setI_sell(myJSONObject.getString("i_sell"));

            models.add(farData);
        }

        System.out.println("Response are : ");
        for (int index = 0; index<models.size(); index++){
            int stok = Integer.parseInt(models.get(index).getI_qty());
            int harga = Integer.parseInt(models.get(index).getI_sell());
            String name = String.valueOf(models.get(index).getI_name().charAt(0));

            if (stok > 0) {

                if(name.contains("s")&& harga < 7000) {
                    System.out.println("NAMA : " + models.get(index).getI_name());
                    System.out.println("HARGA : " + models.get(index).getI_sell());
                    System.out.println("STOK TERSEDIA : " + models.get(index).getI_qty());
                }
            }
        }
    }
}
