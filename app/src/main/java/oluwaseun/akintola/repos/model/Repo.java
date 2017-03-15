package oluwaseun.akintola.repos.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Akintola on 26/01/2015.
 */
public class Repo implements Parcelable {

    private String login;
    private int forks;
    private String url;
    private String contributorsUrl;

    public Repo() {
    }

    public String getName() {
        return login;
    }

    public void setName(String login) {
        this.login = login;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Repo(Parcel in) {
        login = in.readString();
        forks = in.readInt();
        url = in.readString();
        contributorsUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login);
        dest.writeInt(forks);
        dest.writeString(url);
        dest.writeString(contributorsUrl);
    }

    public static final Creator<Repo> CREATOR = new Creator<Repo>() {
        @Override
        public Repo createFromParcel(Parcel source) {
            return new Repo(source);
        }

        @Override
        public Repo[] newArray(int size) {
            return new Repo[0];
        }
    };

    public String getContributorsUrl() {
        return contributorsUrl;
    }

    public void setContributorsUrl(String contributorsUrl) {
        this.contributorsUrl = contributorsUrl;
    }
}
