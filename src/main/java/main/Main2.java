package main;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import model.Transakcija;

public class Main2 {

	private static final String BASE_URL = "http://api.currencylayer.com/";
	private static final String ACCESS_KEY = "64aa6de9179fa77b73261146e1182705";

	public static void main(String[] args) {
		try {
			LocalDateTime datumTransakcije = LocalDateTime.of(2020, 7, 2, 0, 0);
			String date = datumTransakcije.toLocalDate().toString();

			Transakcija t1 = new Transakcija();
			t1.setIzvornaValuta("USD");
			t1.setKrajnjaValuta("EUR");
			t1.setPocetniIznos(100);
			t1.setDatumTransakcije(datumTransakcije);
			
			
			Transakcija t2 = new Transakcija();
			t2.setIzvornaValuta("USD");
			t2.setKrajnjaValuta("CHF");
			t2.setPocetniIznos(100);
			t2.setDatumTransakcije(datumTransakcije);

			Transakcija t3 = new Transakcija();
			t3.setIzvornaValuta("USD");
			t3.setKrajnjaValuta("CAD");
			t3.setPocetniIznos(100);
			t3.setDatumTransakcije(datumTransakcije);
			List<Transakcija> transakcije = new ArrayList<>();
			transakcije.add(t1);
			transakcije.add(t2);
			transakcije.add(t3);
			for (Transakcija transakcija : transakcije) {
				URL url = new URL(BASE_URL + "historical?access_key=" + ACCESS_KEY + "&date=" + date + "&source="
						+ transakcija.getIzvornaValuta() + "&currencies=" + transakcija.getKrajnjaValuta());
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
				JsonObject jsonResult = gson.fromJson(in, JsonObject.class);
				JsonObject quotes = (JsonObject) jsonResult.get("quotes");
				in.close();
				double kurs = quotes.get(transakcija.getIzvornaValuta() + transakcija.getKrajnjaValuta()).getAsDouble();
				double krajnjiIznos = transakcija.getPocetniIznos()*kurs;
				transakcija.setKrajnjiIznos(krajnjiIznos);
			}
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			try (FileWriter fw = new FileWriter("ostale_transakcije.json")) {
				gson.toJson(transakcije, fw);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
