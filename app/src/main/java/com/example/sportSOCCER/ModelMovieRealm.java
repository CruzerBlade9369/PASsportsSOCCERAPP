package com.example.sportSOCCER;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ModelMovieRealm extends RealmObject {
    @PrimaryKey
    private String strTeam;
    private String intFormedYear;
    private String strCountry;
    private String strTeamBadge;
    private String strDescriptionEN;
    private Integer idTeam;

    public int getidTeam() {
        return idTeam;
    }

    public void setidTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    public String getstrTeam() {
        return strTeam;
    }

    public void setstrTeam(String strTeam) {
        this.strTeam = strTeam;
    }

    public String getintFormedYear() {
        return intFormedYear;
    }

    public void setintFormedYear(String intFormedYear) {
        this.intFormedYear = intFormedYear;
    }

    public String getstrCountry() {
        return strCountry;
    }

    public void setstrCountry(String strCountry) {
        this.strCountry = strCountry;
    }

    public String getstrTeamBadge() {
        return strTeamBadge;
    }

    public void setstrTeamBadge(String strTeamBadge) { this.strTeamBadge = strTeamBadge; }

    public String getstrDescriptionEN() {
        return strDescriptionEN;
    }

    public void setstrDescriptionEN(String strDescriptionEN) {
        this.strDescriptionEN = strDescriptionEN;
    }


}
