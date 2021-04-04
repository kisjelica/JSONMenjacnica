package main;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import model.Transakcija;

public class Main1 {

	private static final String BASE_URL = "http://api.currencylayer.com/";
	private static final String ACCESS_KEY = "64aa6de9179fa77b73261146e1182705";

	public static void main(String[] args) {
		try {
			String izvornaValuta = "USD";
			String krajnjaValuta = "CAD";
			double pocetniIznos = 95;
			Transakcija transakcija = new Transakcija();
			transakcija.setIzvornaValuta(izvornaValuta);
			transakcija.setKrajnjaValuta(krajnjaValuta);
			transakcija.setPocetniIznos(pocetniIznos);
			URL url = new URL(
					BASE_URL + "live?access_key=" + ACCESS_KEY + "&source=" + izvornaValuta + "&currencies=" + krajnjaValuta);
			System.out.println(url);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
			JsonObject jsonResult = gson.fromJson(in, JsonObject.class);
			JsonObject quotes = (JsonObject) jsonResult.get("quotes");
			in.close();
			double kurs = quotes.get(izvornaValuta + krajnjaValuta).getAsDouble();
			double krajnjiIznos = pocetniIznos*kurs;
			transakcija.setKrajnjiIznos(krajnjiIznos);
			transakcija.setDatumTransakcije(LocalDateTime.now());
			try (FileWriter fw = new FileWriter("prva_transakcija.json")) {
				 

				gson.toJson(transakcija,fw);
			} catch (Exception e) {
				e.printStackTrace();

			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}

	}

}
