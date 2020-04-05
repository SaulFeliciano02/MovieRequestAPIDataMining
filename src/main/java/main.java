import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class main {
    public static void main(String[] args) throws IOException, ParseException {
        ArrayList<Integer> tvShowsIDs = new ArrayList<>();
        int[] ids = {1435, 64551, 41727, 16208, 1420, 21762, 61418, 40075, 61406, 62560, 61175, 42282, 79084, 64254, 61118, 33880, 65817, 43082, 61920, 66573, 34594, 17967, 60839, 2947, 1621, 79788,
                48891, 70964, 63161, 1424, 62611, 32726, 59659, 40008, 60622, 1399, 1920, 18347, 33400, 60059, 66859, 46619, 1436, 65495, 46533, 67070, 61222, 1396, 8592, 54344};
//        ArrayList<String> nombres = new ArrayList<>();
//        ArrayList<String> mainGenres = new ArrayList<>();
//        ArrayList<String> countries = new ArrayList<>();
//        ArrayList<Long> seasons = new ArrayList<>();
//        ArrayList<Long> episodes = new ArrayList<>();
//        ArrayList<Long> duration = new ArrayList<>();
//        ArrayList<String> allnetworks = new ArrayList<>();
//        ArrayList<String> monthsOfFirstEmition = new ArrayList<>();
//        ArrayList<String> secondaryGenres = new ArrayList<>();
        ArrayList<String> statues = new ArrayList<>();
        ArrayList<Double> voteAverages = new ArrayList<>();
        ArrayList<Long> voteCounts = new ArrayList<>();

        for (int i = 0; i < ids.length; i++)
        {
            tvShowsIDs.add(ids[i]);
        }

        for (Integer id: tvShowsIDs
             ) {
            URL url = new URL("https://api.themoviedb.org/3/tv/" + id + "?api_key=998b6e7495fb74a280c86e6a80c976f3");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responsecode = conn.getResponseCode();

            if(responsecode != 200)
                throw new RuntimeException("HttpResponseCode: " +responsecode);
            else
            {
                Scanner sc = new Scanner(url.openStream());
                String inline = "";
                while(sc.hasNext())
                {
                    inline+=sc.nextLine();
                }
//                System.out.println("\nJSON data in string format");
//                System.out.println(inline);
                sc.close();

                JSONParser parse = new JSONParser();
                JSONObject jobj = (JSONObject)parse.parse(inline);

                String name = (String) jobj.get("original_name");
                JSONArray genres = (JSONArray) jobj.get("genres");
                JSONObject mainGenreName = (JSONObject) genres.get(0);
                JSONArray origin_country = (JSONArray) jobj.get("origin_country");
                String origin_country_name = (String) origin_country.get(0);
                Long number_of_seasons = (Long) jobj.get("number_of_seasons");
                Long number_of_episodes = (Long) jobj.get("number_of_episodes");
                JSONArray episode_run_time = (JSONArray) jobj.get("episode_run_time");
                Long episode_run_time_print = 0L;
                if(episode_run_time.size() > 0)
                {
                    episode_run_time_print = (Long) episode_run_time.get(0);
                }

                //personaje principal
                JSONArray networks = (JSONArray) jobj.get("networks");
                JSONObject networksName = (JSONObject) networks.get(0);
                //camera setup
                //certification
//                String first_air_date = (String) jobj.get("first_air_date");
//                first_air_date = first_air_date.substring(first_air_date.indexOf("-")+1,first_air_date.lastIndexOf("-"));
                String status = (String) jobj.get("status");

                JSONArray lang = (JSONArray) jobj.get("languages");
                boolean multilanguageSupport;

                if (lang.size() > 1)
                {
                    multilanguageSupport = true;
                }
                else
                {
                    multilanguageSupport = false;
                }

                JSONObject secondaryGenreName = null;

                if(genres.size() > 1)
                {
                    secondaryGenreName = (JSONObject) genres.get(1);
                }

                Double voteAverage = (Double) jobj.get("vote_average");
                voteAverage = Math.floor(voteAverage);

                Long voteCount = (Long) jobj.get("vote_count");

                if(secondaryGenreName == null)
                {
                    System.out.println("'"+name+"'"+","+"'"+mainGenreName.get("name")+"'"+","+"'"+origin_country_name+"'"+","+number_of_seasons+","+number_of_episodes+","+episode_run_time_print+","+" ,"+"'"+networksName.get("name")+"'"+","+" ,"+"'"+status+"'"+","+voteAverage+","+"'"+multilanguageSupport+"'"+","+"'"+"n/a"+"'"+","+voteCount);
                }
                else
                {
                    System.out.println("'"+name+"'"+","+"'"+mainGenreName.get("name")+"'"+","+"'"+origin_country_name+"'"+","+number_of_seasons+","+number_of_episodes+","+episode_run_time_print+","+" ,"+"'"+networksName.get("name")+"'"+","+" ,"+"'"+status+"'"+","+voteAverage+","+"'"+multilanguageSupport+"'"+","+"'"+secondaryGenreName.get("name")+"'"+","+voteCount);
                }

//                nombres.add("'"+name+"'");
//                if(!mainGenres.contains("'"+mainGenreName.get("name")+"'"))
//                {
//                    mainGenres.add("'"+mainGenreName.get("name")+"'");
//                }
//                if(!countries.contains("'"+origin_country_name+"'"))
//                {
//                    countries.add("'"+origin_country_name+"'");
//                }
//                if(!seasons.contains(number_of_seasons))
//                {
//                    seasons.add(number_of_seasons);
//                }
//                if(!episodes.contains(number_of_episodes))
//                {
//                    episodes.add(number_of_episodes);
//                }
//                if(!duration.contains(episode_run_time_print))
//                {
//                    duration.add(episode_run_time_print);
//                }
//                if(!allnetworks.contains("'"+networksName.get("name")+"'"))
//                {
//                    allnetworks.add("'"+networksName.get("name")+"'");
//                }
//                if(!monthsOfFirstEmition.contains("'"+first_air_date+"'"))
//                {
//                    monthsOfFirstEmition.add("'"+first_air_date+"'");
//                }
//                if(secondaryGenreName != null && !secondaryGenres.contains("'"+secondaryGenreName.get("name")+"'"))
//                {
//                    secondaryGenres.add("'"+secondaryGenreName.get("name")+"'");
//                }
                if(!statues.contains("'"+status+"'"))
                {
                    statues.add("'"+status+"'");
                }
                if(!voteCounts.contains(voteCount))
                {
                    voteCounts.add(voteCount);
                }
                if(!voteAverages.contains(voteAverage))
                {
                    voteAverages.add(voteAverage);
                }
            }
        }
//        secondaryGenres.add("'n/a'");
//        System.out.println(duration);
//        System.out.println(allnetworks);
//        System.out.println(monthsOfFirstEmition);
//        System.out.println(secondaryGenres);
        System.out.println(statues);
        System.out.println(voteAverages);
        System.out.println(voteCounts);
    }
}
