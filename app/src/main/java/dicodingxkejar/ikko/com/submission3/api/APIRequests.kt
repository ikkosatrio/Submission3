package dicodingxkejar.ikko.com.submission3.api

object APIRequests{
    fun getNextEvent(id: String?): String{
        return API.BASE_URL +
                "api/v1/json/" + API.API_KEY + "/eventsnextleague.php?id=" + id
    }

    fun getPastEvent(id: String?): String{
        return API.BASE_URL +
                "api/v1/json/" + API.API_KEY + "/eventspastleague.php?id=" + id
    }

    fun getDetail(detailid: String?): String{
        return API.BASE_URL +
                "api/v1/json/" + API.API_KEY + "/lookupevent.php?id=" + detailid
    }

    fun getLogoAway(detailid: String?): String{
        return API.BASE_URL +
                "api/v1/json/" + API.API_KEY + "/lookupteam.php?id=" + detailid
    }

    fun getLogoHome(detailid: String?): String{
        return API.BASE_URL +
                "api/v1/json/" + API.API_KEY + "/lookupteam.php?id=" + detailid
    }
}