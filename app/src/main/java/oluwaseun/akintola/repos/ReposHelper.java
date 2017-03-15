package oluwaseun.akintola.repos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import oluwaseun.akintola.repos.model.Contributor;
import oluwaseun.akintola.repos.model.Repo;

/**
 * Created by Akintola on 6/03/2017.
 */


public class ReposHelper {

    public static ArrayList<Repo> parseRepos(JSONArray array) {
        ArrayList<Repo> repos = new ArrayList<>();
        JSONObject jsonRepo;
        Repo repo;
        try {
            for (int i = 0; i < array.length(); i++) {
                repo = new Repo();
                jsonRepo = array.getJSONObject(i);
                repo.setName(jsonRepo.getString("login"));
                repo.setForks(jsonRepo.getInt("id"));
                repo.setUrl(jsonRepo.getString("url"));
                repo.setContributorsUrl(jsonRepo.getString("url"));
                repos.add(repo);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return repos;
    }

    public static ArrayList<Contributor> parseContributors(JSONArray array) {
        ArrayList<Contributor> contributors = new ArrayList<>();
        JSONObject jsonCont;
        Contributor cont;
        try {
            for (int i = 0; i < array.length(); i++) {
                cont = new Contributor();
                jsonCont = array.getJSONObject(i);
                cont.setName(jsonCont.getString("login"));
                cont.setImageUrl(jsonCont.getString("avatar_url"));
                contributors.add(cont);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return contributors;
    }
}
